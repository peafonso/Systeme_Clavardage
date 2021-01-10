package system;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient extends Thread {

public static void SendMessage(String msg, String clientIP, int clientPort)  {
    new Thread(() -> {
        try {
            Socket s = new Socket(clientIP, clientPort);
            OutputStream os = s.getOutputStream();
            byte[] dataBytes = msg.getBytes();
            System.out.println("envoi "+msg);
            os.write(dataBytes);
            os.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();
        
}


}

