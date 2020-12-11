package system;

import java.io.IOException;

//classe ouvrir conversation a intégrer à client
public class Test {

	public static void main(String[] args) {
		//Test de change pseudo
		
		
		//Test Broadcast
		SocketServer sok = new SocketServer(4445);
		//tant qu'on appelle pas envoyer message on attend de recevoir quelque chose
		System.out.println(sok.Listen());
		//SocketClient sok1= new SocketClient("127.0.0.1",4445);
		//sok1.SendMessage("coucou");
	}

}