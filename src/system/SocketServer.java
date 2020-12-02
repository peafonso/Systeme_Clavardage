package system;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread{
	
	private static String serverIP; //"127.0.0.1";
    private static int serverPort ;
    
    public void SocketServer(String IP, int port){
        this.serverIP=IP;
        this.serverPort=port;
    }
    
    public static void main(String[] args) {

    	try {
    		//Creating a ServerSocket instance
    		ServerSocket ss = new ServerSocket(serverPort);
	            
	       	//1 char= 1 octet
	       	//Max message 100*10^6 octets
	        byte[] array = new byte[100000000];

	        while(true) {
	        	System.out.println("Awaiting connection");
	            //Waiting for prospective clients connections (blocking)
	            Socket link = ss.accept();
	                
	            new Thread(() -> {
	            	System.out.println("Thread started");
	        		try {
	        			InputStream is = link.getInputStream();
	        		    is.read(array);
	        		    String data = new String(array);
	        		    System.out.println("Received: "+data);
	        		          
	        		    System.out.println("Finishing thread");
	        		    is.close();
	        		    link.close();
	        		    } catch (Exception e) {
	        		        e.printStackTrace();
	        		    }
	        		}).start();
	            ss.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            }
	        }
    }

