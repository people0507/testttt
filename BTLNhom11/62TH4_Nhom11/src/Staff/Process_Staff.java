package Staff;

import MySQLCon.MySQLCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Process_Staff implements IStaff {
	
	
	public static void main(String[] args) {
//		Connection cn = getCon();
//		if(cn!=null) {
//			System.out.print("Thanh cong");
//		}
//		else 
//			System.out.print("That bai");
		System.out.print(MySQLCon.getCon());
		
//			System.out.println(MySQLCon.getCon());
//			ArrayList<Staff> l = new ArrayList<>();
//			l = getListStaff();
//			System.out.println(l.get(0).getName());
		
	}
	
	


//	@Override
	public ArrayList<Staff> getListStaff() {
		// TODO Auto-generated method stub
		Connection con =  MySQLCon.getCon();
		String sql = "select * from tb_staff";
		ArrayList<Staff> lsstaff = new ArrayList<>();
		try {
			//tao chuoi truy van sql
			PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Staff st = new Staff();
				st.setStaffId(rs.getString("id"));
				st.setName(rs.getString("name"));				
				st.setSalary(rs.getDouble("salary"));
				st.setBirthDate(rs.getString("birthdate"));
				st.setGender(rs.getBoolean("gender"));
				lsstaff.add(st);
			}
			
		} catch (SQLException e) {
			
		}
		return lsstaff;
	}


	@Override
	public Staff get_StaffID(String id) {
		// TODO Auto-generated method stub
		Connection cn =  MySQLCon.getCon();
		Staff st = new Staff();
		String sql = "select * from tb_staff where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				st.setStaffId(rs.getString("id"));
				st.setName(rs.getString("name"));				
				st.setSalary(rs.getDouble("salary"));
				st.setBirthDate(rs.getString("birthdate"));
				st.setGender(rs.getBoolean("gender"));
			}
			
		} catch (SQLException e) {
			
		}
		return st;
	}


	@Override
	public boolean insert_Staff(String staffId, String name, double salary, String birthDate, boolean gender) {
		// TODO Auto-generated method stub
		Connection cn =  MySQLCon.getCon();
		String sql = "insert into tb_staff (id,name,salary,birthdate, gender) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1, staffId);
			ps.setString(2, name);
			ps.setDouble(3, salary);
			ps.setString(4, birthDate);
			ps.setBoolean(5, gender);		
			ps.executeUpdate();
			cn.close();
			return true;
		}catch (Exception e) {
			return false;
		}
	}


	@Override
	public boolean update_Staff(String staffId, String name, double salary, String birthDate, boolean gender) {
		// TODO Auto-generated method stub
		Connection cn = MySQLCon.getCon();
		String sql = "Update tb_staff set name = ?,salary = ?, birthdate = ?, gender = ? where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setDouble(2, salary);
			ps.setString(3, birthDate);
			ps.setBoolean(4, gender);
			ps.setString(5, staffId);
			ps.executeUpdate();
			cn.close();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public ArrayList<String> takeNames(){
		//This will collect all names from db
		ArrayList<String> names = new ArrayList<>();
		Connection cnStaff = MySQLCon.getCon();
		String sql = "Select distinct staffid from tb_Staff order by staffid";
		try {
			PreparedStatement ps = (PreparedStatement) cnStaff.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				names.add(rs.getString("staffid"));
			}
			ps.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return names;
	}

	@Override
	public boolean delete_Staff(String id) {
		// TODO Auto-generated method stub
		Connection cn = MySQLCon.getCon();
		String sql = "delete from tb_staff where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);

			ps.setString(1, id);
			ps.executeUpdate();
			cn.close();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	}
	

