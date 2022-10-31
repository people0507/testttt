package Ex4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Process_Student implements IStudent {

	@Override
	public boolean delStudent(String ID) {
		// TODO Auto-generated method stub
		return false;
	}
	private static Connection cn ;
	@Override
	public Connection getCon() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_student2","root","Nhan36889599");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			
		}
		return cn;
	}

	@Override
	public ArrayList<Student> getListStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Student> getStudent_byClass(String ClassID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Student> getStudent_byClass_Gender(String ClassID, boolean Gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudent_byID(String ID) {
		Connection cn = getCon();
		Student st = new Student();
		String sql = "Select * from tb_student where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				st.setID(rs.getString("id"));
				st.setName(rs.getString("name"));
				st.setGender(rs.getBoolean("gender"));
				st.setClassID(rs.getString("classid"));
				st.setMark(rs.getDouble("mark"));
			}
		} catch (Exception e) {
			
		}
		return st;
	}

	@Override
	public boolean insertStudent(String ID, String Name, String ClassID, boolean Gender, double Mark) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStudent(String ID, String Name, String ClassID, boolean Gender, double Mark) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
