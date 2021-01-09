package system;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Enumeration;

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
	        serverSocket.setSoTimeout(5000);
	        String sentence="";
	        //1 char= 1 octet
	       	//Max message 100*10^6 octets
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
	

	
	
	public void listenUDP(int port) {
		try {
	        serverSocket = new DatagramSocket(port);
			while (true) {
		        //1 char= 1 octet
		       	//Max message 100*10^6 octets
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
	
	//pour etre a l'ecoute des arrivees et departs sur le systeme continuellement port 4445
	public void run() {
		try {
	        serverSocket = new DatagramSocket(4445);
			while (true) {
		        //1 char= 1 octet
		       	//Max message 100*10^6 octets
		        byte[] array = new byte[100000000];
		        
		        System.out.printf("Listening on udp:%s:%d%n", UDPListener.getCurrentIp().getHostAddress(), 4445);     
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
        serverSocket.close();
	}
	
	
	//Recupere l'adresse IP de l'hote (si plusieurs disponibles, prend la première)
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
	
	public boolean isOuvert() {
		return ouvert;
	}

	public void setOuvert(boolean ouvert) {
		this.ouvert = ouvert;
	}

}
