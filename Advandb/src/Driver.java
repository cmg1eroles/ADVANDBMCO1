
import java.util.ArrayList;

import model.Database;
import util.Optimize;
import util.Query;
import util.QueryNumber;

public class Driver {
	
	public static void main(String[] args){
		//connects to the database
		Database.getInstance().connect();
		
		/* Queries to use
		 *QueryNumber.one("2012-1-23")
		 *QueryNumber.two("2009-3-12")
		 *QueryNumber.three("Dennell", "Shelly")
		 *QueryNumber.four("Ablex Publishing")
		 *QueryNumber.five("John", "Ogden")
		 *QueryNumber.six("3")
		 */

		//querying the rows
		ArrayList<String[]> rows = Query.rows(QueryNumber.two("2009-3-12"));
		for(String[] s : rows){
			for(String a : s){
				System.out.print("[" + a + "] ");
			}
			System.out.print("\n");
		}
		System.out.println("rows returned: " + (rows.size() - 1));
		
		//getting the profiling execution time
		for(String[] s : Query.profiling(QueryNumber.two("2009-3-12"))){
			for(String a : s){
				System.out.print("[" + a + "] ");
			}
			System.out.print("\n");
		}
		
		//closes connection
		Database.getInstance().close();
	}
}
