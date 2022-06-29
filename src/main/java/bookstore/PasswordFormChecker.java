package bookstore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* Used in a Thymeleaf template to store the password fields of a reset password
 * form and to valildate credentials.
*/
public class PasswordFormChecker {
	
	String rawPassword; // Used to check if it matches the current password

	String newPassword; // The new password that the user wants
	String reTypedPassword; 
	
	public PasswordFormChecker() {

	} // PasswordChecker
	
	public String getRawPassword() {
		return rawPassword;
	} // getRawPassword

	public String getNewPassword() {
		return newPassword;
	} // getNewPassword

	public String getReTypedPassword() {
		return reTypedPassword;
	} // getReTypedPassword
	
	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setReTypedPassword(String reTypedPassword) {
		this.reTypedPassword = reTypedPassword;
	}

	
	// Precondition: Parameters are not null
	public boolean passedValidationTest(String encodedPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
				
		if (encoder.matches(rawPassword, encodedPassword) && newPassword.equals(reTypedPassword)) { 
			return true;
		} else {
	     	return false;
		} // if
	} // passedValidationTest
	
	// Precondition: Parameters are not null
	public boolean passedValidationTest() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (newPassword.equals(reTypedPassword)) { 
			return true;
		} else {
	     	return false;
		} // if
	} // passedValidationTest

	@Override
	public String toString() {
		return "PasswordChecker [rawPassword=" + rawPassword + ", newPassword=" + newPassword + ", reTypedPassword="
				+ reTypedPassword + "]";
	} // toString
	
	
} // PasswordChecker