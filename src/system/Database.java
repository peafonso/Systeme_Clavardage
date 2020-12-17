package system;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	    /**
	     * Connect to a sample database
	     *
	     * @param fileName the database file name
	     */
	    public static void createNewDatabase(String fileName) {
	    	try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;

	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        createNewDatabase("test.db");
	    }
}

