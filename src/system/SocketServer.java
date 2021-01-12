package system;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Interface.Home;

public class SocketServer extends Thread{
	private int clientPort;
	
	public SocketServer (int port) {
		this.setClientPort(port);
	}
	
	@Override
	public void run() {
		try {
    		while(true) {
    			ServerSocket link = new ServerSocket(2000);
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
	
	public static void Receive(int clientPort)  {
	    new Thread(() -> {
	    	try {
	    		while(true) {
	    			System.out.println("qqq");
	    			ServerSocket link = new ServerSocket(2000);
		    		System.out.println("qqq2");
	    			Socket sock=link.accept();
	    			Home.displayNotification(link.getInetAddress().getHostAddress());
	    			byte[] array = new byte[100000000];
	    			OutputStream out= sock.getOutputStream();
	    			out.flush();
	    			InputStream is = sock.getInputStream();
	    			is.read(array);
	    			String data = new String(array);
	    			Home.display(data);
	    			is.close();
	    			link.close();
	    		}	
        } catch (Exception e) {
            e.printStackTrace();
        }}).start();
	        
	}

	
	public int getClientPort() {
		return clientPort;
	}
	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

}

