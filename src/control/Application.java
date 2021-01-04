package control;

import model.Contacts;
import model.User;

//a remplir (main app)
public class Application {
	private User me;
	private Contacts friends;

	public Application(User u1) {
		this.setMe(u1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public User getMe() {
		return me;
	}

	public void setMe(User me) {
		this.me = me;
	}

}
