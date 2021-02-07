package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

import control.Application;
import model.InteractiveChatSystem;
import model.User;

/**
 * Classe UDPRunner pour être à l'écoute lors des demandes de connexions et changements pseudos 
 * cas : integer permettant de savoir quel problème gérer entre la connexion et le changement de pseudo 
 * ouvert : booléen permettant de gérer la fermeture du socket
 * disponible : booléen permettant de savoir si le pseudo est disponible (unique) dans le système
 * app : Application associée
 * 
 */

public class UDPRunner extends Thread {
	
	private int cas; //1-> connexion 2 -> changement pseudo
	private static DatagramSocket serverSocket;
	private DatagramPacket receivePacket;
	private boolean disponible;
	private boolean ouvert;
	private Application app;

	/**
	 * Constructeur UDPRunner
	 * (permettant l'ouverture du socket)
	 */
	public UDPRunner(Application app) {
		setOuvert(true);
		setApp(app);
		setDisponible(true);
	}
	
	/**
	 * Réception de broadcast UDP correspondant à la connexion et changement de pseudos
	 *   
	 */
	public void run() {  
		int serverPort=4445;
		try {
	        serverSocket = new DatagramSocket(serverPort);
	        String sentence="";
	        byte[] array = new byte[100000000];

	        System.out.printf("Listening on udp:%s:%d%n", UDPListener.getCurrentIp().getHostAddress(), serverPort);
        	if (getCas()==1) {
	        	while (ouvert) {
	        		try {	
	        	        serverSocket.setSoTimeout(2000);
	        			receivePacket = new DatagramPacket(array, array.length);
	        			serverSocket.receive(receivePacket);
	        			sentence = new String( receivePacket.getData(), 0, receivePacket.getLength() );
	        			System.out.println("On a reçu: "+ sentence);
	        			User usertoadd= User.toUser(sentence);
	        			String[] parametersuser=sentence.split("_");
	        			String validate= parametersuser[0];
	        			//Si réponse négative then renvoi faux
	        			if (validate.equals("notOk")) {
	        				System.out.println("pseudo Not ok");
	        				setDisponible(false);
	        			}else {
	        				//Si réponse positive then renvoi vrai
	        				System.out.println("pseudo ok");
	        				setDisponible(true);
	        				if(usertoadd.getIP().equals(getApp().getMe().getIP())) {
	        					//nothing to do
	        				}
	        				else if(!(usertoadd.getIP().equals("IP"))) {
	        					//si on est le 1er du réseau on ajoute personne 
	        					System.out.println("on ajoute "+usertoadd);
	        					getApp().getFriends().addContact(usertoadd);
	        					getApp().getDb().createTableConvo(usertoadd.getIP()); //on ajoute dans la bd

	        				}
	        			}

	        		}
	        		catch(SocketTimeoutException e){
	        			sentence="ok_pseudo_IP_4445";
        				setDisponible(true);
	        			System.out.println("cas2");
	    	        	serverSocket.close();
	    	        	setOuvert(false);
	        		}
	        		catch(Exception e) {
	        			System.out.println("cas3");
	        			e.printStackTrace();
	    	        	serverSocket.close();
	    	        	setOuvert(false);
	        		}

	        	}
	        	serverSocket.close();
	        }
	        else if (getCas()==2) {
	        	while (ouvert) {
	        		try {			
	        			System.out.println("go tester");
	        			receivePacket = new DatagramPacket(array, array.length);
	        			serverSocket.receive(receivePacket);
	        			sentence = new String( receivePacket.getData(), 0, receivePacket.getLength() );
	        			System.out.println("On a reçu: "+ sentence);
	        			User usertoadd= User.toUser(sentence);
	        			String[] parametersuser=sentence.split("_");
	        			String validate= parametersuser[0];
	        			//Si réponse négative then renvoi faux
	        			if (validate.equals("notOk")) {
	        				System.out.println("pseudo Not ok");
	        				setDisponible(false);
		    	        	setOuvert(false);
	        			}else {
	        				//Si réponse positive then renvoi vrai
	        				System.out.println("pseudo ok");
	        				setDisponible(true);
	        			}
	        		}
	        		catch (SocketTimeoutException e ) {
	    	        	serverSocket.close();
        				setDisponible(true);
	    	        	setOuvert(false);

	        		}
	        		catch(Exception e) {
	        			e.printStackTrace();
	    	        	serverSocket.close();
	    	        	setOuvert(false);
	        		}
	        	}
	        	serverSocket.close();

	        }
	        else if (getCas()==3) {
	        	while (ouvert) {			        
			        System.out.printf("Listening on udp:%s:%d%n", UDPListener.getCurrentIp().getHostAddress(), 4445);     
			        receivePacket = new DatagramPacket(array, array.length);
			        serverSocket.receive(receivePacket);
			        String response = new String( receivePacket.getData(), 0, receivePacket.getLength() );
			        System.out.println("on va dans receptionmsg\n");
			        InteractiveChatSystem.ReceptionMsg(response);
			        
				}
	        }

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("im here");
		}
	}
	
	//-------------------- GETTEURS & SETTEURS -----------------------------//
	
	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public void closeSocket() {
		serverSocket.close();
	}
	
	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public boolean isOuvert() {
		return ouvert;
	}

	public void setOuvert(boolean ouvert) {
		this.ouvert = ouvert;
	}

	public int getCas() {
		return cas;
	}

	public void setCas(int cas) {
		this.cas = cas;
	}
}
