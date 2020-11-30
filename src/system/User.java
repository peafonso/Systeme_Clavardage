package system;

import java.util.ArrayList;


enum State {CONNECTED, CONNECTING, DISCONNECTED };

public class User {
	
	private int id;
	private String pseudo;
	private State state;



	public User(int identifier, String pseudonym) {
		
		//identifiant
		this.setId(identifier);
		
		//Pseudonyme
		Change_pseudo(pseudonym);
		
		//Etat de l'utilisateur (connect� car construction)
		this.setState(State.CONNECTED);
		
		
	}
	
	public void Initilisation() {
		//KIK�LA
		Contacts.Add(this);
	}
	 
	
	public void Connexion() {
		this.setState(State.CONNECTED);
		
	}
	
	public void Deconnexion() {
		this.setState(State.DISCONNECTED);
	}
	
	public void Change_pseudo(String pseudonym) {
		
		//it�rateur afin de v�rifier si quelqu'un a le m�me
		Iterator<User> iter = 
		
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
