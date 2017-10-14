
import model.Book;
import model.BookAuthor;
import model.BookCopies;
import model.BookLoans;
import model.Borrower;
import model.Database;
import model.Publisher;
import util.Query;

public class Driver {
	public static void main(String[] args){
		//connects to the database
		Database.getInstance().connect();
		
		for(Book b : Query.bookQuery("select * from book")){
			System.out.println(b.toString());
		}
		for(BookAuthor ba : Query.authorQuery("select * from book_author")){
			System.out.println(ba.toString());
		}
		for(BookCopies bc : Query.copiesQuery("select * from book_copies")){
			System.out.println(bc.toString());
		}
		for(BookLoans bl : Query.loansQuery("select * from book_loans")){
			System.out.println(bl.toString());
		}
		for(Borrower br : Query.borrowerQuery("select * from borrower")){
			System.out.println(br.toString());
		}
		for(Publisher p : Query.publisherQuery("select * from publisher")){
			System.out.println(p.toString());
		}
		
		//closes connection
		Database.getInstance().close();
	}
}
