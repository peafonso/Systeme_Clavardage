package system;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Contacts;
import model.User;

public class ChatSystem {
	private static User user;
	private Contacts listeusers;
	private static SocketServer sockserv;

	public ChatSystem(User us,SocketServer ss) {
		this.setUser(us);
		this.setSockserv(ss);
	}
		
	public static void main(String[] args) throws IOException {
		ChatSystem csys= new ChatSystem(new User("127.0.0.1",1234,"pp"),new SocketServer(1234));
		
		//si on est le premier utilisateur on a pas besoin de checker l'unicite des pseudos
		if (getUser().getId()!=1) {
				csys.Connexion();
		}
		
		SocketClient sockclient= new SocketClient();
		ChatSystemServer listener = new ChatSystemServer(getUser(),getSockserv(),sockclient);
		listener.run();
    }
	
	public void Connexion () {
		try {
			//on commence par lancer un broadcast avec notre pseudo
			Scanner scanner= new Scanner(System.in);
			Network.broadcast("CHANGEMENT PSEUDO_"+getUser().toString(), InetAddress.getByName("255.255.255.255"));
			try {
				Thread.sleep(3000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			String response= getSockserv().Listen();
			User usertoadd= User.toUser(response);
			String[] parametersuser=response.split("_");
			String validate= parametersuser[0];
			if (validate.equals("not ok")){
				System.out.println("pseudo existant; choissisez en un autre");
	            String newpseudo= scanner.next();
	            getUser().setPseudo(newpseudo);
	            //on recommence la procedure pour tester l'unicite du nouveau pseudo
	            Connexion();
			}
			else {
				//on ajoute le user à notre liste de personnes connectes
				listeusers.add(usertoadd);
			}
            scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void Deconnexion() {
		try {
			Network.broadcast("DECONNEXION_"+getUser().getPseudo(), InetAddress.getByName("255.255.255.255"));
			//un utilisateur en moins sur le systeme
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		ChatSystem.user = user;
	}

	public static SocketServer getSockserv() {
		return sockserv;
	}

	public static void setSockserv(SocketServer sockserv) {
		ChatSystem.sockserv = sockserv;
	}
}
