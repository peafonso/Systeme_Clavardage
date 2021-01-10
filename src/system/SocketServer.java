package system;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread{
	private String clientIP;
	private int clientPort;
	
	public SocketServer(String IP, int port) {
		this.setClientIP(IP);
		this.setClientPort(port);
	}
	
	@Override
	public void run() {
		try {
    		while(true) {
    			ServerSocket link = new ServerSocket(clientPort);
    			Socket sock=link.accept();
    			byte[] array = new byte[100000000];
    			OutputStream out= sock.getOutputStream();
    			out.flush();
    			InputStream is = sock.getInputStream();
    			is.read(array);
    			String data = new String(array);
    			System.out.println("received "+data);
    			is.close();
    			link.close();
    		}
		} catch (Exception e) {
        e.printStackTrace();
		}
	}
	
	public static void Receive(String clientIP, int clientPort)  {
	    new Thread(() -> {
	    	try {
	    		while(true) {
	    			ServerSocket link = new ServerSocket(clientPort);
	    			Socket sock=link.accept();
	    			byte[] array = new byte[100000000];
	    			OutputStream out= sock.getOutputStream();
	    			out.flush();
	    			InputStream is = sock.getInputStream();
	    			is.read(array);
	    			String data = new String(array);
	    			is.close();
	    			link.close();
	    		}
        } catch (Exception e) {
            e.printStackTrace();
        }}).start();
	        
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public int getClientPort() {
		return clientPort;
	}
	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

}

