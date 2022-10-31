package Ex3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Process_Student implements IStudent {

	public Process_Student() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean delStudent(String ID) {
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

	private Connection cn;
	@Override
	public  Connection getCon() {
		
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
		Connection cn = getCon();
		String sql = "Select * from tb_student";
		ArrayList<Student> lists = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student st = new Student();
				st.setID(rs.getString("ID"));
				st.setName(rs.getString("Name"));
				st.setClassID(rs.getString("ClassID"));
				st.setGender(rs.getBoolean("Gender"));
				st.setMark(rs.getDouble("Mark"));
				lists.add(st);
			}
			}
		catch(Exception e) {}
		return lists;
	}

	@Override
	public ArrayList<Student> getStudent_byClass(String ClassID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Student> getStudent_byClass_Gender(String ClassID, boolean Gender) {
		Connection cn = getCon();
		ArrayList<Student> arrStudent = new ArrayList<>();
		String sql = "Select * from tb_student where classid = ? and gender = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,ClassID);
			ps.setBoolean(2,Gender);
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
			
		}
		catch(Exception e ) {
			
		}
		return arrStudent;
		
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
				st.setClassID(rs.getString("classid"));
				st.setGender(rs.getBoolean("gender"));
				st.setMark(rs.getDouble("mark"));
			}
		} catch (Exception e) {
			
		}
		return st;
	}

	@Override
	public boolean insertStudent(String ID, String Name, String ClassID, boolean Gender, double Mark) {
		Connection cn = getCon();
		String sql = "insert into tb_student (id,name,classid,gender,mark)"+"values(?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,ID);
			ps.setString(2,Name);
			ps.setString(3,ClassID);
			ps.setBoolean(4,Gender);
			ps.setDouble(5,Mark);
			ps.executeUpdate();
			cn.close();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	

	@Override
	public boolean updateStudent(String ID, String Name, String ClassID, boolean Gender, double Mark) {
		Connection cn = getCon();
		String sql = "update tb_student set name= ?, classid= ?,gender = ? , mark = ? where id = ?";	
	try {
		PreparedStatement ps =(PreparedStatement) cn.prepareStatement(sql);
		ps.setString(5,ID);
		ps.setString(1,Name);
		ps.setString(2,ClassID);
		ps.setBoolean(3, Gender);
		ps.setDouble(4,Mark);
		ps.executeUpdate();
		cn.close();
		return true;
	}catch (Exception e) {
		return false;
	}
	}

}
