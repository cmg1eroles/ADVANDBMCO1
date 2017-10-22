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
import util.Optimize;
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
	
	private boolean original;
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
		initMisc();
	}
	
	public void initMisc(){
		original = true;
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
				
				queryTextArea = new TextArea ("");
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
			original = true;
			originalButton.setSelected (true);
			searchTextField.setDisable(false);
			queryTextArea.setText("");
			profilingTextArea.setText("");
			
			queryHBox.getChildren ().removeAll (queryHBox.getChildren ());
			queryHBox.getChildren ().addAll(queryComboBox, searchTextField, queryButton, raQueryButton);
			
			queryDetailsVBox.getChildren ().removeAll (queryDetailsVBox.getChildren ());
			queryDetailsVBox.getChildren ().addAll (queryDetailsLabel, queryDetailsTextArea);
			profilingVBox.getChildren ().removeAll (profilingVBox.getChildren ());
			profilingVBox.getChildren ().addAll (profilingLabel, profilingTextArea);
			
			/*
			 * automatically drops table / view / index when going back to original
			 */
			for(String[] s : Optimize.dropIndexTags){
				Optimize.dropIndex(s[0], s[1]);
				System.out.println("dropped an index");
			}
			
			for(String s : Optimize.dropTableTags){
				Optimize.dropTable(s);
				System.out.println("dropped a table");
			}
			
			for(String s : Optimize.dropViewTags){
				Optimize.dropView(s);
				System.out.println("dropped a view");
			}
			
			Optimize.optimizations = "";
			Optimize.dropIndexTags.clear();
			Optimize.dropTableTags.clear();
			Optimize.dropViewTags.clear();
			
			setQueryDetails();
		});
		customizeButton.setOnAction (e -> {
			original = false;
			customizeButton.setSelected (true);
			searchTextField.setDisable(true);
			profilingTextArea.setText("");
			
			queryHBox.getChildren ().removeAll (queryHBox.getChildren ());
			queryHBox.getChildren ().addAll(queryComboBox, customizeComboBox, searchTextField, queryButton, raQueryButton);
			
			queryDetailsVBox.getChildren ().removeAll (queryDetailsVBox.getChildren ());
			profilingVBox.getChildren ().removeAll (profilingVBox.getChildren ());
			profilingVBox.getChildren ().addAll (profilingLabel, profilingTextArea, queryLabel, queryTextArea);
			queryDetailsVBox.getChildren().addAll(queryDetailsLabel, queryDetailsTextArea);
			switch (customizeComboBox.getSelectionModel ().getSelectedIndex ()) {
				case 0: queryDetailsVBox.getChildren ().addAll (tableLabel, tableTextField, tableTextArea);
					break;
				case 1: queryDetailsVBox.getChildren ().addAll (viewLabel, viewTextField, viewTextArea);
					break;
				case 2: queryDetailsVBox.getChildren ().addAll (indexLabel, indexNameTextField, indexTableTextField);
					break;
			}
			
			String[] s = new String[2];
			s[0] = "";
			s[1] = "";
			String[] s2 = searchTextField.getText().split("_");
			if(s2.length == 2){
				s[0] = s2[0];
				s[1] = s2[1];
			}else{
				s[0] = s2[0];
			}
			switch (queryComboBox.getValue()) {
            case "query 1": queryTextArea.setText(QueryNumber.one(s[0]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER").replace("UNION", "\n\nUNION\n\n"));
                            break;
            case "query 2": queryTextArea.setText(QueryNumber.two(s[0]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER"));
            				break;
            case "query 3": queryTextArea.setText(QueryNumber.three(s[0],s[1]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER"));
            				break;
            case "query 4": queryTextArea.setText(QueryNumber.four(s[0]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER"));
            				break;
            case "query 5": queryTextArea.setText(QueryNumber.five(s[0], s[1]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER"));
            				break;
            case "query 6": queryTextArea.setText(QueryNumber.six(s[0]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER"));
            				break;
            case "query 7": searchTextField.setPromptText("DateOut");
            				queryDetailsTextArea.setText(QueryNumber.sevenDescription ());
                            break;
            case "query 8": searchTextField.setPromptText("DateOut");
            				queryDetailsTextArea.setText(QueryNumber.eightDescription ());
            }
			
		});
		customizeComboBox.setOnAction (e -> {
			queryDetailsVBox.getChildren ().removeAll (queryDetailsVBox.getChildren ());
			profilingVBox.getChildren ().removeAll (profilingVBox.getChildren ());
			profilingVBox.getChildren ().addAll (profilingLabel, profilingTextArea, queryLabel, queryTextArea);
			queryDetailsVBox.getChildren().addAll(queryDetailsLabel, queryDetailsTextArea);
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
			if(original){
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
			}else{
				switch (customizeComboBox.getSelectionModel ().getSelectedIndex ()) {
				case 0: if(!tableTextField.getText().equals("") && !tableTextArea.getText().equals("")){
							Optimize.createTable(tableTextField.getText(), tableTextArea.getText());
							queryDetailsTextArea.appendText("\n\n[NEW OPTIMIZATION]\n");
							queryDetailsTextArea.appendText(Optimize.optimizations);
						}
					break;
				case 1: if(!viewTextField.getText().equals("") && !viewTextArea.getText().equals("")){
							Optimize.createView(viewTextField.getText(), viewTextArea.getText());
							queryDetailsTextArea.appendText("\n\n[NEW OPTIMIZATION]\n");
							queryDetailsTextArea.appendText(Optimize.optimizations);
						}
					break;
				case 2: if(!indexNameTextField.getText().equals("") && !indexTableTextField.getText().equals("")){
							Optimize.createIndex(indexNameTextField.getText(), indexTableTextField.getText());
							queryDetailsTextArea.appendText("\n\n[NEW OPTIMIZATION]\n");
							queryDetailsTextArea.appendText(Optimize.optimizations);
						}
					break;
				}
				
				Database.getInstance().query(queryTextArea.getText().replace("\n", ""));
			}
		});
		raQueryButton.setOnAction(e -> {
			if(original){
				String[] s = searchTextField.getText().split("_");
				switch(queryComboBox.getSelectionModel().getSelectedIndex()){
					case 0: Database.getInstance().query(QueryNumber.one(s[0]));
						break;
					case 1: Database.getInstance().query(QueryNumber.two(s[0]));
						break;
					case 2: Database.getInstance().query(QueryNumber.threeRA(s[0], s[1]));
						break;
					case 3: Database.getInstance().query(QueryNumber.fourRA(s[0]));
						break;
					case 4: Database.getInstance().query(QueryNumber.fiveRA(s[0],s[1]));
						break;
					case 5: Database.getInstance().query(QueryNumber.sixRA(s[0]));
						break;
					case 6: Database.getInstance().query(QueryNumber.sevenRA());
						break;
					case 7: Database.getInstance().query(QueryNumber.eightRA());
						break;
				}
			}else{
				switch (customizeComboBox.getSelectionModel ().getSelectedIndex ()) {
				case 0: if(!tableTextField.getText().equals("") && !tableTextArea.getText().equals("")){
							Optimize.createTable(tableTextField.getText(), tableTextArea.getText());
							queryDetailsTextArea.appendText("\n\n[NEW OPTIMIZATION]\n");
							queryDetailsTextArea.appendText(Optimize.optimizations);
						}
					break;
				case 1: if(!viewTextField.getText().equals("") && !viewTextArea.getText().equals("")){
							Optimize.createView(viewTextField.getText(), viewTextArea.getText());
							queryDetailsTextArea.appendText("\n\n[NEW OPTIMIZATION]\n");
							queryDetailsTextArea.appendText(Optimize.optimizations);
						}
					break;
				case 2: if(!indexNameTextField.getText().equals("") && !indexTableTextField.getText().equals("")){
							Optimize.createIndex(indexNameTextField.getText(), indexTableTextField.getText());
							queryDetailsTextArea.appendText("\n\n[NEW OPTIMIZATION]\n");
							queryDetailsTextArea.appendText(Optimize.optimizations);
						}
					break;
				}
				
				Database.getInstance().query(queryTextArea.getText().replace("\n", ""));
			}
		});
		queryComboBox.setOnAction(e -> {
			searchTextField.setText("");

            setQueryDetails();
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
				profilingTextArea.appendText(profiles.getString("Query").replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER") + "\n\n");
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
	
	public void setQueryDetails(){
		String[] s = new String[2];
		s[0] = "";
		s[1] = "";
		String[] s2 = searchTextField.getText().split("_");
		if(s2.length == 2){
			s[0] = s2[0];
			s[1] = s2[1];
		}else{
			s[0] = s2[0];
		}
		switch (queryComboBox.getValue()) {
        case "query 1": searchTextField.setPromptText("DateOut");
        				queryDetailsTextArea.setText(QueryNumber.oneDescription ());
        				queryTextArea.setText(QueryNumber.one(s[0]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER").replace("UNION", "\n\nUNION\n\n"));
                        break;
        case "query 2": searchTextField.setPromptText("DateReturned");
        				queryDetailsTextArea.setText(QueryNumber.twoDescription ());
        				queryTextArea.setText(QueryNumber.two(s[0]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER").replace("UNION", "\n\nUNION\n\n"));
                        break;
        case "query 3": searchTextField.setPromptText("AuthorLName_AuthorFName");
        				queryDetailsTextArea.setText(QueryNumber.threeDescription ());
        				queryTextArea.setText(QueryNumber.three(s[0],s[1]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER").replace("UNION", "\n\nUNION\n\n"));
                        break;
        case "query 4": searchTextField.setPromptText("PublisherName");
        				queryDetailsTextArea.setText(QueryNumber.fourDescription ());
        				queryTextArea.setText(QueryNumber.four(s[0]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER").replace("UNION", "\n\nUNION\n\n"));
                        break;
        case "query 5": searchTextField.setPromptText("BFName_BLName");
        				queryDetailsTextArea.setText(QueryNumber.fiveDescription ());
        				queryTextArea.setText(QueryNumber.five(s[0],s[1]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER").replace("UNION", "\n\nUNION\n\n"));
                        break;
        case "query 6": searchTextField.setPromptText("BorrowedCount");
        				queryDetailsTextArea.setText(QueryNumber.sixDescription ());
        				queryTextArea.setText(QueryNumber.six(s[0]).replace("FROM", "\nFROM").replace("WHERE",  "\nWHERE").replace("GROUP", "\nGROUP").replace("HAVING", "\nHAVING").replace("ORDER", "\nORDER").replace("UNION", "\n\nUNION\n\n"));
                        break;
        case "query 7": searchTextField.setPromptText("DateOut");
        				queryDetailsTextArea.setText(QueryNumber.sevenDescription ());
                        break;
        case "query 8": searchTextField.setPromptText("DateOut");
        				queryDetailsTextArea.setText(QueryNumber.eightDescription ());
        }
        
        queryDetailsTextArea.appendText("\n\n[NEW OPTIMIZATION]\n");
		queryDetailsTextArea.appendText(Optimize.optimizations);
	}
}
