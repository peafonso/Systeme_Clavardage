package system;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import control.Application;
import model.User;

public class TCPChat extends Thread{
	private Application app;
	private User them;
	private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    //constructeur a utiliser lorsque quelqu'un veut clavarder avec nous
    public TCPChat (Application app, Socket sock) {
    	
    }
    
    //constructeur a utiliser lorsqu'on veut clavarder avec quelqu'un
    public TCPChat (Application app, User u2) {
    	
    }


}
