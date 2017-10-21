package view;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import controller.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.*;
import model.Database;
import util.QueryNumber;

public class ResultsView extends ScrollPane implements View{

	private MainController mc;
	
	private TableView tableView;
	private ObservableList<ObservableList> data;
	
	private ArrayList<TableColumn> col;
	private ObservableList<String> row;
	
	private ResultSet rs = null;
	
	public ResultsView (MainController mc) {
		super ();
		this.mc = mc;
		
		Database.getInstance ().attach (this);
		
		initTableElements();
		initRV ();
	}
	
	private void initRV() {
		setHbarPolicy (ScrollPane.ScrollBarPolicy.AS_NEEDED);
		setVbarPolicy (ScrollPane.ScrollBarPolicy.NEVER);
		setId ("Border");
		setContent (tableView);
	}
	
	public void initTableElements(){
		tableView = new TableView();
		tableView.setId("TableView");
		col = new ArrayList<TableColumn>();
		data = FXCollections.observableArrayList();
	}

	@Override
	public void update() {
		//System.out.println("update");
		rs = Database.getInstance().getRS();
		
		if (rs == null)
			return;
		
		if (!tableView.getColumns ().isEmpty ()){
			for(int i = 0; i < col.size(); i++){
				tableView.getColumns ().removeAll (col.get(i));
			}
			col.clear();
		}
			
		
		if (!tableView.getItems ().isEmpty ()){
			tableView.getItems ().removeAll (row);
			data.clear();
		}
		try {
			for (int i = 0; i < rs.getMetaData ().getColumnCount (); i++) {
				final int j = i;
				TableColumn c = new TableColumn (rs.getMetaData ().getColumnName (i + 1));
				c.setCellValueFactory (new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>> () {
					public ObservableValue<String> call (CellDataFeatures<ObservableList, String> param) {
							return new SimpleStringProperty (param.getValue ().get (j).toString ());
					}
				});
				
				col.add(c);
				tableView.getColumns ().addAll (c);
				//System.out.println ("Column " + i + " added");
			}
			
			while (rs.next()) {
				row = FXCollections.observableArrayList ();
				
				for (int i = 1; i <= rs.getMetaData ().getColumnCount (); i++) {
					String s = "";
					switch(rs.getMetaData().getColumnType(i)){
						case Types.INTEGER: s = Integer.toString(rs.getInt(i));
							break;
						case Types.DATE: Date d = rs.getDate(i);
							s = d.toString();
							break;
						case Types.VARCHAR: s = rs.getString(i);
							break;
						case Types.BIGINT: s = Integer.toString(rs.getInt(i));
							break;
					}
					row.add (s);
				}
				
				data.add(row);
				//System.out.println ("Row " + row + " added");
				
			}
			Database.getInstance().closeQuery();
			tableView.setItems (data);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
