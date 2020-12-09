package system;

//classe ouvrir conversation a intégrer à client
public class Test {

	public static void main(String[] args) {
		//Test de change pseudo
		User coumbz =  new User("127.0.0.1", 1234, "coucou");
		User yaya = new User("127.0.0.1", 1234, "pp");
		coumbz.init();
		coumbz.showContacts();
		coumbz.Change_Pseudo("pp");
		
		
		//Test Broadcast
		//SocketServer sok = new SocketServer("127.0.0.1",4445);
		//tant qu'on appelle pas envoyer message on attend de recevoir quelque chose
		//sok.Listen();
	}

}