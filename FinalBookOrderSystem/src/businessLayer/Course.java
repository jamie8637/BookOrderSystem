package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Course extends DatabaseBridge {

	public ResultSet getList() {
		rs = dbc.query("SELECT course_id FROM bookstore_course ORDER BY course_id");
		return rs;

	}

	public ResultSet getDbData(String courseId) {
		rs = dbc.query("SELECT * FROM bookstore_course WHERE course_id = '"
				+ courseId + "'");
		return rs;

	}

	public String validateData(String courseId, String courseName) {
		String resultCourseName = null;
		rs = dbc.query("SELECT * FROM bookstore_course WHERE course_id = '"
				+ courseId + "'");
		try {
			if (!rs.isBeforeFirst())
				return "new"; // if the courseId doesn't exist, returns "new"
			while (rs.next()) {
				resultCourseName = (rs.getString(2));
			}
		} catch (SQLException e) {
			LOGGER.warning("course validate failure");
		}
		if (courseName.equals(resultCourseName))
			return "nochange"; // if all data is the same, returns "nochange"
		else
			return "update"; // if some data is different and needs update,
								// returns "update"
	}

	public boolean newRow(String courseId, String courseName) {
		updateCount = dbc.update("INSERT INTO bookstore_course VALUES ('"
				+ courseId + "', '" + courseName + "')");
		if (updateCount == 1)
			return true;
		else
			return false;
	}

	public boolean updateRow(String courseId, String courseName) {
		updateCount = dbc.update("UPDATE bookstore_course SET course_name = '"
				+ courseName + "' WHERE course_id = '" + courseId + "'");
		if (updateCount == 1)
			return true;
		else
			return false;
	}
}
