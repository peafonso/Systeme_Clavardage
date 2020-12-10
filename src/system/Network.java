package system;
import java.net.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;

public class Network {
	
	//première connexion envoi d'un broadcast aux numéros de ports 

	    public static void main(String[] args) throws IOException {
	    	System.out.println("Envoi Hello");
	        broadcast("Hello", InetAddress.getByName("255.255.255.255"));
	    }

	    public static void broadcast(String broadcastMessage, InetAddress address) throws IOException {
	    	DatagramSocket socket = new DatagramSocket();
	        socket.setBroadcast(true);

	        byte[] buffer = broadcastMessage.getBytes();
	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);
	        socket.send(packet);
	        socket.close();
	    }
	    
	    /*static ArrayList<InetAddress> listAllBroadcastAddresses() throws SocketException {
	        ArrayList<InetAddress> broadcastList = new ArrayList<>();
	        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	        while (interfaces.hasMoreElements()) {
	            NetworkInterface networkInterface = interfaces.nextElement();

	            if (networkInterface.isLoopback() || !networkInterface.isUp()) {
	                continue;
	            }

	            networkInterface.getInterfaceAddresses().stream() 
	              .map(a -> a.getBroadcast())
	              .filter(Objects::nonNull)
	              .forEach(broadcastList::add);
	        }
	        return broadcastList;
	    }*/
	    
}
	
	
	
