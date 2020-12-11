package system;
import java.util.ArrayList;


public class User {
	
	private String iP;
	private String pseudo;
	private int port;
	//liste des personnes connectés
	private ArrayList<User> contacts= new ArrayList<User>();
 

	public User(String address, int port, String pseudonym) {
		this.setIP(address);
		this.setPort(port);
		this.setPseudo(pseudo);
	}
	
	public void addContact(User e) {
		this.contacts.add(e);
	}
	

	//Methode affichant tous les contacts de la liste
	public void showContacts() {
		System.out.println("Contacts list:");
		for (User user : this.contacts) {
			System.out.println(user.getPseudo());
		}
	}
	
	//teste l'égalité de deux users par rapport à leur pseudo
	public boolean equals (User user) {
		boolean bool= false;
		if (this.pseudo.equals(user.pseudo)) {
			bool=true;
		}
		return bool; 
	}
	
	public String toString() {
		return "_"+this.pseudo+"_"+this.iP+"_"+String.valueOf(this.port);
	}
	
	public User toUser(String s) {
		String[] parametersuser=s.split("_");
		//String validate= parametersuser[0];
		String userpseudo = parametersuser[1];
		String userip = parametersuser[2];
		String userport = parametersuser[3];
		User people= new User(userip, Integer.parseInt(userport), userpseudo);
		return people;
	}
	
	//SETTEUR & GETTEUR

	public String getIP() {
		return iP;
	}

	public void setIP(String address) {
		this.iP = address;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	
	public int getPort() {
		return port;
	}

	public void setPort(int p) {
		this.port = p;
	}

}
