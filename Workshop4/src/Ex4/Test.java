package Ex4;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	static Process_Student ps = new Process_Student();
	
	public  void delStudent(String ID) {
	}
	public static void displayStudent(String ClassID) {
		ArrayList<Student> ls = ps.getStudent_byClass(ClassID);
		for(int i = 0; i< ls.size(); i++) {
			Student st = (Student) ls.get(i) ;
			System.out.print(st.getID()+"\t"+st.getName());
		}
		
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		displayStudent("TH4");
		
	}

}
