package system;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.User;

//classe message pour toutes les communications tcp en clavardage

public class Message implements Serializable {
	enum typemsg {DECONNEXION, CONNEXION, ENVOIMSG, CHANGEMENTPSEUDO};
	private User sender;
	private User receiver;
	private String data;
	private String time; //variable pour l'horodatage
	private typemsg type;
	
	public Message() {
		
	}
	
	public Message(User from, User to, String msg) {
		this.setSender(from);
		this.setReceiver(to);
		this.setData(msg);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.setTime(dateFormat.format(new Date()));
		this.setType(typemsg.ENVOIMSG);
	}
	
	public Message(User from, User to, String msg, String date) {
		this.setSender(from);
		this.setReceiver(to);
		this.setData(msg);
		this.setTimeString(date);
		this.setType(typemsg.ENVOIMSG);
	}
	
	public Message(String msg) {
		this.setData(msg);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.setTime(dateFormat.format(new Date()));

	}

	@Override
	public String toString() {
		String smsg= "Sender: "+this.getSender()+"\n"+"Receiver:  "+this.getReceiver()+"\n"
	+"Time:  "+ this.getTime()+"\n"+"Type:  "+this.getType()+"\n"+
	"Data:  "+this.getData()+"\n";
		return smsg;	
	}
	
	public static Message toMessage(String smsg) {
		String[] paramsg=smsg.split("\n");
		User sender= User.toUser(paramsg[0].split(":")[1]);
		User receiver= User.toUser(paramsg[1].split(":")[1]);
		String date= (paramsg[2].split(":")[1]);
		String [] tabdata=paramsg[4].split(":");
		String data="";
		for (int i=1;i<tabdata.length;i++) {
			data+=tabdata[i];
		}
		return new Message(sender,receiver,data,date);
	
	}
	
	public typemsg toTypemsg(String s) {
		if (s.equals("DECONNEXION")){
			return typemsg.DECONNEXION;
		}
		else if (s.equals("CONNEXION")){
			return typemsg.CONNEXION;
		}
		else {
			return typemsg.ENVOIMSG;
		}
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getTime() {
		return time;
	}
	
	public String getTimeString() {
		return time.toString();
	}

	public void setTime(String string) {
		this.time = string;
	}
	
	public void setTimeString (String date) {
		this.time = date;

	}

	public typemsg getType() {
		return type;
	}

	public void setType(typemsg type) {
		this.type = type;
	}

}
