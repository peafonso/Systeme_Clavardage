package system;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Interface.Home;
import control.Application;
import model.User;
import system.Message.typemsg;

public class TCPChat extends Thread{
	private Application app;
	private User them;
	private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    //constructeur a utiliser lorsque quelqu'un veut clavarder avec nous
    public TCPChat (Application app, Socket sock) {
    	setApp(app);
    	setSocket(sock);
    	try {
			setOut(new ObjectOutputStream(sock.getOutputStream()));
			setIn(new ObjectInputStream(sock.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	start();
    }
    
    //constructeur a utiliser lorsqu'on veut clavarder avec quelqu'un
    public TCPChat (Application app, User u2) {
    	setApp(app);
    	setThem(u2);
    	try {
			setSocket(new Socket(u2.getIP(),2000));
			setOut(new ObjectOutputStream(socket.getOutputStream()));
			setIn(new ObjectInputStream(socket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//start();
    }

    public void SendMessage(String data) {
        Message msg = new Message(getApp().getMe(),getThem(), data);
        try {
            getOut().writeObject(msg.toString());
            getApp().getDb().addMessage(getThem().getIP(), msg);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void close() {
    	Message msg= new Message(getApp().getMe(),getThem(),"",typemsg.FINMSG);
    	try {
            getOut().writeObject(msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	finally {
    		try {
    			socket.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

    public void run() {
        String data = null;
        Message msg = null;
       // while(true) {
            try {
                data = (String) getIn().readObject();
                msg = Message.toMessage(data);
    			System.out.println("jai recu"+data);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            	//break;
            }
    		
            if(msg.getType()==typemsg.FINMSG) {
    			try {
    				socket.close();
    			}
    			catch(IOException e){
   					//break;
   				}
   				//break;
   			}
            else {
    			Home.displayNotification(socket.getInetAddress().getHostAddress());
    			Home.display(socket.getInetAddress().getHostAddress());
    			getApp().getDb().addMessage(socket.getInetAddress().getHostAddress(), msg);
    			try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
   			}
    			
      //  } 
    }
    

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public User getThem() {
		return them;
	}

	public void setThem(User them) {
		this.them = them;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}


}
