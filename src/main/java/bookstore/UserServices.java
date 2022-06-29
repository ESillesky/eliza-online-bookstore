package bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// This is a utility class for user objects

@Service
@Transactional
public class UserServices {
	
	@Autowired
	db_access dataBaseRepo = new db_access();
	
	/* 
	 * Function: Sends a token to a user when they forget their password 
	 * Postcondition: Throws an exception if user's email does not exist in the database.
	 *                 Otherwise, a reset password token is given to a user.
	*/
	public void updateResetPasswordToken(String email, String token) throws Exception {
		User user = dataBaseRepo.getUserByEmail(email);
		if (user.getEmail() == "" || user.getEmail() == null) {
			System.out.println("error");
			throw new Exception("The email " + email + " is not registered. No email will be sent");
		} // if
		
		System.out.println(user);
		user.setResetPasswordToken(token);
		dataBaseRepo.editPasswordToken(user.getId(), token);
	} // updateResetPasswordToken
	
	
	public void removeItemFromCart(User user, int cartItemIndex) {
		user.getCart().removeBook(cartItemIndex);
	} // removeItemfromCart

} // UserServices
