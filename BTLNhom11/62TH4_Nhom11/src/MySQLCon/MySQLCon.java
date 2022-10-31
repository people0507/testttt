package MySQLCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLCon {
	public static Connection cn = null;
	public static Connection getCon() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_hotels","root","Nhan36889599");
		
		
		}
		catch(ClassNotFoundException | SQLException e)
		{
			
		}
		return cn;
	}
}
