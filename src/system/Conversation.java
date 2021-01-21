package system;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import control.Application;
import model.User;
import system.Message.typemsg;

public class Conversation extends Thread {
	
	private Application app;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket sock;
	private ServerSocket server;
	private User them;
	
	public Conversation(User them, Application ap) {
		setApp(ap);
		setThem(them);
	}
	
	public void sendMessage(String data){
		Message msg= new Message(getThem(),getApp().getMe(),data);
		try
		{
			getOutput().writeObject(" :" + msg.toString());
	        getOutput().flush();
		}
	    catch(IOException ioException)
	    {
	    	System.out.println("\n Unable to Send Message");
	    }
	}
	
	//si c'est moi qui lance la discussion (je suis le server)
	public void startChattingasServer()
    {
        try
        {
            setServer(new ServerSocket(getApp().getMe().getPort()));
            while(true)
            {
                try
                {
                	System.out.println(" Waiting for Someone to Connect...");
                    setSock(server.accept());
                    System.out.println(" Now Connected to "+getThem().getIP());


                    setOutput(new ObjectOutputStream(getSock().getOutputStream()));
                    getOutput().flush();
                    setInput(new ObjectInputStream(getSock().getInputStream()));

                    whileChatting();

                }catch(EOFException eofException)
                {
                }
            }
        }
        catch(IOException ioException)
        {
                ioException.printStackTrace();
        }
    }
	
	//si c'est quelqu'un qui lance la discussion avec moi (je suis le client)
	 public void startChattingasClient()
	 {
		 try{
			 System.out.println("Attempting Connection ...");
			 try{
				 setSock(new Socket(getApp().getMe().getIP(),getApp().getMe().getPort()));
			 }
			 catch(IOException ioEception){
				 JOptionPane.showMessageDialog(null,"Server Might Be Down!","Warning",JOptionPane.WARNING_MESSAGE);
			 }
			 System.out.println("Connected to: " + getSock().getInetAddress().getHostName());
			 setOutput(new ObjectOutputStream(getSock().getOutputStream()));
			 getOutput().flush();
	         setInput(new ObjectInputStream(getSock().getInputStream()));

	         whileChatting();
		 }
		 catch(IOException ioException){
			 ioException.printStackTrace();
		 }
	 }
	
	private void whileChatting() throws IOException
	{
		String message="";    
		do{
			try{
				message = (String) input.readObject();
			}
			catch(ClassNotFoundException classNotFoundException){
				
			}
	    }
		while(!message.equals("byyyee"));
	}


	
	
	//GETTEUR & SETTEUR
	public User getThem() {
		return them;
	}

	public void setThem(User them) {
		this.them = them;
	}

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public ObjectInputStream getInput() {
		return input;
	}

	public void setInput(ObjectInputStream input) {
		this.input = input;
	}
	
	public ObjectOutputStream getOutput() {
		return output;
	}

	public void setOutput(ObjectOutputStream output) {
		this.output = output;
	}

	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}
	
	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}
	
}
