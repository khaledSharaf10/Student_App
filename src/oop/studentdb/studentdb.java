package oop.studentdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class studentdb {
	
	static Connection conn;
	static String driver =("com.mysql.jdbc.Driver");
	static String url ="jdbc:mysql://localhost/students";
	static String user ="root";
	static String pass ="";
	
	
	public static Connection getConnection() throws Exception{
		if(conn == null) {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pass);
		}
		
		return conn;
	}
	
	
}
