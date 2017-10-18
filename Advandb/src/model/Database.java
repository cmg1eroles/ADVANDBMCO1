package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private final String directory = "jdbc:mysql://localhost:3306/library"; //change schema name <--
	private final String user = "root";
	private final String pass = "1234";
	private static Database instance = new Database();
	
	private Connection con;
	private Statement s;
	private ResultSet rs;
	
	private Database(){
		
	}
	
	public void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(directory, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Database getInstance(){
		return instance;
	}
	
	public ResultSet query(String query){
		try {
			s = con.createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet profiling(String query){
		try {
			s = con.createStatement();
			s.executeUpdate("set profiling = 1;");
			s.executeQuery(query);
			rs = s.executeQuery("show profiles");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void closeQuery(){
		try {
			s.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
