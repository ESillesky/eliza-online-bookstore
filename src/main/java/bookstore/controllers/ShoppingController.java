package bookstore.controllers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bookstore.AnonymousUser;
import bookstore.Email;
import bookstore.OrderPayment;
import bookstore.Promotion;
import bookstore.User;
import bookstore.db_access;
import bookstore.springSecurity.CustomUserDetails;

/* 
 * This controller is used to handle operations for anonymous, unregistered, and registered
*  when managing a shopping cart.
*/
@Controller
public class ShoppingController {
	

	@Autowired
	db_access dataBaseRepo;
	
	@Autowired
	AnonymousUser anonUser;
	
	@Autowired
	Email email;
	
	/* 
	 * Used to obtain attributes from the order form that will not be saved to the user's account,
	 * but will be present on the payment form (Example payment information)
	*/
	User dummyUser = new User();
	
	// The card number that is saved on the user's profile
	private Integer cardNumberChosen = 1; 
	
	/*
	 * Function: Updates the attributes of the dummy user to match that of the current user and sets the first payment card 
	 *           attribute of the dummy user as a template for a selected payment card on file.
	 */
	public void updateDummyUser(User currentUser) {
		dummyUser.copyUser(currentUser);
		// Use the first payment card attribute as the template for a selected payment card
		switch (cardNumberChosen) {
		case 1:
			dummyUser.getPayment1().copyPaymentInfo(currentUser.getPayment1());
			break;
		case 2:
			dummyUser.getPayment1().copyPaymentInfo(currentUser.getPayment2());
			break;
		case 3:
			dummyUser.getPayment1().copyPaymentInfo(currentUser.getPayment3());
			break;
		} // switch
	} // setPresetPaymentCard
	
	@GetMapping("/cart")
	public String showCart(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
		System.out.println(customUserDetails == null);
		if (customUserDetails == null) {
			model.addAttribute("currentUser", anonUser.getUser());
			return "cart";
		} // if

		updateDummyUser(customUserDetails.getUser());

		model.addAttribute("currentUser", dummyUser);
		return "cart";
	} // showCart

	
	// Precondition: User selects a card on profile in cart.html
	@PostMapping("/cart")
	public String processPaymentCardInCart(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails,
		  Model model) {
		if (customUserDetails == null) {
			model.addAttribute("currentUser", anonUser.getUser());
			return "cart";
		} // if
		
		// Get the contents of the field named "cardNum" on the form containing the different card types
		String cardNum = request.getParameter("cardNum");
		cardNumberChosen = Integer.valueOf(cardNum.substring(4));
		
		updateDummyUser(customUserDetails.getUser());
		System.out.println("card number: " + cardNumberChosen);
		model.addAttribute("currentUser", dummyUser);
		return "cart";
	} // processPaymentCardInCart
	
	// Precondition: User presses the remove button next to a cart item in checkout.hmtl
	@PostMapping("/rmCartItem")
	public String processRemoveCartItem(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
		String cartItemIndex = request.getParameter("cartItemIndex");
		if (customUserDetails == null) {
			anonUser.getUser().getCart().removeBook(Integer.parseInt(cartItemIndex));
			model.addAttribute("currentUser", anonUser.getUser());
			return "cart";
		} // if
		customUserDetails.getUser().getCart().removeBook(Integer.parseInt(cartItemIndex));
		
		updateDummyUser(customUserDetails.getUser());
		model.addAttribute("currentUser", dummyUser);
		return "cart";
	} // rmCartItem

	// Function: User wants to checkout their cart
	// Precondition: The checkout button is pressed in the user's cart
	@PostMapping("/payment")
	public String checkoutCart(HttpServletRequest request, 
			@AuthenticationPrincipal CustomUserDetails customUserDetails, User user, Model model) {
		
		// if the user tries to checkout an empty cart
		if (customUserDetails.getUser().getCart().getCartSize() < 1) {
			updateDummyUser(customUserDetails.getUser());
			String error = "Please add items to cart before checkout";
			model.addAttribute("currentUser", dummyUser);
			model.addAttribute("error", error);
			return "cart";
		} // if
		
		List<Promotion> promotionList = new LinkedList<>(dataBaseRepo.getPromotionList());
		String promoCodeEntered = request.getParameter("promo");
		System.out.println("promo entered: " + promoCodeEntered);
		Promotion promotionUsed = new Promotion();
		
		// if the user enters an invalid promo code
		if (promoCodeEntered != "") {
			for (int i = 0; i < promotionList.size(); i++) {
				if (promotionList.get(i).getPromotionCode().equals(promoCodeEntered)) {
					promotionUsed = new Promotion(promotionList.get(i));
					break;
				} // if
			} // for
			if (promotionUsed.getPromotionName() == null || promotionUsed.getPromotionName() == "") {
				updateDummyUser(customUserDetails.getUser());
				String error = "Please enter a valid promotion code";
				model.addAttribute("currentUser", dummyUser);
				model.addAttribute("error", error);
				return "cart";
			} // if
		} // if
		
		double totalPrice = customUserDetails.getUser().getCart().getTotalPrice();
		double finalPrice = totalPrice - (totalPrice * promotionUsed.getDiscount())/100;
		
		Random rand = new Random();
		int confirmationNumber = rand.nextInt(99999 - 10000);
		System.out.println(dummyUser);
		OrderPayment orderPayment = new OrderPayment(-1, finalPrice);
		// orderPaymentID is obtained here
		int orderId = dataBaseRepo.addOrder(customUserDetails.getUser(), orderPayment);
		orderPayment.setOrderID(orderId);
		orderPayment.setAmountPurchased(customUserDetails.getUser().getCart().getCartSize());
		orderPayment.setItemsPurchased(customUserDetails.getUser().getCart().displayOrderItems());
		orderPayment.setConfirmationNumber(confirmationNumber);
		customUserDetails.getUser().addToOrderHistory(orderPayment);
		customUserDetails.getUser().getCart().emptyCart();
		model.addAttribute("currentUser", dummyUser);
		model.addAttribute("orderPayment", orderPayment);
		model.addAttribute("promoUsed", promotionUsed);
		model.addAttribute("promoCodeEntered", promoCodeEntered);
		String message = "Order Success!\n " + orderPayment;
		email.customEmailMessage(customUserDetails.getUser().getEmail(), message);
		return "payment";
	} // checkoutCart


	@GetMapping("/orderForm")
	public String showOrderForm() {
		return "orderForm";
	} // showOrderForm

	@GetMapping("/orderhistory")
	public String showOrderHistory(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
		model.addAttribute("orderHistory", customUserDetails.getUser().getOrderHistory());
		return "orderhistory";
	} // showorderhistory
	


} // ShoppingController
