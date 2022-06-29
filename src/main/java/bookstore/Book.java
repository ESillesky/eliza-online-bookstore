package bookstore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class represents a book object in the bookstore system

@Data
@AllArgsConstructor
public class Book {

	private int id;
	private String title;
	private String author;
	private double price;
	private String genre;
	private String description;
	private int releaseYear;
	private String isbn;
	private double rating;
	private int quantity;

	public Book(int id, String title,
			String author, double price,
			String genre, String description) {
		this.id = id;
		this.title = title;
		this.author = author;
		//this.description = description;
		this.price = price;
		this.genre = genre;
		this.description = description;
		quantity = 1;
	}


	public Book(int id, String title,
			String author, double price,
			String genre, int quantity) {
		this.id = id;
		this.title = title;
		this.author = author;
		//this.description = description;
		this.price = price;
		this.genre = genre;
		this.quantity = quantity;

	}


	public Book() {
		id = -1;
		title = "";
		author = "";
		price = 0.0;
		genre = "";

	} // Book

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", genre=" + genre
				+  ", quantity=" + quantity + "]";
	}



} // Book
