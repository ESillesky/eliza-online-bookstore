package bookstore;

//  This class represents a payment card that a user stores into the system

public class PaymentInfo {
	
	private String nameOnCard;
	private String cardNumberStr; 
	private int cardType; 
	private String cardTypeStr;
	private Integer expirationMonth; 
	private Integer expirationYear;
	
    public PaymentInfo() {	
		setCardType(0);
		expirationMonth = 1;
    	expirationYear = 2021;
    	nameOnCard= "";
        cardNumberStr= "";
     
    } // PaymentInfo
    
    // Function: Copy constructor
    public PaymentInfo(PaymentInfo paymentInfo) {
		copyPaymentInfo(paymentInfo);
    } // PaymentInfo
    
	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	// Function: Contains the string value of a card number in an html form
    public void setCardNumberStr(String cardNumberStr) {
    	this.cardNumberStr = cardNumberStr;
    }
    
    public String getCardNumberStr() {
    	return cardNumberStr;
    } // getCardNumberStr
    

	public int getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		switch(cardType) {
		case "VISA":
			this.cardType = 0;
			this.cardTypeStr = "VISA";
			break;
		case "Mastercard":
			this.cardType = 1;
			this.cardTypeStr = "Mastercard";
			break;
		case "Discover":
			this.cardType = 2;
			this.cardTypeStr = "Discover";
			break;
		case "AMEX":
			this.cardType = 3;
			this.cardTypeStr = "AMEX";
			break;
		default:
			this.cardType = -1;
			this.cardTypeStr = "";
			break;
		} // switch
	} // setCardType

	public void setCardType(int cardType) {
		this.cardType = cardType;
		switch(cardType) {
		case 0:
			cardTypeStr = "VISA";
			break;
		case 1:
			cardTypeStr = "Mastercard";
			break;
		case 2:
			cardTypeStr = "Discover";
			break;
		case 3:
			this.cardType = 3;
			cardTypeStr = "AMEX";
			break;
		default:
			this.cardType = -1;
			cardTypeStr = "";
			break;
		} // switch
	} // setCardTypeString
	
	public void setCardTypeStr(String cardTypeStr) {
		setCardType(cardTypeStr);
	} // setCardTypeStr
	
	public String getCardTypeStr() {
		return cardTypeStr;
	} // getCardTypeStr
	
	public Integer getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(int month) {
		this.expirationMonth = month;
	}

	public Integer getExpirationYear() {
		return expirationYear;
	}


	public void setExpirationYear(int year) {
		this.expirationYear = year;
	}

	public void copyPaymentInfo(PaymentInfo paymentInfo) {
		setCardType(paymentInfo.getCardTypeStr());
    	this.nameOnCard = paymentInfo.getNameOnCard();
		this.cardNumberStr = paymentInfo.getCardNumberStr();
		this.expirationMonth = paymentInfo.getExpirationMonth();
		this.expirationYear = paymentInfo.getExpirationYear();
	} // copy
	
	@Override
	public String toString() {
		return "PaymentInfo [nameOnCard=" + nameOnCard + ", cardNumberStr=" + cardNumberStr + ", cardType=" + cardType
				+ ", cardTypeStr=" + cardTypeStr + ", expirationMonth=" + expirationMonth + ", expirationYear="
				+ expirationYear + "]";
	} // toString

	

} // Payment
