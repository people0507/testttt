package Ex4;

import java.sql.Connection;
import java.util.ArrayList;

public interface IStudent {
	public boolean delStudent(String ID);
	public Connection getCon();
	public ArrayList<Student> getListStudent();
	public ArrayList<Student> getStudent_byClass(String ClassID);
	public ArrayList<Student> getStudent_byClass_Gender(String ClassID,boolean Gender);
	public Student getStudent_byID(String ID);
	public boolean insertStudent(String ID,String Name,String ClassID,boolean Gender,double Mark);
	public boolean updateStudent(String ID,String Name,String ClassID,boolean Gender,double Mark);
	
}
