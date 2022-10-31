package Ex2;

public class Student extends Person {

		public boolean Gender;
		public double Mark;
		public Student() {}
		public Student(String ID,String Name,String ClassID,boolean Gender,double Mark) {
			super(ID,Name,ClassID);
			this.Gender =Gender ;
			this.Mark = Mark;
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
