package control;

import model.Contacts;
import model.User;
import system.Database;
import system.InteractiveChatSystem;

/**
 *  Classe de l'application, rassemblant l'instance des arguments n�cessaires � l'utilisation 
 *  
 *  me : instance de la classe User repr�sentant le user connect� sur le syt�me
 *  friend : instance de la classe Contacts repr�sentant la liste des contacts associ� � l'user connect�
 *  cSystem : instance de la classe InteractiveChatSystem permettant � l'user de g�rer connection,
 *           d�connexion et changement de pseudo
 *  db : instance de la classe Database repr�sentant la base de donn�es de l'user (permettant la gestion
 *       de l'historique des conversations)
 *  
 */

public class Application {
	
	private User me;
	private Contacts friends;
	private InteractiveChatSystem cSystem;
	private Database db;

	/**
	 * Constructeur de l'application 
	 * @param u1 utilisateur de l'application
	 * */
	public Application(User u1) {
		this.setMe(u1);
		setFriends(new Contacts());
	}
	

	//-------------------- GETTEURS & SETTEURS -----------------------------//
	
	public User getMe() {
		return me;
	}

	public void setMe(User me) {
		this.me = me;
	}

	public Contacts getFriends() {
		return friends;
	}

	public void setFriends(Contacts friends) {
		this.friends = friends;
	}

	public InteractiveChatSystem getcSystem() {
		return cSystem;
	}

	public void setcSystem(InteractiveChatSystem cSystem) {
		this.cSystem = cSystem;
	}

	public Database getDb() {
		return db;
	}

	public void setDb(Database db) {
		this.db = db;
	}


}
