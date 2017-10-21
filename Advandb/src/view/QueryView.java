package view;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Database;
import util.QueryNumber;

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
				
				searchTextField = new TextField ("");
				searchTextField.setId ("TextField");
				searchTextField.setPromptText("DateOut");
				
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
		centerVBox.setSpacing(35);

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
				
				queryDetailsTextArea = new TextArea (QueryNumber.oneDescription ());
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
		queryButton.setOnAction(e -> {
			String[] s = searchTextField.getText().split("_");
			switch(queryComboBox.getSelectionModel().getSelectedIndex()){
				case 0: Database.getInstance().query(QueryNumber.one(s[0]));
					break;
				case 1: Database.getInstance().query(QueryNumber.two(s[0]));
					break;
				case 2: Database.getInstance().query(QueryNumber.three(s[0], s[1]));
					break;
				case 3: Database.getInstance().query(QueryNumber.four(s[0]));
					break;
				case 4: Database.getInstance().query(QueryNumber.five(s[0],s[1]));
					break;
				case 5: Database.getInstance().query(QueryNumber.six(s[0]));
					break;
				case 6: Database.getInstance().query(QueryNumber.seven());
					break;
				case 7: Database.getInstance().query(QueryNumber.eight());
					break;
			}
		});
		queryComboBox.setOnAction(e -> {
            switch (queryComboBox.getValue()) {
            case "query 1": searchTextField.setPromptText("DateOut");
            				queryDetailsTextArea.setText(QueryNumber.oneDescription ());
                            break;
            case "query 2": searchTextField.setPromptText("DateReturned");
            				queryDetailsTextArea.setText(QueryNumber.twoDescription ());
                            break;
            case "query 3": searchTextField.setPromptText("AuthorLName_AuthorFName");
            				queryDetailsTextArea.setText(QueryNumber.threeDescription ());
                            break;
            case "query 4": searchTextField.setPromptText("PublisherName");
            				queryDetailsTextArea.setText(QueryNumber.fourDescription ());
                            break;
            case "query 5": searchTextField.setPromptText("BFName_BLName");
            				queryDetailsTextArea.setText(QueryNumber.fiveDescription ());
                            break;
            case "query 6": searchTextField.setPromptText("BorrowedCount");
            				queryDetailsTextArea.setText(QueryNumber.sixDescription ());
                            break;
            case "query 7": searchTextField.setPromptText("DateOut");
            				queryDetailsTextArea.setText(QueryNumber.sevenDescription ());
                            break;
            case "query 8": searchTextField.setPromptText("DateOut");
            				queryDetailsTextArea.setText(QueryNumber.eightDescription ());
            }
        });
	}

	@Override
	public void update() {
		ResultSet rs = Database.getInstance().getRS();
		ResultSet profiles = Database.getInstance().getProfiles();
		profilingTextArea.setText("");
		
		try{
			rs.last();
			queryResultsLabel.setText("Query Results: " + rs.getRow());
			rs.beforeFirst();
			
			while(profiles.next()){
				profilingTextArea.appendText("[SQL STATEMENT]\n");
				profilingTextArea.appendText(profiles.getString("Query") + "\n");
				profilingTextArea.appendText("[DURATION]\n");
				profilingTextArea.appendText(Double.toString(profiles.getDouble("Duration")) + "\n");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Parent getView() {
		return this;
	}
}
