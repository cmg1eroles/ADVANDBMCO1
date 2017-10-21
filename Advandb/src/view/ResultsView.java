package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.*;
import model.Database;

public class ResultsView extends ScrollPane implements View{

	private MainController mc;
	
	private TableView tableView;
	private ObservableList<ObservableList> data;
	
	private TableColumn col;
	private ObservableList<String> row;
	
	private ResultSet rs = null;
	
	public ResultsView (MainController mc) {
		super ();
		this.mc = mc;
		
		Database.getInstance ().attach (this);
		
		initRV ();
	}
	
	private void initRV() {
		setHbarPolicy (ScrollPane.ScrollBarPolicy.AS_NEEDED);
		setVbarPolicy (ScrollPane.ScrollBarPolicy.AS_NEEDED);
		setId ("Border");
		
		setContent (tableView);
	}

	@Override
	public void update() {
		rs = Database.getInstance ().getRS ();
		
		if (rs == null)
			return;
		
		if (!tableView.getColumns ().isEmpty ())
			tableView.getColumns ().removeAll (col.getColumns());
		
		if (!tableView.getItems ().isEmpty ())
			tableView.getItems ().removeAll (row);
		
		try {
			for (int i = 0; i < rs.getMetaData ().getColumnCount (); i++) {
				final int j = i;
				col = new TableColumn (rs.getMetaData ().getColumnName (i + 1));
				col.setCellFactory (new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>> () {
					public ObservableValue<String> call (CellDataFeatures<ObservableList, String> param) {
							return new SimpleStringProperty (param.getValue ().get (j).toString ());
					}
				});
				
				tableView.getColumns ().addAll (col);
				System.out.println ("Column " + i + " added");
			}
			
			while (rs.next()) {
				row = FXCollections.observableArrayList ();
				
				for (int i = 1; i <= rs.getMetaData ().getColumnCount (); i++) {
					row.add (rs.getString (i));
				}
				
				data.add(row);
				System.out.println ("Row " + row + " added");
			}
			
			tableView.setItems (data);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
