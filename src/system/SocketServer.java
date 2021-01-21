package system;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Interface.Home;
import historique.Conversations;

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
	    			System.out.println("jairecuqqchose");
	    			ServerSocket link = new ServerSocket(2000);
	    			Socket sock=link.accept();
	    			Home.displayNotification(sock.getInetAddress().getHostAddress());
	    			byte[] array = new byte[100000000];
	    			OutputStream out= sock.getOutputStream();
	    			out.flush();
	    			InputStream is = sock.getInputStream();
	    			is.read(array);
	    			String data = new String(array);
	    			Message msg= new Message(data);
	    			Home.getApp().getDb().addMessage(sock.getInetAddress().getHostAddress(), msg);
	    			//Conversations.write_msg(data,Home.getApp().getFriends().getPseudofromIP(sock.getInetAddress().getHostAddress()));
	    			//Home.display(data,Home.getApp().getFriends().getPseudofromIP(sock.getInetAddress().getHostAddress()));
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

