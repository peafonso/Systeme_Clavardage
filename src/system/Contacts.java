package system;

import java.util.ArrayList;

//Base de données des ptits gens
public class Contacts extends ArrayList<User>{
	
	private static final long serialVersionUID = 1L;
	static ArrayList<User> contacts = new ArrayList<User>();
	
	//Constructeur
	public Contacts() {
		super();
	}
	
	//Methode affichant tous les contact de la liste
	public static void showContacts() {
		System.out.println("Contacts list:");
		for (User user : contacts) {
			System.out.println(user.getPseudo());
		}
	}

}
