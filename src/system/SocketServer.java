package system;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SocketServer extends Thread{
	
	public static void Receive(String clientIP, int clientPort)  {
	    new Thread(() -> {
	    	try {
	    		while(true) {
	    			System.out.println("avant");
	    			ServerSocket link = new ServerSocket(clientPort);
	    			Socket sock=link.accept();
	    			System.out.println("apres");
	    			byte[] array = new byte[100000000];
	    			OutputStream out= sock.getOutputStream();
	    			out.flush();
	    			InputStream is = sock.getInputStream();
	    			is.read(array);
	    			String data = new String(array);
	    			System.out.println("Received: "+data);
	    			is.close();
	    			link.close();
	    		}
        } catch (Exception e) {
            e.printStackTrace();
        }}).start();
	        
	}

}

