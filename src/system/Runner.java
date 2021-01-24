package system;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import control.Application;

public class Runner extends Thread {
	private Application app;
	private Socket link;
    byte[] array = new byte[100000000];
    
	public Runner(Application app) {
        this.app=app;
    }

    public void run() {
        ServerSocket server;
        System.out.println("letsgo");
        try {
            server = new ServerSocket(2000); 
            System.out.println("Socket d'ecoute cree");
            while(true) { 
                System.out.println("Attente Session de clavardage");
                Socket link = server.accept(); 
                TCPChat chat = new TCPChat(app,link);
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}