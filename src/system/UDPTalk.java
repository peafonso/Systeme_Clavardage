package system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPTalk {

	public UDPTalk() {
		// TODO Auto-generated constructor stub
	}
	
    public static void broadcast(String broadcastMessage, int port) throws IOException {
    	DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), port);
		System.out.println("Envoi msg en broadcast");
        socket.send(packet);
        socket.close();
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
