package system;

import java.io.IOException;

import model.Contacts;
import model.User;
import system.Message.typemsg;
import java.util.Date;

import control.Application;

public class InteractiveChatSystem {

	private static User user;
	private Application app;

	public InteractiveChatSystem(User us) {
		this.setUser(us);
		app= new Application(us);
			
	}

	
	//appel fonction Envoi message Broadcast � la liste des contacts pour v�rification du pseudo
	//if pseudoOk return true else return false
	public boolean Connexion(String pseudo) {
		boolean connected=false;
		
		if (ChangePseudo(pseudo)) {
			connected=true;
		}
		return connected;
	}		

	
	public boolean ChangePseudo(String newPseudo) {
		boolean disponible=true;
		//envoi broadcast
		UDPListener socketReception = new UDPListener();
		int port=4445;
		
		try {
		    System.out.println("Tentative de changement de pseudo en broadcast");
			UDPTalk.broadcast(("CHANGEMENTPSEUDO_"+newPseudo+"_"+user.getIP()+"_"+port), port);
		}catch (Exception e) {
			System.out.println("Erreur broadcast dans ChangePseudo");
		}
		
		user.setPseudo(newPseudo);
		//�coute tant qu'il y a une r�ponse
	    System.out.println("Attente de reception");
		String response= socketReception.receiveUDP(port);
	    System.out.println("On a re�u: "+ response);
		User usertoadd= User.toUser(response);
		String[] parametersuser=response.split("_");
		String validate= parametersuser[0];
		//Si r�ponse n�gative then renvoi faux
		if (validate.equals("notOk")) {
		    System.out.println("pseudo Not ok");
			disponible=false;
		}else {
			//Si r�ponse positive then renvoi vrai
		    System.out.println("pseudo ok");
	        getUser().setPseudo(newPseudo);
			//app.getFriends().add(usertoadd);

		}
			
		return disponible;
	}
	
	
	
	
	public void Deconnexion() {
		int port=4445;
		try {
			UDPTalk.broadcast(("DECONNEXION_"+getUser().getPseudo()), port);
			//un utilisateur en moins sur le systeme
			User.nbuser--;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ReceptionMsg (String msgrecu) {
		String[] splitmessage=msgrecu.split("_");
		
		typemsg type= typemsg.valueOf(splitmessage[0]);

		switch (type) {
        case DECONNEXION:
        	break;
        case CONNEXION:
        	break;
        case ENVOIMSG:
        	break;
        case CHANGEMENTPSEUDO:
    	    System.out.println(msgrecu);
    	    
    	    User usertoadd= User.toUser(msgrecu);
    	    if (usertoadd.getPseudo().equals(user.getPseudo())) {
    		    System.out.println("pseudo utilis�");
    	    	String envoiko= "notOk"+user.toString();
    	    	try {
    	    		System.out.println("envoiko "+ usertoadd.getIP());
        	    	UDPTalk.sendUDP(envoiko, usertoadd.getPort(), usertoadd.getIP());
    	    	}catch (Exception e) {
    	    		//TODO
    	    	}
    	    }else{
    		    System.out.println("pseudo ok");
    	    	String envoiok= "ok"+user.toString();
       	    	try {
    	    		System.out.println("envoiok "+ usertoadd.getIP());
        	    	UDPTalk.sendUDP(envoiok, usertoadd.getPort(), usertoadd.getIP());
    	    	}catch (Exception e) {
    	    		//TODO
    	    	}    	    
       	    }	
        	
		default:
			break;
        
        }
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		InteractiveChatSystem.user = user;
	}

}
