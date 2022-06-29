package bookstore;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

// This class represents the shopping cart of a user in the bookstore system

public class Cart {

	private int cartID;
	private List<Book> bookList;

	public Cart() {
		bookList = new LinkedList<Book>();
	} // Cart

	public Cart(Cart cart) {
		cartID = cart.getCartID();
		bookList = new LinkedList<>(cart.getBookList());
	} // Cart

	public int getCartID() {
		return cartID;
	} // getCartID

	public void setCartID(int cartID) {
		this.cartID = cartID;
	} // setCartID

	public void addBook(Book book) {
		System.out.println(book.getQuantity());
		for (int i = 0; i < book.getQuantity(); i++) {
			bookList.add(book);
		} // for
	} // addBook

	public void removeBook(Book book) {
		bookList.remove(book);
	} // removeBook

	// Function: removes book by its index in the string
	public void removeBook(int bookItemIndex) {
		bookList.remove(bookItemIndex);
	} // removeBook

	public int getQuantity() {
		return bookList.size();
	} // getQuantity

	public double getTotalPrice() {
		double totalPrice = bookList.stream()
		   .map(Book::getPrice)
		   .reduce(0.0, (a, b) -> {
			   return a + b;
			});
		return totalPrice;
	} // getTotalPrice

	public int getCartSize() {
		return bookList.size();
	} // getCartSize

	public void emptyCart() {
		bookList = new LinkedList<Book>();
	} // emptyCart

	// Function: Display the title of all the ordered items
	public String displayOrderItems() {
		String string =  bookList.stream()
				.map(Book::getTitle)
				.reduce("", (a, b) -> {
					return a + "; " + b;
				});
		// Remove the initial comma and whitespace
		return string.substring(2);
	} // displayOrderItems

	public List<Book> getBookList() {
		return bookList;
	} // getBookList

	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + " totalPrice: $" + getTotalPrice() + ", bookList=" + bookList + "]";
	}


} // Cart
