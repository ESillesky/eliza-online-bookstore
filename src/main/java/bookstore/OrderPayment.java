package bookstore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// This class represents an order payment form after a user checks out a shopping cart

public class OrderPayment {
	
	private int orderID;
	private double orderTotal;
	private String orderDate;
	private int amountPurchased;
	private String itemsPurchased;
	private int confirmationNumber;
			
	public OrderPayment(int orderID, double orderTotal) {
		this.orderID = orderID;
		this.orderTotal = orderTotal;
		setOrderDate();
	} // OrderPayment
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime unformattedCurrentTime = LocalDateTime.now();
		orderDate = dateTimeFormatter.format(unformattedCurrentTime);
	} // setCurrentTime
	
	public int getAmountPurchased() {
		return amountPurchased;
	}

	public void setAmountPurchased(int amountPurchased) {
		this.amountPurchased = amountPurchased;
	}

	public String getItemsPurchased() {
		return itemsPurchased;
	}

	public void setItemsPurchased(String itemsPurchased) {
		this.itemsPurchased = itemsPurchased;
	}
	public int getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(int confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	@Override
	public String toString() {
		return "OrderPayment orderID: " + orderID + ", orderTotal: " + orderTotal + "\norderDate: " + orderDate
				+ ", amountPurchased: " + amountPurchased + "\nitemsPurchased=" + itemsPurchased
				+ ", confirmationNumber: " + confirmationNumber ;
	} // toString


	
} // OrderPayment
