package system;

import model.User;

public class ChatSystemServer extends Thread {
	private static User user;
	static SocketServer sockserv;
	static SocketClient sockclient;
	
	public ChatSystemServer(User u,SocketServer ss,SocketClient sc) {
		ChatSystemServer.user=u;
		ChatSystemServer.sockserv=ss;
		ChatSystemServer.sockclient=sc;
		
	}
	public void run() {
	    long start = System.currentTimeMillis();
	    // boucle tant que la durée de vie du thread est < à 30 secondes
	    while( System.currentTimeMillis() < ( start + (1000 * 30))) {
	    	try {
	    		//traitement
	    		String response= sockserv.Listen();
	    		System.out.println(response);
	    		User usertoadd= User.toUser(response);
	    		if (usertoadd.equals(user)) {
		    		System.out.println("pseudo utilisé");
	    			String envoiko= "not ok"+user.toString();
	    			sockclient.SendMessage(envoiko, usertoadd.getIP(), usertoadd.getPort());
	    		}
	    		else {
		    		System.out.println("pseudo ok");
	    			String envoiko= "ok"+user.toString();
	    			sockclient.SendMessage(envoiko,usertoadd.getIP(), usertoadd.getPort());
	    		}
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
	    	}
	     
	    }
	  }   
	

}
