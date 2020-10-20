import java.util.ArrayList;

public class Filters {
	private String lessThanPrice;
	private String greaterThanPrice;
	private ArrayList<String>  categories;
	
	public Filters(){
		this.lessThanPrice = "";
		this.greaterThanPrice = "";
		this.categories = new ArrayList<String>();
	}

	public String getLessThanPrice() {
		return lessThanPrice;
	}

	public void setLessThanPrice(String lessThanPrice) {
		this.lessThanPrice = lessThanPrice;
	}

	public String getGreaterThanPrice() {
		return greaterThanPrice;
	}

	public void setGreaterThanPrice(String greaterThanPrice) {
		this.greaterThanPrice = greaterThanPrice;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
}