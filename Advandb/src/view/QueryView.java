package view;
import controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Book;
import model.BookAuthor;
import model.Database;
import util.Query;

public class QueryView extends BorderPane implements View{
	private MainController mc;
	
	private HBox topHBox;
		private HBox queryHBox;
			private ComboBox<String> queryComboBox;
			private TextField searchTextField;
			private Button queryButton;
		private HBox optimizeHBox;
			private Button optimizeButton;
	
	private VBox centerVBox;
		private HBox upperCenterHBox;
			private VBox queryDetailsVBox;
				private Label queryDetailsLabel;
				private TextArea queryDetailsTextArea;
			private VBox profilingVBox;
				private Label profilingLabel;
				private TextArea profilingTextArea;
		private VBox lowerCenterVBox;
			private Label queryResultsLabel;
			private ResultsView rv;
		
	/*private Label bookLabel;
	private Label authorLabel;
	private Label copiesLabel;
	private Label loansLabel;
	private Label borrowerLabel;
	private Label publisherLabel;*/
	
	public QueryView (MainController mc) {
		super ();
		this.mc = mc;
		
		Database.getInstance().attach(this);
		
		initScene();
		initHandlers();
		
		update();
	}
	
	private void initScene() {
		setMaxSize (Double.MAX_VALUE, Double.MAX_VALUE);
		initTopHBox();
		initCenterVBox();
		
		setTop(topHBox);
		setCenter (centerVBox);
		
	}
	
	private void initTopHBox() {
		topHBox = new HBox ();
		topHBox.setId("TopHbox");
			
			queryHBox = new HBox ();
			queryHBox.setSpacing (50);
			queryHBox.setAlignment (Pos.CENTER_LEFT);
			//queryHBox.setId("Border");
			
				queryComboBox = new ComboBox<String> ();
				queryComboBox.getStyleClass().add("ComboBox");
				
				for (int i = 0; i < 8; i++) {
					queryComboBox.getItems().add("query " + (i + 1));
				}
				
				queryComboBox.getSelectionModel ().selectFirst ();	
				
				searchTextField = new TextField ("seach strings");
				searchTextField.setId ("TextField");
				
				queryButton = new Button ("Query");
				queryButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				queryButton.getStyleClass().add("Button");
				
			queryHBox.getChildren (). addAll(queryComboBox, searchTextField, queryButton);
			
			optimizeHBox = new HBox ();
			optimizeHBox.setAlignment (Pos.CENTER_RIGHT);
			//optimizeHBox.setId("Border");
			
				optimizeButton = new Button ("Optimize");
				optimizeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				optimizeButton.getStyleClass().add("Button");
			
			optimizeHBox.getChildren ().addAll (optimizeButton);
		
		topHBox.getChildren ().addAll (queryHBox, optimizeHBox);
		
		HBox.setHgrow (queryHBox, Priority.ALWAYS);
		HBox.setHgrow (optimizeHBox, Priority.ALWAYS);
	}

	protected void initCenterVBox () {
		centerVBox = new VBox ();
		centerVBox.setId ("CenterVbox");
		centerVBox.setSpacing(100);

		initUpperCenterVBox ();
		initLowerCenterVBox ();
			
		centerVBox.getChildren ().addAll (upperCenterHBox, lowerCenterVBox);
		
		HBox.setHgrow (queryDetailsVBox, Priority.ALWAYS);
		HBox.setHgrow (upperCenterHBox, Priority.ALWAYS);
		HBox.setHgrow (centerVBox, Priority.ALWAYS);
	}
	
	private void initUpperCenterVBox () {
		upperCenterHBox = new HBox ();
		upperCenterHBox.setSpacing (50);
		//upperCenterHBox.setId ("Border");
		
			queryDetailsVBox = new VBox ();
			//queryDetailsVBox.setId ("Border");
			
				queryDetailsLabel = new Label ();
				queryDetailsLabel.setId ("DefaultLabel");
				queryDetailsLabel.setText ("Query Details");
				
				queryDetailsTextArea = new TextArea ();
				queryDetailsTextArea.setId ("TextArea");
				queryDetailsTextArea.setEditable(false);
				queryDetailsTextArea.setWrapText(true);
				queryDetailsTextArea.setMaxWidth(Double.MAX_VALUE);
				queryDetailsTextArea.setMaxHeight(Double.MAX_VALUE);
			
			queryDetailsVBox.getChildren ().addAll (queryDetailsLabel, queryDetailsTextArea);
			
			profilingVBox = new VBox ();
			//profilingVBox.setId ("Border");
			
				profilingLabel = new Label ();
				profilingLabel.setId ("DefaultLabel");
				profilingLabel.setText ("Profiling");
				
				profilingTextArea = new TextArea ();
				profilingTextArea.setId ("TextArea");
				profilingTextArea.setEditable(false);
				profilingTextArea.setWrapText(true);
				profilingTextArea.setMaxWidth(Double.MAX_VALUE);
				profilingTextArea.setMaxHeight(Double.MAX_VALUE);	
			
			profilingVBox.getChildren ().addAll (profilingLabel, profilingTextArea);
	
		upperCenterHBox.getChildren ().addAll (queryDetailsVBox, profilingVBox);
	}

	private void initLowerCenterVBox () {
		lowerCenterVBox = new VBox ();
		
			queryResultsLabel = new Label ();
			queryResultsLabel.setId("DefaultLabel");
			queryResultsLabel.setText ("QueryResults");
			
			rv = new ResultsView (mc);
		
		lowerCenterVBox.getChildren ().addAll (queryResultsLabel, rv);
	}

	private void initHandlers() {
		
	}

	@Override
	public void update() {
		/*for(Book b : Query.bookQuery("select * from book where bookID = 1")){
			bookLabel.setText(b.toString());
		}
		for(BookAuthor ba : Query.authorQuery("select * from book_authors where bookID = 1")){
			authorLabel.setText(ba.toString());
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
		}*/
	}
	
	public Parent getView() {
		return this;
	}
}
