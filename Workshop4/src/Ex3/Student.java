package Ex3;

public class Student {
	public String ID;
	public String Name;
	public String ClassID;
	public boolean Gender;
	public double Mark;
	
	public Student() {}
	public Student(String ID,String Name,String ClassID,boolean Gender,double Mark) {
		this.ID = ID;
		this.Name = Name;
		this.ClassID = ClassID;
		this.Gender = Gender; 
		this.Mark = Mark;
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
	public boolean getGender() {
		return Gender;
	}
	public void setGender(boolean gender) {
		Gender = gender;
	}
	public double getMark() {
		return Mark;
	}
	public void setMark(double mark) {
		Mark = mark;
	}
	
	public String rank() {
		String s="";
		if(this.Mark >= 9) {
			s="Xuất sắc";
		}
		else if(this.Mark >= 8){
			s="Giỏi";	
			}
		else if(this.Mark >= 7) {
			s="Khá";
		}
		else 
			s="Trung bình";
		return s;
}
}
