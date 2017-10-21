
import java.util.ArrayList;

import model.Database;
import util.Optimize;
import util.Query;

public class Driver {
	public static void main(String[] args){
		//connects to the database
		Database.getInstance().connect();
		
		String query = "select Title from book, book_authors where AuthorFirstName = 'Harper' and book.BookID = 3312;";
		
		//querying the rows
		ArrayList<String[]> rows = Query.rows(query);
		for(String[] s : rows){
			for(String a : s){
				System.out.print("[" + a + "] ");
			}
			System.out.print("\n");
		}
		System.out.println("rows returned: " + (rows.size() - 1));
		
		//getting the profiling execution time
		for(String[] s : Query.profiling(query)){
			for(String a : s){
				System.out.print("[" + a + "] ");
			}
			System.out.print("\n");
		}
		
		//closes connection
		Database.getInstance().close();
	}
}
