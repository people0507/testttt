package Client;

public class Client {
	private String clientID;
	private String name;
	private String idIC;
	private String num;
	private boolean gender;
	private int age;
	
	public Client() {}
	public Client(String clientID, String name, String idIC, String num, boolean gender, int age) {
		super();
		this.clientID = clientID;
		this.name = name;
		this.idIC = idIC;
		this.num = num;
		this.gender = gender;
		this.age = age;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdIC() {
		return idIC;
	}
	public void setIdIC(String idIC) {
		this.idIC = idIC;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
