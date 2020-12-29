package system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPTalk {

	public UDPTalk() {
		// TODO Auto-generated constructor stub
	}
	
    public static void broadcast(String broadcastMessage, int port) throws IOException {
    	DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), port);
		System.out.println("Envoi msg");
        socket.send(packet);
        socket.close();
    }
    
    public void sendUDP(int port, InetAddress laddr) throws SocketException {
		// TODO

    }
}
