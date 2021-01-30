package system;

import system.Message;
import java.sql.Statement;
import java.util.ArrayList;

import control.Application;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	private static Application app;
	// SQLite connection string
    String url = "jdbc:sqlite:C://sqlite/db/test.db";
    
	//creation d'une table pour chaque conversation du nom de la personne à qui on parle
	//on met dedans tous les messages pour l'historique
    
    public Database(Application app) {
    	this.setApp(app);
    	createNewDatabase(); //on crée la base de données
    	
    }
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
	  * Connect to the test.db database
	  * @return the Connection object
	  *  */
	 private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
 	}	 
	 
	 
	/** 
     * Create a new table conversation ip2 (nom Chatwith_IP2, id num du message 
     * 1 était le plus ancien, time horodatage du message, message -> texte envoyé) 
     * 
     * Si on lance createTableConvo avec un correspondant avec lequel on a déjà conversé on 
     * ne recrée pas une nouvelle table (IF NOT EXISTS)
     *
     */
	public void createTableConvo(String ip2) {
        String sqlconvo= "CREATE TABLE IF NOT EXISTS `" +getNomTable(ip2)+"`(\n"
        		+ "	id integer PRIMARY KEY,\n"
                + "	time text NOT NULL, \n"
        		+ " message text NOT NULL, \n"
                + " sender integer NOT NULL"
                + ");"; 
        
        try (Connection conn = DriverManager.getConnection(url);
        		Statement stmt = conn.createStatement()) {
        		stmt.execute(sqlconvo);
        } catch (SQLException e) {
         	System.out.println("erreur at createTableConvo\n");
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
		String sql = "SELECT id, time, message, sender FROM `"+nomtable+"`";
	        
	    try (Connection conn = this.connect();
			 Statement stmt  = conn.createStatement();
	         ResultSet rs    = stmt.executeQuery(sql)){

	    	// loop through the result set
	    	while (rs.next()) {
	    		Message msg= new Message();
	    		msg.setData(rs.getString("message"));
	    		msg.setTimeString(rs.getString("time"));
	    		if (rs.getInt("sender")==0) {
	    			System.out.println("sender it's me");
	    			msg.setSender(getApp().getMe());
		    		msg.setReceiver(getApp().getFriends().getUserfromIP(ip2));
	    		}
	    		else {
	    			System.out.println("sender it's NOOT me");

	    			msg.setSender(getApp().getFriends().getUserfromIP(ip2));
		    		msg.setReceiver(getApp().getMe());
	    		}
	    		historique.add(msg);
	         	}
	        } catch (SQLException e) {
	            System.out.println("error at recupHistory\n");
	            System.out.println(e.getMessage());
	        }
		return historique;
	}


	/**
     * Ajouter des messages à l'historique 
     *
     */
	public void addMessage(String ip2, Message msg) {
		String nomtable= getNomTable(ip2);
		String sql = "INSERT INTO `"+nomtable+"`(id,time,message,sender) VALUES(?,?,?,?)";

		try (Connection conn =  this.connect() ; PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(2, msg.getTimeString());
	        pstmt.setString(3, msg.getData());
	        // 0 -> j'ai envoyé le message
	        if (msg.getSender().equals(getApp().getMe())) {
	        	pstmt.setInt(4, 0);
	        }
	        // 1 -> j'ai reçu le message
	        else {
	        	pstmt.setInt(4, 1);

	        }
	    	System.out.println("on ajoute le msg");

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
     * On suprime la conversation 
     *
     */
	public void deleteConvo(String ip2) {
		String nomtable= getNomTable(ip2);
		String sql = "DROP TABLE `"+nomtable+"`";
		System.out.println("DROP TABLE `"+nomtable+"`");
	        try (Connection conn = DriverManager.getConnection(url);
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.executeUpdate();

	        } catch (SQLException e) {
	            System.out.println("erreur deleteconvo");
	        }
	}

	    
	   
		public static Application getApp() {
			return app;
		}
		public void setApp(Application app) {
			Database.app = app;
		}
		
}

