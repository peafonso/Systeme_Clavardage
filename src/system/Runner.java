package system;

import java.io.InputStream;
import java.net.Socket;
//1 char= 1 octet
//Max message 100*10^6 octets
public class Runner implements Runnable {

	private Socket link;
    byte[] array = new byte[100000000];
    
	public Runner(Socket link) {
        this.link = link;
    }

    @Override
    public void run() {
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
    }

}
