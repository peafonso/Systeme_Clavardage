package system;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPListener extends Thread{
	
	private DatagramSocket serverSocket;
	private DatagramPacket receivePacket;
	private boolean ouvert;

	public UDPListener() {
		// TODO Auto-generated constructor stub
	}
	
	public String receiveUDP(int serverPort) {  
		try {
			
	        serverSocket = new DatagramSocket(serverPort);
	        //1 char= 1 octet
	       	//Max message 100*10^6 octets
	        byte[] array = new byte[100000000];

	        System.out.printf("Listening on udp:%s:%d%n",
	        		InetAddress.getLocalHost().getHostAddress(), serverPort);     
	        receivePacket = new DatagramPacket(array,
	                           array.length);
	        
	        serverSocket.receive(receivePacket);
	        String sentence = new String( receivePacket.getData(), 0, receivePacket.getLength() );
	        serverSocket.close();
	        while (sentence=="") {
	        	sentence=receiveUDP(serverPort);
	        }
	        return sentence;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Erreur_reception";
		}
	}
	
	
	public void listenUDP(int port) {
		try {
	        serverSocket = new DatagramSocket(port);
			while (ouvert) {
		        //1 char= 1 octet
		       	//Max message 100*10^6 octets
		        byte[] array = new byte[100000000];

		        System.out.printf("Listening on udp:%s:%d%n", InetAddress.getLocalHost().getHostAddress(), port);     
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
	

	public boolean isOuvert() {
		return ouvert;
	}

	public void setOuvert(boolean ouvert) {
		this.ouvert = ouvert;
	}

}
