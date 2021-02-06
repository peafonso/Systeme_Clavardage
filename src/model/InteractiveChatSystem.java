package model;

import java.io.IOException;

import Interface.Home;
import control.Application;
import network.UDPListener;
import network.UDPTalk;

/**
 * Classe InteractiveChatSystem permettant à l'user de gérer connection, déconnexion et 
 *   changement de pseudo
 * 
 * app: instance de la classe Application 
 *
 */
public class InteractiveChatSystem {
	private static Application app;

	/*type des messages à envoyer ou recevoir en udp broadcast correspondant aux différentes
	  situations à gérer par l'user*/
	enum typemsg {DECONNEXION, CONNEXION, CHANGEMENTPSEUDO};
	
	/**
	 * Constructeur 
	 * @param app Application associée
	 */
	public InteractiveChatSystem(Application app) {
		this.setApp(app);
	}

	

	/**
	 * Connexion de l'user
	 * @param newPseudo pseudo de l'user tentant de se connecter
	 * @return un booléen correspondant à la validation de l'unicité de son pseudo
	 */
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
			    System.out.println("on ajoute "+usertoadd);
		    	getApp().getFriends().addContact(usertoadd);
 				getApp().getDb().createTableConvo(usertoadd.getIP()); //on ajoute dans la bd

		    }
	    	getApp().getMe().setPseudo(newPseudo);
		}
		return disponible;
	}		

	/**
	 * Changement de pseudo
	 * @param newPseudo nouveau pseudo à tester de l'user 
	 * @param port port sur lequel on lance le broadcast du changement de pseudo
	 * @return un booléen correspondant à la validation de l'unicité de son pseudo
	 */
	public boolean ChangePseudo(String newPseudo, int port) {
		boolean disponible=true;
		//envoi broadcast
		UDPListener socketReception = new UDPListener();
		
		try {
		    System.out.println("Tentative de changement de pseudo en broadcast");
			UDPTalk.broadcast(("CHANGEMENTPSEUDO_"+newPseudo+"_"+getApp().getMe().getIP()+"_"+getApp().getMe().getPort()), port);
		}catch (Exception e) {
			System.out.println("Erreur broadcast dans ChangePseudo");
		}
		
		getApp().getMe().setPseudo(newPseudo);
		//Écoute tant qu'il y a une réponse
	    System.out.println("Attente de reception");
		String response= socketReception.receiveUDP(getApp().getMe().getPort());
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
	
	
	
	/**
	 * Déconnexion de l'user sur le port 4445
	 */
	public void Deconnexion() {
		int port=4445;
		try {
			System.out.println("je me deconnecte et je l'envoie en broadcast ");
			UDPTalk.broadcast(("DECONNEXION_"+getApp().getMe().getPseudo()+"_"+getApp().getMe().getIP()+"_"+getApp().getMe().getPort()), port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Réception d'un message en broadcast udp
	 * @param msgrecu message reçu
	 */
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
     				getApp().getFriends().addContact(usertoadd); //on ajoute dans le tableau
     				getApp().getDb().createTableConvo(usertoadd.getIP()); //on ajoute dans la bd
         	    	UDPTalk.sendUDP(envoiok, usertoadd.getPort(), usertoadd.getIP());
    	    		Home.displayNotifUsers(usertoadd.getPseudo()," just connect \n");
         		    System.out.println("j'ajoute" +usertoadd+ "et je maj");
        			Home.miseAJourContact();


     	    	}catch (Exception e) {
     	    		System.out.println("Pb envoi UDP OK");
     	    	}    	    
     	    }
     	    break;
     	    
        case CHANGEMENTPSEUDO:
    	    System.out.println(msgrecu);
    	 
    	    User usertocompare= User.toUser(msgrecu);
    	    //Si c'est moi meme qui recoit
    	    if(usertocompare.getIP().equals(getApp().getMe().getIP())) {
    		    System.out.println("JE MAUTORISE");
    	    }
    	    else {
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
    	    			String oldpseudo=getApp().getFriends().getUserfromIP(usertocompare.getIP()).getPseudo();
    	    			getApp().getFriends().getUserfromIP(usertocompare.getIP()).setPseudo(usertocompare.getPseudo());
    	    			UDPTalk.sendUDP(envoiok, usertocompare.getPort(), usertocompare.getIP());
        	    		Home.displayNotifUsers(oldpseudo," just changed his pseudo to "+usertocompare.getPseudo()+"\n");
        	    		Home.miseAJourContact();
            	    	if (Home.getTalkingto().getText()!=usertocompare.getPseudo()) {
            	    		Home.getTalkingto().setText(usertocompare.getPseudo());
            	    	}
    	    		}catch (Exception e) {
    	    			System.out.println("Pb envoi UDP OK");
    	    		}    	    
    	    	}	
    	    }
    	    break;

        case DECONNEXION:
    	    System.out.println(msgrecu);
    	    User usertodisconnect= User.toUser(msgrecu);
    	    //Si c'est moi meme qui recoit
    	    if(usertodisconnect.getIP().equals(getApp().getMe().getIP())) {
    		    System.out.println("JE MAUTORISE");
    	    }
    	    else {
    	    	getApp().getFriends().deleteContact(getApp().getFriends().getUserfromPseudo(usertodisconnect.getPseudo()));
	    		Home.displayNotifUsers(usertodisconnect.getPseudo()," just disconnect \n");
	    		if (Home.getTalkingto().getText().equals(usertodisconnect.getPseudo())) {
	    			System.out.println("triste il est parti");
	    			Home.getTalkingto().setText("");
	    			Home.clearMessagesArea();
	    		}
    	    	Home.miseAJourContact();
    	    }
        	break;
		default:
			break;
        
        }
	}


	//-------------------- GETTEURS & SETTEURS -----------------------------//

	public static Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		InteractiveChatSystem.app = app;
	}


}
