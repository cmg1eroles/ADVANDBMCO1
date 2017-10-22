package util;

public class QueryNumber {
	
	public static boolean isOriginal = true;

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
		if(isOriginal){
			return "SELECT title "
				 + "FROM book B, book_authors BA "
				 + "WHERE B.BookID = BA.BookID AND AuthorLastName = '"+input1+"' AND AuthorFirstName = '"+input2+"';";
		}else{
			return "SELECT title "
				 + "FROM book NATURAL JOIN (SELECT BookID FROM book_authors "
				 + "WHERE AuthorLastName = '"+input1+"' AND AuthorFirstName = '"+input2+"') BA;";
		}
	}
	
	public static String four(String input){
		if(isOriginal){
			return "SELECT BranchName "
				 + "FROM library_branch LB, publisher P "
				 + "WHERE BranchAddress = Address AND PublisherName = '"+input+"';";
		}else{
			return "SELECT BranchName "
				 + "FROM library_branch INNER JOIN (SELECT Address, PublisherName FROM publisher "
				 + "WHERE PublisherName = '"+input+"') P ON BranchAddress = Address;";
		}
	}
	
	public static String five(String input1, String input2){
		if(isOriginal){
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
		}else{
			return "SELECT * "
				 + "FROM ((SELECT BranchName AS 'Branch/Publisher', Address "
				 + "FROM library_branch INNER JOIN borrower ON BranchAddress = Address "
				 + "WHERE BorrowerFName = '"+input1+"' AND BorrowerLName = '"+input2+"') "

				 + "UNION "

	   			 + "(SELECT PublisherName AS 'Branch/Publisher', BO.Address "
	   			 + "FROM publisher P INNER JOIN borrower BO ON P.Address = BO.Address "
	   			 + "WHERE BorrowerFName = '"+input1+"' AND BorrowerLName = '"+input2+"') "
	   			 + ") T1";
		}
	}
	
	public static String six(String input){
		if(isOriginal){
		return "SELECT BorrowerLName, BorrowerFName, COUNT(*) AS '#BooksBorrowed' "
			  +"FROM borrower BO, book_loans BL, library_branch LB "
			  +"WHERE BL.CardNo = BO.CardNo AND BL.BranchID = LB.BranchID "
			  		+"AND BO.Address = LB.BranchAddress "
			  +"GROUP BY BO.CardNo "
			  +"HAVING COUNT(*) >= "+input+";";
		}else{
			return "SELECT BorrowerLName, BorrowerFName, COUNT(*) AS '#BooksBorrowed' "
				 + "FROM book_loans NATURAL JOIN (SELECT BorrowerLName, BorrowerFName, CardNo, BranchID "
				 + "FROM borrower INNER JOIN library_branch LB ON Address = BranchAddress) T1 "
				 + "GROUP BY T1.CardNo "
				 + "HAVING COUNT(*) >= "+input+";";
		}

	}
	
	public static String seven(String input){
		if(isOriginal){
			return "SELECT a.BranchAddress, a.BranchID, a.BranchName, a.BookID, NoTimesLoaned, Title, AuthorLastName, AuthorFirstName, a.PublisherName, Address AS PublisherAddress "
				 + "FROM (SELECT LB.BranchAddress, BL1.BranchID, LB.BranchName, BL1.BookID, Title, AuthorLastName, AuthorFirstName, Count(*) AS NoTimesLoaned, B.PublisherName, Address "
				 	   + "FROM book_loans BL1, book B, book_authors BA, publisher P, library_branch LB "
				 	   + "WHERE BL1.BookID = B.BookID AND BL1.BookID = BA.BookID AND B.PublisherName = P.PublisherName AND BL1.BranchID = LB.BranchID "
				 	   + "GROUP BY BL1.BranchID, LB.BranchName, BL1.BookID, Title, AuthorLastName, AuthorFirstName, B.PublisherName, Address) AS a "
				 + "WHERE EXISTS (SELECT b.BranchID, MAXLOAN "
				 			   + "FROM (SELECT c.BranchID, MAX(LoanCnt2) as MAXLOAN "
				 			   		 + "FROM (SELECT BL2.BranchID, BL2.BookID, Count(*) AS LoanCnt2 "
				 			   		 	   + "FROM book_loans BL2 "
				 			   		 	   + "GROUP BY BL2.BranchID, BL2.BookID) as c "
				 			   		 + "GROUP BY c.BranchID) as b "
				 			   + "WHERE a.BranchID = b.BranchID AND NoTimesLoaned = MAXLOAN) and a.BranchAddress = '"+input+"' "
				 + "ORDER BY a.BranchID ASC, Title;";
		}else{
			return null;
		}
		
	}
	
	public static String eight(String input){
		if(isOriginal){
			return "SELECT a.DateOut, a.BranchAddress, a.BranchID, a.BranchName, a.BookID, NoTimesLoaned, Title, AuthorLastName, AuthorFirstName, a.PublisherName, Address AS PublisherAddress "
					 + "FROM (SELECT BL1.DateOut, LB.BranchAddress, BL1.BranchID, LB.BranchName, BL1.BookID, Title, AuthorLastName, AuthorFirstName, Count(*) AS NoTimesLoaned, B.PublisherName, Address "
					 	   + "FROM book_loans BL1, book B, book_authors BA, publisher P, library_branch LB "
					 	   + "WHERE BL1.BookID = B.BookID AND BL1.BookID = BA.BookID AND B.PublisherName = P.PublisherName AND BL1.BranchID = LB.BranchID AND year(BL1.DateOut) = '"+input+"' "
					 	   + "GROUP BY BL1.BranchID, LB.BranchName, BL1.BookID, Title, AuthorLastName, AuthorFirstName, B.PublisherName, Address) AS a "
					 + "WHERE EXISTS (SELECT b.BranchID, minLoan "
					 			   + "FROM (SELECT c.BranchID, min(LoanCnt2) as minLoan "
					 			   		 + "FROM (SELECT BL2.BranchID, BL2.BookID, Count(*) AS LoanCnt2 "
					 			   		 	   + "FROM book_loans BL2 "
					 			   		 	   + "GROUP BY BL2.BranchID, BL2.BookID) as c "
					 			   		 + "GROUP BY c.BranchID) as b "
					 			   + "WHERE a.BranchID = b.BranchID AND NoTimesLoaned = minLoan) "
					 + "ORDER BY a.BranchID ASC, Title;";
		}else{
			return null;
		}
	}
	
	public static String oneDescription(){
		return "This query is used for showing the user the list of book loans where books were borrowed by borrowers on a specific date, i.e. the list of book loans which occured on a specified date.";
	}

	public static String twoDescription(){
		return "This query is used for showing the user the book loans where books were returned by borrowers on a specific date, i.e. the list of book returns on a specified date.";
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
		return "This query is used for showing the user the list of books which were the least borrowed in each branch in a specific year.";
	}
}
