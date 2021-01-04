package system;

import java.io.IOException;
import java.net.InetAddress;

import control.Application;
import model.Contacts;
import model.User;
import system.Message.typemsg;

public class InteractiveChatSystem {

	private static User user;
	private Contacts listeusers;

	public InteractiveChatSystem(User us) {
		this.setUser(us);
			
	}

	enum typemsg {DECONNEXION, CONNEXION, ENVOIMSG, CHANGEMENTPSEUDO };

	
	//appel fonction Envoi message Broadcast à la liste des contacts pour vérification du pseudo
	//if pseudoOk return true else return false
	public boolean Connexion(String pseudo) {
		boolean connected=false;
		
		if (getUser().getId()==1) {
			connected=true;
		}else {
			if (ChangePseudo(pseudo)) {
				connected=true;
			}
		}
		return connected;
	}		

	
	public boolean ChangePseudo(String newPseudo) {
		boolean disponible=true;
		//envoi broadcast
		UDPListener socketReception = new UDPListener();
		int port=4445;
		
		try {
			UDPTalk.broadcast(("CHANGEMENTPSEUDO_"+newPseudo), port);
		}catch (Exception e) {
			System.out.println("Erreur broadcast dans ChangePseudo");
		}
		try {
		Thread.sleep(3000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		//Écoute tant qu'il y a une réponse
		String response= socketReception.receiveUDP(port);
		User usertoadd= User.toUser(response);
		String[] parametersuser=response.split("_");
		String validate= parametersuser[0];
		//Si réponse négative then renvoi faux et ajoute le contact?
		if (validate.equals("notOk")) {
			disponible=false;
			listeusers.add(usertoadd);
		}else {
			//Si réponse positive then renvoi vrai
            getUser().setPseudo(newPseudo);
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
        	break;
        case CHANGEMENTPSEUDO:
    	    System.out.println(msgrecu);
    	    User usertoadd= User.toUser(msgrecu);
    	    if (usertoadd.getPseudo()==user.getPseudo()) {
    		    System.out.println("pseudo utilisé");
    	    	String envoiko= "notOk"+user.toString();
    	    	try {
        	    	UDPTalk.sendUDP(envoiko, usertoadd.getPort(), usertoadd.getIP());
    	    	}catch (Exception e) {
    	    		//TODO
    	    	}
    	    }else{
    		    System.out.println("pseudo ok");
    	    	String envoiko= "ok"+user.toString();
       	    	try {
        	    	UDPTalk.sendUDP(envoiko, usertoadd.getPort(), usertoadd.getIP());
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

	public Contacts getListeusers() {
		return listeusers;
	}

	public void setListeusers(Contacts listeusers) {
		this.listeusers = listeusers;
	}

}
