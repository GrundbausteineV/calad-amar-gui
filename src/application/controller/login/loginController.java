package application.controller.login;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
	private CheckBox login_checkbox_savedata;
	@FXML
	private MenuBar login_menubar_language;
	@FXML
	private Menu login_menu_language;
	@FXML
	private ImageView login_icon_language;
	@FXML
	private ImageView login_image_background;
	@FXML
	private WebView login_webview_news;
	
	private String tempSavedPassword = "";

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
		
		this.login_webview_news.setContextMenuEnabled(false);
		WebEngine webEngine = this.login_webview_news.getEngine();
		webEngine.load("http://www.calad-amar.de");
		
		if(Main.getInstance().config.getString("System.Login.Password") != null) {
			this.login_checkbox_savedata.setSelected(true);
			this.login_textfield_username.setText(Main.getInstance().config.getString("System.Login.Username"));
			this.login_passwordfield_password.setText("*****");
			this.tempSavedPassword = Main.getInstance().config.getString("System.Login.Password");
			this.login_button_login.requestFocus();
		} else {
			this.login_textfield_username.requestFocus();
		}
		
		this.login_button_quit.setOnAction((event) -> {
			Platform.exit();
		});
		
		this.login_button_info.setOnAction((event) -> {
			Main.getInstance().loginLogicC.displayInfoLayer();
		});
		
		this.login_checkbox_savedata.setOnAction((event) -> {
			if(this.login_checkbox_savedata.isSelected()) {
				
			} else {
				this.login_passwordfield_password.setText("");
				this.login_passwordfield_password.requestFocus();
			}
		});

		this.login_button_login.setOnAction((event) -> {
			if(this.login_checkbox_savedata.isSelected() && Main.getInstance().config.getString("System.Login.Password") == null) {
				Main.getInstance().config.set("System.Login.Username", this.login_textfield_username.getText());
				Main.getInstance().config.set("System.Login.Password", Main.getInstance().loginLogicC.generatePasswordHash(this.login_passwordfield_password.getText()));
				try {
					Main.getInstance().config.save(Main.getInstance().configFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(this.login_checkbox_savedata.isSelected() && Main.getInstance().config.getString("System.Login.Password") != null) {
				
			} else {
				Main.getInstance().config.set("System.Login.Username", null);
				Main.getInstance().config.set("System.Login.Password", null);
				try {
					Main.getInstance().config.save(Main.getInstance().configFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(this.login_textfield_username.getText().equalsIgnoreCase("")) {
				Main.getInstance().showTooltip(Main.getInstance().primaryStage, this.login_textfield_username, Main.getInstance().resourceBundle.getString("key.login_tooltip_fillin_username"), null);
				return;
			} else if(this.login_passwordfield_password.getText().equalsIgnoreCase("")) {
				Main.getInstance().showTooltip(Main.getInstance().primaryStage, this.login_passwordfield_password, Main.getInstance().resourceBundle.getString("key.login_tooltip_fillin_password"), null);
				return;
			}
			if(tempSavedPassword.equalsIgnoreCase("")) {
				if(!Main.getInstance().loginLogicC.checkUsername(this.login_textfield_username, this.login_textfield_username.getText(), this.login_passwordfield_password.getText(), true)) {
					Main.getInstance().showTooltip(Main.getInstance().primaryStage, this.login_button_login, Main.getInstance().resourceBundle.getString("key.login_tooltip_error_login"), null);
				}
			} else {
				if(!Main.getInstance().loginLogicC.checkUsername(this.login_textfield_username, this.login_textfield_username.getText(), this.tempSavedPassword, false)) {
					Main.getInstance().showTooltip(Main.getInstance().primaryStage, this.login_button_login, Main.getInstance().resourceBundle.getString("key.login_tooltip_error_login"), null);
				}
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