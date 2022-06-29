package bookstore.springSecurity;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bookstore.User;

/*
 * This class is used to retrieve information pertaining to the currently logged in user
 */
public class CustomUserDetails implements UserDetails {
  
	private final Set<? extends GrantedAuthority> grantedAuthorities;	  
	private final User user;
	private final String username;
	
	public CustomUserDetails(Set<? extends GrantedAuthority> grantedAuthorities,
		User user, String emailOrId) {
		
		this.grantedAuthorities = grantedAuthorities;
		this.user = user;
		this.username = emailOrId;	
	} // CustomerUserDetails
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	} // getAuthorities

	// Function: Returns the password needed to authenticate a user
	@Override
	public String getPassword() {
		return user.getPassword();
	} // getPassword

	// Function: Returns the username parameter needed to authenticate a user
	// Postcondition: User is authenticated by entering their email or account id
	@Override
	public String getUsername() {
		return username;
	} // getUsername


	// Function: Indicates if the user account is expired
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// Function: Indicates whether the user is locked or not
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// Function: Indicates whether the user's password is expired
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// Function: Indicates whether the user account is suspended or not
	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	} // user
	

} // CustomUserDetails