
import model.Book;
import model.BookAuthor;
import model.BookCopies;
import model.BookLoans;
import model.Borrower;
import model.Database;
import model.Publisher;
import util.Query;
import util.QueryNumber;
import controller.MainController;
import javafx.application.*;
import javafx.stage.*;

public class DriverView extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new MainController (primaryStage);
	}
	
	public static void main(String[] args){
		Database.getInstance().connect();
		
		launch (args);
		
		Database.getInstance().close();
	}
}
