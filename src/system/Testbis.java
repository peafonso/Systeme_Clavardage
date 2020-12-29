package system;

public class Testbis {

	public Testbis() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		UDPListener udpListen = new UDPListener();
		UDPTalk udpTalk = new UDPTalk();
		try {
			udpTalk.broadcast("Hello", 4445);
		}catch (Exception e) {
			System.out.println("Erreur reception");
		}
		String msg = udpListen.receiveUDP(4445);
		System.out.println("Message reçu: " + msg);
	}


}
