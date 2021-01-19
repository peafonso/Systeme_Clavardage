package historique;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Interface.Interface;
import control.Application;
import model.Contacts;
import model.User;
import system.InteractiveChatSystem;

public class Conversations {
	
	private static Application app;
	private static Contacts friends;
	
	String recu;

	public Conversations(Application app) {
		// TODO Auto-generated constructor stub
		this.setApp(app);
		friends=getApp().getFriends();
	}
	
	public static void initialize_hist() {
		//création répertoire historique et fichiers des contacts déjà présents dans la liste d'amis
		File allConv = new File(".\\src\\historique\\");			
		//si nom du fichier n'existe pas, pas encore de conv avec la personne donc création du message
		for (User user : friends) {
			File conv=new File(".\\src\\historique\\all_conv\\"+user.getPseudo()+".txt");
		}
		
	}
	
	public static void delete_historique(){
		for (User user : friends) {
			File conv=new File(".\\src\\historique\\all_conv\\"+user.getPseudo()+".txt");
			conv.delete();
		}
		
		File allConv = new File(".\\src\\historique\\");			
		allConv.delete();
	}
	
	
	public String Chat_with(String pseudo) {
		// TODO 
		//Ouverture du fichier s'il existe sinon création d'un fichier de nom pseudo
		//renvoie le path du fichier de conversation avec la personne
		String path = (".\\src\\historique\\all_conv\\"+pseudo+".txt");
		File conv=new File(path);	
		return path;
	}
	
	public void add_friend(String pseudo) {
		File conv = new File(".\\src\\historique\\all_conv\\"+pseudo+".txt");
	}
	
	public static String read_msg(String pseudo) {
		// TODO 
		String pathFichier=".\\src\\historique\\all_conv\\"+pseudo+".txt";
		
		String msg="OUPPS J'AI RIEN LU";
		
		BufferedReader fluxEntree=null;
		try {
			/* Création du flux vers le fichier texte */
			fluxEntree = new BufferedReader(new FileReader(pathFichier));

			/* Lecture d'une ligne */
			String ligneLue = fluxEntree.readLine();

			while(ligneLue!=null){
				System.out.println(ligneLue);
				ligneLue = fluxEntree.readLine();
			}
			msg=ligneLue;
		}
		catch(IOException exc){
			exc.printStackTrace();
		}
		finally{
			try{
				if(fluxEntree!=null){
					/* Fermeture du flux vers le fichier */
					fluxEntree.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		return msg;

	}
	
	public static void write_msg(String msg, String pseudo){
		// TODO 
		PrintWriter out=null;
		try{
			/* Path vers le fichier à créer*/
			String pathFichier=".\\src\\historique\\all_conv\\"+pseudo+".txt";

			/* Ouverture du fichier en écriture */
			out = new PrintWriter(new BufferedWriter(new FileWriter(pathFichier)));

			/* Ecriture d'une ligne puis saut de ligne */
			out.println(msg);

		}
		catch(IOException exc){
			exc.printStackTrace();
		}
		finally {
			if(out!=null){
				/* Fermeture du flux */
				out.close();
			}
		}
	}
	
	public void rename_friend(String old_pseudo, String new_pseudo) {
		// TODO 
		//renommer le fichier si changement de pseudo
		File old_file=new File(".\\src\\historique\\all_conv\\"+old_pseudo+".txt");
		old_file.renameTo(new File(".\\src\\historique\\all_conv\\"+old_pseudo+".txt"));
	}
	
	public static Application getApp() {
		return app;
	}


	public void setApp(Application app) {
		Conversations.app = app;
	}

}
