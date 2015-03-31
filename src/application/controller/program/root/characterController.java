package application.controller.program.root;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class characterController {
	
	@FXML
	private Canvas character_canvas_skin;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public characterController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		Image skin = new Image("http://skins.minecraft.net/MinecraftSkins/Eirik_Forskeren.png", 512, 256, false, false);
		Image bg = new Image("resources/images/backgrounds/character.png");
		
		GraphicsContext gc = this.character_canvas_skin.getGraphicsContext2D();
		gc.drawImage(bg, 0, 0, 348, 388, 0, 0, 348, 388);
		gc.drawImage(skin, 64, 64, 64, 64, 140, 70, 64, 64);
		gc.drawImage(skin, 160, 160, 64, 96, 140, 134, 64, 96);
		gc.drawImage(skin, 32, 160, 32, 96, 140, 230, 32, 96);
		gc.drawImage(skin, 64, 160, -32, 96, 172, 230, 32, 96);
		gc.drawImage(skin, 352, 128, 32, 96, 108, 134, 32, 96);
		gc.drawImage(skin, 384, 128, -32, 96, 204, 134, 32, 96);
		
	}

}
