package system;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	 public static void main(String[] args) {

	        try {
	        	//Creating a ServerSocket instance
	            ServerSocket ss = new ServerSocket(1234);

	            while(true) {
	                System.out.println("Awaiting connection");
	                //Waiting for prospective clients connections (blocking)
	                Socket link = ss.accept();
	                new Thread(new Runner(link)).start();
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }


	    }



}
