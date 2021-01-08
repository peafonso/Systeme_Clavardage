package model;

import java.util.ArrayList;

//Base de donnees des ptits gens
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
	
	public void addContact(User e) {
		contacts.add(e);
	}
	
	public void deleteContact (User e) {
		contacts.remove(e);
	}
	
	public boolean appartient (String pseudo) {
		for (User user : contacts) {
			if (user.getPseudo().equals(pseudo)) {
				return true;
			}
		}
		return false;
	}
	
	//pour r�cup�rer un tableau de string des pseudos
	public String[] getListPseudo() {
		String[] liste = {};
		if (!(contacts.isEmpty())) {
			for (User user : contacts) {
				liste[liste.length]=user.getPseudo();
			}
		}
		return liste;
	}
	
	//Recuperer un utilisateur d'apres son pseudo
	public User getUserfromPseudo (String pseudo) {
		User toreturn = null;
		for (User user : contacts) {
			if (user.getPseudo().equals(pseudo)) {
				toreturn=user;
			}
		}
		return toreturn;
	}
	
	
	

}
