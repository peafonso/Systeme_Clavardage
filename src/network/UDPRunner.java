package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

import control.Application;
import model.User;

public class UDPRunner extends Thread {
	
	private int cas; //1-> connexion 2 -> changement pseudo
	private static DatagramSocket serverSocket;
	private DatagramPacket receivePacket;
	private static boolean disponible;
	private static boolean ouvert;
	private Application app;

	/**
	 * Constructeur UDPListener
	 * (permettant l'ouverture du socket)
	 */
	public UDPRunner(Application app) {
		setOuvert(true);
		setApp(app);
		setDisponible(true);
	}
	
	/**
	 * Réception de broadcast UDP correspondant à la connexion
	 *   
	 */
	public void run() {  
		int serverPort=4445;
		try {
	        serverSocket = new DatagramSocket(serverPort);
	        serverSocket.setSoTimeout(2000);
	        String sentence="";
	        byte[] array = new byte[100000000];

	        System.out.printf("Listening on udp:%s:%d%n", UDPListener.getCurrentIp().getHostAddress(), serverPort);
        	if (getCas()==1) {
	        	while (ouvert) {
	        		try {			
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
	        		}

	        	}
	        	serverSocket.close();
	        }
	        else if (getCas()==2) {
	        	while (ouvert) {
	        		try {			
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
	        			}
	        		}
	        		catch (SocketTimeoutException e ) {
	        			//nothing to do
	        		}
	        	}
	        	serverSocket.close();

	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//-------------------- GETTEURS & SETTEURS -----------------------------//
	
	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public static void closeSocket() {
		serverSocket.close();
	}
	
	public static boolean isDisponible() {
		return disponible;
	}

	public static void setDisponible(boolean disponible) {
		UDPRunner.disponible = disponible;
	}
	
	public boolean isOuvert() {
		return ouvert;
	}

	public static void setOuvert(boolean ouvert) {
		UDPRunner.ouvert = ouvert;
	}

	public int getCas() {
		return cas;
	}

	public void setCas(int cas) {
		this.cas = cas;
	}
}
