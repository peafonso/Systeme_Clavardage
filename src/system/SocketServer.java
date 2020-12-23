package system;

import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class SocketServer extends Thread{
	
    private int serverPort ;
    
    public SocketServer(int port){
        this.serverPort=port;
    }
    
	public String Listen() {  
		try {
	        DatagramSocket serverSocket = new DatagramSocket(serverPort);
	        //1 char= 1 octet
	       	//Max message 100*10^6 octets
	        byte[] array = new byte[100000000];

	        System.out.printf("Listening on udp:%s:%d%n",
	                InetAddress.getLocalHost().getHostAddress(), serverPort);     
	        DatagramPacket receivePacket = new DatagramPacket(array,
	                           array.length);
	        
	        serverSocket.receive(receivePacket);
	        String sentence = new String( receivePacket.getData(), 0, receivePacket.getLength() );
	        serverSocket.close();
	        return sentence;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Erreur_reponse";
		}
	}

}

