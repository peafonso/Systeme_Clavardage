package system;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	// SQLite connection string
    String url = "jdbc:sqlite:C://sqlite/db/test.db";
    
	//creation d'une table pour chaque conversation du nom de la personne à qui on parle
	//on met dedans tous les messages pour l'historique
    
    /**
	* Connect to a sample database
	*
	* @param fileName the database file name
	*/
	 public void createNewDatabase() {
	    	try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
	    	
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
     * Create a new table conversation ip2
     *
     */
	public void createTableConvo(String ip2) {
        String sqlconvo= "CREATE TABLE IF NOT EXISTS `" +getNomTable(ip2)+"`(\n"
        		+ "	id integer PRIMARY KEY,\n"
                + "	time text NOT NULL \n"
        		+ " message text NOT NULL"
                + ");"; 
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
        		stmt.execute(sqlconvo);
        } catch (SQLException e) {
                  System.out.println(e.getMessage());
        }
	}
	
	/**
     * Recupérer historique par rapport à l'ip de notre correspondant
     *
     */
	public ArrayList<Message> recupHistory(String ip2) {
        ArrayList<Message> historique = new ArrayList<Message>();
		String nomtable= getNomTable(ip2);
		String sql = "SELECT id, time, message FROM `"+nomtable+"`";
	        
	    try (Connection conn = this.connect();
			 Statement stmt  = conn.createStatement();
	         ResultSet rs    = stmt.executeQuery(sql)){
	    	System.out.println("\nTABLE MESSAGES (id, time, message)\n");

	    	// loop through the result set
	    	while (rs.next()) {
	    		//Message msg= Message()
	    		//historique.add(e);
	    		System.out.println(rs.getInt("textid") +  "\t" + 
	    				rs.getString("message") + "\t" +
	    				rs.getFloat("time"));
	         	}
	        } catch (SQLException e) {
	            System.out.println("error at selectMessages\n");
	            System.out.println(e.getMessage());
	        }
		return null;
	}
	
	/**
     * Ajouter des messages à l'historique 
     *
     */
	public void addMessage(String ip2, Message msg) {
		String nomtable= getNomTable(ip2);
		String sql = "INSERT INTO `"+nomtable+"`(id,time,msg) VALUES(?,?,?)";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, msg.getTimeString());
	        pstmt.setString(2, msg.getData());
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	    	System.out.println("error at addMessage\n");
	    	System.out.println(e.getMessage());
	    }
	}
	
	/**
     * Récupérer le nom de la table correspondant à la conversation choisie
     *
     */
	public String getNomTable(String ip2) {
		return "Chatwith_"+ip2;
	}
	
	/**
     * On vérifie que la table n'existe pas
     *
     */
	public boolean getNomTable( ) {
		//TODO
		return true;
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
}

