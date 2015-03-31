package application.logic.program.root;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import application.Main;

public class rootLogic {

	// Reference to the main application
	private Main app;
	
	private BorderPane pane = null;
	
	private AnchorPane employeePage;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public rootLogic(Main app, BorderPane pane) {
		this.app = app;
		this.pane = pane;
	}
	
	public void loadCharacter() {
		
		try {
	        FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/resources/fxml/program/root/character.fxml"));
	        this.employeePage = (AnchorPane) loader.load();
	        this.pane.setCenter(this.employeePage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
