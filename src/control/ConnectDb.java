package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectDb {

	static Connection con = null;
	static String conString = "jdbc:mysql://localhost:8888/thuvientonghop?useUnicode=true&characterEncoding=utf-8";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "Loitv";
	static String password = "xxxx";
	
	public ConnectDb() {
		
	}
	
	/**
	 * the method create a connection to connect with databases
	 * 
	 * @return connection to connect databases
	 */
	public static Connection getConnection() {
		try {

			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(conString, userName, password);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to database");
		}
		return con;
	}
	
}
