package businessLayer;

import java.sql.ResultSet;
import java.util.logging.Logger;

import dataLayer.DatabaseComm;

public class DatabaseBridge {
	protected int updateCount;
	protected final static Logger LOGGER = Logger
			.getLogger(DatabaseBridge.class.getName());

	protected static DatabaseComm dbc = new DatabaseComm();
	static private boolean isConnEstablished = false;
	protected ResultSet rs;

	public DatabaseBridge() {
		if (!isConnEstablished)
			dbc.InitializeDatabaseConnection();
		isConnEstablished = true;
	}
}
