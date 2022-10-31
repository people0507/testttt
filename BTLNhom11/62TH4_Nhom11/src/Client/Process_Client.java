package Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import MySQLCon.MySQLCon;

public class Process_Client implements IClient {

	public Process_Client() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Client> get_listClient() {
		Connection cn = MySQLCon.getCon();
		String sql = "Select * from tb_client";
		ArrayList<Client> arr = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Client cl = new Client();
				cl.setClientID(rs.getString("id"));
				cl.setName(rs.getString("name"));
				cl.setIdIC(rs.getString("ic"));
				cl.setNum(rs.getString("num"));
				cl.setGender(rs.getBoolean("gender"));
				cl.setAge(rs.getInt("age"));
				arr.add(cl);
			}
		}
		catch(Exception e ) {}
		return arr;
	}

	public ArrayList<Client> getClient_byID(String ID) {
		Connection cn = MySQLCon.getCon();
		ArrayList<Client> arr = new ArrayList<>();
		String sql = "Select * from tb_client where id = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client cl = new Client();
				cl.setClientID(rs.getString("id"));
				cl.setName(rs.getString("name"));
				cl.setIdIC(rs.getString("ic"));
				cl.setGender(rs.getBoolean("gender"));
				cl.setNum(rs.getString("num"));
				cl.setAge(rs.getInt("age"));
				arr.add(cl);
			}
			
		}
		catch(Exception e ) {
			
		}
		return arr;
	}
	
	public ArrayList<String> getCI(){
        ArrayList<String> arr = new ArrayList<>();
        Connection cn = MySQLCon.getCon();
        String sql = "Select distinct ic  from tb_client order by ic";
        try{
            PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                 arr.add(rs.getString("ic"));
            }               
            ps.close();                
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return arr;
      }
	
	@Override
	public boolean add_Client(String clientID, String name, String idIC, String num, boolean gender, int age) {
		Connection cn = MySQLCon.getCon();
		String sql = "insert into tb_client (id,name,ic,num,gender,age) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,clientID);
			ps.setString(2,name);
			ps.setString(3,idIC);
			ps.setString(4,num);
			ps.setBoolean(5,gender);
			ps.setInt(6,age);
			ps.executeUpdate();
			cn.close();
			System.out.println("ádas");
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean update_Client(String clientID, String name, String idIC, String num, boolean gender, int age) {
		Connection cn = MySQLCon.getCon();
		String sql ="update tb_client set name =? ,num =?,ic =?, gender = ?,age =? where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,idIC);
			ps.setString(3,num);
			ps.setBoolean(4, gender);
			ps.setInt(5, age);
			ps.setString(6,clientID);
			ps.executeUpdate();
			cn.close();
		return true;	
		}
		catch(Exception e ) {
			return false;
		}
		
	}

	@Override
	public boolean delete_Client(String clientID) {
		Connection cn = MySQLCon.getCon();
		String sql ="delete from tb_client where id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,clientID);
			ps.executeUpdate();
			cn.close();
			return true;
		}
		catch(Exception e ){
			return false;
		}
	}

}
