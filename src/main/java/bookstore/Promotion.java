package bookstore;

// This class defines a promotion to be applied to a book order at checkout

public class Promotion {

	private String promotionName;
	private String promotionCode;
	private Double discount; 
	private String startDate;
	private String endDate;
	
	public Promotion() {
		promotionName = null;
		discount = 0.0;
	} // Promotion
	
	public Promotion(String promotionName, String promotionCode, 
			         Double discount, String startDate, String endDate) {
		this.promotionName = promotionName;
		this.promotionCode = promotionCode;
		this.discount = discount;
		this.startDate = startDate;
		this.endDate = endDate;
		
	} // Promotion
	
	public Promotion(Promotion promotion) {
		this.promotionName = promotion.getPromotionName();
		this.promotionCode = promotion.getPromotionCode();
		this.discount = promotion.getDiscount();
		this.startDate = promotion.getStartDate();
		this.endDate = promotion.getEndDate();
		
	} // Promotion
	
	public String getPromotionName() {
		return promotionName;
	}
	
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	
	public String getPromotionCode() {
		return promotionCode;
	}
	
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	
	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "promotionName: " + promotionName + ", promotionCode: " + promotionCode + "\ndiscount: "
				+ discount + ", startDate: " + startDate + ", endDate: " + endDate;
	}

	
	

} // Promotion
