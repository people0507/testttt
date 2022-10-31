package Ex1;

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
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_student","root","Nhan36889599");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			
		}
		return cn;
		
	}
	
	public ArrayList<Student>  getListStudent(){
		Connection cn = getCon();
		String sql= "select * from tb_student";
		ArrayList<Student> lists = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student st = new Student();
				st.setID(rs.getString("id"));
				st.setName(rs.getString("name"));
				st.setClassID(rs.getString("classid"));
				st.setGender(rs.getString("gender"));
				st.setMark(rs.getDouble("mark"));
				lists.add(st);
			}
			cn.close();
		}
		catch(SQLException e) {}
		
		return lists;
	}
	
	public ArrayList<Student> getStudent_byClass(String ClassID) {
		Connection cn = getCon();
		ArrayList<Student> arrStudent = new ArrayList<>();
		String sql = "Select * from tb_student where classid = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, ClassID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student st = new Student();
				st.setID(rs.getString("id"));
				st.setName(rs.getString("name"));
				st.setClassID(rs.getString("classid"));
				st.setGender(rs.getString("gender"));
				st.setMark(rs.getDouble("mark"));
				arrStudent.add(st);
			}
		} catch (SQLException e) {
			
		}
		return arrStudent;
	}
	
	public ArrayList<Student> getStudent_byID(String ID) {
		Connection cn = getCon();
		ArrayList<Student> arrStudent = new ArrayList<>();
		String sql = "Select * from tb_student where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student st = new Student();
				st.setID(rs.getString("id"));
				st.setName(rs.getString("name"));
				st.setClassID(rs.getString("classid"));
				st.setGender(rs.getString("gender"));
				st.setMark(rs.getDouble("mark"));
				arrStudent.add(st);
			}
		} catch (SQLException e) {
			
		}
		return arrStudent;
	}
	
	public static boolean insertStudent(String ID,String Name,String ClassID,String Gender,double Mark) {
		Connection cn = getCon();
		String sql = "Insert into tb_student(ID, Name, ClassID,Gender, Mark) "+ "values(?,?,?,?,?)";
	try {
		PreparedStatement ps =(PreparedStatement) cn.prepareStatement(sql);
		ps.setString(1,ID);
		ps.setString(2,Name);
		ps.setString(3,ClassID);
		ps.setString(4,Gender);
		ps.setDouble(5,Mark);
		ps.executeUpdate();
		cn.close();
		return true;
	}catch (Exception e) {
		return false;
	}
}
	public static boolean delStudent(String ID) {
		Connection cn = getCon();
		String sql = "delete from tb_student where id = ?";
				try {
					PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
					ps.setString(1,ID);
					ps.executeUpdate();
					cn.close();
					return true;
				}catch(Exception e){
					return false;
			}
	}
	
	public static boolean updateStudent(String ID,String Name,String ClassID,String Gender,double Mark) {
		Connection cn = getCon();
		String sql = "update tb_student set name= ?, classid= ?,gender = ? , mark = ? where id = ?";	
	try {
		PreparedStatement ps =(PreparedStatement) cn.prepareStatement(sql);
		ps.setString(5,ID);
		ps.setString(1,Name);
		ps.setString(2,ClassID);
		ps.setString(3,Gender);
		ps.setDouble(4,Mark);
		ps.executeUpdate();
		cn.close();
		return true;
	}catch (Exception e) {
		return false;
	}
}
	
		 public static void main(String[] args) throws SQLException {
			getCon();
		}

}

