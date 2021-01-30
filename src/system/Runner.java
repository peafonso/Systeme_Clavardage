package system;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import control.Application;

public class Runner extends Thread {
	private Application app;
	private Socket link;
    byte[] array = new byte[100000000];
	private static boolean ouvert;
    
	public Runner(Application app) {
        this.app=app;
		setOuvert(true);

    }

    public void run() {
        ServerSocket server;
        try {
            server = new ServerSocket(2000); 
            System.out.println("listening on port 2000 ready to have conversation");
            while(ouvert) { 
                Socket link = server.accept(); 
                TCPChat chat = new TCPChat(app,link);
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean isOuvert() {
		return ouvert;
	}

	public static void setOuvert(boolean ouvert) {
			Runner.ouvert = ouvert;
	}

}