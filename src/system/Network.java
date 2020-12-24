package system;
import java.net.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;

public class Network {
	
	//premiere connexion envoi d'un broadcast aux numeros de ports 

	    public static void main(String[] args) throws IOException {
	    	System.out.println("Envoi Hello");
	        broadcast("Hello", InetAddress.getByName("255.255.255.255"));
	        SocketServer sok = new SocketServer(4445);
			//tant qu'on appelle pas envoyer message on attend de recevoir quelque chose
			System.out.println(sok.Listen());
	    }

	    public static void broadcast(String broadcastMessage, InetAddress address) throws IOException {
	    	DatagramSocket socket = new DatagramSocket();
	        socket.setBroadcast(true);

	        byte[] buffer = broadcastMessage.getBytes();
	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);
	        socket.send(packet);
	        socket.close();
	    }
	    
	    
}
	
	
	
