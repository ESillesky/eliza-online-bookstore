package bookstore.springSecurity;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bookstore.User;
import bookstore.db_access;

/* 
 * This class is used for locating a specific user to be authenticated during spring security login.
* The user object is found based on the user name parameter entered during login. 
*/

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	db_access dataBaseRepo = new db_access();
	
    // Function: Locates a user based on the contents entered in the username field of login
    // Postcondition: If the user object exist, the contents entered in the password field of login is 
    //                checked in CustomUserDetails. Otherwise, an expcetion is thrown. 
    // The param username is the value that is entered into the username field
    @Override
	public UserDetails loadUserByUsername(String emailOrId) throws UsernameNotFoundException {
	
//		User user = new User("alex", "jones", "boy", "a@gmail.com", 0, 1);
//		user.setPassword("$2a$10$721IV4PW.BDVMXA5tOOnkuLtgjhn1rQBZQ12TIKqeSsuuTUboz6Nu"); // password is hi
		
		
		User user = null;
	
		// Find user by email
		if (emailOrId.indexOf("@") != -1 ) {
			System.out.println("Repo: " + dataBaseRepo.getUserByEmail(emailOrId));
			user = dataBaseRepo.getUserByEmail(emailOrId);
			System.out.print("is null: " + user == null);
			if (user == null) {
			    throw new UsernameNotFoundException("User not found");
			} // if    
		} else { // Find user by ID
			try {
				System.out.println("Repo: " + dataBaseRepo.getUserByID(Integer.parseInt(emailOrId)));
				user = dataBaseRepo.getUserByID(Integer.parseInt(emailOrId));
				// Test if the user exist
			    if (user == null) {
			    	throw new UsernameNotFoundException("User not found");
			    } // if	    
			} // if
			catch (NumberFormatException e) {
				System.out.print(e);
				throw new UsernameNotFoundException("User not found");
			} // try
		} // if

		System.out.println("The 'logged' in user is: "  + user);
				
		return new CustomUserDetails(user.getUserRole().getGrantedAuthorities(),
				user, emailOrId);
	} // UserDetails
} // CustomUserDeatilService
