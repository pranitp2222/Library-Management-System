package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect  {
	
	private static String url = "jdbc:mysql://www.papademas.net:3307/510fp";
	private static String username = "fp510";
	private static String password = "510";
	protected static Connection connection;
	public static Connection getConnection() { 
		return connection; 
		}

	public DBConnect() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
	}
     }
