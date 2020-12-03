package system;

import java.io.OutputStream;
import java.net.Socket;

public class SocketClient extends Thread {

	private static String clientIP; //"127.0.0.1";
    private static int clientPort ;
	
    public SocketClient(String IP, int port){
        this.clientIP=IP;
        this.clientPort=port;
    }
    


public static void SendMessage(String msg)  {
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

