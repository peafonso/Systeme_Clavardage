package system;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatSystem {
	private static User user;
	static SocketServer sockserv;

	public ChatSystem(User us,SocketServer ss) {
		this.user=us;
		this.sockserv=ss;
	}
	public static void main(String[] args) throws IOException {
		ChatSystem csys= new ChatSystem(new User("127.0.0.1",1234,"pp"),new SocketServer(1234));
		//csys.Connexion();
		SocketClient sockclient= new SocketClient(user.getIP(),user.getPort());
		ChatSystemServer listener = new ChatSystemServer(user,sockserv,sockclient);
		listener.run();
    }
	public void Connexion () {
		try {
			//on commence par lancer un broadcast avec notre pseudo
			Network.broadcast(user.toString(), InetAddress.getByName("255.255.255.255"));
			Scanner scanner= new Scanner(System.in);
			long start = System.currentTimeMillis();
			while( System.currentTimeMillis() < ( start + (1000 * 30))) {
			String response= sockserv.Listen();
			User usertoadd= user.toUser(response);
			String[] parametersuser=response.split("_");
			String validate= parametersuser[0];
			if (validate.equals("not ok")){
				System.out.println("pseudo existant; choissisez en un autre");
	            String newpseudo= scanner.next();
	            user.setPseudo(newpseudo);
	            //on recommence la procédure pour tester l'unicité du nouveau pseudo
	            Connexion();
			}
			else {
				//on ajoute le user à notre liste de personnes connectés
				user.addContact(usertoadd);
			}
			}
            scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
}
