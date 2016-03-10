package businessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionBook extends DatabaseBridge {

	
	 public ResultSet getList() 
	 { 
		 rs = dbc.query("SELECT req_id FROM bookstore_section_book ORDER BY req_id"); 
		 return rs;	  
	 }
	 /*
	 * public ResultSet getDbData(String courseId) { rs =
	 * dbc.query("SELECT * FROM bookstore_course WHERE course_id = " +
	 * courseId); return rs;
	 * 
	 * }
	 */

	public String validateData(int reqId, String isbn, String courseId, String sectionId,
			int quantity, String status) {
		String resultIsbn = null;
		String resultCourseId = null;
		String resultSection = null;		
		int resultQuantity = 0;
		String resultStatus = null;

		rs = dbc.query("SELECT * FROM bookstore_section_book WHERE req_id = '"
				+ reqId + "'");
		try {
			if (!rs.isBeforeFirst())
				return "new"; // if the sectionId+courseId+isbn doesn't exist,
								// returns "new"
			while (rs.next()) {
				resultIsbn = (rs.getString(2));
				resultCourseId = (rs.getString(3));
				resultSection = (rs.getString(4));
				resultQuantity = (rs.getInt(5));
				resultStatus = (rs.getString(6));
			}
		} catch (SQLException e) {
			LOGGER.warning("course validate failure");
		}
		if ((isbn.equals(resultIsbn)) && (courseId.equals(resultCourseId))
				&& (sectionId.equals(resultSection))
				&& (quantity == resultQuantity) 
				&& (status.equals(resultStatus)))
			return "nochange"; // if all data is the same, returns "nochange"
		else
			return "update"; // if some data is different and needs update,
								// returns "update"
	}

	public boolean newRow(int reqId, String isbn, String courseId, String sectionId,
			int quantity, String status) {
		updateCount = dbc.update("INSERT INTO bookstore_section_book VALUES ('"
				+ reqId + "', '" + isbn + "', '" + courseId + "', '" + sectionId + "', '"
				+ quantity + "', '" + status + "')");
		if (updateCount == 1)
			return true;
		else
			return false;
	}

	public boolean updateRow(int reqId, String isbn, String courseId, String sectionId,
			int quantity, String status) {
		updateCount = dbc.update("UPDATE bookstore_section_book SET isbn = '" + isbn 
						+ "', course_id = '" + courseId 
						+ "', section_id = '" + sectionId 
						+ "', quantity = '" + quantity 
						+ "', status = '" + status
						+ "' WHERE req_id = '" + reqId + "'");
		if (updateCount == 1)
			return true;
		else
			return false;
	}
	
	public ResultSet getCombinedRow(String reqId)
	{
		rs = dbc.query("SELECT * FROM bookstore_section_book NATURAL JOIN " +
				"bookstore_section NATURAL JOIN bookstore_professor NATURAL JOIN " +
				"bookstore_course NATURAL JOIN  bookstore_book WHERE req_id = '"
				+ reqId + "'");
		return rs;		
	}
	
	public void deleteRow(String reqId)
	{
		rs = dbc.query("DELETE FROM bookstore_section_book WHERE req_id = '" + reqId + "'");
	}

}
