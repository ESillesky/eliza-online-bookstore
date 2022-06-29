package bookstore;

import java.util.LinkedList;
import java.util.List;

public class User {

	private int id;
	private String firstName;
    private String lastName;
    private String fullName;
    private String phoneNumber;
    private String password;
    private String email;
    private int userType; // Determines if UserRole.USER or UserRole.ADMIN
    private int userstatus; 
    private UserRole userRole;
 
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
	private String district;
    private String zipCode;
    
    private String billingAddress1;
	private String billingAddress2;
	private String billingCity;
	private String billingState;
	private String billingZipCode;
    
	private Boolean promoSignUp;
		
	private PaymentInfo payment1; 
	private PaymentInfo payment2; 
	private PaymentInfo payment3;
	
	private Cart cart;
	private List<OrderPayment> orderHistory;

    // Only used when requested directly by a query
	private String resetPasswordToken;
	
	public User() {
		setUserType(0);
    	firstName = "";
        lastName = "";
        phoneNumber= "";
        password= "";
        promoSignUp = false;
        userType= 0;
        email= "";
        addressLine1= "";
        addressLine2= "";
        city= "";
        state= "";
        zipCode= "";
        billingAddress1= "";
        billingAddress2= "";
        billingCity= "";
        billingState= "";
        billingZipCode= "";  
    	promoSignUp = null;
    	payment1 = new PaymentInfo();
    	payment2 = new PaymentInfo();
    	payment3 = new PaymentInfo();
    	cart = new Cart();
    	orderHistory = new LinkedList<>();
    	//cart.addBook(new Book(1, "hello", "java", 13.3,"adv", "d"));
	} // User
	
	// Function: Copy constructor
	public User(User user) {
		copyUser(user);
	} // User
	
    public int getId() {		
    	return id;
    }

    public void setId(int id) {
    	this.id = id;
    }


    public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
        fullName = firstName + " " + lastName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
        fullName = firstName + " " + lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


    public int getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(int userstatus) {
		this.userstatus = userstatus;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
		setUserRole(userType);
	} // setUserType

	private void setUserRole(int userType) {
		switch(userType) {
		case 0:
			this.userRole = UserRole.USER_INACTIVE;
			break;
		case 1:
			this.userRole = UserRole.USER_ACTIVE;
			break;
		case 2:
			this.userRole = UserRole.ADMIN;
			break;
		default:
			this.userRole = UserRole.USER_BANNED;
			break;
		} // switch
	} // setUserRole

	public UserRole getUserRole() {
		return userRole;
	} // getUserRole
	
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
		
	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.state = district;
	}

	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	

    public String getBillingAddress1() {
		return billingAddress1;
	}

	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	public String getBillingAddress2() {
		return billingAddress2;
	}

	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingZipCode() {
		return billingZipCode;
	}

	public void setBillingZipCode(String billingZipCode) {
		this.billingZipCode = billingZipCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setPromoSignUp(Boolean promo) {
		promoSignUp = promo;
	} // setPromoSignUp
	
	
	public Boolean getPromoSignUp() {
		return promoSignUp;
	} // getPromoSignUp

    public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	
	public void copyUser(User user) {
		setUserType(user.getUserType());
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		this.id = user.getId();
   	 	this.email = user.getEmail();
   	 	this.phoneNumber = user.getPhoneNumber();
		this.password = user.getPassword();
		this.promoSignUp = user.getPromoSignUp();
		this.addressLine1 = user.getAddressLine1();
		this.addressLine2 = user.getAddressLine2();
		this.city = user.getCity();
		this.state = user.getState();
		this.zipCode = user.getZipCode();
		this.billingAddress1 = user.getBillingAddress1();
		this.billingAddress2 = user.getBillingAddress2();
		this.billingCity = user.getBillingCity();
		this.billingState = user.getBillingState();
		this.billingZipCode = user.getBillingZipCode();
    	payment1 = new PaymentInfo(user.getPayment1());
    	payment2 = new PaymentInfo(user.getPayment2());
    	payment3 = new PaymentInfo(user.getPayment3());
    	cart = new Cart(user.getCart());
    	orderHistory = new LinkedList<>();
	} // copyUser
	
	public PaymentInfo getPayment1() {
		return payment1;
	}

	public PaymentInfo getPayment2() {
		return payment2;
	}

	public PaymentInfo getPayment3() {
		return payment3;
	}
	
	public Cart getCart() {
		return cart;
	} // getCart
	
	public List<OrderPayment> getOrderHistory() {
		return orderHistory;
	} 
	
	public void addToOrderHistory(OrderPayment orderPayment) {
		orderHistory.add(orderPayment);
	} // addToOrderHistory
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", fullName=" + fullName
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", email=" + email + ", userType="
				+ userType + ", userstatus=" + userstatus + ", userRole=" + userRole + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state + ", district=" + district
				+ ", zipCode=" + zipCode + ", billingAddress1=" + billingAddress1 + ", billingAddress2="
				+ billingAddress2 + ", billingCity=" + billingCity + ", billingState=" + billingState
				+ ", billingZipCode=" + billingZipCode + ", promoSignUp=" + promoSignUp + ", \n\npayment1=" + payment1
				+ ", \npayment2=" + payment2 + ", \npayment3=" + payment3 + "\ncart" + cart + " ]]\n";
	} // toString

	
	

} // User
