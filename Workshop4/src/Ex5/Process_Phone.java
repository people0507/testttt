package Ex5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Process_Phone {

	private static Connection cn ;
	public Connection getCon() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_phone","root","Nhan36889599");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			
		}
		return cn;
	}
	
	public boolean insertPhone(String id, String name, double year, String company, double price,double amount) {
		Connection cn = getCon();
		String sql = "insert into tbphone (id,name,year,company,price,amount) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,name);
			ps.setDouble(3,year);
			ps.setString(4,company);
			ps.setDouble(5,price);
			ps.setDouble(6,amount);
			ps.executeUpdate();
			cn.close();
			System.out.println("sss");
			return true;
			
		}
		catch(Exception e){
			return false;
		}
	}

}
