package bookstore;
import java.sql.*;

import java.util.Date;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

/*
 * This class is used to connect to MYSQL services 
 */

@Service
public class db_access {

int addressIDTEMP = 0;


	public void hi() {
		System.out.println("hi");
	}

    //get books from database

    public String[] getBooks(){

        //System.out.println(getSearchedBook("Gone Girl"));

        //addPromoSub("834533", 10, "2012-01-08 00:00:00", "2012-01-08 00:00:00" , "Valentines Day");
        //addAddressInfo(95879, "testing", " ", "test", "TX", "33214");


        String[] books = new String[100];

        try {
            //1. get connection

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement

            Statement myStmt = myConn.createStatement();
            //3. execute sql query

            ResultSet myRs = myStmt.executeQuery("select * from books");

            //4. Process the result set

            int i = 0;
            while (myRs.next()) {
               books[i] = myRs.getString("title") + myRs.getString("author");
               i++;

            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return books;
    }

    public List<User> getUserList() {
    	List<User> listUser = new LinkedList<>();

    	try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");
            Statement myStmt = myConn.createStatement();
            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("select * from customers");
            //4. Retrieve the specified column information for each book
            for (int i = 0; myRs.next(); i++) {
            	User user = new User();
            	user.setFirstName(myRs.getString("firstName"));
            	user.setLastName(myRs.getString("lastName"));
            	user.setEmail(myRs.getString("email"));
            	user.setUserType(myRs.getInt("userType"));
            	user.setPromoSignUp(myRs.getBoolean("promoSubscription"));

               listUser.add(user);
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return listUser;

    }

    public List<Promotion> getPromotionList() {
    	List<Promotion> promotionList = new LinkedList<>();

    	try {
            //1. get connection

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement

            Statement myStmt = myConn.createStatement();
            //3. execute sql query

            ResultSet myRs = myStmt.executeQuery("select * from promos");

            //4. Retrieve the specified column information for each book

            for (int i = 0; myRs.next(); i++) {
            	String promoCode = myRs.getString("promoCode");
            	double discountAmount = myRs.getDouble("discountAmount");
            	String startDate = myRs.getString("startDate");
            	String endDate = myRs.getString("endDate");
            	String promoName = myRs.getString("promoName");

            	promotionList.add(new Promotion(promoName, promoCode, discountAmount, startDate, endDate));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try

    	return promotionList;
    } // getPromotionList


    /*parameter bookID value passed in to return a book for description page*/
    public Book getBookById(int value) {
        Book book = new Book();
    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");
            //2. create statement
            Statement myStmt = myConn.createStatement();
            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT Title, books.bookID, genreName, descrip, bookPrice, author FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND books.bookID = " + value + "");

            //4. Retrieve the specified column information for each book
            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");
               book = new Book(id, title, author, price, genre, description);
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try

    	return book;
    }

   /*Get list of all books*/
    public List<Book> getBookList() {
    	List<Book> listBook = new LinkedList<>();
    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT Title, descrip, books.bookID, genreName, bookPrice, author FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID ORDER BY books.title ASC");

            //4. Retrieve the specified column information for each book
            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               listBook.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return listBook;
    } // getBookList



    /*Get list of all books*/
     public List<Book> getBookListByISBN() {
     	List<Book> listBook = new LinkedList<>();
     	try {
             //1. get connection
             Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

             //2. create statement
             Statement myStmt = myConn.createStatement();

             //3. execute sql query
             ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT Title, descrip, isbn, books.bookID, genreName, bookPrice, author FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID ORDER BY books.isbn");

             //4. Retrieve the specified column information for each book
             for (int i = 0; myRs.next(); i++) {
             	int id =  Integer.parseInt(myRs.getString("bookID"));
             	String title = myRs.getString("title");
             	String author = myRs.getString("author");
             	double price =  Double.parseDouble(myRs.getString("bookPrice"));
             	String genre = myRs.getString("genreName");
             	String description = myRs.getString("descrip");

                listBook.add(new Book(id, title, author, price, genre, description));
             } // for
         } catch (Exception exc) {
             exc.printStackTrace();
         }  // try
     	return listBook;
     } // getBookList


    /*Get list of all books*/
     public List<Book> getBookListByAuthor() {
     	List<Book> listBook = new LinkedList<>();
     	try {
             //1. get connection
             Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

             //2. create statement
             Statement myStmt = myConn.createStatement();

             //3. execute sql query
             ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT Title, descrip, books.bookID, genreName, bookPrice, author FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID ORDER BY books.author ASC");

             //4. Retrieve the specified column information for each book
             for (int i = 0; myRs.next(); i++) {
             	int id =  Integer.parseInt(myRs.getString("bookID"));
             	String title = myRs.getString("title");
             	String author = myRs.getString("author");
             	double price =  Double.parseDouble(myRs.getString("bookPrice"));
             	String genre = myRs.getString("genreName");
             	String description = myRs.getString("descrip");

                listBook.add(new Book(id, title, author, price, genre, description));
             } // for
         } catch (Exception exc) {
             exc.printStackTrace();
         }  // try
     	return listBook;
     } // getBookList



     /*Get list of all books*/
      public List<Book> getBookListByAuthorZ() {
      	List<Book> listBook = new LinkedList<>();
      	try {
              //1. get connection
              Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

              //2. create statement
              Statement myStmt = myConn.createStatement();

              //3. execute sql query
              ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT Title, descrip, books.bookID, genreName, bookPrice, author FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID ORDER BY books.author DESC");

              //4. Retrieve the specified column information for each book
              for (int i = 0; myRs.next(); i++) {
              	int id =  Integer.parseInt(myRs.getString("bookID"));
              	String title = myRs.getString("title");
              	String author = myRs.getString("author");
              	double price =  Double.parseDouble(myRs.getString("bookPrice"));
              	String genre = myRs.getString("genreName");
              	String description = myRs.getString("descrip");

                 listBook.add(new Book(id, title, author, price, genre, description));
              } // for
          } catch (Exception exc) {
              exc.printStackTrace();
          }  // try
      	return listBook;
      } // getBookList


    /*Get list of all books z to a*/
     public List<Book> getBookListZ() {
     	List<Book> listBook = new LinkedList<>();
     	try {
             //1. get connection
             Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

             //2. create statement
             Statement myStmt = myConn.createStatement();

             //3. execute sql query
             ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT Title, descrip, books.bookID, genreName, bookPrice, author FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID ORDER BY books.title DESC");

             //4. Retrieve the specified column information for each book
             for (int i = 0; myRs.next(); i++) {
             	int id =  Integer.parseInt(myRs.getString("bookID"));
             	String title = myRs.getString("title");
             	String author = myRs.getString("author");
             	double price =  Double.parseDouble(myRs.getString("bookPrice"));
             	String genre = myRs.getString("genreName");
             	String description = myRs.getString("descrip");

                listBook.add(new Book(id, title, author, price, genre, description));
             } // for
         } catch (Exception exc) {
             exc.printStackTrace();
         }  // try
     	return listBook;
     } // getBookList

    /*Returns list of Book objects for featured list on main*/
    public List<Book> getFeaturedList() {
    	List<Book> featured = new LinkedList<>();

    	try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            Statement myStmt = myConn.createStatement();

            ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT Title, descrip, books.bookID, genreName, bookPrice, author FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID WHERE books.bookID > 35 AND books.bookID < 42 LIMIT 5");

            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               featured.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return featured;
    } // getBookList



    /*Returns list of Book objects for top trending on main*/
    public List<Book> getTrendingList() {
    	List<Book> trending = new LinkedList<>();

    	try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            Statement myStmt = myConn.createStatement();

            ResultSet myRs = myStmt.executeQuery("SELECT DISTINCT Title, descrip, books.bookID, genreName, bookPrice, author FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID WHERE books.bookID > 30 AND books.bookID < 37 LIMIT 5;");

            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               trending.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return trending;
    } // getBookList



    /*Returns list of Book objects with action genre, genreID = 1*/
    public List<Book> getThriller() {
    	List<Book> thriller = new LinkedList<>();

    	try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            Statement myStmt = myConn.createStatement();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 4");

            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               thriller.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return thriller;
    } // getBookList

    /*Returns list of Book objects with action genre, genreID = 1*/
    public List<Book> getHorror() {
    	List<Book> horror = new LinkedList<>();

    	try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            Statement myStmt = myConn.createStatement();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 7");

            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               horror.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return horror;
    } // getBookList

    /*Returns list of Book objects with action genre, genreID = 1*/
    public List<Book> getRomance() {
    	List<Book> romance = new LinkedList<>();

    	try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            Statement myStmt = myConn.createStatement();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 5");

            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               romance.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return romance;
    } // getBookList

    //mystery genre
    public List<Book> getMystery() {
    	List<Book> mystery = new LinkedList<>();

    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 2");

            //4. Retrieve the specified column information for each book

            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               mystery.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return mystery;
    } // getBookList


    //history genre
    public List<Book> getHistory() {
    	List<Book> history = new LinkedList<>();

    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 8");

            //4. Retrieve the specified column information for each book

            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               history.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return history;
    } // getBookList

    //other gener, mostly cookbooks
    public List<Book> getOther() {
    	List<Book> other= new LinkedList<>();

    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 6");

            //4. Retrieve the specified column information for each book

            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");

               other.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return other;
    } // getBookList



    public List<Book> getFantasy() {
    	List<Book> other= new LinkedList<>();

    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 3");

            //4. Retrieve the specified column information for each book
            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");
               other.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return other;
    } // getBookList



    public List<Book> getSelfHelp() {
    	List<Book> selfhelp= new LinkedList<>();

    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 12");

            //4. Retrieve the specified column information for each book
            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");
                selfhelp.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return selfhelp;
    } // getBookList



    public List<Book> getFiction() {
    	List<Book> fiction= new LinkedList<>();

    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 11");

            //4. Retrieve the specified column information for each book
            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");
               fiction.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return fiction;
    } // getBookList



    public List<Book> getBiography() {
    	List<Book> biography = new LinkedList<>();

    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 13");

            //4. Retrieve the specified column information for each book
            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");
                biography.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return biography;
    } // getBookList


    public List<Book> getComic() {
    	List<Book> comics= new LinkedList<>();

    	try {
            //1. get connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            //3. execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books INNER JOIN bookGenre ON books.bookID = bookGenre.bookID INNER JOIN genre ON bookGenre.genreID = genre.genreID AND genre.genreID = 14");

            //4. Retrieve the specified column information for each book
            for (int i = 0; myRs.next(); i++) {
            	int id =  Integer.parseInt(myRs.getString("bookID"));
            	String title = myRs.getString("title");
            	String author = myRs.getString("author");
            	double price =  Double.parseDouble(myRs.getString("bookPrice"));
            	String genre = myRs.getString("genreName");
            	String description = myRs.getString("descrip");
               comics.add(new Book(id, title, author, price, genre, description));
            } // for
        } catch (Exception exc) {
            exc.printStackTrace();
        }  // try
    	return comics;
    } // getBookList


    /* Adds new user to database */
    public void addUser(User user, int userType) throws Exception  {

    	String firstName = user.getFirstName();
    	String lastName = user.getLastName();
        String phoneNumber = user.getPhoneNumber();
    	String password = user.getPassword();
    	String email = user.getEmail();
        Boolean promo = user.getPromoSignUp();
        String resetPasswordToken = user.getResetPasswordToken();

        //randomly generate a customerID
        int randomID = (int) ((Math.random() * (100000 - 0)) + 0);
    	user.setId(randomID);

        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

        //2. create statement

        Statement myStmt = myConn.createStatement();
        int myRs = myStmt.executeUpdate("INSERT INTO CUSTOMERS VALUES (" + randomID + " , '" + password + "' , '" + firstName + "' , '" + lastName + "' , '" + email + "', '" + phoneNumber + "' , " + userType + " , " + promo + " , '" + resetPasswordToken + "' )");

    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }



    /* Adds payment to database */

    public void addPaymentInfo(int customerID, String nameOnCard, String cardNumber, int cardType, int month, int year) {


        try {


            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

                //2. create statement


            Statement myStmt = myConn.createStatement();

            int myRs = myStmt.executeUpdate("INSERT INTO payments (nameOnCard, cardNumber, cardType, month, year, customerID)"
            + "VALUES ('" + nameOnCard + "' , '" + cardNumber + "' , " + cardType + " , " + month + " , " + year + " , " + customerID + ")");

                //"INSERT INTO PAYMENTS VALUES (" + customerID + " , '" + nameOnCard + "' , " + cardNumber + " , " + cardType + " , '" + expirationDate + "')");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }


    /* Adds address to database */
    public void addAddressInfo(int customerID, String addressLine1, String addressLine2, String city, String district, String zipCode) {
        try {

            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

                //2. create statement

            Statement myStmt = myConn.createStatement();
            int myRs = myStmt.executeUpdate("INSERT INTO address (customerID, line1, line2, city, district, zipCode)" +
             "VALUES (" + customerID + " , '" + addressLine1 + "' , '" + addressLine2 + "' , '" + city + "' , '" + district + "' , '" + zipCode + "')");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /* Adds promo to database */
    public void addPromoSub(String promoCode, double discountAmount, String startDate, String endDate, String promoName) throws Exception {

        //generate random 7 digit user ID

        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

        //2. create statement

        Statement myStmt = myConn.createStatement();
        int myRs = myStmt.executeUpdate("INSERT INTO promos (promoCode, discountAmount, startDate, endDate, promoName) " +
             "VALUES ('" + promoCode + "' , " + discountAmount + " , '" + startDate + "' , '" + endDate + "' , '" + promoName + "')");


    }

    public void addBookByAdmin(Book book) {

    	String title = book.getTitle();
    	String author = book.getAuthor();
    	String description = book.getDescription();
    	int year = book.getReleaseYear();
    	String isbn = book.getIsbn();
    	double bookPrice = book.getPrice();
    	double rating = book.getRating();

        try {

            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

                //2. create statement

            Statement myStmt = myConn.createStatement();

            int myRs = myStmt.executeUpdate("INSERT INTO books (title, author, descrip, releaseYear, isbn, bookPrice, rating)" +
             "VALUES ( '" + title + "' , '" + author + "' , '" + description + "' , " + year + " , '" + isbn + "' , " + bookPrice + " , " + rating + " )");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }


    }


    /* Adds address to database */
    public void addBillingAddressInfo(int customerID, String billingAddress1, String billingAddress2, String billingCity, String billingState, String billingZipCode) {
        try {

            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

                //2. create statement

            Statement myStmt = myConn.createStatement();

            int myRs = myStmt.executeUpdate("INSERT INTO billingAddress (billingAddress1, billingAddress2, billingCity, billingState, billingZipCode, customerID)" +
             "VALUES ( '" + billingAddress1 + "' , '" + billingAddress2 + "' , '" + billingCity + "' , '" + billingState + "' , '" + billingZipCode + "' , " + customerID + " )");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }


//EDIT PROFILE - UPDATE DATABASE

public void addUserPromo(int customerID, boolean promo) {

    try {
        //generate random 7 digit user ID

        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement

        Statement myStmt = myConn.createStatement();
        int myRs = myStmt.executeUpdate("INSERT INTO customers (customerID, promoSubscription) " +
        "VALUES ( " + customerID + " , " + promo + " )");
    }
    catch (Exception exc) {
        exc.printStackTrace();
    }
}


//adds order info to database
public int addOrder(User user, OrderPayment orderPayment) {

	int customerID = user.getId();
	double total = orderPayment.getOrderTotal();
	String orderDate = orderPayment.getOrderDate();
	List<Book> books = new LinkedList<Book>(user.getCart().getBookList());

    int addressID = 0;
    int paymentID = 0;
    int orderID = 0;

    try {

        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement


        Statement myStmt = myConn.createStatement();

        ResultSet addressInfo = myStmt.executeQuery("SELECT addressID from address WHERE customerID = " + customerID);

        while (addressInfo.next()){
        addressID = addressInfo.getInt("addressID");
        }

        ResultSet paymentInfo = myStmt.executeQuery("SELECT paymentID from payments WHERE customerID = " + customerID);

        while (paymentInfo.next()) {
        paymentID = paymentInfo.getInt("paymentID");
        }


        //check address

        int myRs = myStmt.executeUpdate("INSERT INTO orders (customerID, paymentID, addressID, total, orderDate)" +
        "VALUES ( " + customerID + " , " + paymentID + " , " + addressID + " , " + total + " , '" + orderDate + "' )");

        ResultSet order = myStmt.executeQuery("SELECT orderID from orders order by orderID ASC");

        while (order.next()) {
            orderID = order.getInt("orderID");
            orderPayment.setOrderID(orderID);
        }

        for (int i = 0; i < books.size(); i++) {

           insertBook(books.get(i), orderID);

        }

    }

    catch (Exception exc) {
        exc.printStackTrace();
    }

    return orderID;
}

    public void insertBook(Book book, int orderID) {
        try {

            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();

            int bookID = book.getId();
            double bookPrice = book.getPrice();
            int quantity = book.getQuantity();


            int myRs = myStmt.executeUpdate("INSERT INTO booksOrdered (orderID, bookID, quantity, priceEach )" +
            "VALUES (" + orderID + " , " + bookID + " , " + quantity + " , " + bookPrice + " )");



        }

        catch (Exception exc) {
            exc.printStackTrace();

        }

    }


     public void registerUser(String email) {

    	 try {
        	 User user = new User(getUserByEmail(email));

             Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

             //2. create statement
             Statement myStmt = myConn.createStatement();
             int myRs = myStmt.executeUpdate("UPDATE customers SET userType = '" + 1 +  "' WHERE customerID = " + user.getId());
         }
         catch (Exception exc) {
             exc.printStackTrace();
         } // try
     } // registerUser

     /* update firstName lastName in database */
     public void editUserFirstLastName(int customerID, String firstName, String lastName){

            try {
            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

                //2. create statement

            Statement myStmt = myConn.createStatement();
            int myRs = myStmt.executeUpdate("UPDATE customers SET firstName = '" + firstName + "' , " + "lastName = '" + lastName + "' WHERE customerID = " + customerID);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /* update phone number in database */
    public void editPhoneNumber(int customerID, String phoneNumber){

        try {
            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

                //2. create statement

            Statement myStmt = myConn.createStatement();
            int myRs = myStmt.executeUpdate("UPDATE customers SET phoneNumber = '" + phoneNumber + "' WHERE customerID = " + customerID);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    /* update password in database */
    public void editPassword(int customerID, String password){

        try {
            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

                //2. create statement

        	// Locate user by ID and set it with the encoded password
        	// user.setPassword(encodedPassword);


            Statement myStmt = myConn.createStatement();

            int myRs = myStmt.executeUpdate("UPDATE customers SET pswd = '" + password + "' WHERE customerID = " + customerID);


        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

     /* update Billing address in database */
     public void editAddress(int customerID, String addressLine1, String addressLine2, String city, String district, String zipCode){

        try {
            //generate random 7 digit user ID

        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT * from address WHERE customerID = " + customerID);
                //2. create statement



                if (myRs.next()) {

                    int updateAddress = myStmt.executeUpdate("UPDATE address SET line1 = '" + addressLine1 + "' , " + "line2 = '" + addressLine2
            + "' , " + "city = '" + city + "' , " + "district = '" + district + "' , " + "zipCode = '" + zipCode + "' WHERE customerID = " + customerID);

           } else {


            int addAddress = myStmt.executeUpdate("INSERT INTO address (customerID, line1, line2, city, district, zipCode) " +
            "VALUES (" + customerID + " , '" + addressLine1 + "' , '" + addressLine2 + "' , '" + city + "' , '" + district + "' , '" + zipCode + "')");


           }


    }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /* update Billing address in database */
    public void editBillingAddress(int customerID, String billingAddress1, String billingAddress2, String billingCity, String billingState, String billingZipCode){

        try {
            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery("SELECT * from billingAddress WHERE customerID = " + customerID);
                //2. create statement


            if (myRs.next()) {

                int updateBilling = myStmt.executeUpdate("UPDATE billingAddress SET billingAddress1 = '" + billingAddress1 + "' , " + "billingAddress2 = '" + billingAddress2
                + "' , " + "billingCity = '" + billingCity + "' , " + "billingState = '" + billingState + "' , " + "billingZipCode = '" + billingZipCode + "' WHERE customerID = " + customerID);


            }
            else {
                int addAddress = myStmt.executeUpdate("INSERT INTO billingAddress (billingAddress1, billingAddress2, billingCity, billingState, billingZipCode, customerID)" +
                "VALUES ( '" + billingAddress1 + "' , '" + billingAddress2 + "' , '" + billingCity + "' , '" + billingState + "' , '" + billingZipCode + "' , " + customerID + ")");
            }

    }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public int searchedBooks(String searchedBook) {
        int id = 0;

        try {
            //generate random 7 digit user ID

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            Statement myStmt = myConn.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM books WHERE title = '" + searchedBook + "'");
             while (myRs.next()) {
                id =  Integer.parseInt(myRs.getString("bookID"));
             }

         }
         catch (Exception exc) {
             exc.printStackTrace();
         }

         if (id == 0) {
             return -1;
         }
        return id;

    }

    /* update Payment Info in database */
    public void editPaymentInfo(User user){
    	   int customerID = user.getId();
           String nameOnCard = "";
           String cardNumber = "";
           int cardType= 0;
           int month = 0;
           int year = 0;
        //2. create statement

        for(int i = 0; i < 3; i++){


            try {
                //generate random 7 digit user ID

                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

                    //2. create statement

                Statement myStmt = myConn.createStatement();
                ResultSet myRs = myStmt.executeQuery("SELECT * from payments WHERE customerID = " + customerID + " AND paymentOptionNum =  "+ i);

                if(i == 0){
                    nameOnCard = user.getPayment1().getNameOnCard();
                    cardNumber = user.getPayment1().getCardNumberStr();
                    cardType = user.getPayment1().getCardType();
                    month = user.getPayment1().getExpirationMonth();
                    year = user.getPayment1().getExpirationYear();
                }else if(i == 1){
                    nameOnCard = user.getPayment2().getNameOnCard();
                    cardNumber = user.getPayment2().getCardNumberStr();
                    cardType = user.getPayment2().getCardType();
                    month = user.getPayment2().getExpirationMonth();
                    year = user.getPayment2().getExpirationYear();
                }else if(i == 2){
                    nameOnCard = user.getPayment3().getNameOnCard();
                    cardNumber = user.getPayment3().getCardNumberStr();
                    cardType = user.getPayment3().getCardType();
                    month = user.getPayment3().getExpirationMonth();
                    year = user.getPayment3().getExpirationYear();
                }

                if(myRs.next()) {

                    int paymentUpdate = myStmt.executeUpdate("UPDATE payments SET nameOnCard = '" + nameOnCard + "' , " + "cardNumber = '" + cardNumber
                    + "' , " + "cardType = " + cardType + " , " + "month = " + month + " , " + "year = " + year + " WHERE customerID = " + customerID + " AND paymentOptionNum = " + i);
                } else {
                    int paymentInsert = myStmt.executeUpdate("INSERT INTO payments (nameOnCard, cardNumber, cardType, month, year, customerID, paymentOptionNum)"
                            + "VALUES ('" + nameOnCard + "' , '" + cardNumber + "' , " + cardType + " , " + month + " , " + year + " , " + customerID + " , " + i + ")"); 
                    } // if
            } // try
            catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }


    public User getUserByID(int id) {
        User foundUser = new User();
		foundUser.setId(id);

        try{
        //get basic user information
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT * from customers WHERE customerID = " + id);

        while (myRs.next()){
            foundUser.setFirstName(myRs.getString("firstName"));
            foundUser.setLastName(myRs.getString("lastName"));
            foundUser.setPhoneNumber(myRs.getString("phoneNumber"));
            foundUser.setPassword(myRs.getString("pswd"));
            foundUser.setUserType(myRs.getInt("userType"));
            foundUser.setEmail(myRs.getString("email"));
            foundUser.setPromoSignUp(myRs.getBoolean("promoSubscription"));
        } // while

        myRs = myStmt.executeQuery("SELECT * from address WHERE customerID = " + id);

        while(myRs.next()){
            foundUser.setAddressLine1(myRs.getString("line1"));
            foundUser.setAddressLine2(myRs.getString("line2"));
            foundUser.setCity(myRs.getString("city"));
            foundUser.setState(myRs.getString("district"));
            foundUser.setZipCode(myRs.getString("zipCode"));
        } // while

        myRs = myStmt.executeQuery("SELECT * from payments WHERE customerID = " + id);

        for (int i = 0; myRs.next() && i < 3; i++) {
            if (i == 0) {
            	foundUser.getPayment1().setNameOnCard(myRs.getString("nameOnCard"));
            	foundUser.getPayment1().setCardNumberStr(myRs.getString("cardNumber"));
            	foundUser.getPayment1().setCardType(myRs.getInt("cardType"));
            	foundUser.getPayment1().setExpirationMonth(myRs.getInt("month"));
            	foundUser.getPayment1().setExpirationYear(myRs.getInt("year"));
            } else if (i == 1) {
            	foundUser.getPayment2().setNameOnCard(myRs.getString("nameOnCard"));
            	foundUser.getPayment2().setCardNumberStr(myRs.getString("cardNumber"));
            	foundUser.getPayment2().setCardType(myRs.getInt("cardType"));
            	foundUser.getPayment2().setExpirationMonth(myRs.getInt("month"));
            	foundUser.getPayment2().setExpirationYear(myRs.getInt("year"));
            } else {
            	foundUser.getPayment3().setNameOnCard(myRs.getString("nameOnCard"));
            	foundUser.getPayment3().setCardNumberStr(myRs.getString("cardNumber"));
            	foundUser.getPayment3().setCardType(myRs.getInt("cardType"));
            	foundUser.getPayment3().setExpirationMonth(myRs.getInt("month"));
            	foundUser.getPayment3().setExpirationYear(myRs.getInt("year"));
            } // if      
       } // while

        myRs = myStmt.executeQuery("SELECT * from billingAddress WHERE customerID = " + id);

                while(myRs.next()){

                	foundUser.setBillingAddress1(myRs.getString("billingAddress1"));
                	foundUser.setBillingAddress2(myRs.getString("billingAddress2"));
                	foundUser.setBillingCity(myRs.getString("billingCity"));
                	foundUser.setBillingState(myRs.getString("billingState"));
                	foundUser.setBillingZipCode(myRs.getString("billingZipCode"));

                } // while
                return foundUser;

        } catch(Exception exc){
                exc.printStackTrace();
                foundUser = null;

        } // try

        return foundUser;
    } // getUserByID

    public User getUserByEmail(String getEmail) {
        User foundUser = new User();
        int userID = 0;

        try{
        	//get basic user information
        	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");
        	Statement myStmt = myConn.createStatement();
        	ResultSet customerInfo = myStmt.executeQuery("SELECT * from customers WHERE email = '" + getEmail + "'");

        	while (customerInfo.next()){
        		userID = customerInfo.getInt("customerID");
        		foundUser.setId(userID);
        		// check if userID is null
        	} // While

        	ResultSet myRs = myStmt.executeQuery("SELECT * from customers WHERE customerID = " + userID);
            	while (myRs.next()){
            		foundUser.setFirstName(myRs.getString("firstName"));
            		foundUser.setLastName(myRs.getString("lastName"));
            		foundUser.setPhoneNumber(myRs.getString("phoneNumber"));
            		foundUser.setPassword(myRs.getString("pswd"));
            		foundUser.setUserType(myRs.getInt("userType"));
                	foundUser.setEmail(myRs.getString("email"));
                	foundUser.setPromoSignUp(myRs.getBoolean("promoSubscription"));
                } // while

            myRs = myStmt.executeQuery("SELECT * from address WHERE customerID = " + userID);

                while(myRs.next()){
                	 foundUser.setAddressLine1(myRs.getString("line1"));
                     foundUser.setAddressLine2(myRs.getString("line2"));
                     foundUser.setCity(myRs.getString("city"));
                     foundUser.setState(myRs.getString("district"));
                     foundUser.setZipCode(myRs.getString("zipCode"));
                } // while

            myRs = myStmt.executeQuery("SELECT * from payments WHERE customerID = " + userID);

            for (int i = 0; myRs.next() && i < 3; i++) {
                if (i == 0) {
                	foundUser.getPayment1().setNameOnCard(myRs.getString("nameOnCard"));
                	foundUser.getPayment1().setCardNumberStr(myRs.getString("cardNumber"));
                	foundUser.getPayment1().setCardType(myRs.getInt("cardType"));
                	foundUser.getPayment1().setExpirationMonth(myRs.getInt("month"));
                	foundUser.getPayment1().setExpirationYear(myRs.getInt("year"));
                } else if (i == 1) {
                	foundUser.getPayment2().setNameOnCard(myRs.getString("nameOnCard"));
                	foundUser.getPayment2().setCardNumberStr(myRs.getString("cardNumber"));
                	foundUser.getPayment2().setCardType(myRs.getInt("cardType"));
                	foundUser.getPayment2().setExpirationMonth(myRs.getInt("month"));
                	foundUser.getPayment2().setExpirationYear(myRs.getInt("year"));
                } else {
                	foundUser.getPayment3().setNameOnCard(myRs.getString("nameOnCard"));
                	foundUser.getPayment3().setCardNumberStr(myRs.getString("cardNumber"));
                	foundUser.getPayment3().setCardType(myRs.getInt("cardType"));
                	foundUser.getPayment3().setExpirationMonth(myRs.getInt("month"));
                	foundUser.getPayment3().setExpirationYear(myRs.getInt("year"));
                } // if      
           } // while

           myRs = myStmt.executeQuery("SELECT * from billingAddress WHERE customerID = " + userID);

           while(myRs.next()){
               foundUser.setBillingAddress1(myRs.getString("billingAddress1"));
               foundUser.setBillingAddress2(myRs.getString("billingAddress2"));
               foundUser.setBillingCity(myRs.getString("billingCity"));
               foundUser.setBillingState(myRs.getString("billingState"));
               foundUser.setBillingZipCode(myRs.getString("billingZipCode"));
           } // while

        }catch(Exception exc){
                exc.printStackTrace();
                foundUser = null;
        } // try
        return foundUser;

    } // getUserByEmail

    /*
     * Function: This method is only concerned with obtaining attributes of a user that are listed in the customer table
     * Postcondition: returns a null user if their customer ID is not found in the table
     */
    public User getUserByResetPasswordToken(String token) {
    	 User foundUser = new User();
    	 foundUser.setResetPasswordToken("");
         int userID = 0;

         try{
             //get basic user information
             Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");
             Statement myStmt = myConn.createStatement();
             ResultSet customerInfo = myStmt.executeQuery("SELECT * from customers WHERE resetPasswordToken = '" + token + "'");

             while (customerInfo.next()){
                 userID = customerInfo.getInt("customerID");
                 foundUser.setId(userID);
                 // check if userID is null
             } // while

             ResultSet myRs = myStmt.executeQuery("SELECT * from customers WHERE customerID = " + userID);

                     while (myRs.next()){
                    	foundUser.setFirstName(myRs.getString("firstName"));
                 		foundUser.setLastName(myRs.getString("lastName"));
                 		foundUser.setPhoneNumber(myRs.getString("phoneNumber"));
                 		foundUser.setPassword(myRs.getString("pswd"));
                 		foundUser.setUserType(myRs.getInt("userType"));
                     	foundUser.setEmail(myRs.getString("email"));
                     	foundUser.setPromoSignUp(myRs.getBoolean("promoSubscription"));
                        foundUser.setResetPasswordToken(myRs.getString("resetPasswordToken"));

                     } // while

        } catch(Exception exc) {
            exc.printStackTrace();
        } // try
        return foundUser;
    } // getUserByResetPasswordToken

    public void editPasswordToken(int customerID, String token) {
        try {
        	//generate random 7 digit user ID
        	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookwormdb", "root", "password");

            //2. create statement
            Statement myStmt = myConn.createStatement();
            int myRs = myStmt.executeUpdate("UPDATE customers SET resetPasswordToken = '" + token  + "' WHERE customerID = " + customerID);
        } catch (Exception exc) {
        	exc.printStackTrace();
        } // try

    } // editPasswordToken
} // db_access

