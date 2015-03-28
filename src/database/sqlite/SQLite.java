package database.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;

public class SQLite {

	private Main app = null;
	private Connection conn = null;
	private Statement stmt = null;

	public SQLite(Main app){
		this.app = app;
		
		File dir = new File(this.app.getDataFolder() + File.separator + this.app.DATABASE_DIRECTORY);
		if(dir.exists() == false) {
			dir.mkdir();
		}
		
		dir = new File(this.app.getDataFolder() + File.separator + this.app.SAVE_DIRECTORY);
		if(dir.exists() == false) {
			dir.mkdir();
		}
		
	}

	public SQLite(){
		
	}
	
	public ResultSet getMetaData() {
		
		DatabaseMetaData md;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public int create(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			this.app.log.LogError("", e);
			return 0;
		}

	}

	public int update(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			this.app.log.LogError("", e);
			return 0;
		}

	}

	public int insert(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			this.app.log.LogError("", e);
			return 0;
		}

	}

	public ResultSet select(String statement) {
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			return rs;
		} catch (Exception e) {
			this.app.log.LogError("", e);
			return null;
		}

	}

	public void connect(String directory, String database) {		
		
		try {
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection("jdbc:sqlite:" + Main.getInstance().getDataFolder() + File.separator + directory + File.separator + database);
			Main.getInstance().log.LogDebug("Successfully connected to Database.");
		} catch (Exception e) {
			Main.getInstance().log.LogError("", e);
		}

	}

	public void disconnect() {

		try {
			if(stmt != null)
				this.stmt.close();
			this.conn.close();
			Main.getInstance().log.LogDebug("Successfully disconnected from Database.");
		} catch (Exception e) {
			Main.getInstance().log.LogError("", e);
		}

	}

}
