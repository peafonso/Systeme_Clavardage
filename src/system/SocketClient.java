package system;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import Interface.Home;

public class SocketClient extends Thread {

public static void SendMessage(String msg, String clientIP, int clientPort)  {
    new Thread(() -> {
        try {
            Socket s = new Socket(clientIP, 2000);
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

