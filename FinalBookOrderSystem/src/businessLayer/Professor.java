package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor extends DatabaseBridge {

	public ResultSet getList() {
		rs = dbc.query("SELECT emp_id FROM bookstore_professor ORDER BY emp_id");
		return rs;
	}

	public ResultSet getDbData(String empId) {
		rs = dbc.query("SELECT * FROM bookstore_professor WHERE emp_id = '"
				+ empId + "'");
		return rs;
	}

	public String validateData(String empId, String fName, String lName,
			String progChair, String email, String campus) {
		String resultfName = null;
		String resultlName = null;
		String resultProgChair = null;
		String resultEmail = null;
		String resultCampus = null;
		rs = dbc.query("SELECT * FROM bookstore_professor WHERE emp_id = '"
				+ empId + "'");
		try {
			if (!rs.isBeforeFirst())
				return "new"; // if the empId doesn't exist, returns "new"
			while (rs.next()) {
				resultfName = (rs.getString(2));
				resultlName = (rs.getString(3));
				resultProgChair = (rs.getString(4));
				resultEmail = (rs.getString(5));
				resultCampus = (rs.getString(6));
			}
		} catch (SQLException e) {
			LOGGER.warning("course validate failure");
		}
		if (fName.equals(resultfName) && (lName.equals(resultlName))
				&& (resultProgChair.equals(progChair)) 
				&& (resultEmail.equals(email))
				&& (resultCampus.equals(campus)))
			return "nochange"; // if all data is the same, returns "nochange"
		else
			return "update"; // if some data is different and needs update,
								// returns "update"
	}

	public boolean newRow(String empId, String fName, String lName,
			String progChair, String email, String campus) {
		updateCount = dbc.update("INSERT INTO bookstore_professor VALUES ('"
				+ empId + "', '" + fName + "', '" + lName + "', '" + progChair
				+ "', '" + email + "', '" + campus + "')");
		if (updateCount == 1)
			return true;
		else
			return false;
	}

	public boolean updateRow(String empId, String fName, String lName,
			String progChair, String email, String campus) {
		updateCount = dbc.update("UPDATE bookstore_professor SET f_name = '"
				+ fName + "', l_name = '" + lName + "', prog_chair = '"
				+ progChair + "', email = '" + email + "', campus = '" + campus 
				+ "' WHERE emp_id = '" + empId + "'");
		if (updateCount == 1)
			return true;
		else
			return false;
	}
}
