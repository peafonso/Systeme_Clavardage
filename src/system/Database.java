package system;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
	
	/**
     * Create a new table in the test database
     *
     */
    public static void createTables() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";
        
        // SQL statement for creating a new table of contatcs
        String sqlcontacts = "CREATE TABLE IF NOT EXISTS contacts (\n"
                + "	user User PRIMARY KEY,\n"
                + ");";
        
        String sqlconversation = "CREATE TABLE IF NOT EXISTS conversations (\n"
        		+ "	usera User PRIMARY KEY,\n"
        		+ " userb User NOT NULL,\n"
        		+ ");";
        
        String sqlmessages = "CREATE TABLE IF NOT EXISTS messsages (\n"
        		+ "	text Message PRIMARY KEY,\n"
        		+ ");";
        
                
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create new tables
            stmt.execute(sqlcontacts);
            stmt.execute(sqlconversation);
            stmt.execute(sqlmessages);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
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
	     * Connect to the test.db database
	     * @return the Connection object
	     */
	 	private Connection connect() {
	        // SQLite connection string
	        String url = "jdbc:sqlite:C://sqlite/db/test.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	 	}
	 	
	 	/**
	     * Insert a new row into the warehouses table
	     *
	     * @param name
	     * @param capacity
	     */
	    public void insert(String name, double capacity) {
	        String sql = "INSERT INTO contacts(user) VALUES(?,?)";

	        try (Connection conn = this.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, name);
	            pstmt.setDouble(2, capacity);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }


	 
	 /**
	  * @param args the command line arguments
	 */
	 public static void main(String[] args) {
		 createNewDatabase("test.db");
		 createTables();
		 insert();
	 }
}

