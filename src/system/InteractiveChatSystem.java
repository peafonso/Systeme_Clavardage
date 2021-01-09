package system;

import java.io.IOException;

import model.Contacts;
import model.User;
import system.Message.typemsg;
import java.util.Date;

import Interface.Home;
import control.Application;

public class InteractiveChatSystem {

	private static Application app;
	private static Home home;

	public InteractiveChatSystem(Application app) {
		this.setApp(app);
	}

	
	//appel fonction Envoi message Broadcast à la liste des contacts pour vérification du pseudo
	//if pseudoOk return true else return false
	public boolean Connexion(String newPseudo) {
		boolean disponible=true;
		//envoi broadcast
		UDPListener socketReception = new UDPListener();
		int port = 4445;
		try {
		    System.out.println("Tentative de connexion");
			UDPTalk.broadcast(("CONNEXION_"+newPseudo+"_"+getApp().getMe().getIP()+"_"+port), port);
		}catch (Exception e) {
			System.out.println("Erreur broadcast dans Connexion");
		}
		
		getApp().getMe().setPseudo(newPseudo);
		//Écoute tant qu'il y a une réponse
	    System.out.println("Attente de reception");
		String response= socketReception.receiveUDP(port);
	    System.out.println("On a reçu: "+ response);
		User usertoadd= User.toUser(response);
		String[] parametersuser=response.split("_");
		String validate= parametersuser[0];
		//Si réponse négative then renvoi faux
		if (validate.equals("notOk")) {
		    System.out.println("pseudo Not ok");
			disponible=false;
		}else {
			//Si réponse positive then renvoi vrai
		    System.out.println("pseudo ok");
		    if(!(usertoadd.getIP().equals("IP"))) {
		    	//si on est le 1er du réseau on ajoute personne 
		    	getApp().getFriends().addContact(usertoadd);
		    }
	    	getApp().getMe().setPseudo(newPseudo);
		}
		return disponible;
	}		

	
	public boolean ChangePseudo(String newPseudo, int port) {
		boolean disponible=true;
		//envoi broadcast
		UDPListener socketReception = new UDPListener();
		
		try {
		    System.out.println("Tentative de changement de pseudo en broadcast");
			UDPTalk.broadcast(("CHANGEMENTPSEUDO_"+newPseudo+"_"+getApp().getMe().getIP()+"_"+port), port);
		}catch (Exception e) {
			System.out.println("Erreur broadcast dans ChangePseudo");
		}
		
		getApp().getMe().setPseudo(newPseudo);
		//Écoute tant qu'il y a une réponse
	    System.out.println("Attente de reception");
		String response= socketReception.receiveUDP(port);
	    System.out.println("On a reçu: "+ response);
		User usertoadd= User.toUser(response);
		String[] parametersuser=response.split("_");
		String validate= parametersuser[0];
		//Si réponse négative then renvoi faux
		if (validate.equals("notOk")) {
		    System.out.println("pseudo Not ok");
			disponible=false;
		}else {
			//Si réponse positive then renvoi vrai
		    System.out.println("pseudo ok");
	    	getApp().getMe().setPseudo(newPseudo);
		}
			
		return disponible;
	}
	
	
	
	
	public void Deconnexion() {
		int port=4445;
		try {
			System.out.println("je me deconnecte et je l'envoie en broadcast ");
			UDPTalk.broadcast(("DECONNEXION_"+getApp().getMe().getPseudo()), port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ReceptionMsg (String msgrecu) {
		String[] splitmessage=msgrecu.split("_");
		
		typemsg type= typemsg.valueOf(splitmessage[0]);

		switch (type) {
        case CONNEXION:
    	    System.out.println(msgrecu);

        	User usertoadd= User.toUser(msgrecu);
     	    if (usertoadd.getPseudo().equals(getApp().getMe().getPseudo())) {
     		    System.out.println("pseudo utilisé");
     	    	String envoiko= "notOk"+getApp().getMe().toString();
     	    	try {
     	    		System.out.println("envoiko "+ usertoadd.getIP());
         	    	UDPTalk.sendUDP(envoiko, usertoadd.getPort(), usertoadd.getIP());
     	    	}catch (Exception e) {
     	    		System.out.println("Pb envoi UDP KO");
     	    	}
     	    }else{
     		    System.out.println("pseudo ok");
     	    	String envoiok= "ok"+getApp().getMe().toString();
        	    	try {
     	    		System.out.println("envoiok "+ usertoadd.getIP());
     				getApp().getFriends().addContact(usertoadd);
         	    	UDPTalk.sendUDP(envoiok, usertoadd.getPort(), usertoadd.getIP());
         	    	//home.miseAJourContact();
     	    	}catch (Exception e) {
     	    		System.out.println("Pb envoi UDP OK");
     	    	}    	    
       }	
     	    

        case CHANGEMENTPSEUDO:
    	    System.out.println(msgrecu);
    	    
    	    User usertocompare= User.toUser(msgrecu);
    	    if (usertocompare.getPseudo().equals(getApp().getMe().getPseudo())) {
    		    System.out.println("pseudo utilisé");
    	    	String envoiko= "notOk"+getApp().getMe().toString();
    	    	try {
    	    		System.out.println("envoiko "+ usertocompare.getIP());
        	    	UDPTalk.sendUDP(envoiko, usertocompare.getPort(), usertocompare.getIP());
    	    	}catch (Exception e) {
    	    		System.out.println("Pb envoi UDP KO");
    	    	}
    	    }else{
    		    System.out.println("pseudo ok");
    	    	String envoiok= "ok"+getApp().getMe().toString();
       	    	try {
    	    		System.out.println("envoiok "+ usertocompare.getIP());
        	    	UDPTalk.sendUDP(envoiok, usertocompare.getPort(), usertocompare.getIP());
        	    	home.miseAJourContact();
    	    	}catch (Exception e) {
    	    		System.out.println("Pb envoi UDP OK");
    	    	}    	    
       	    }	
        case ENVOIMSG:
        	break;
        case DECONNEXION:
        	break;
		default:
			break;
        
        }
	}



	public static Application getApp() {
		return app;
	}


	public void setApp(Application app) {
		InteractiveChatSystem.app = app;
	}


	public Home getHome() {
		return home;
	}


	public void setHome(Home home) {
		InteractiveChatSystem.home = home;
	}

}
