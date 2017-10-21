package view;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
			private ComboBox<String> customizeComboBox;
			private TextField searchTextField;
			private Button queryButton;
			private Button raQueryButton;
		private HBox optimizeHBox;
			private ToggleGroup group;
				private ToggleButton originalButton; 
				private ToggleButton customizeButton;
	
	private VBox centerVBox;
		private HBox upperCenterHBox;
			private VBox queryDetailsVBox;
				private Label queryDetailsLabel;
				private TextArea queryDetailsTextArea;
				private Label tableLabel;
				private TextField tableTextField;
				private TextArea tableTextArea;
				private Label viewLabel;
				private TextField viewTextField;
				private TextArea viewTextArea;
				private Label indexLabel;
				private TextField indexNameTextField;
				private TextField indexTableTextField;
			private VBox profilingVBox;
				private Label profilingLabel;
				private TextArea profilingTextArea;
				private Label queryLabel;
				private TextArea queryTextArea;
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
				
				customizeComboBox = new ComboBox<String> ();
				customizeComboBox.getStyleClass ().add ("ComboBox");
				
				customizeComboBox.getItems ().add ("Create Table");
				customizeComboBox.getItems ().add ("Create View");
				customizeComboBox.getItems ().add ("Create Index");
				
				customizeComboBox.getSelectionModel ().selectFirst ();
				
				searchTextField = new TextField ("");
				searchTextField.setId ("TextField");
				searchTextField.setPromptText ("DateOut");
				
				queryButton = new Button ("Query");
				queryButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				queryButton.getStyleClass().add("Button");
				
				raQueryButton = new Button ("Relational Algebra Query");
				raQueryButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				raQueryButton.getStyleClass().add("Button");
				
			queryHBox.getChildren (). addAll(queryComboBox, searchTextField, queryButton, raQueryButton);
			
			optimizeHBox = new HBox ();
			optimizeHBox.setAlignment (Pos.CENTER_RIGHT);
			//optimizeHBox.setId("Border");
			
				group = new ToggleGroup ();
			
					originalButton = new ToggleButton ("Orignal");
					originalButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					originalButton.getStyleClass().add("Button");
					originalButton.setToggleGroup (group);
					originalButton.setSelected(true);
				
					customizeButton = new ToggleButton ("Customize");
					customizeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					customizeButton.getStyleClass().add("Button");
					customizeButton.setToggleGroup (group);
					
			optimizeHBox.getChildren ().addAll (originalButton, customizeButton);
		
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
		HBox.setHgrow(rv, Priority.ALWAYS);
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
				
				tableLabel = new Label ();
				tableLabel.setId ("DefaultLabel");
				tableLabel.setText("Create Table");
				
				tableTextField = new TextField ();
				tableTextField.setId ("TextField");
				tableTextField.setPromptText ("Table name");
				
				tableTextArea = new TextArea ();
				tableTextArea.setId ("TextArea");
				tableTextArea.setPromptText ("Table query");
				tableTextArea.setEditable(true);
				tableTextArea.setWrapText(true);
				tableTextArea.setMaxWidth(Double.MAX_VALUE);
				tableTextArea.setMaxHeight(Double.MAX_VALUE);
				
				viewLabel = new Label ();
				viewLabel.setId ("DefaultLabel");
				viewLabel.setText ("Create View");
				
				viewTextField = new TextField ();
				viewTextField.setId ("TextField");
				viewTextField.setPromptText ("View name");
				
				viewTextArea = new TextArea ();
				viewTextArea.setId ("TextArea");
				viewTextArea.setPromptText ("View query");
				viewTextArea.setEditable(true);
				viewTextArea.setWrapText(true);
				viewTextArea.setMaxWidth(Double.MAX_VALUE);
				viewTextArea.setMaxHeight(Double.MAX_VALUE);
				
				indexLabel = new Label ();
				indexLabel.setId ("DefaultLabel");
				indexLabel.setText ("Create index");
				
				indexNameTextField = new TextField ();
				indexNameTextField.setId ("TextField");
				indexNameTextField.setPromptText ("Index name");
				
				indexTableTextField = new TextField ();
				indexTableTextField.setId ("TextField");
				indexTableTextField.setPromptText ("Table name");
			
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
				
				queryLabel = new Label ();
				queryLabel.setId ("DefaultLabel");
				queryLabel.setText ("SQL Query");
				
				queryTextArea = new TextArea ();
				queryTextArea.setId ("TextArea");
				queryTextArea.setPromptText ("View query");
				queryTextArea.setEditable(true);
				queryTextArea.setWrapText(true);
				queryTextArea.setMaxWidth(Double.MAX_VALUE);
				queryTextArea.setMaxHeight(Double.MAX_VALUE);
			
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
		originalButton.setOnAction (e -> {
			originalButton.setSelected (true);
			searchTextField.setDisable(false);
			
			queryHBox.getChildren ().removeAll (queryHBox.getChildren ());
			queryHBox.getChildren ().addAll(queryComboBox, searchTextField, queryButton, raQueryButton);
			
			queryDetailsVBox.getChildren ().removeAll (queryDetailsVBox.getChildren ());
			queryDetailsVBox.getChildren ().addAll (queryDetailsLabel, queryDetailsTextArea);
			profilingVBox.getChildren ().removeAll (profilingVBox.getChildren ());
			profilingVBox.getChildren ().addAll (profilingLabel, profilingTextArea);
		});
		customizeButton.setOnAction (e -> {
			customizeButton.setSelected (true);
			searchTextField.setDisable(true);
			
			queryHBox.getChildren ().removeAll (queryHBox.getChildren ());
			queryHBox.getChildren ().addAll(customizeComboBox, searchTextField, queryButton, raQueryButton);
			
			queryDetailsVBox.getChildren ().removeAll (queryDetailsVBox.getChildren ());
			profilingVBox.getChildren ().removeAll (profilingVBox.getChildren ());
			profilingVBox.getChildren ().addAll (queryLabel, queryTextArea);
			switch (customizeComboBox.getSelectionModel ().getSelectedIndex ()) {
				case 0: queryDetailsVBox.getChildren ().addAll (tableLabel, tableTextField, tableTextArea);
					break;
				case 1: queryDetailsVBox.getChildren ().addAll (viewLabel, viewTextField, viewTextArea);
					break;
				case 2: queryDetailsVBox.getChildren ().addAll (indexLabel, indexNameTextField, indexTableTextField);
					break;
			}
		});
		customizeComboBox.setOnAction (e -> {
			queryDetailsVBox.getChildren ().removeAll (queryDetailsVBox.getChildren ());
			profilingVBox.getChildren ().removeAll (profilingVBox.getChildren ());
			profilingVBox.getChildren ().addAll (queryLabel, queryTextArea);
			switch (customizeComboBox.getSelectionModel ().getSelectedIndex ()) {
				case 0: queryDetailsVBox.getChildren ().addAll (tableLabel, tableTextField, tableTextArea);
					break;
				case 1: queryDetailsVBox.getChildren ().addAll (viewLabel, viewTextField, viewTextArea);
					break;
				case 2: queryDetailsVBox.getChildren ().addAll (indexLabel, indexNameTextField, indexTableTextField);
					break;
			}
		});
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
