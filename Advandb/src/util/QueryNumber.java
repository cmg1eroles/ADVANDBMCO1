package util;

public class QueryNumber {

	public static String one(String input){
		return "SELECT * "
			 + "FROM book_loans "
			 + "WHERE DateOut = '"+input+"';";
	}
	
	public static String two(String input){
		return "SELECT * "
			 + "FROM book_loans "
			 + "WHERE DateReturned = '"+input+"';";
	}
	
	public static String three(String input1, String input2){
		return "SELECT title "
			 + "FROM book B, book_authors BA "
			 + "WHERE B.BookID = BA.BookID AND AuthorLastName = '"+input1+"' AND AuthorFirstName = '"+input2+"';";
	}
	
	public static String four(String input){
		return "SELECT BranchName "
			 + "FROM library_branch LB, publisher P "
			 + "WHERE BranchAddress = Address AND PublisherName = '"+input+"';";
	}
	
	public static String five(String input1, String input2){
		return "SELECT T1.`Branch/Publisher`, Address "
			  +"FROM ((SELECT BranchName AS 'Branch/Publisher', BorrowerFName AS 'FName', BorrowerLName AS 'LName', Address "
			  		+"FROM library_branch LB, borrower BO "
			  		+"WHERE BranchAddress = Address) "

	   		  +"UNION"

	   		  +" (SELECT PublisherName AS 'Branch/Publisher', BorrowerFName AS 'FName', BorrowerLName AS 'LName', BO.Address "
	   		  		+"FROM publisher P, borrower BO "
	   		  		+"WHERE P.Address = BO.Address)"
	   		  +") T1 "

			  +"WHERE FName = '"+input1+"' AND LName = '"+input2+"';";
	}
	
	public static String six(String input){
		return "SELECT BorrowerLName, BorrowerFName, COUNT(*) AS '#BooksBorrowed' "
			  +"FROM borrower BO, book_loans BL, library_branch LB "
			  +"WHERE BL.CardNo = BO.CardNo AND BL.BranchID = LB.BranchID "
			  		+"AND BO.Address = LB.BranchAddress "
			  +"GROUP BY BO.CardNo "
			  +"HAVING COUNT(*) >= "+input+";";

	}
	
	public static String seven(){
		return null;
	}
	
	public static String eight(){
		return null;
	}
	
	public static String oneDescription(){
		return "This query is used for showing the user the book loans where the borrower returned their borrowed book on the date it was due.";
	}

	public static String twoDescription(){
		return "This query is used for showing the user the book loans where the borrower returned their borrowed book after the due date.";
	}
	
	public static String threeDescription(){
		return "This query is used for showing the user the list of books written by a specific author.";
	}
	
	public static String fourDescription(){
		return "This query is used for showing the user the list of branches that are in the same area of a specific publisher.";
	}
	
	public static String fiveDescription(){
		return "This query is used for showing the user the list of branches and publishers that are in his area.";
	}
	
	public static String sixDescription(){
		return "This query is used for showing the user the list of borrowers who have borrowed at least a specific number of books from their local branches.";
	}
	
	public static String sevenDescription(){
		return "This query is used for showing the user the list of books which were most loaned in each branch at a specific area.";
	}
	
	public static String eightDescription(){
		return "This query is used for showing the user the list of books which were the least borrowed in each branch over the past specific number of years.";
	}
}
