package bookstore;

import org.springframework.stereotype.Service;

/* This class represents an anonymous user in the bookstore system. It is instantiated
 * in {@link HomeController} and {@link ShoppingController} during the circumstances
 * in which a user object has not been passed into Spring Security (the CustomUserDetails 
 * object is null due to the anonymous user not logging in).
 * 
 * By default, Spring Security recognizes any user that is not logged in as anonymous,
 * so the role attribute of the User object returned by this class is insignificant. 
*/

@Service
public class AnonymousUser {
	
	private User user;
	
	public AnonymousUser() {
		user = new User();
	}  // AnonymousUser
	
	public User getUser() {
		return user;
	} // getCart
	
} // AnonymousUser
