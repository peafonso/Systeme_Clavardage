package system;
import java.util.Random;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

//rajouter un mot de passe pour les connexions et protéger les pseudos
enum State {CONNECTED, CONNECTING, DISCONNECTED };

public class User {
	
	private String iP;
	private String pseudo;
	private State state;
	private int port;
	static ArrayList<User> contacts = new ArrayList<User>();

	public User(String address) {
		this.setIP(address);
	}

	public User(String address, int port, String pseudonym) {
		
		//address
		this.setIP(address);
		//port
		this.setPort(port);
		this.pseudo=pseudonym;

		//Etat de l'utilisateur (connecté car construction)
		this.setState(State.CONNECTED);	
		
	}
	
	public void init() {
		//Pseudonyme (on vérifie l'unicité dès la création du User)
		Change_Pseudo(pseudo);
		
		try {
			addContact();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// On ajoute le user dans la liste contacts dès qu'il s'instancie
	public void addContact() throws IOException {
		DatagramSocket socketRec = new DatagramSocket(this.port);
        DatagramSocket socketEnvoi = new DatagramSocket();

		byte[] array = new byte[100000000];
		DatagramPacket receivePacket = new DatagramPacket(array,array.length);
		User usertoadd= new User(receivePacket.getAddress().getHostAddress());

		  while(true) {
			  socketRec.receive(receivePacket);
			  String otherpseudo = new String( receivePacket.getData(), 0, receivePacket.getLength());
			  if (otherpseudo.equals(this.pseudo)) {
				  //on prévient le user qui nous as envoyé son pseudo que l'unicité 
				  //n'est pas respecté étant donné qu'il vient de choisir notre pseudo
				  byte[] buffer = ("already used").getBytes();
			      DatagramPacket packet = new DatagramPacket(buffer, buffer.length,4445);
			      socketEnvoi.send(packet);
			  }
			  else {
				  //on envoie son adresse IP pour qu'il nous ajoute dans sa liste
				  byte[] buffer = (this.iP).getBytes();
			      DatagramPacket packet = new DatagramPacket(buffer, buffer.length,4445);
			      socketEnvoi.send(packet);
			  }
			  contacts.add(usertoadd);
		  }
	}
	 
	
	public void Connexion() {
		this.setState(State.CONNECTED);
		
	}
	
	public void Deconnexion() {
		this.setState(State.DISCONNECTED);
	}
	
	public void Change_Pseudo(String pseudonym) {
		try {
			Network.broadcast(this.pseudo, InetAddress.getByName("255.255.255.255"));
			Scanner scanner= new Scanner(System.in);
			DatagramSocket socketRec = new DatagramSocket(this.port);
			byte[] array = new byte[100000000];
			DatagramPacket receivePacket = new DatagramPacket(array,array.length);
			socketRec.receive(receivePacket);
            String response = new String( receivePacket.getData(), 0, receivePacket.getLength() );
            User usertoadd= new User(receivePacket.getAddress().getHostAddress());
            if (response.equals("already used")) {
            	//on change le pseudo car quelqu'un le possède déjà
            	String newpseudo= scanner.next();
            	this.pseudo=newpseudo;
            	Change_Pseudo(newpseudo);
            }
            else {
            	//if(!(contacts.contains()))
            		contacts.add(usertoadd);
            }
            socketRec.close();
            scanner.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		 
	}
	
	/*public boolean Change_pseudo(String pseudonym) {
		
		//itérateur afin de vérifier si quelqu'un a le même pseudo 
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
	}*/
	
	//Methode affichant tous les contact de la liste
	public static void showContacts() {
		System.out.println("Contacts list:");
		for (User user : contacts) {
			System.out.println(user.getPseudo());
		}
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
