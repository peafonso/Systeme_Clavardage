package system;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	/**
     * Create a new table in the test database
     *
     */
    public static void createTables() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        
        // SQL statement for creating a new tables
        String sqlcontacts = "CREATE TABLE IF NOT EXISTS contacts (\n"
        		+ "	contactid integer PRIMARY KEY,\n"
                + "	ip text NOT NULL UNIQUE\n"
                + ");";
        
        String sqlconversation = "CREATE TABLE IF NOT EXISTS conversations (\n"
        		+ "	convoid integer PRIMARY KEY,\n"
        		+ "	ip1 text NOT NULL,\n"
        		+ " ip2 text NOT NULL UNIQUE\n"
        		+ ");";
        
        String sqlmessages = "CREATE TABLE IF NOT EXISTS messages (\n"
        		+ "	textid integer PRIMARY KEY,\n"
        		+ "	message text NOT NULL,\n"
        		+ "	time real NOT NULL\n"
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
	     * Insert a new row into the contacts table
	     *
	     * @param adress
	     */
	    public void insertcontact(String ip) {
	        String sql = "INSERT INTO contacts(ip) VALUES(?)";

	        try (Connection conn = this.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, ip);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("error at insertcontact\n");
	            System.out.println(e.getMessage());
	        }
	    }

	    /**
	     * Insert a new row into the conversation table
	     *
	     * @param ip1
	     * @param ip2
	     * 
	     */
	    public void insertconvo(String ip1,String ip2) {
	        String sql = "INSERT INTO conversations(ip1,ip2) VALUES(?,?)";

	        try (Connection conn = this.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, ip1);
	            pstmt.setString(2, ip2);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("error at insertconvo\n");
	            System.out.println(e.getMessage());
	        }
	    }
	 
	    /**
	     * Insert a new row into the messages table
	     *
	     * @param msg
	     * @param time
	     * 
	     */
	    public void inserttext(String msg,int time) {
	        String sql = "INSERT INTO messages(msg,time) VALUES(?,?)";

	        try (Connection conn = this.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, msg);
	            pstmt.setLong(2, time);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("error at inserttext\n");
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    /**
	     * Delete a warehouse specified by the id
	     *
	     * @param id
	     */
	    public void deleteConversations(int id) {
	        String sql = "DELETE FROM conversations WHERE convoid = ?";

	        try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            // set the corresponding param
	            pstmt.setInt(1, id);
	            // execute the delete statement
	            pstmt.executeUpdate();

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    /**
	     * select all rows in the contacts table
	     */
	    public void selectContacts(){
	        String sql = "SELECT contactid, ip FROM contacts";
	        
	        try (Connection conn = this.connect();
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
                System.out.println("\nTABLE CONTACTS (contactid, ip)");

	            // loop through the result set
	            while (rs.next()) {
	                System.out.println(rs.getInt("contactid") +  "\t" + 
	                                   rs.getString("ip"));
	            }
	        } catch (SQLException e) {
	            System.out.println("error at selectContacts\n");
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    /**
	     * select all rows in the conversations table
	     */
	    public void selectConversations(){
	        String sql = "SELECT convoid, ip1, ip2 FROM conversations";
	        
	        try (Connection conn = this.connect();
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
                System.out.println("\nTABLE CONVERSATIONS (convoid, ip1, ip2)");
                
	            // loop through the result set
	            while (rs.next()) {
	                System.out.println(rs.getInt("convoid") +  "\t" + 
	                                   rs.getString("ip1") + "\t" +
	                                   rs.getString("ip2"));
	            }
	        } catch (SQLException e) {
	            System.out.println("error at selectConversations\n");
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    /**
	     * select all rows in the conversations table
	     */
	    public boolean selectOneConversation(String u1){
	    	boolean thereis= false;
	        String sql = "SELECT convoid, ip1, ip2 FROM conversations WHERE ip2=?";
	        
	        try (Connection conn = this.connect();
	                PreparedStatement pstmt  = conn.prepareStatement(sql)){
                // set the value
                pstmt.setString(1,u1);
                ResultSet rs  = pstmt.executeQuery();
	            while (rs.next()) {
	                System.out.println(rs.getInt("convoid") +  "\t" + 
	                                   rs.getString("ip1") + "\t" +
	                                   rs.getString("ip2"));
	                thereis=true;
	            }
	        } catch (SQLException e) {
	            System.out.println("error at selectOneConversation\n");
	            System.out.println(e.getMessage());
	        }
			return thereis;
	    }
	    
	    /**
	     * select all rows in the messages table
	     */
	    public void selectMessages(){
	        String sql = "SELECT textid, message, time FROM messages";
	        
	        try (Connection conn = this.connect();
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
                System.out.println("\nTABLE MESSAGES (textid, message, time)\n");

	            // loop through the result set
	            while (rs.next()) {
	                System.out.println(rs.getInt("textid") +  "\t" + 
	                                   rs.getString("message") + "\t" +
	                                   rs.getFloat("time"));
	            }
	        } catch (SQLException e) {
	            System.out.println("error at selectMessages\n");
	            System.out.println(e.getMessage());
	        }
	    }
	    
	 /**
	  * @param args the command line arguments
	 */
	 public static void main(String[] args) {
		 Database app= new Database();
		 
		 createNewDatabase("test.db");
		 createTables();
		 app.insertcontact("127.0.0.1");
		 app.selectContacts();
		 app.selectConversations();
		 app.selectMessages();
		 System.out.println(app.selectOneConversation("127.0.0.1"));

	 }
}

