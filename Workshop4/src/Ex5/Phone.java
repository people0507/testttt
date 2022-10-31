package Ex5;

public class Phone extends Product {

	public double amount;
	public double year;
	public double price;
	public String company;
	
	public Phone() {}
	public Phone(String id,String name,double amount ,double year,double price,String company) {
		super(id,name);
		this.amount = amount;
		this.year = year;
		this.price = price;
		this.company =company;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getYear() {
		return year;
	}
	public void setYear(double year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public double total() {
		double s =0 ;
		s = this.amount *this.price;
		return s ;
	};
}
