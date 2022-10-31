package Service;

public class Service {
	public String serviceID, name;
	public double price;
	
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Service(String serviceID, String name, double price) {
		super();
		this.serviceID = serviceID;
		this.name = name;
		this.price = price;
	}
	public Service() {
		super();
	}
	
}
