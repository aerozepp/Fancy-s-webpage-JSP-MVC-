package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionToSql {

	Connection connection = null;

	
	public ConnectionToSql(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "codelab10", "oracle_11g");
				
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	
	}
	
	public Connection getConnection(){
		System.out.println("SQL connected");
		return connection;
	}
	
	
}
