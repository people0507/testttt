package Ex2;

public abstract class Person {
	public String ID;
	public String Name;
	public String ClassID;
	public Person(){};
	public Person(String ID,String Name,String ClassID) {
		this.ID = ID;
		this.Name = Name;
		this.ClassID = ClassID;
		// TODO Auto-generated constructor stub
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getClassID() {
		return ClassID;
	}
	public void setClassID(String classID) {
		ClassID = classID;
	}
	public abstract String rank();


}
