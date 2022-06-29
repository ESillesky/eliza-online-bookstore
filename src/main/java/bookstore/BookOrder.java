package bookstore;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
public class BookOrder {

	private int orderId = 1;
	private String deliveryName;
	private String deliveryStreet;
	private int cardNumber;



}
