package Ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Process_Student {

	public static Connection getCon() {
		Connection cn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_student1","root","Nhan36889599");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			
		}
		return cn;
		
	}
	
	public ArrayList<Student>  getListStudent(){
		Connection cn = getCon();
		String sql= "select * from tb_students";
		ArrayList<Student> lists = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student st = new Student();
				st.setID(rs.getString("id"));
				st.setName(rs.getString("name"));
				st.setClassID(rs.getString("classid"));
				st.setGender(rs.getBoolean("gender"));
				st.setMark(rs.getDouble("mark"));
				lists.add(st);
			}
			cn.close();
		}
		catch(SQLException e) {}
		
		return lists;
	}
	
	public ArrayList<Student> getStudent_byClass_Gender(String ClassID, boolean Gender) {
		Connection cn = getCon();
		ArrayList<Student> arrStudent = new ArrayList<>();
		String sql = "Select * from tb_students where classid = ? and gender = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, ClassID);
			ps.setBoolean(2, Gender);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student st = new Student();
				st.setID(rs.getString("id"));
				st.setName(rs.getString("name"));
				st.setClassID(rs.getString("classid"));
				st.setGender(rs.getBoolean("gender"));
				st.setMark(rs.getDouble("mark"));
				arrStudent.add(st);
			}
		} catch (Exception e) {
			
		}
		return arrStudent;
	}
	public boolean delStudent(String ClassID) {
		Connection cn = getCon();
		String sql = "delete from tb_students where classid = ?";
		try {
			PreparedStatement ps =(PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, ClassID);
			ps.executeUpdate();
			cn.close();
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		getCon();
	}

}
