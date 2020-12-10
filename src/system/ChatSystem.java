package system;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatSystem {
	private User user;
	
	public void Connexion () {
		try {
			//on commence par lancer un broadcast avec notre pseudo
			Network.broadcast(user.toString(), InetAddress.getByName("255.255.255.255"));
			Scanner scanner= new Scanner(System.in);

			SocketServer sockserv= new SocketServer(user.getPort());
			String response= sockserv.Listen();
			if (response.equals("not ok")){
				System.out.println("pseudo existant; choissisez en un autre");
	            String newpseudo= scanner.next();
	            user.setPseudo(newpseudo);
	            //Change_Pseudo(newpseudo);
			}
            scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
