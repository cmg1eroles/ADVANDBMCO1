package controller;
import javafx.stage.Stage;
import view.QueryView;

public class MainController extends Controller {

	private QueryView qv;
	
	public MainController(Stage stage) {
		super(stage);
		mainStage.setTitle("ADVANDB MCO1");
		scene.getStylesheets ().add ("./StyleSheet.css");
	}

	@Override
	protected void initViews() {
		qv = new QueryView (this);		
	}

	@Override
	protected void changeView() {
		switch (currentView) {
			case 0:
				scene.setRoot (qv.getView ());
				break;
				
			default:
				scene.setRoot (qv.getView ());
		}
	}

	@Override
	public void setScene(int n) {
		switch (n) {
			case 0:
			case 1:
				currentView = n;
				break;
				
			default:
				currentView = 0;
		}
		
		changeView ();
	}

}
