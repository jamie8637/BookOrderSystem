package frontEndLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import dataLayer.DatabaseComm;
import businessLayer.Book;
import businessLayer.Course;
import businessLayer.Professor;
import businessLayer.Section;
import businessLayer.SectionBook;

public class nonGui {
	private final static Logger LOGGER = Logger.getLogger(DatabaseComm.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Instantiates classes
		Book currentBook = new Book();
		Course currentCourse = new Course();
		Professor currentProfessor = new Professor();
		Section currentSection = new Section();
		SectionBook currentSectionBook = new SectionBook();
		
		
		/*
		//	Gets the PK list from each table that will have a jcombobox
		testList(currentCourse.getList());
		testList(currentBook.getList());
		testList(currentProfessor.getList());
		testList(currentSection.getList());
		*/
		
		/*
		//	Gets the table data from all the selected PKs, that will go into the jtextfeilds
		testDbData("book", currentBook.getDbData("BOOK11111"));
		testDbData("course", currentCourse.getDbData("CET 1111"));
		testDbData("professor", currentProfessor.getDbData("PRO3333"));
		testDbData("section", currentSection.getDbData("S1111"));
		*/

		/*
		//	Tests validation of data in each table.  Results should be in order "exists", "update", "new".
		System.out.println(currentCourse.validateData("CET 1111",
				"FIRST COURSE"));
		System.out.println(currentCourse.validateData("CET 1111",
				"FIRST COURSE update"));
		System.out.println(currentCourse.validateData("CET 1111 new",
				"FIRST COURSE"));
		
		System.out.println(currentProfessor.validateData("PRO1111",
				"Firstfpro", "Firstlpro", "First Chair", "emailaddress", "somecampus"));
		System.out.println(currentProfessor.validateData("PRO1111",
				"Firstfpro update", "Firstlpro", "First Chair", "emailaddress", "somecampus"));
		System.out.println(currentProfessor.validateData("PRO1111 new",
				"Firstfpro", "Firstlpro", "First Chair", "emailaddress", "somecampus"));
		
		System.out.println(currentBook.validateData("BOOK11111", "First Book",
				9, "Firstfauthor", "Firstlauthor", "First Publisher",
				"04-01-2013", 111.11));
		System.out.println(currentBook.validateData("BOOK11111",
				"First Book update", 9, "Firstfauthor", "Firstlauthor",
				"First Publisher", "04-01-2013", 111.11));
		System.out.println(currentBook.validateData("BOOK11111 new",
				"First Book", 9, "Firstfauthor", "Firstlauthor",
				"First Publisher", "04-01-2013", 111.11));
		
		System.out.println(currentSection.validateData("S1111", "CET 1111",
				"PRO1111", "01-01-2010", "01-01-2011", "01:00", "SUMMER B"));
		System.out.println(currentSection.validateData("S1111", "CET 1111",
				"PRO1111 update", "01-01-2010", "01-01-2011", "01:00", "SUMMER B"));
		System.out.println(currentSection.validateData("S1111 new", "CET 1111",
				"PRO1111", "01-01-2010", "01-01-2011", "01:00", "SUMMER B"));
		
		System.out.println(currentSectionBook.validateData(1111111, "BOOK11111",
				"CET 1111", "S1111", 11, "REQUIRED"));
		System.out.println(currentSectionBook.validateData(1111111, "BOOK11111",
				"CET 1111 new", "S1111", 11, "REQUIRED"));
		System.out.println(currentSectionBook.validateData(1234567, "BOOK11111 new",
				"CET 1111", "S1111", 11, "REQUIRED"));
		*/

		
		//	Adds and row then updates that row in each table
		System.out.println(currentCourse.newRow("CIT 1111", "IT Course"));
		System.out.println(currentCourse.updateRow("CIT 1111",
				"IT Course update"));
		System.out.println(currentBook.newRow("ADDED123", "Added Book", 8,
				"AddedFName", "AddedLName", "Added Pub", "12-12-2012", 999.99));
		System.out.println(currentBook.updateRow("ADDED123",
				"Added Book update", 8, "AddedFName", "AddedLName",
				"Added Pub", "12-12-2012", 999.99));
		System.out.println(currentProfessor.newRow("Prof999", "firstname",
				"lastname", "program chair", "someaddress", "somewhere"));
		System.out.println(currentProfessor.updateRow("Prof999",
				"first update", "lastname", "program chair", "someaddress", "somewhere"));
		System.out.println(currentSection.newRow("S9999", "CET 2222",
				"PRO2222", "12-12-2012", "12-12-2013", "01:00:00", "FALL"));
		System.out.println(currentSection.updateRow("S9999", "CET 2222",
				"PRO2222", "09-09-1999", "09-09-1999", "01:00:00", "FALL"));
		System.out.println(currentSectionBook.newRow(1234567, "BOOK33333", "CET 1111",
				"S1111", 77, "Optional"));
		System.out.println(currentSectionBook.updateRow(1234567, "BOOK33333",
				"CET 1111", "S1111", 77, "Recommended"));
		

	}

	public static void testList(ResultSet rs) {
		try {
			while (rs.next()) System.out.println(rs.getString(1));
		} catch (SQLException e) {
			LOGGER.warning("testList method in nonGui failed");
		}
	}

	public static void testDbData(String table, ResultSet rs) {
		try {
			while (rs.next()) {
				if (table == "book")
					System.out.println(rs.getString(2) + " " + rs.getInt(3)
							+ " " + rs.getString(4) + " " + rs.getString(5)
							+ " " + rs.getString(6) + " " + rs.getString(7) + " "
							+ rs.getDouble(8));
				if (table == "course")
					System.out.println(rs.getString(2));
				if (table == "professor")
					System.out.println(rs.getString(2) + " " + rs.getString(3)
							+ " " + rs.getString(4) + " " + rs.getString(5) 
							+ " " + rs.getString(6));
				if (table == "section")
					System.out.println(rs.getString(4) + " " + rs.getString(5)
							+ " " + rs.getString(6) + " " + rs.getString(7));
			}
		} catch (SQLException e) {
			LOGGER.warning("testList method in nonGui failed");
		}
	}
}
