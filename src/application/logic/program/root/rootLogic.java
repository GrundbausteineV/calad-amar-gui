package application.logic.program.root;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import application.Main;

public class rootLogic {

	// Reference to the main application
	private Main app;
	
	private AnchorPane employeePage;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public rootLogic(Main app) {
		this.app = app;
		
	}
	
	public void loadEmployee(BorderPane pane) {
		
		try {
	        FXMLLoader loader = new FXMLLoader(app.getClass().getResource("/resources/fxml/program/root/employee.fxml"));
	        this.employeePage = (AnchorPane) loader.load();
	        pane.setCenter(this.employeePage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
