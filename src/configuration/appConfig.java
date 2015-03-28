package configuration;

import java.io.File;
import java.io.IOException;

import yaml.file.*;
import application.Main;

public class appConfig {

	public Main app = null;

	public appConfig(Main app) {
		this.app = app;
	}

	private void reloadConfig() {
		if (this.app.configFile == null) {	    	
			File dir = new File(this.app.getDataFolder() + File.separator + this.app.CONFIG_DIRECTORY);
			if(dir.exists() == false) {
				dir.mkdir();
			}
			this.app.configFile = new File(this.app.getDataFolder() + File.separator + this.app.CONFIG_DIRECTORY + File.separator + this.app.CONFIG_FILE);
			if(this.app.configFile.exists() == false) {
				try {
					this.app.configFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					this.app.log.LogError("Couldn't create new config in 'configs/config.yml'", e);
				}
			}	
			this.app.configFile = new File(this.app.getDataFolder() + File.separator + this.app.CONFIG_DIRECTORY + File.separator + this.app.CONFIG_FILE);
		}
		this.app.config = YamlConfiguration.loadConfiguration(this.app.configFile);
	
		
	}

	private FileConfiguration getConfig() {
		if (this.app.config == null) {
			reloadConfig();
		}
		return this.app.config;
	}

	public void saveConfig() {
		if (this.app.config == null || this.app.configFile == null) {
			return;
		}
		try {
			this.app.config.save(this.app.configFile);
		} catch (IOException ex) {
			this.app.log.LogError("Could not save config to " + this.app.configFile, ex);
		}
	}

	public boolean initiateConfig() {

		reloadConfig();
		getConfig();

		this.app.config.set("Credit.Author", this.app.CREDIT_AUTHOR);
		this.app.config.set("Credit.Company", this.app.CREDIT_ORGANIZATION);
		this.app.config.set("Credit.Version", this.app.CREDIT_VERSION);

		saveConfig();

		return true;

	}
	
	public String getLanguageDescription() {
		return this.app.config.getString("Settings.Language.description", "Deutsch");
	}
	
	public String getLanguageLanguage() {
		return this.app.config.getString("Settings.Language.language", "de");
	}
	
	public String getLanguageCountry() {
		return this.app.config.getString("Settings.Language.country", "DE");
	}
	
	public void setLanguage(String language, String country, String description) {
		this.app.config.set("Settings.Language.language", language);
		this.app.config.set("Settings.Language.country", country);
		this.app.config.set("Settings.Language.description", description);
		saveConfig();
	}

}