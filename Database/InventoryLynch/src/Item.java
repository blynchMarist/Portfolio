import java.util.Currency;

public class Item {
	private Integer itemId;
	private String itemName;
	private String itemCategory;
	private Currency wholesalePrice;
	private Currency retailPrice;
	private Integer qoh;
	private Integer minQuant;
	private String clerk;
	
	public Item() {
		this.itemId = null;
		this.itemName = null;
		this.itemCategory = null;
		this.wholesalePrice = null;
		this.retailPrice = null;
		this.qoh = null;
		
		
	}
	
	
	public Item(int itemId, String itemName, String itemCategory, Currency wholesalePrice, Currency retailPrice,
			int qoh, int minQuant, String clerk) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
		this.qoh = qoh;
		this.minQuant = minQuant;
		this.clerk = clerk;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public Currency getWholesalePrice() {
		return wholesalePrice;
	}
	public void setWholesalePrice(Currency wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	public Currency getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Currency retailPrice) {
		this.retailPrice = retailPrice;
	}
	public int getQoh() {
		return qoh;
	}
	public void setQoh(int qoh) {
		this.qoh = qoh;
	}
	public int getMinQuant() {
		return minQuant;
	}
	public void setMinQuant(int minQuant) {
		this.minQuant = minQuant;
	}
	public String getClerk() {
		return clerk;
	}
	public void setClerk(String clerk) {
		this.clerk = clerk;
	}
	

}
