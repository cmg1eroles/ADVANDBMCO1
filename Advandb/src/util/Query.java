package util;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import model.Book;
import model.BookAuthor;
import model.BookCopies;
import model.BookLoans;
import model.Borrower;
import model.Database;
import model.Publisher;

public class Query {
	
	public static ArrayList<String[]> rows(String query){
		ArrayList<String[]> rows = new ArrayList<String[]>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			//gets the column names
			String[] col = new String[rsmd.getColumnCount()];
			for(int i = 1; i <= rsmd.getColumnCount(); i++){
				col[i-1] = rsmd.getColumnLabel(i);
			}
			rows.add(col);
			
			while(rs.next()){
				String[] s = new String[rsmd.getColumnCount()];
				for(int i = 1; i <= rsmd.getColumnCount(); i++){
					switch(rsmd.getColumnType(i)){
						case Types.INTEGER: s[i-1] = Integer.toString(rs.getInt(i));
							break;
						case Types.DATE: Date d = rs.getDate(i);
							s[i-1] = d.toString();
							break;
						case Types.VARCHAR: s[i-1] = rs.getString(i);
							break;
					}
				}
				rows.add(s);
			}
			Database.getInstance().closeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	public static ArrayList<String[]> profiling(String query){
		ArrayList<String[]> profiles = new ArrayList<String[]>();
		ResultSet rs = Database.getInstance().profiling(query);
		
		try {
			rs.next();
			String[] col = new String[]{"Query", "Duration"};
			profiles.add(col);
			profiles.add(new String[]{rs.getString(col[0]), Double.toString(rs.getDouble(col[1]))});
			Database.getInstance().closeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return profiles;
	}
	
	public static ArrayList<BookAuthor> authorQuery(String query){
		ArrayList<BookAuthor> ba = new ArrayList<BookAuthor>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				ba.add(new BookAuthor(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			Database.getInstance().closeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ba;
	}
	
	public static ArrayList<BookCopies> copiesQuery(String query){
		ArrayList<BookCopies> bc = new ArrayList<BookCopies>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				bc.add(new BookCopies(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			Database.getInstance().closeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bc;
	}
	
	public static ArrayList<BookLoans> loansQuery(String query){
		ArrayList<BookLoans> bl = new ArrayList<BookLoans>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				bl.add(new BookLoans(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6)));
			}
			Database.getInstance().closeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bl;
	}
	
	public static ArrayList<Book> bookQuery(String query){
		ArrayList<Book> b = new ArrayList<Book>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				b.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			Database.getInstance().closeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	public static ArrayList<Publisher> publisherQuery(String query){
		ArrayList<Publisher> p = new ArrayList<Publisher>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				p.add(new Publisher(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
			Database.getInstance().closeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public static ArrayList<Borrower> borrowerQuery(String query){
		ArrayList<Borrower> br = new ArrayList<Borrower>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				br.add(new Borrower(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			Database.getInstance().closeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return br;
	}
}
