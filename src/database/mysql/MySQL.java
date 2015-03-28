package database.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import application.Main;

public class MySQL {

	private Main app = null;
	private Connection conn = null;
	private Statement stmt = null;

	public MySQL(Main app){
		this.app = app;		
	}
	
	public void testConnection() {
		this.connect("localhost", 3306, "test", "test", "test");
		create("CREATE TABLE IF NOT EXISTS Test ( ID int(11) NOT NULL, A text NOT NULL, B text NOT NULL, C text NOT NULL ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
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

	public void connect(String url, int port, String database, String username, String password) {		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			MysqlDataSource ds = new MysqlDataSource();
			ds.setServerName(url);
			ds.setPort(port);
			ds.setDatabaseName(database);
			ds.setUser(username);
			ds.setPassword(password);
			this.conn = ds.getConnection();
			//this.conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + database + "?user="+ username + "&password=" + password + "");
			//this.conn = DriverManager.getConnection("jdbc:mysql://10.0.100.111/test?user=test&password=test");
			//this.conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + database, username, password);
			this.app.log.LogDebug("Successfully connected to Database.");
		} catch (Exception e) {
			this.app.log.LogError("Can't connect to MySQL Database", e);
		}

	}

	public void disconnect() {

		try {
			if(stmt != null)
				this.stmt.close();
			this.conn.close();
			this.app.log.LogDebug("Successfully disconnected from Database.");
		} catch (Exception e) {
			this.app.log.LogError("", e);
		}

	}

}
