package system;

import java.util.ArrayList;


enum Etat {CONNECTED, CONNECTING, DISCONNECTED };

public class User {
	
	private int id;
	private String pseudo;
	private Etat state;


	public User(int identifier, String pseudonyme ,Etat etat) {
		
		//identifiant
		this.setId(identifier);
		
		//Pseudonyme
		this.setPseudo(pseudonyme);
		
		//Etat de l'utilisateur (connecté ou déconnecté)
		this.setState(etat);
		
		//Base de données des ptits potes
		ArrayList<User> listeUsers=new ArrayList<User>();

		
	}
	
	 
	
	public void Connexion(String pseudonyme) {
		this.pseudo=pseudonyme;
		this.setState(Etat.CONNECTED);
	}
	
	public void Deconnexion() {
	
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Etat getState() {
		return state;
	}

	public void setState(Etat state) {
		this.state = state;
	}

}
