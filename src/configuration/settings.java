package configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.sqlite.SQLite;
import application.Main;

public class settings {

	private Main app = null;

	public settings(Main app) {
		this.app = app;

		initiateSettings();
	}

	private void initiateSettings() {

		createSettingsDatabase();
		createUserDatabase();
		createDebugDatabase();

		updateDatabase();

	}

	private void createSettingsDatabase() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		ResultSet rs = dbconn.getMetaData();		
		Boolean contains = false;

		try {
			while (rs.next()) {
				if(rs.getString(3).contains("Settings")) {
					contains = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(contains == false) {

			String sql = "CREATE TABLE IF NOT EXISTS Settings " +
					"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
					" Credit_Author           	TEXT    NOT NULL, " +
					" Credit_Organization       TEXT    NOT NULL, " +
					" Credit_Version           	TEXT    NOT NULL, " +
					" Localization_Language     TEXT    NOT NULL, " +
					" Localization_Country      TEXT    NOT NULL, " +
					" Localization_Description  TEXT    NOT NULL);";

			dbconn.create(sql);
			
			sql = "INSERT INTO Settings " +
					"(Credit_Author, " +
					" Credit_Organization, " +
					" Credit_Version, " +
					" Localization_Language, " +
					" Localization_Country, " +
					" Localization_Description) " + 
					" VALUES " + 
					"('" + this.app.CREDIT_AUTHOR + "', " +
					"'" + this.app.CREDIT_ORGANIZATION + "', " +
					"'" + this.app.CREDIT_VERSION + "', " +
					"'de', " +
					"'DE', " +
					"'Deutsch');";

			dbconn.insert(sql);
		}
		
		dbconn.disconnect();
		dbconn = null;

	}

	private void createUserDatabase() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "CREATE TABLE IF NOT EXISTS User " +
				"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
				" Username				    TEXT    			NOT NULL, " +
				" Password				    TEXT    			NOT NULL);";

		dbconn.create(sql);
		
		//String securePassword = this.app.loginLogicC.generatePasswordHash("A8e5untV");
		//sql = "INSERT INTO User (Username, Password) VALUES ('JEDressler','" + securePassword + "');";

		dbconn.insert(sql);
		
		dbconn.disconnect();
		dbconn = null;

	}

	private void createDebugDatabase() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "CREATE TABLE IF NOT EXISTS Debug " +
				"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
				" Username				    TEXT    			NOT NULL, " +
				" Password				    TEXT    			NOT NULL);";

		dbconn.create(sql);
		
		dbconn.disconnect();
		dbconn = null;

	}

	private void updateDatabase() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);

		String sql = "UPDATE Settings SET " +
				"Credit_Author='" + this.app.CREDIT_AUTHOR + "', " +
				"Credit_Organization='" + this.app.CREDIT_ORGANIZATION + "', " +
				"Credit_Version='" + this.app.CREDIT_VERSION + "' WHERE ID=1;";

		dbconn.update(sql);
		
		dbconn.disconnect();
		dbconn = null;

	}

	public String getLanguageDescription() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);
		
		ResultSet rs = dbconn.select("SELECT * FROM Settings WHERE ID=1;");
		if(rs == null) {			
			dbconn.disconnect();
			dbconn = null;
			return "Deutsch";
		} else {
			String description = "";
			try {
				while ( rs.next() ) {
					description = rs.getString("Localization_Description");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.app.log.LogDebug("Description: " + description);
			dbconn.disconnect();
			dbconn = null;
			return description;
		}
	}

	public String getLanguageLanguage() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);
		
		ResultSet rs = dbconn.select("SELECT * FROM Settings WHERE ID=1;");
		if(rs == null) {
			dbconn.disconnect();
			dbconn = null;
			return "de";
		} else {
			String language = "";
			try {
				while ( rs.next() ) {
					language = rs.getString("Localization_Language");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.app.log.LogDebug("Language: " + language);
			dbconn.disconnect();
			dbconn = null;
			return language;
		}
	}

	public String getLanguageCountry() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);
		
		ResultSet rs = dbconn.select("SELECT * FROM Settings WHERE ID=1;");
		if(rs == null) {
			dbconn.disconnect();
			dbconn = null;
			return "DE";
		} else {
			String country = "";
			try {
				while ( rs.next() ) {
					country = rs.getString("Localization_Country");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.app.log.LogDebug("Country: " + country);
			dbconn.disconnect();
			dbconn = null;
			return country;
		}
	}

	public void setLanguage(String language, String country, String description) {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.DATABASE_DIRECTORY, this.app.DATABASE_FILE);
		
		String sql = "UPDATE Settings SET " +
				"LOCALIZATION_LANGUAGE='" + language + "', " +
				"LOCALIZATION_COUNTRY='" + country + "', " +
				"LOCALIZATION_DESCRIPTION='" + description + "' WHERE ID=1;";

		dbconn.update(sql);
		
		dbconn.disconnect();
		dbconn = null;
		
	}

}
