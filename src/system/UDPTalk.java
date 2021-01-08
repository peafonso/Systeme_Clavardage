package system;
import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.net.NetworkInterface;

public class UDPTalk {

	public UDPTalk() {
		// TODO Auto-generated constructor stub
	}
	
    public static void broadcast(String broadcastMessage, int port) throws IOException {
		for (InetAddress  addrbroadcast : listAllBroadcastAddresses()) {
    		DatagramSocket socket = new DatagramSocket();
    		socket.setBroadcast(true);
    		byte[] buffer = broadcastMessage.getBytes();
    		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, addrbroadcast, port);
    		System.out.println("Envoi msg en broadcast to"+addrbroadcast);
    		socket.send(packet);
    		socket.close();
		}
    }
    
    
    //liste des adresses de broadcast
    static List<InetAddress> listAllBroadcastAddresses() throws SocketException {
        List<InetAddress> broadcastList = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces 
          = NetworkInterface.getNetworkInterfaces();
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
    }
    
    public static void sendUDP(String msg, int port, String laddr) throws SocketException {

    	DatagramSocket socket = new DatagramSocket();

        byte[] buffer = msg.getBytes();
        DatagramPacket packet;
		try {
			packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(laddr), port);
			System.out.println("Envoi msg");
			socket.send(packet);
        	socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
