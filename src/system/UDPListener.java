package system;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

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
	        serverSocket.setSoTimeout(10000);
	        String sentence="";
	        //1 char= 1 octet
	       	//Max message 100*10^6 octets
	        byte[] array = new byte[100000000];

	        System.out.printf("Listening on udp:%s:%d%n",
	        		InetAddress.getLocalHost().getHostAddress(), serverPort);
	        while (true) {
	        	try {
			        receivePacket = new DatagramPacket(array, array.length);
			        
			        serverSocket.receive(receivePacket);
			        sentence = new String( receivePacket.getData(), 0, receivePacket.getLength() );
			        return sentence;
	        	}catch(SocketTimeoutException e){
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
	

	
	
	public void listenUDP(int port) {
		try {
	        serverSocket = new DatagramSocket(port);
			while (true) {
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
	
	public void run() {
		try {
	        serverSocket = new DatagramSocket(4445);
			while (true) {
		        //1 char= 1 octet
		       	//Max message 100*10^6 octets
		        byte[] array = new byte[100000000];
		        
		        System.out.printf("Listening on udp:%s:%d%n", InetAddress.getLocalHost().getHostAddress(), 4445);     
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
