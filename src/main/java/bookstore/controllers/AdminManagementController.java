package bookstore.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bookstore.Book;
import bookstore.Email;
import bookstore.Promotion;
import bookstore.User;
import bookstore.db_access;

// This controller maps handlers to operations that only an Admin can perform

@Controller
public class AdminManagementController {
	
	@Autowired
	db_access dataBaseRepo;
	
	@Autowired
	Email email;
	
	@GetMapping("/listPromotions")
	public String showListPromotions(Model model) {
	
		List<Promotion> promotionList = new LinkedList<>(dataBaseRepo.getPromotionList());
		model.addAttribute("promotionList", promotionList);
		return "/listPromotions";
	} // showManagePromotion
	
	@GetMapping("/addpromo")
	public String addPromotion(Model model) {
		model.addAttribute("newPromotion", new Promotion("", "", 0.0, "", ""));
		return "addpromo";
	} // addPromotion
	
	// Precondition: Admin user clicks the button to add promotion
	@PostMapping("/promotionsuccess")
	public String showPromotionSuccess(Promotion promotion) {
		System.out.println("Promotion: " + promotion);
		
		try {
		dataBaseRepo.addPromoSub(promotion.getPromotionCode(), promotion.getDiscount().doubleValue(), 
				                 promotion.getStartDate(), promotion.getEndDate(), 
				                 promotion.getPromotionName());
		} catch (Exception e) {
			e.printStackTrace();
			return "promotionfailure";
		} // try
		notifyUsersoFNewPromo(promotion);
		return "promotionsuccess";
	} // showPromotionSuccess
	
	@PostMapping("/addbook")
	public String showAddBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	} // processAddBook
	
	@PostMapping("addBookSuccess")
	public String processAddBook(Book book) {
		System.out.println("added book: " + book);
		
		dataBaseRepo.addBookByAdmin(book);
		return "addBookSuccess";
	} // processAddBook
	
	// Function: Admin user wants to view the list of all books
	@GetMapping("/listBooks")
	public String listAllBooks(Model model) {
		// Obtain a list of all the users in the database
        List<Book> listBooks =  new LinkedList<>(dataBaseRepo.getBookList());

		model.addAttribute("listBooks", listBooks);
	    return "listBooks";
	} // listAllBooks
	
	// Function: Admin user wants to view the list of all registered users
	@GetMapping("/listUsers")
	public String listAllUsers(Model model) {
		List<User> userList = new LinkedList<>(dataBaseRepo.getUserList());

		model.addAttribute("userList", userList);
	    return"listUsers";
	} // viewAllUsers
	
	
	public void notifyUsersoFNewPromo(Promotion promotion) {
		List<User> userList = new LinkedList<>(dataBaseRepo.getUserList());
		List<User> signedUpPromoUsers = userList.stream()
				.filter(user -> user.getPromoSignUp() == true)
				.collect(Collectors.toList()); 
		
		String message =  "New promotion available!"
        		+ "\n" + promotion;
		
		for (int i = 0;  i < signedUpPromoUsers.size(); i++) {
		    email.customEmailMessage(signedUpPromoUsers.get(i).getEmail(), message, "New Promotion!");

			
		} // for

	
	} // notifyUsersNewPromo
	
	
	
} // AdminManagementController
