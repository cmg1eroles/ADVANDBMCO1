package util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Book;
import model.BookAuthor;
import model.BookCopies;
import model.BookLoans;
import model.Borrower;
import model.Database;
import model.Publisher;

public class Query extends Model{
	
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
