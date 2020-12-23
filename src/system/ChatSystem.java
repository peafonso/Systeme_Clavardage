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
	static SocketServer sockserv;

	public ChatSystem(User us,SocketServer ss) {
		this.user=us;
		this.sockserv=ss;
	}
	
	enum typemsg {DECONNEXION, CONNEXION, ENVOIMSG };
	
	public static void main(String[] args) throws IOException {
		ChatSystem csys= new ChatSystem(new User("127.0.0.1",1234,"pp"),new SocketServer(1234));
		
		//si on est le premier utilisateur on a pas besoin de checker l'unicité des pseudos
		if (user.getId()!=1) {
				csys.Connexion();
		}
		
		SocketClient sockclient= new SocketClient();
		ChatSystemServer listener = new ChatSystemServer(user,sockserv,sockclient);
		listener.run();
    }
	
	public void Connexion () {
		try {
			//on commence par lancer un broadcast avec notre pseudo
			Scanner scanner= new Scanner(System.in);
			Network.broadcast("CHANGEMENT PSEUDO_"+user.toString(), InetAddress.getByName("255.255.255.255"));
			try {
				Thread.sleep(3000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			String response= sockserv.Listen();
			User usertoadd= User.toUser(response);
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
				listeusers.add(usertoadd);
			}
            scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void Deconnexion() {
		try {
			Network.broadcast("DECONNEXION_"+user.getPseudo(), InetAddress.getByName("255.255.255.255"));
			//un utilisateur en moins sur le système
			User.nbuser--;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void ReceptionMsg (String msgrecu) {
		String[] splitmessage=msgrecu.split("_");
		
		typemsg type= typemsg.valueOf(splitmessage[0]);
		System.out.println(type);
        switch (type) {
        case DECONNEXION:
        	break;
        case CONNEXION:
        	break;
        case ENVOIMSG:
		default:
			break;
        
        }
	}
}
