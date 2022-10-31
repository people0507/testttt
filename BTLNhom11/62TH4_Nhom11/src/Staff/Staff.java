package Staff;

public class Staff {
	private String staffId;
	public String name;
	public double salary;
	public String birthDate;
	public boolean gender;
	Staff(){}
	public Staff(String staffId, String name, double salary, String birthDate, boolean gender) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
		this.birthDate = birthDate;
		this.gender = gender;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
}
