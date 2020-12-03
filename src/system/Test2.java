package system;

public class Test2 {

	public static void main(String[] args) {
		SocketClient sockclient= new SocketClient("127.0.0.1",4445);
		sockclient.SendMessage("salut c'est cool");
	}
}
