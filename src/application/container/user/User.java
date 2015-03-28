package application.container.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.sqlite.SQLite;
import application.Main;

public class User {
	
	private Main app = null;

	private String username;
	private String password;
	private String databaseType;
	private String databaseFile;
	private String databaseName;
	private String databaseURL;
	private String databaseUser;
	private String databasePassword;
	
	public User(Main app, String username, String password, String databaseType, String databaseName, String databaseURL, String databaseUser, String databasePassword) {
		
		this.app = app;
		this.username = username;
		this.password = password;
		this.databaseType = databaseType;
		this.databaseName = databaseName;
		this.databaseURL = databaseURL;
		this.databaseUser = databaseUser;
		this.databasePassword = databasePassword;
		
	}
	
	public User(Main app, String username, String password, String databaseType, String databaseFile) {
		
		this.app = app;
		this.username = username;
		this.password = password;
		this.databaseType = databaseType;
		this.databaseFile = databaseFile;
		
		initDatabase();
		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDatabaseType() {
		return this.databaseType;
	}
	
	public String getDatabaseFile() {
		return this.databaseFile;
	}
	
	public String getDatabaseName() {
		return this.databaseName;
	}
	
	public String getDatabaseURL() {
		return this.databaseURL;
	}
	
	public String getDatabaseUser() {
		return this.databaseUser;
	}
	
	public String getDatabasePassword() {
		return this.databasePassword;
	}

	private void initDatabase() {
		
		SQLite dbconn = new SQLite();
		dbconn.connect(this.app.SAVE_DIRECTORY, this.databaseFile);

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
					" User			           	TEXT    	NOT NULL, " +
					" Created				    TEXT    	NOT NULL, " +
					" Last_Modified           	TEXT    	NOT NULL);";

			dbconn.create(sql);

			sql = "CREATE TABLE IF NOT EXISTS Plans " +
					"(ID 						INTEGER 	PRIMARY KEY	AUTOINCREMENT, " +
					" Name			           	TEXT    	NOT NULL, " +
					" Description			    TEXT, " +
					" Start_Date			    INTEGER, " +
					" End_Date				    INTEGER, " +
					" Prefix      	     		TEXT    	NOT NULL);";

			dbconn.create(sql);
		}
		
		dbconn.disconnect();
		dbconn = null;

	}

}
