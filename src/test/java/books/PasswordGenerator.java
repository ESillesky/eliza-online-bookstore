package books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import bookstore.db_access;

/* Creates an encoded password. Every registered user needs to have
 * an encrypted password.
*/
public class PasswordGenerator {
	
	@Autowired
	db_access dataBaseRepo = new db_access();

	public static void main(String[] args) {		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassw = "hi";
		String encodedPassw = encoder.encode(rawPassw);
    
		System.out.println(encodedPassw);
	} // main
    
} // PasswordGenerator
