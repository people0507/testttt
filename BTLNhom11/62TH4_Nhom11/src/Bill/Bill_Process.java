package Bill;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import Client.Client;
import MySQLCon.MySQLCon;
import Service.Service;

public class Bill_Process {
	public String generateBillID(String clientID,String roomID, Date checkInDay) {
		String checkInDayS = checkInDay.toString().replaceAll("-", "");
		Random rd = new Random();
		int rdx = rd.nextInt(999-111)+111;
		String BillID = roomID + clientID + checkInDayS+rdx;
		return BillID;		
	}
	public ResultSet getBillData() throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select tb_bill.id as 'billID', tb_client.name as 'ClientName',tb_client.num as 'phone',tb_room.id as 'RoomID',roomtype,tb_room.price as'price', startdate as 'checkinday', enddate as 'checkoutday',roomcharge,servicecharge, (roomcharge+servicecharge) as total from tb_bill,tb_client,tb_room where tb_bill.room_id = tb_room.id and tb_bill.client_id = tb_client.id";
		Statement stm = cn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		return rs;
	}
	public ResultSet getBillByDate(java.sql.Date d1, java.sql.Date d2) throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select tb_bill.id as 'billID', tb_client.name as 'ClientName',tb_client.num as 'phone',tb_room.id as 'RoomID',roomtype,tb_room.price as'price', startdate as 'checkinday', enddate as 'checkoutday',roomcharge,servicecharge, (roomcharge+servicecharge) as total from tb_bill,tb_client,tb_room where tb_bill.room_id = tb_room.id and tb_bill.client_id = tb_client.id and enddate between ? and ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setDate(1, d1);
		ps.setDate(2, d2);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public void changeRoomStatus(String roomID) throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select status from tb_room where id = ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, roomID);
		ResultSet rs = ps.executeQuery();
		int status = 0;
		while (rs.next()){
			if(rs.getInt("status")==0) status =1;
		}
		sql = "update tb_room set status = ? where id = ?";
		ps = cn.prepareStatement(sql);
		ps.setInt(1, status);
		ps.setString(2, roomID);
		ps.executeUpdate();
	}
	public ResultSet getAvaiabledRoom() throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "SELECT id FROM db_hotels.tb_room where tb_room.status = 1;";
		Statement stm = cn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		return rs;
	}
	public ResultSet getDetailBillData(String billID) throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "SELECT service_id as 'serviceid', tb_service.name as'servicename', quatity as 'quantity', price as'price', quatity*price as'cost' FROM db_hotels.tb_detail_bill, tb_service where bill_id='"+billID+"' and service_id = tb_service.id";
		
		Statement stm = cn.createStatement();
		
		ResultSet rs = stm.executeQuery(sql);
		return rs;
	}
	public boolean isExitedClient(String ClientIC) {
		Connection cn = MySQLCon.getCon();
		String sql = "select ic from tb_client where ic = '"+ClientIC+"'";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				if (ClientIC.equals(rs.getString("ic")) )
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Client getClientbyIC(String ic) throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select * from tb_client where ic = '"+ic+"'";
		Statement stm = cn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		Client client = new Client();
		while (rs.next()) {
		client.setClientID(rs.getString("id"));
		client.setName(rs.getString("name"));
		client.setNum(rs.getString("num"));
		client.setAge(rs.getInt("age"));
		client.setIdIC(rs.getString("ic"));
		}
		return client;
	}
	public ResultSet getListEmptyRoomByRoomType(String roomtype) throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select id from tb_room where roomtype = '"+roomtype+"' and status = 1";
		Statement stm = cn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		return rs;
	}
	public ResultSet getListSerVice() throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select name from tb_service";
		Statement stm = cn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		return rs;
	}
	public Service getServiceByName(String name) throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select * from tb_service where name = '"+name+"'";
		Statement stm = cn.createStatement();
		Service sv = new Service();
		ResultSet rs =  stm.executeQuery(sql);
		while (rs.next()) {
			sv.setName(rs.getString("name"));
			sv.setPrice(rs.getDouble("price"));
			sv.setServiceID(rs.getString("id"));
		}
		return sv;
	}
	public boolean createNewBill(String roomID, String clientID) {
		long millis=System.currentTimeMillis();
		Date checkInDate=new java.sql.Date(millis);
//		String dat = checkInDate.toString();
		Connection cn = MySQLCon.getCon();
		String id = generateBillID(clientID, roomID,checkInDate);
		String sql = "insert into tb_bill (id,room_id,client_id,startdate) values (?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,roomID);
	        ps.setString(3,clientID );
	        ps.setDate(4, checkInDate);
	        ps.executeUpdate();
	        cn.close();
	        return true;
		} 
		catch(SQLException e) {
			return false;			
		}
	}
	public boolean isExitedService(String billID, String svID) throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select service_id from tb_detail_bill where bill_id = ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, billID);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if(svID.equals(rs.getString("service_id"))) {
//				cn.close();
				return true;
			}
		}
		cn.close();
		return false;
	}
	public boolean addNewSerVice(String billID, String svID, int quantity){
		Connection cn = MySQLCon.getCon();
		String sql;
		try {
			if(!isExitedService(billID,svID)) {
				sql = "insert into tb_detail_bill (bill_id,service_id,quatity) values (?,?,?)";
				PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
				ps.setString(1, billID);
				ps.setString(2, svID);
				ps.setInt(3, quantity);
				ps.executeUpdate();
				cn.close();
				return true;
			}
			else {
				sql = "select quatity from tb_detail_bill where bill_id = ? and service_id = ?";
				PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
				ps.setString(1, billID);
				ps.setString(2, svID);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					quantity = quantity+ rs.getInt("quatity");
				}
				String sql2 = "update tb_detail_bill set quatity = ? where bill_id = ? and service_id = ?";
				PreparedStatement ps2 = (PreparedStatement) cn.prepareStatement(sql2);
				ps2 = (PreparedStatement) cn.prepareStatement(sql2);
				ps2.setInt(1, quantity);
				ps2.setString(2, billID);
				ps2.setString(3, svID);
				ps2.executeUpdate();
				cn.close();
				return true;
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public double totalServiceCharge(String billID) throws SQLException {
		Connection cn = MySQLCon.getCon();
		double total =0;
		String sql = "select sum(charge) as total from (select quatity*price as charge from tb_detail_bill,tb_service where bill_id = ? and tb_service.id = service_id) as tb";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, billID);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			total = total + rs.getDouble("total");
		}
		return total;
	}
	public boolean checkOut(String billID, double serviceCharge,double roomCharge) {
		Connection cn = MySQLCon.getCon();
		long millis=System.currentTimeMillis();
		Date today=new java.sql.Date(millis);
		String sql = "update tb_bill set enddate = ?, servicecharge =?, roomcharge = ? where id =?";
		try {
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setDate(1, today);
			ps.setDouble(2, serviceCharge);
			ps.setDouble(3, roomCharge);
			ps.setString(4, billID);
			ps.executeUpdate();
			cn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public ResultSet searchRoomForCheckOut(String roomID) throws SQLException {
		Connection cn = MySQLCon.getCon();
		String sql = "select tb_bill.id as 'billID', tb_client.name as 'ClientName',tb_client.num as 'phone',tb_room.id as 'RoomID',roomtype,tb_room.price as'price', startdate as 'checkinday', enddate as 'checkoutday',roomcharge,servicecharge, (roomcharge+servicecharge) as total from tb_bill,tb_client,tb_room where tb_bill.room_id = tb_room.id and tb_bill.client_id = tb_client.id and tb_room.id = ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, roomID);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public boolean deleteServiceFromBill(String billID, String svID) {
		Connection cn = MySQLCon.getCon();
		String sql = "delete from tb_detail_bill where bill_id = ? and service_id = ?";
		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, billID);
			ps.setString(2, svID);
			ps.executeUpdate();
		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
			return false;
		}	
	}
	public boolean updateServiceFromBill(String billID, String svID, int quantity) {
		Connection cn = MySQLCon.getCon();
		String sql = "update tb_detail_bill set quatity = ?  where bill_id = ? and service_id = ?";
		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setString(2, billID);
			ps.setString(3, svID);
			ps.executeUpdate();
		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
			return false;
		}
		
	}
	public static void main(String[] args) throws SQLException {
//		Connection cn = MySQLCon.getCon();
//		String sql = "select service_id from tb_detail_bill where bill_id = ?";
//		PreparedStatement ps = cn.prepareStatement(sql);
//		ps.setString(1, "1012201");
//		String svID = "SV01";
//		ResultSet rs = ps.executeQuery();
//		while (rs.next()) {
//			if(svID.equals(rs.getString("service_id"))) {
////				cn.close();
//				System.out.println("1");
//			}
//		}
//		Connection cn = MySQLCon.getCon();
//		String sql = "select quatity from tb_detail_bill where bill_id = ? and service_id = ?";
//		int quantity = 1;
//		PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
//		ps.setString(1, "1012201");
//		ps.setString(2, "SV01");
//		ResultSet rs = ps.executeQuery();
//		while (rs.next()) {
//			quantity = quantity+ rs.getInt("quatity");
//		}
//		System.out.println(quantity);
		Connection cn = MySQLCon.getCon();
		String sql = "select tb_bill.id as 'billID', tb_client.name as 'ClientName',tb_client.num as 'phone',tb_room.id as 'RoomID',roomtype,tb_room.price as'price', startdate as 'checkinday', enddate as 'checkoutday',roomcharge,servicecharge, (roomcharge+servicecharge) as total from tb_bill,tb_client,tb_room where tb_bill.room_id = tb_room.id and tb_bill.client_id = tb_client.id and tb_room.id = ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "102");
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			if (rs.getString("billID")!=null) {
				if (rs.getDate("checkoutday")!=null) {
					
					System.out.println(1);
				}
			}
		}
	}
}
