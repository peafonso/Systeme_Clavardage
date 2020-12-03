package system;
import java.util.Random;
import java.util.Iterator;

//rajouter un mot de passe pour les connexions et prot�ger les pseudos
enum State {CONNECTED, CONNECTING, DISCONNECTED };

public class User {
	
	private String iP;
	private String pseudo;
	private State state;
	private int port;



	public User(String address, int port, String pseudonym) {
		
		//address
		this.setIP(address);
		
		//port
		this.setPort(port);
		
		//Pseudonyme (on v�rifie l'unicit� d�s la cr�ation du User)
		while (!(Change_pseudo(pseudonym))) {
			pseudonym=Pseudo_Random(6);
		}
		
		//Etat de l'utilisateur (connect� car construction)
		this.setState(State.CONNECTED);
		
		addContact();
		
	}
	
	// On ajoute le user dans la liste contacts d�s qu'il s'instancie
	public void addContact() {
		//KIK�LA
		Contacts.contacts.add(this);
	}
	 
	
	public void Connexion() {
		this.setState(State.CONNECTED);
		
	}
	
	public void Deconnexion() {
		this.setState(State.DISCONNECTED);
	}
	
	public boolean Change_pseudo(String pseudonym) {
		
		//it�rateur afin de v�rifier si quelqu'un a le m�me pseudo 
		Iterator<User> iter = Contacts.contacts.iterator();
		boolean stop=true;
		
		while(iter.hasNext() && stop) {
			User user=iter.next();
			if (pseudonym.equals(user.getPseudo())) {
				System.out.println("Pseudo already used, choose another one (we the best music DJ KHALEED)");
				stop=false;
			}
		}
		
		if (stop) {
			//System.out.println("Pseudo OK");
			this.setPseudo(pseudonym);
		}
		
		return stop;
	}
	
	public String Pseudo_Random(int length) {
	    // create an object of Random class
	    Random random = new Random();
	    // create random string builder
	    StringBuilder sb = new StringBuilder();
	    
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    for (int i=0;i<length;i++) {
	    	int index=random.nextInt(alphabet.length());
	    	char charandom= alphabet.charAt(index);
	    	sb.append(charandom);
	    }
	    String pseudo= sb.toString();
	    System.out.println("Random String is: " + pseudo);
	    return pseudo;
	}
	
	
	//SETTEUR & GETTEUR

	public String getIP() {
		return iP;
	}

	public void setIP(String iP) {
		this.iP = iP;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public State isConnected() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int p) {
		this.port = p;
	}

}
