package util.localization;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Main;

public class initLanguages {
	
	private Main app;

	public initLanguages(Main app) {
		this.app = app;
	}
	
	public boolean initiateLanguages() {
		
		MenuItem german = new MenuItem("Deutsch");
		german.setGraphic(new ImageView(new Image(Main.getInstance().getClass().getResourceAsStream("/resources/icons/flags/DE.png"))));
		MenuItem english = new MenuItem("English");		
		english.setGraphic(new ImageView(new Image(Main.getInstance().getClass().getResourceAsStream("/resources/icons/flags/GB.png"))));
		MenuItem french = new MenuItem("Français");		
		french.setGraphic(new ImageView(new Image(Main.getInstance().getClass().getResourceAsStream("/resources/icons/flags/FR.png"))));
		
		this.app.languageList.add(german);
		this.app.languageList.add(english);
		this.app.languageList.add(french);
		
		/*File dir = new File(app.getDataFolder(), "lang" + File.separator);
		String fileName;
		String icon;
		MenuItem item = null;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
				}
				else {
					fileName = files[i].getName();
					propertiesFile prop = new propertiesFile(fileName);
					icon = prop.getPropertyValue("key.init_country");;
					item = new MenuItem(prop.getPropertyValue("key.init_description"));
					item.setGraphic(new ImageView(new Image(Main.getInstance().getClass().getResourceAsStream("/resources/icons/flags/" + icon + ".png"))));
					this.app.languageList.add(item);
					//Town town = new Town(plugin, tempName);
					//plugin.LogInfo("initialized: Town (" + town.getName() + ")");
				}
			}
		}*/
		
		return true;
	}

}
