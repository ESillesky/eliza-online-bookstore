package bookstore.controllers;



import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bookstore.AnonymousUser;
import bookstore.Book;
import bookstore.Email;
import bookstore.User;
import bookstore.db_access;
import bookstore.springSecurity.CustomUserDetails;

/*
 * This class handles operations that appear on the home page 
 * 
 * notes:
 * Post mapping attribute can only be accessed by clicking a button or link that invokes it.
 * So, you can't type in /{url_name} in the url field.
 *
 * Preauthorize tag methods:
 * hasRole('ROLE_'), hasAnyRole('ROLE_', ...),
 * hasAuthority('permission'), hasAnyAuthority('permission', ...)
 *
 *
*/

@Controller
public class HomeController {

	@Autowired
	db_access dataBaseRepo;

	@Autowired
	Email email;

	@Autowired
	AnonymousUser anonUser;

	public void printCurrentUserDetails(Authentication authentication) {
    	System.out.println("Get authoritiy name: " + authentication.getName());
    	System.out.println("Get authorities: " + authentication.getAuthorities());
        System.out.println("Role: " + authentication.getAuthorities().iterator().next()
        		+ "\n\n");

	} // printCurrentUserDetails


	@GetMapping("/") // This maps to the  home page
	public String home(Model model) {
		List<User> userList = new LinkedList<>(dataBaseRepo.getUserList());
		List<User> signedUpPromoUsers = userList.stream()
				.filter(user -> user.getPromoSignUp() == true)
				.collect(Collectors.toList()); 
		System.out.print(signedUpPromoUsers);
		List<Book> listTrending =  new LinkedList<>(dataBaseRepo.getTrendingList());
		model.addAttribute("listTrending", listTrending);

		List<Book> listFeatured = new LinkedList<>(dataBaseRepo.getFeaturedList());
		model.addAttribute("listFeatured", listFeatured);
		return "main"; // the html file
		//return "adminView";
	} // home

	/*
	 *
	 * Precondition: user registers for an account
	 * The user parameter contains the attributes of the registered user
	 * from  the register.html file.
	 */
	@PostMapping("/accountcreated")
	public String accountCreatedConfirmation(HttpServletRequest request, User user) {
		// Encrypt password with Bcrypt encoder
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		System.out.println("The User: " + user);

		try {
		    dataBaseRepo.addUser(user, 0);
		} catch (Exception e) { // duplicate user email
			e.printStackTrace();
			return "duplicateuser";
		} // try

		System.out.println("HOMECONTROLLER GET USERID " + user.getId());

		dataBaseRepo.addAddressInfo(user.getId(), user.getAddressLine1(), user.getAddressLine2(), user.getCity(),
				user.getState(), user.getZipCode());

		dataBaseRepo.addBillingAddressInfo(user.getId(), user.getBillingAddress1(), user.getBillingAddress2(), user.getBillingCity(),
		user.getBillingState(), user.getBillingZipCode());

		// Payment info

		try {
		    dataBaseRepo.addPaymentInfo(user.getId(), user.getPayment1().getNameOnCard(), user.getPayment1().getCardNumberStr(),
				user.getPayment1().getCardType(), user.getPayment1().getExpirationMonth(), user.getPayment1().getExpirationYear());
		} catch (NumberFormatException n) {
			// n.printStackTrace();
			dataBaseRepo.addPaymentInfo(user.getId(), user.getPayment1().getNameOnCard(), "",
				user.getPayment1().getCardType(), user.getPayment1().getExpirationMonth(),
				user.getPayment1().getExpirationMonth());
		} // try

		// generate reset password link
		String siteURL = request.getRequestURL().toString();
		String registerLink = siteURL.replace(request.getServletPath(), "")
				+ "/upgradeUser?email=" + user.getEmail();
		String message = "Acitvate your account."
				+ "\nClick the link below to activate your account\n"
				+ registerLink;

		email.customEmailMessage(user.getEmail(), message);
		return "accountcreated";
	} // accountConfirmationScreen

	@GetMapping("/upgradeUser")
	public String upgradeUserForm(@Param(value = "email") String email, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		dataBaseRepo.registerUser(email);
		return "upgradeUser";
	} // upgradeUserForm

	@GetMapping("/accessdenied")
	public String getAccessDenied() {
		return "accessdenied";
	} // getAdminView


	@GetMapping("/bookdescription/{bookID}")
	public String getBookDescription(@PathVariable(value="bookID") int insert, Model model) {
		Book book =  new Book();
		book = dataBaseRepo.getBookById(insert);
		model.addAttribute("book", book);

		return "bookdescription";
	} // getbookdescription

	@PostMapping("bookdescription")
	public String processBookDescription(@AuthenticationPrincipal CustomUserDetails customUserDetails, Book book, Model model) {
		if (customUserDetails == null) {
			System.out.println("Is customer null: " + customUserDetails == null);
			anonUser.getUser().getCart().addBook(book);
			model.addAttribute("currentUser", anonUser.getUser());
			model.addAttribute("currentUserPaymentInfo", anonUser.getUser().getPayment1());
			return "cart";
		} // if

		customUserDetails.getUser().getCart().addBook(book);
		model.addAttribute("currentUser", customUserDetails.getUser());
		// Load the user's first payment card as the default card on the form
		model.addAttribute("currentUserPaymentInfo", customUserDetails.getUser().getPayment1());
		return "cart";
	} // processBookDescription

	@GetMapping("/booksbyauthor")
	public String showBooksByAuthor(Model model) {
		List<Book> author = new LinkedList<>(dataBaseRepo.getBookListByAuthor());
		model.addAttribute("author", author);
		return "booksbyauthor";
	} // getbooksbyauthor

	@GetMapping("/booksbyauthorz")
	public String showBooksByAuthorZtoA(Model model) {
		List<Book> authorz = new LinkedList<>(dataBaseRepo.getBookListByAuthorZ());
		model.addAttribute("authorz", authorz);
		return "booksbyauthorz";
	} // getbooksbyauthor

	@GetMapping("/booksbygenre")
	public String showBooksByGenre(Model model) {
		List<Book> mystery = new LinkedList<>(dataBaseRepo.getMystery());
		model.addAttribute("mystery", mystery);

		List<Book> other = new LinkedList<>(dataBaseRepo.getOther());
		model.addAttribute("other", other);

		List<Book> fantasy = new LinkedList<>(dataBaseRepo.getFantasy());
		model.addAttribute("fantasy", fantasy);

		List<Book> thriller = new LinkedList<>(dataBaseRepo.getThriller());
		model.addAttribute("thriller", thriller);

		List<Book> horror = new LinkedList<>(dataBaseRepo.getHorror());
		model.addAttribute("horror", horror);

		List<Book> romance = new LinkedList<>(dataBaseRepo.getRomance());
		model.addAttribute("romance", romance);

		List<Book> history = new LinkedList<>(dataBaseRepo.getHistory());
		model.addAttribute("history", history);

		List<Book> selfhelp = new LinkedList<>(dataBaseRepo.getSelfHelp());
		model.addAttribute("selfhelp", selfhelp);

		List<Book> fiction = new LinkedList<>(dataBaseRepo.getFiction());
		model.addAttribute("fiction", fiction);

		List<Book> biography = new LinkedList<>(dataBaseRepo.getBiography());
		model.addAttribute("biography", biography);

		List<Book> comic = new LinkedList<>(dataBaseRepo.getComic());
		model.addAttribute("comic", comic);

		return "booksbygenre";

	} // getbooksbygenre

	@GetMapping("/booksbyisbn")
	public String showBooksByISBN(Model model) {
		List<Book> isbn = new LinkedList<>(dataBaseRepo.getBookListByISBN());
		model.addAttribute("isbn", isbn);
		return "booksbyisbn";
	} // getbooksbyisbn

	@GetMapping("/booksbyisbna")
	public String showBooksByISBNA() {
		return "booksbyisbna";
	} // getbooksbyisbna

	@GetMapping("/booksbyisbnd")
	public String showBooksByISBND() {
		return "booksbyisbnd";
	} // getbooksbyisbnd


	@GetMapping("/booksbytitlea")
	public String showBooksByTitle(Model model) {
		List<Book> AtoZ =  new LinkedList<>(dataBaseRepo.getBookList());
		model.addAttribute("AtoZ", AtoZ);
		return "booksbytitlea";
	} // getbooksbytitle

	@GetMapping("/booksbytitlez")
	public String showBooksByTitleZ(Model model) {
		List<Book> ZtoA =  new LinkedList<>(dataBaseRepo.getBookListZ());
		model.addAttribute("ZtoA", ZtoA);
		return "booksbytitlez";
	} // getbooksbytitle

	@GetMapping("/editaccount")
	public String editaccount() {
		return "editaccount";
	} // editAccount

	@GetMapping("/freqquestion")
	public String showFreqquestion() {
		return "freqquestion";
	} // showFreqquestion

	@GetMapping("/invalidsearch")
	public String showInvalidSearch() {
		return "invalidsearch";
	} // showInvalidUserScreen

	@GetMapping("/invaliduser")
	public String showInvalidUserSreen() {
		return "invaliduser";
	} // showInvalidUserScreen

	@GetMapping("/invalidpassword")
	public String showInvalidPasswordSreen() {
		return "invalidpassword";
	} // showInvalidUserScreen


    @GetMapping("/login")
	public String showLoginView() {
    	// See if current user is authenticated
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	printCurrentUserDetails(authentication);
    	// If not authenticated, go to the home page
    	if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    		return "login";
    	} // if
		return "main";
	} // showLoginView

    @GetMapping("/logout")
 	public String showLogoutView() {
    	// See if current user is authenticated
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	printCurrentUserDetails(authentication);
    	// If not authenticated, go to the home page
    	if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    		return "main";
    	} // if
 		return "logout3";
 	} // showLogoutView

	// load the current user onto the front-end
	@GetMapping("/myaccount")
	public String showMyAccount() {
		return "myaccount";
	} // showMyAccount

	@GetMapping("/main")
	public String showMainMenu(Model model) {
		List<Book> listTrending =  new LinkedList<>(dataBaseRepo.getTrendingList());
		model.addAttribute("listTrending", listTrending);

		List<Book> listFeatured = new LinkedList<>(dataBaseRepo.getFeaturedList());
		model.addAttribute("listFeatured", listFeatured);
		return "main";
	} // showMainMenu

	/*
	 * Function: User wants to search for a book via the search bar
	 * Precondition: User submits text in the search bar in main.html
	*/
	@PostMapping("/main")
	public String processSearchBar(HttpServletRequest request, Model model) {
		String textField = request.getParameter("booksearch");
		int bookId = dataBaseRepo.searchedBooks(textField);

		Book book =  new Book();
		book = dataBaseRepo.getBookById(bookId);
		model.addAttribute("book", book);

		if (bookId == -1) {
			return "invalidsearch";
		}

		return "bookdescription";

	} // processSearchBar

	/*
	 * Function: Creates a new user object specified by a sign up form
	 * Precondition: Current user is anonymous
	 * Postcondition: The user object is added to the database by the
	 *                controller responsible for accountcreated.html
	 */
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		// Add user to the front-end
		model.addAttribute("user", new User());
		return "register";
	} // showregister

	@GetMapping("/searchresults")
	public String showSearchResults() {
		return "searchresults";
	} // getsearchresults

} // HomeController
