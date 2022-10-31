package Ex5;

public abstract class Product  {
	public String id;
	public String name;

	public Product() {}
	public Product(String id,String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public abstract double total();

}
