package controller;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public abstract class Controller {

	protected Stage mainStage;
	protected Scene scene;
	protected int currentView;
	
	public Controller (Stage stage) {
		this.mainStage = stage;
		mainStage.setMinWidth (1280);
		mainStage.setMinHeight (720);
		
		scene = new Scene (new Group (), mainStage.getWidth (), mainStage.getHeight ());
		initViews ();
		
		setScene (0);
		
		stage.setScene (scene);
		stage.show ();
	}
	
	protected abstract void initViews ();
	
	protected abstract void changeView ();
	
	public abstract void setScene (int n);
	
	public Stage getStage () {
		return mainStage;
	}
	
}
