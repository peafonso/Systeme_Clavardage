package system;
import java.net.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;

public class Network {
	
	//premi�re connexion envoi d'un broadcast aux num�ros de ports (m�me num de port si IP diff�rentes sinon num port diff�rents)
	    private static DatagramSocket socket = null;

	    public static void main(String[] args) throws IOException {
	    	System.out.println("Envoi Hello");
<<<<<<< HEAD
	        broadcast("Hello tete de noeud", InetAddress.getByName("255.255.255.255"));
=======
	        broadcast("Hello", InetAddress.getByName("255.255.255.255"));
>>>>>>> db4ef11490f868fcee7f5b486d9f33766367fcd6
	    }

	    public static void broadcast(String broadcastMessage, InetAddress address) throws IOException {
	        socket = new DatagramSocket();
	        socket.setBroadcast(true);

	        byte[] buffer = broadcastMessage.getBytes();
	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);
	        socket.send(packet);
	        socket.close();
	    }
	    
	    

	    }
	
	
	//connexion d�j� faite et communication entre deux utilisateurs
	//public void Conversation (user1, user2) {
		
	//}


