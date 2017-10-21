package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import view.View;

public class Database extends Model{
	private final String directory = "jdbc:mysql://localhost:3306/library"; //change schema name <--
	private final String user = "root";
	private final String pass = "1234";
	private static Database instance = new Database();
	
	private Connection con;
	private Statement s;
	private ResultSet rs, profiles;
	
	private Database(){
		views = new ArrayList<View>();
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
			profiling(query);
			notifyViews();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet profiling(String query){
		try {
			s = con.createStatement();
			s.executeUpdate("SET profiling = 0;");
			s.executeUpdate("SET profiling_history_size = 0;");
			s.executeUpdate("SET profiling_history_size = 100;");
			s.executeUpdate("set profiling = 1;");
			s.executeQuery(query);
			profiles = s.executeQuery("show profiles");
			//notifyViews();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profiles;
	}
	
	public void executeUpdate(String update){
		try {
			s = con.createStatement();
			s.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeQuery(){
		try {
			s.close();
			rs.close();
			profiles.close();
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
	
	public ResultSet getRS(){
		return rs;
	}
	
	public ResultSet getProfiles(){
		return profiles;
	}
}
