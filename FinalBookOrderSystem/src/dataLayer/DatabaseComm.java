package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseComm {

	private final static Logger LOGGER = Logger.getLogger(DatabaseComm.class
			.getName());

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private int updateCount;

	public boolean InitializeDatabaseConnection() {
		// ******** set your database name, login, and password here ********
		String dbName = "bikerace";
		String login = "SYSTEM";
		String password = "advdbm";

		String dbURL = "jdbc:oracle:thin:@localhost:1521:" + dbName;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(dbURL, login, password);
			stmt = con.createStatement();
			LOGGER.info("The database connection has been established.");
			return true;
		} catch (ClassNotFoundException e) {
			LOGGER.warning("Couldn't register JDBC driver.");
			LOGGER.warning("Application Ending.");
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.warning("The database connection failed.");
		}
		return false;
	}

	public ResultSet query(String query) {
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Query for " + query + " failed");
		}
		return rs;
	}

	public int update(String update) {
		try {
			System.out.println(update);
			updateCount = stmt.executeUpdate(update);
		} catch (SQLException e) {
			LOGGER.warning("Update for " + update + " failed");
		}
		
		return updateCount;
	}
}
