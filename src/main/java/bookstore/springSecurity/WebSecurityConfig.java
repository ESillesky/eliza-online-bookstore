package bookstore.springSecurity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import bookstore.AnonymousUser;
import bookstore.UserRole;

import org.springframework.security.web.access.AccessDeniedHandler;


import java.io.IOException;
import java.util.concurrent.TimeUnit;



/* 
 * This class is responsible for setting up spring security authentication
*  The EnableGlobalMethoSecurity tag allows for role authorization to be configured
*  outside of this class
*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
	@Autowired
	AnonymousUser anonUser;
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	} // UserDetailsService
	 
    // Function: Encodes a password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} // passwordEncoder
	
	// Function: Specifies how authentication is implemented during login
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    // Allow for the authentication service to retrieve user details
	    authProvider.setUserDetailsService(userDetailsService());
	    // Makes it so that a password encoder must be used to validate passwords
	    authProvider.setPasswordEncoder(passwordEncoder());
	    
	    return authProvider;
	} // authenticationProvider

	// Function: Determines the authorization for a user during login
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Adds authentication based on the authentication parameter passed in
		auth.authenticationProvider(authenticationProvider());
	} // configure

	
	// .antMatchers("/userbanned").hasAuthority(UserPermission.USER_BANNED.name()) // 
	// .anyRequest().hasAuthority(UserPermission.USER_UNBANNED.name())   // Any unbanned user can access all other pages

	// Function: Customizes spring security by the means of access control,
	//           custom login page, custom logout, and etc.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// The order of antMatchers matter
		http
		.csrf().disable()
		.authorizeRequests()                                  // Allows for access restriction on checkout
		    .antMatchers("/listBooks", "/editaccount",        // Require login for these links
		    		"/payment", "/orderForm", 
		    		"/updatepassword", "/updatepayment",
		             "/updatepersonal", "/updateshipping",
		             "/editaccount").authenticated()
		    .antMatchers("/payment", "/orderhistory").hasRole(UserRole.USER_ACTIVE.name())
		    .antMatchers("editaccount", "/updatepassword", "/updatepayment",
		             "/updatepersonal", "/updateshipping",
		             "/editaccount").hasAnyRole(UserRole.USER_ACTIVE.name(), UserRole.USER_INACTIVE.name())
		    .antMatchers("/register", "myaccount").hasRole("ANONYMOUS")
		    .antMatchers("/listUsers", "/managebookview",       // Assign admin priviledges
		    		"/listbooks").hasRole(UserRole.ADMIN.name())
		    .anyRequest().permitAll()                        // Any user can access all other pages (unless restricted by a global tag)
		    .and()
		    .formLogin().loginPage("/login")                 // Create custom login page called 'login'
		    .successHandler(new AuthenticationSuccessHandler() {
		    	@Override
		    	public void onAuthenticationSuccess(HttpServletRequest request,
		    			HttpServletResponse response, 
		    			Authentication authentication) 
		    					throws IOException, ServletException {
		    		// Empty the anon user's cart after the anon user's session ends
		    		anonUser.getUser().getCart().emptyCart();
		    		// Map to the intended homepage prior to login
		    		response.sendRedirect(request.getContextPath());
		    	} // onAuthenticationSuccessHandler 	
		    })
		    .failureUrl("/invaliduser").permitAll()
		    .and()
		    .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) // extend session to 21 days when checked
		    .key("somethingveryseccured")
		    .and()
		    .logout().logoutUrl("/logout3")                                        // Clear session and cookies upon logout
		    .clearAuthentication(true)
		    .invalidateHttpSession(true)
		    .deleteCookies("JESSIONID", "remember-me")
	        .logoutSuccessUrl("/login").permitAll()
	        .and()
	        .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		
	} // configure
	
} // WebSecurityConfig
