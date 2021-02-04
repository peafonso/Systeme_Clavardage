package control;

import model.Contacts;
import model.User;
import system.Database;
import system.InteractiveChatSystem;

/**
 *  Classe de l'application, rassemblant l'instance des arguments nécessaires à l'utilisation 
 *  
 *  me : instance de la classe User représentant le user connecté sur le sytème
 *  friend : instance de la classe Contacts représentant la liste des contacts associé à l'user connecté
 *  cSystem : instance de la classe InteractiveChatSystem permettant à l'user de gérer connection,
 *           déconnexion et changement de pseudo
 *  db : instance de la classe Database représentant la base de données de l'user (permettant la gestion
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
