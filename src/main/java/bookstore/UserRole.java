package bookstore;


import com.google.common.collect.Sets;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;

/*
 *  This class defines the various roles and permissions of a user that has logged in
 *  via Spring security. ROLE_ANONYMOUS is the default role given to a user who has not
 *  logged in via Spring Security.
 */
public enum UserRole {  
	// Hash set contains the permissions that a role has
    USER_BANNED(Sets.newHashSet(UserPermission.USER_BANNED)),      // ROLE_USER_BANNED  
    USER_INACTIVE(Sets.newHashSet(UserPermission.USER_UNBANNED)),  // ROLE_USER_INACTIVE
	USER_ACTIVE(Sets.newHashSet(UserPermission.USER_UNBANNED)),    // ROLE_USER        
    ADMIN(Sets.newHashSet(UserPermission.USER_UNBANNED));          // ROLE_ADMIN
	                                                               
    
    private final Set<UserPermission> userPermissions;
	
	UserRole(Set<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	} 
	
	public Set<UserPermission> getPermissions() {
		return userPermissions;
	} // getPermissions
	
	// Function: Returns a collection of all authorities/permissions of a role
 	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		// Turning all user permissions into a collection (permissions = authorities)
		Set<SimpleGrantedAuthority> permissions = 
				getPermissions().stream()
				.map(userPermissions -> new SimpleGrantedAuthority(userPermissions.getPermission()))
				.collect(Collectors.toSet());
		// Defines how to specify a role
		permissions.add( new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	} // getGrantedAuthroities
} // UserRole

