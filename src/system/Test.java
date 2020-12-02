package system;

public class Test {

	public static void main(String[] args) {
		//Test de change pseudo
		User coumbz =  new User("127.0.0.1", 1234, "coucou");
		User pepe =  new User("127.0.0.1", 1234, "pp");
		User yaya = new User("127.0.0.1", 1234, "pp");
		Contacts.showContacts();
		/*coumbz.Change_pseudo("pp");
		coumbz.Change_pseudo("pp2");
		Contacts.showContacts();*/

		
	}

}
