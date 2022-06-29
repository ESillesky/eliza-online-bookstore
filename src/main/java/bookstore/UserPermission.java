package bookstore;

// This class defines the permissions that a user can have

public enum UserPermission {
	USER_BANNED("user:banned"),
	USER_UNBANNED("user:unbanned");
	
	private final String permission;
	
	UserPermission(String permission) {
		this.permission = permission;
	} 
	
	public String getPermission() {
		return permission;
	}
} // UserPermission
