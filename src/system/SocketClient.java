package system;

import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
	
	public static void main(String[] args) {
        final int link_id = 123;
        new Thread(() -> {
            try {
                Socket s = new Socket("127.0.0.1", 1234);
                OutputStream os = s.getOutputStream();
                SendMessage(os,"salut c'est toto");
                os.close();
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

}


public static void SendMessage(OutputStream os, String msg)  {
	try {
		// Converts the string into bytes
        byte[] dataBytes = msg.getBytes();
        os.write(dataBytes);
	}
	 catch (Exception e) {
         e.getStackTrace();
     }    
}


}

