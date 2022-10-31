package Service;

import MySQLCon.MySQLCon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Process_Service implements IService {


	@Override
	public boolean add_Service(String serviceID, String name, double price) {
		// TODO Auto-generated method stub
		Connection cn = MySQLCon.getCon();
		String sql = "insert into tb_service (id,name,price)"+"values(?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,serviceID);
			ps.setString(2,name);
			ps.setDouble(3,price);
			ps.executeUpdate();
			cn.close();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean update_Service(String serviceID, String name, double price) {
		// TODO Auto-generated method stub
		Connection cn = MySQLCon.getCon();
		String sql = "update tb_service set name= ?, price = ? where id = ?";	
	try {
		PreparedStatement ps =(PreparedStatement) cn.prepareStatement(sql);
		ps.setString(3,serviceID);
		ps.setString(1,name);
		ps.setDouble(2,price);
		ps.executeUpdate();
		cn.close();
		return true;
	}catch (Exception e) {
		return false;
	}
	}

	@Override
	public boolean delete_Service(String serviceID) {
		// TODO Auto-generated method stub
		Connection cn = MySQLCon.getCon();
		String sql = "delete from tb_service where id = ?";
				try {
					PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
					ps.setString(1,serviceID);
					ps.executeUpdate();
					cn.close();
					return true;
				}catch(Exception e){
					return false;
			}
	}

	@Override
	public ArrayList<Service> get_ListService() {
		// TODO Auto-generated method stub
		Connection cn = MySQLCon.getCon();
		String sql = "Select * from tb_service";
		ArrayList<Service> lists = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Service st = new Service();
				st.setServiceID(rs.getString("ID"));
				st.setName(rs.getString("Name"));
				st.setPrice(rs.getDouble("Price"));
				lists.add(st);
			}
			}
		catch(Exception e) {}
		return lists;
	}

	@Override
	public Service get_ServiceID(String serviceID) {
		// TODO Auto-generated method stub
		Connection cn = MySQLCon.getCon();
		Service sv = new Service();
		String sql = "Select * from tb_service where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,serviceID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sv.setServiceID(rs.getString("id"));
				sv.setName(rs.getString("name"));
				sv.setPrice(rs.getDouble("price"));
			}
		} catch (Exception e) {}
		return sv;
	}

}
