package application.controller.login;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class loginController {	

	@FXML
	private Button login_button_login;
	@FXML
	private Button login_button_info;
	@FXML
	private Button login_button_quit;
	@FXML
	private TextField login_textfield_username;
	@FXML
	private PasswordField login_passwordfield_password;
	@FXML
	private MenuBar login_menubar_language;
	@FXML
	private Menu login_menu_language;
	@FXML
	private ImageView login_icon_language;
	@FXML
	private ImageView login_image_background;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public loginController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		this.login_menu_language.getItems().addAll(Main.getInstance().languageList);

		//int index = languageList.indexOf(Main.getInstance().appConfig.getLanguageDescription());
		//this.login_choicebox_language.getSelectionModel().select(languageList.get(index));
		
		this.login_button_quit.setOnAction((event) -> {
			Platform.exit();
		});
		
		this.login_button_info.setOnAction((event) -> {
			Main.getInstance().loginLogicC.displayInfoLayer();
		});

		this.login_button_login.setOnAction((event) -> {
			if(this.login_textfield_username.getText().equalsIgnoreCase("")) {
				Main.getInstance().showTooltip(Main.getInstance().primaryStage, this.login_textfield_username, Main.getInstance().resourceBundle.getString("key.login_tooltip_fillin_username"), null);
				return;
			} else if(this.login_passwordfield_password.getText().equalsIgnoreCase("")) {
				Main.getInstance().showTooltip(Main.getInstance().primaryStage, this.login_passwordfield_password, Main.getInstance().resourceBundle.getString("key.login_tooltip_fillin_password"), null);
				return;
			}
			if(!Main.getInstance().loginLogicC.checkUsername(this.login_textfield_username, this.login_textfield_username.getText(), this.login_passwordfield_password.getText())) {
				Main.getInstance().showTooltip(Main.getInstance().primaryStage, this.login_button_login, Main.getInstance().resourceBundle.getString("key.login_tooltip_error_login"), null);
			}
		});
		
		Main.getInstance().languageList.forEach(item -> {
			item.setOnAction((event) -> {
				Main.getInstance().loginLogicC.setLanguage(item.getText());
			});
		});
		
		this.login_icon_language.setImage(new Image("/resources/icons/flags/" + Main.getInstance().languageIcon.get(0) + ".png"));
		this.login_image_background.setImage(new Image("/resources/images/backgrounds/login.png"));
		
	}

}