package system;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
	
	public Conversation(User u, Application ap) {
	}
	
	private void sendMessage(String data){
		Message msg= new Message(getThem(),getApp().getMe(),data,typemsg.ENVOIMSG);
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
}
