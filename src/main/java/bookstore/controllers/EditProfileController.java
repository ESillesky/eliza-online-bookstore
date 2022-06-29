package bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bookstore.Email;
import bookstore.User;
import bookstore.db_access;
import bookstore.springSecurity.CustomUserDetails;


/* This controller maps handlers to operations that only registered and unregistered users
 * can when perform when editing their account. 
 */

@Controller
public class EditProfileController {

	@Autowired
	db_access dataBaseRepo;
	
	@Autowired
	Email email;
	
	@GetMapping("/updatepayment")
	public String updatePayment(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			Model model) {
		// load a user object containing the details of the current user onto the front-end
		model.addAttribute("currentUser", new User(customUserDetails.getUser()));
		return "updatepayment";
	} // updatepayment

	// Precondition: User presses the update payment button on the payment form
	@PostMapping("/updatepayment")
	public String processUpdatePayment(@AuthenticationPrincipal CustomUserDetails customUserDetails, User user) {
		
		System.out.println("\nThe updated user info: " + user); 
		System.out.println("\nPrinciapl user before: " + customUserDetails.getUser());
		
		customUserDetails.getUser().getPayment1().copyPaymentInfo(user.getPayment1());
		
		customUserDetails.getUser().getPayment2().copyPaymentInfo(user.getPayment2());
		
		customUserDetails.getUser().getPayment3().copyPaymentInfo(user.getPayment3());
		
		
		System.out.println("\nPrinciapl user after: " + customUserDetails.getUser());
		email.updateProfileEmail(customUserDetails.getUser().getEmail());
		dataBaseRepo.editPaymentInfo(customUserDetails.getUser());
		return "updatesuccess";
	} // processUpdatePayment

	/*
	 * Precondition: current user must be logged in
	 * @param customUserDetails the details of the current user
	 */
	@GetMapping("/updatepersonal")
	public String updatePersonal(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			Model model) {

		model.addAttribute("currentUser", new User(customUserDetails.getUser()));
		return "updatepersonal";
	} // updatePersonal
	

	@GetMapping("/updateshipping")
	public String updateShipping(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			Model model) {
		model.addAttribute("currentUser", new User(customUserDetails.getUser()));
		return "updateshipping";
	} // updatshipping

	/*
	 * Precondition: current user must be logged in
	 * @param customUserDetails the details of the current user
	 */
	@PostMapping("/updatesuccess")
	public String updateSuccess(@AuthenticationPrincipal CustomUserDetails customUserDetails, User user) {
		// if an attribute is not on a form page, it becomes null, so updated attributes are checked by
		// whether or not they are null. 
		System.out.println("\nThe updated user info: " + user); 
		System.out.println("\nPrinciapl user before: " + customUserDetails.getUser());
		
		if (user == null) {
			return "updatesuccess";
		}
		
		if (user.getFirstName() != null && user.getFirstName().equals("") == false) {
			customUserDetails.getUser().setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null && user.getLastName().equals("") == false) {
			customUserDetails.getUser().setLastName(user.getLastName());

		}
		if (user.getPhoneNumber() != null && user.getPhoneNumber().equals("") == false) {
			customUserDetails.getUser().setPhoneNumber(user.getPhoneNumber());
		}

		if (user.getAddressLine1() != null && user.getAddressLine1().equals("") == false) {
			if (user.getAddressLine1().equals("") == false) {
			    customUserDetails.getUser().setAddressLine1(user.getAddressLine1());
			}
		}
		if (user.getAddressLine2() != null && user.getAddressLine2().equals("") == false) {
			if (user.getAddressLine2().equals("") == false) {
			    customUserDetails.getUser().setAddressLine2(user.getAddressLine2());
			}
		}
		if (user.getCity() != null && user.getCity().equals("") == false) {
			customUserDetails.getUser().setCity(user.getCity());
		}
		if (user.getState() != null && user.getState().equals("") == false) {
			customUserDetails.getUser().setState(user.getState());
		}
		if (user.getZipCode() != null && user.getZipCode().equals("") == false) {
			customUserDetails.getUser().setZipCode(user.getZipCode());
		}
		if (user.getPromoSignUp() != null) { // Check if a user ticked the promo sign up box
			// Toggle the user's promotion status
			customUserDetails.getUser().setPromoSignUp(user.getPromoSignUp());
		} 
		if (user.getBillingAddress1() != null && user.getBillingAddress1().equals("") == false) {
			customUserDetails.getUser().setBillingAddress1(user.getBillingAddress1());
		}
		if (user.getBillingAddress2() != null && user.getBillingAddress2().equals("") == false) {
			customUserDetails.getUser().setBillingAddress2(user.getBillingAddress2());
		}
		if (user.getBillingCity()!= null && user.getBillingCity().equals("") == false) {
			customUserDetails.getUser().setBillingCity(user.getBillingCity());
		}
		if (user.getBillingState()!= null && user.getBillingState().equals("") == false) {
			customUserDetails.getUser().setBillingState(user.getBillingState());
		}
		if (user.getBillingZipCode() != null && user.getBillingZipCode().equals("") == false) {
			customUserDetails.getUser().setBillingZipCode(user.getBillingZipCode());
		}

		System.out.println("Princiapl user after: " + customUserDetails.getUser());
		
		dataBaseRepo.editUserFirstLastName(customUserDetails.getUser().getId(),
				customUserDetails.getUser().getFirstName(),
				customUserDetails.getUser().getLastName());

		dataBaseRepo.editPhoneNumber(customUserDetails.getUser().getId(),
				customUserDetails.getUser().getPhoneNumber());

		dataBaseRepo.editAddress(customUserDetails.getUser().getId(),
				customUserDetails.getUser().getAddressLine1(),
				customUserDetails.getUser().getAddressLine2(),
				customUserDetails.getUser().getCity(),
				customUserDetails.getUser().getState(),
				customUserDetails.getUser().getZipCode());

		dataBaseRepo.editBillingAddress(customUserDetails.getUser().getId(),
				customUserDetails.getUser().getBillingAddress1(),
				customUserDetails.getUser().getBillingAddress2(),
				customUserDetails.getUser().getBillingCity(),
				customUserDetails.getUser().getBillingState(),
				customUserDetails.getUser().getBillingZipCode());

		email.updateProfileEmail(customUserDetails.getUser().getEmail());
		return "updatesuccess";
	} // updatesuccess

} // EditProfileController
