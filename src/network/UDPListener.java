package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Enumeration;

import model.InteractiveChatSystem;

/**
 * Classe UDPListener pour être à l'écoute des envois udp 
 * ouvert : booléen permettant de gérer la fermeture du socket
 *
 */

public class UDPListener extends Thread{
	
	private DatagramSocket serverSocket;
	private DatagramPacket receivePacket;
	private static boolean ouvert;

	/**
	 * Constructeur UDPListener
	 * (permettant l'ouverture du socket)
	 */
	public UDPListener() {
		setOuvert(true);
	}
	
	/**
	 * Réception de broadcast UDP correspondant à la connexion
	 *  
	 * @param serverPort port utilisé pour l'écoute
	 * @return réponse fictive "ok_pseudo_IP_4445" si on est le premier à se connecter sur 
	 *  système ou réponse réelles des autres users 
	 */
	public String receiveUDP(int serverPort) {  
		try {
	        serverSocket = new DatagramSocket(serverPort);
	        serverSocket.setSoTimeout(2000);
	        String sentence="";
	        byte[] array = new byte[100000000];

	        System.out.printf("Listening on udp:%s:%d%n",
	        		getCurrentIp().getHostAddress(), serverPort);
	        while (true) {
	        	try {
			        receivePacket = new DatagramPacket(array, array.length);
			        
			        serverSocket.receive(receivePacket);
			        sentence = new String( receivePacket.getData(), 0, receivePacket.getLength() );
	    	        serverSocket.close();
			        return sentence;
	        	}
	        	catch(SocketTimeoutException e){
	        		sentence="ok_pseudo_IP_4445";
	    	        serverSocket.close();
	    	        return sentence;
	        	}

	        }
	        		}
		catch (Exception e) {
			e.printStackTrace();
			return "ok_pseudo_IP_port";
		}
	}
	

	
	/**
	 * Ecoute UDP 
	 * @param port Port utilisé pour l'écoute
	 */
	public void listenUDP(int port) {
		try {
	        serverSocket = new DatagramSocket(port);
			while (true) {
		        byte[] array = new byte[100000000];
		        
		        System.out.printf("Listening on udp:%s:%d%n", getCurrentIp().getHostAddress(), port);     
		        receivePacket = new DatagramPacket(array, array.length);
		        serverSocket.receive(receivePacket);
		        String sentence = new String( receivePacket.getData(), 0, receivePacket.getLength() );
		        InteractiveChatSystem.ReceptionMsg(sentence);
		        
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
        serverSocket.close();
	}
	
	/**
	 * Méthode run permettant d'être a l'ecoute des arrivees et departs sur le
	 *  systeme continuellement sur le port 4445
	 */
	public void run() {
		try {
	        serverSocket = new DatagramSocket(4445);
			while (ouvert) {
		        byte[] array = new byte[100000000];
		        
		        System.out.printf("Listening on udp:%s:%d%n", getCurrentIp().getHostAddress(), 4445);     
		        receivePacket = new DatagramPacket(array, array.length);
		        serverSocket.receive(receivePacket);
		        String sentence = new String( receivePacket.getData(), 0, receivePacket.getLength() );
		        System.out.println("on va dans receptionmsg\n");
		        InteractiveChatSystem.ReceptionMsg(sentence);
		        
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Recuperation de l'adresse IP de l'hote 
	 * @return l'adresse ip correspondante 
	 */
	 public static InetAddress getCurrentIp() { 
		 try { 
			 Enumeration networkInterfaces = NetworkInterface .getNetworkInterfaces();
			 while (networkInterfaces.hasMoreElements()) { 
				 NetworkInterface ni = (NetworkInterface) networkInterfaces .nextElement(); 
				 Enumeration nias = ni.getInetAddresses(); 
				 while(nias.hasMoreElements()) { 
					 InetAddress ia= (InetAddress) nias.nextElement();
					 if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) { 
						 return ia; 
					 } 
				 } 
			 } 
		 } 
		 catch (SocketException e) {
			 System.out.println("unable to get current IP " + e.getMessage());
		 } 
	return null;
	} 
	 

	 //-------------------- GETTEURS & SETTEURS -----------------------------//

	public boolean isOuvert() {
		return ouvert;
	}

	public static void setOuvert(boolean ouvert) {
			UDPListener.ouvert = ouvert;
	}

}
