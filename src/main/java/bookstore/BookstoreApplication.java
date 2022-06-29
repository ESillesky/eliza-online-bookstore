package bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class BookstoreApplication {

	// Main entry point into the application
	public static void main(String[] args) {

		SpringApplication.run(BookstoreApplication.class, args);
	} // main

}
