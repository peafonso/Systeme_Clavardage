package system;

import java.io.OutputStream;
import java.net.Socket;

public class SocketClient extends Thread {

	private String clientIP; //"127.0.0.1";
    private int clientPort ;
	
    public SocketClient(String IP, int port){
        this.clientIP=IP;
        this.clientPort=port;
    }
    


public void SendMessage(String msg)  {
    new Thread(() -> {
        try {
            Socket s = new Socket(clientIP, clientPort);
            OutputStream os = s.getOutputStream();
    		// Converts the string into bytes
            byte[] dataBytes = msg.getBytes();
            os.write(dataBytes);
            os.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();
        
}




}

