package application.controller.program.root;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class rootController {

	@FXML
	private Button root_button_character;
	@FXML
	private Button root_button_player;
	@FXML
	private Button root_button_towns;
	@FXML
	private Button root_button_economy;
	@FXML
	private Button root_button_poi;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public rootController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		this.root_button_character.setOnAction((event) -> {
			Main.getInstance().rootLogicC.loadCharacter();
		});
		
		this.root_button_player.setOnAction((event) -> {
			Platform.exit();
		});
		
		this.root_button_towns.setOnAction((event) -> {
			Platform.exit();
		});
		
		this.root_button_economy.setOnAction((event) -> {
			Platform.exit();
		});
		
		this.root_button_poi.setOnAction((event) -> {
			Platform.exit();
		});
		
	}

}
