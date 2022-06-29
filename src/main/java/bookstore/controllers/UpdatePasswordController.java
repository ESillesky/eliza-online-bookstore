package bookstore.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bookstore.Email;
import bookstore.PasswordFormChecker;
import bookstore.User;
import bookstore.UserServices;
import bookstore.db_access;
import bookstore.springSecurity.CustomUserDetails;
import net.bytebuddy.utility.RandomString;

// This controller is used for users who want to update their password
@Controller
public class UpdatePasswordController {
	
	@Autowired
	db_access dataBaseRepo = new db_access();

	@Autowired
	UserServices userServices;
	
	@Autowired
	Email email;
	
	@GetMapping("/forgetpassword")
	public String showForgetPassword() {
		return "forgetpassword";
	} // forgetpassword

    /*
	 * @param request retrieves the string entered in from the email text field
	 * in the handler responsible for mapping forgetpassword.html
	 */
	@PostMapping("/forgetpassword")
	public String processForgetPassword(HttpServletRequest request, Model model) {
		// Get the contents of the field named "email" on the forget password form
		String userEmail = request.getParameter("email");
		String token = RandomString.make(45); // generates a random token of length 45
		
		try {
			userServices.updateResetPasswordToken(userEmail, token);
		} catch (Exception e) { // user email not found 
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "forgetpassword";
		} // try
		
		// generate reset password link
		String siteURL = request.getRequestURL().toString();
		String resetPasswordLink = siteURL.replace(request.getServletPath(), "") 
				+ "/resetpasswordform?token=" + token;
		
		// send email
		String message =  "You have requested to reset your password"
	        		+ "Click the link below to change your password:\n"
	                + resetPasswordLink 
	                + "\nIgnore this email if you do rememember your password,\n "
	                + "or haven't made the request";
	                
		email.customEmailMessage(userEmail, message);
		//email.resetPasswordEmail(userEmail, resetPasswordLink);
		System.out.println(resetPasswordLink);
		String successMessage = "We have sent a reset password link to your email, " 
				+ userEmail + ". Please check.";
		model.addAttribute("successMessage", successMessage);
		return "forgetpassword";
	} // processForgetPassword
	
	
	@GetMapping("/resetpasswordform")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		// check validity of token
		User user = dataBaseRepo.getUserByResetPasswordToken(token);
		System.out.println("resetpassword controller: " + user);
		if ((user.getResetPasswordToken() == null || user.getResetPasswordToken() == "") && 
				user.getResetPasswordToken().equals(token)) {
			return "invalidtoken";
		} // user
		
		model.addAttribute("passwordChecker", new PasswordFormChecker());
		model.addAttribute("token", token);
		return "resetpasswordform";
	} // showResetPassForm
	
	// Precondition: User presses the reset password button on the reset password form
	@PostMapping("/resetpasswordform")
	public String processResetPasswordForm(HttpServletRequest request, PasswordFormChecker passwordChecker, Model model) {	
		if (passwordChecker.passedValidationTest() == false) {
			String errorMessage = "Passwords do not match";
			model.addAttribute("error", errorMessage);
			return "resetpasswordform";
		} // if
		String token = request.getParameter("token");
		System.out.println("token: " + token);
		User user = dataBaseRepo.getUserByResetPasswordToken(token);
		System.out.println("user: " + user);
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(passwordChecker.getNewPassword());	
		user.setPassword(encodedPassword);
		user.setResetPasswordToken(null);
		dataBaseRepo.editPassword(user.getId(), encodedPassword) ;
		dataBaseRepo.editPasswordToken(user.getId(), null);
		email.resetPasswordConfirmationEmail(user.getEmail());
		return "updatepasswordsuccess";
	} // processResetPasswordForm
	
	@GetMapping("/invalidtoken")
	public String showInvaildTokenPage() {
		return "invalidtoken";
	} // showInvalidToken
	
	/*
	 * Precondition: current user must be logged in
	 * @param customUserDetails the details of the current user
	 */
	@GetMapping("/updatepassword")
	public String updatePassword(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			Model model) {

		model.addAttribute("customUserDetails", customUserDetails);
		model.addAttribute("passwordChecker", new PasswordFormChecker());

		return "updatepassword";
	} // updatePassowrd

	@PostMapping("/updatepasswordsuccess")
	public String updatePasswordConfirmation(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			PasswordFormChecker passwordChecker) {

		if (passwordChecker.passedValidationTest(customUserDetails.getUser().getPassword()) == false) {
			return "invalidpassword";
		} // if

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(passwordChecker.getNewPassword());
		
		customUserDetails.getUser().setPassword(encodedPassword);
		dataBaseRepo.editPassword(customUserDetails.getUser().getId(), encodedPassword);
		
		email.resetPasswordConfirmationEmail(customUserDetails.getUser().getEmail());
		return "updatepasswordsuccess";
	} // resetPasswordConfirmation

} // UpdatePasswordController
