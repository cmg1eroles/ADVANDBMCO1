package view;
import controller.MainController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Book;
import model.BookAuthor;
import util.Query;

public class QueryView extends BorderPane implements View{
	private MainController mc;
	
	private VBox queryVbox;
		private Label bookLabel;
		private Label authorLabel;
		/*private Label copiesLabel;
		private Label loansLabel;
		private Label borrowerLabel;
		private Label publisherLabel;*/
	
	public QueryView (MainController mc) {
		super ();
		this.mc = mc;
		
		initScene();
		initHandlers();
		
		update();
	}
	
	private void initScene() {
		setMaxSize (Double.MAX_VALUE, Double.MAX_VALUE);
		initCenterHBox();
		
		setCenter (queryVbox);
		// TODO Auto-generated method stub
		
	}
	
	protected void initCenterHBox () {
		queryVbox = new VBox();
		queryVbox.setId ("MainVbox");
		
		bookLabel = new Label ();
		bookLabel.setText("book");
		bookLabel.setId ("DefaultLabel");
		
		authorLabel = new Label ();
		authorLabel.setText("author");
		authorLabel.setId("DefaultLabel");
		
		queryVbox.getChildren ().addAll (bookLabel, authorLabel);
	}

	private void initHandlers() {
		
	}

	@Override
	public void update() {
		for(Book b : Query.bookQuery("select * from book where bookID = 1")){
			bookLabel.setText(b.toString());
		}
		for(BookAuthor ba : Query.authorQuery("select * from book_authors where bookID = 1")){
			authorLabel.setText(ba.toString());
		}
		/*for(BookCopies bc : Query.copiesQuery("select * from book_copies")){
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
		}*/
	}
	
	public Parent getView() {
		return this;
	}
}
