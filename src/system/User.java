package system;
import java.util.Random;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;


public class User {
	
	private String iP;
	private String pseudo;
	private int port;
	private ArrayList<User> contacts= new ArrayList<User>();
 

	public User(String address, int port, String pseudonym) {
		//address
		this.setIP(address);
		//port
		this.setPort(port);
		this.pseudo=pseudonym;
	}
	
	public void init() {
		//Pseudonyme (on vérifie l'unicité dès la création du User)
		Change_Pseudo(pseudo);
        System.out.println("change pseudo fini");
	}
	
	// On ajoute le user dans la liste contacts dès qu'il s'instancie
	public void addContact() throws IOException {
		DatagramSocket socketRec = new DatagramSocket(this.port);
        DatagramSocket socketEnvoi = new DatagramSocket();

		byte[] array = new byte[100000000];
		DatagramPacket receivePacket = new DatagramPacket(array,array.length);
		//long start=System.nanoTime(); 
		 
		//while((System.nanoTime()-start)<10000)  {
			  socketRec.receive(receivePacket);
			  String otherpseudo = new String( receivePacket.getData(), 0, receivePacket.getLength());
			  socketRec.receive(receivePacket);
			  String port = new String( receivePacket.getData(), 0, receivePacket.getLength());
			  
			  if (otherpseudo.equals(this.pseudo)) {
				  //on prévient le user qui nous as envoyé son pseudo que l'unicité 
				  //n'est pas respecté étant donné qu'il vient de choisir notre pseudo
				  byte[] buffer = ("not ok").getBytes();
			      DatagramPacket response = new DatagramPacket(buffer, buffer.length,this.port);
			      socketEnvoi.send(response);
				  
			  }
			  else {
				  //on envoie son adresse IP pour qu'il nous ajoute dans sa liste
				  byte[] buffer = ((String) this.iP).getBytes();
			      DatagramPacket response = new DatagramPacket(buffer, buffer.length,this.port);
			      socketEnvoi.send(response);
			  }
			  
			  byte[] login = (this.pseudo).getBytes();
		      DatagramPacket pseudo = new DatagramPacket(login, login.length,this.port);
		      socketEnvoi.send(pseudo);
		      byte[] last = String.valueOf(this.port).getBytes();
		      DatagramPacket ourport = new DatagramPacket(last, last.length,this.port);
		      socketEnvoi.send(ourport);
		      
			  
			  User usertoadd= new User(receivePacket.getAddress().getHostAddress(), Integer.parseInt(port) ,otherpseudo);

				if(!(contacts.contains(usertoadd)))
            		contacts.add(usertoadd);
		  
		  socketEnvoi.close();
		  socketRec.close();
	}
	 
	
	public void Change_Pseudo(String pseudonym) {
		try {
			Network.broadcast(this.pseudo, InetAddress.getByName("255.255.255.255"));
			Scanner scanner= new Scanner(System.in);
			long start=System.nanoTime(); 
			 
			while((System.nanoTime()-start)<10000)  {
				
			DatagramSocket socketRec = new DatagramSocket(this.port);
            DatagramSocket socketEnvoi = new DatagramSocket();
			byte[] array = new byte[100000000];
			DatagramPacket receivePacket = new DatagramPacket(array,array.length);
			socketRec.receive(receivePacket);
			System.out.println("received");
            String response = new String( receivePacket.getData(), 0, receivePacket.getLength() );
            socketRec.receive(receivePacket);
            String pseudo = new String( receivePacket.getData(), 0, receivePacket.getLength() );
            socketRec.receive(receivePacket);
            String port = new String( receivePacket.getData(), 0, receivePacket.getLength() );
    		User usertoadd= new User(receivePacket.getAddress().getHostAddress(), Integer.parseInt(port), pseudo);
            if (response.equals("not ok")) {
            	//on change le pseudo car quelqu'un le possède déjà
                System.out.println("pseudo existant; choissisez en un autre");

            	String newpseudo= scanner.next();
            	this.pseudo=newpseudo;
            	Change_Pseudo(newpseudo);
            }
            else {
            	if(!(contacts.contains(usertoadd)))
            		contacts.add(usertoadd);
              byte[] login = (this.pseudo).getBytes();
  		      DatagramPacket ourpseudo = new DatagramPacket(login, login.length,this.port);
  		      socketEnvoi.send(ourpseudo);
  		      byte[] last = String.valueOf(this.port).getBytes();
  		      DatagramPacket ourport = new DatagramPacket(last, last.length,this.port);
  		      socketEnvoi.send(ourport);

            }
            socketRec.close();
            socketEnvoi.close();
            scanner.close();
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		 
	}

	//Methode affichant tous les contacts de la liste
	public void showContacts() {
		System.out.println("Contacts list:");
		for (User user : this.contacts) {
			System.out.println(user.getPseudo());
		}
	}
	
	
	public String toString() {
		return this.pseudo+this.iP+String.valueOf(this.port);
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
