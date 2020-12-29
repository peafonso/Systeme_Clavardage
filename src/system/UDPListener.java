package system;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPListener extends Thread{
	
	private DatagramSocket serverSocket;
	private DatagramPacket receivePacket;

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
	        return sentence;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Erreur_reception";
		}
	}

}
