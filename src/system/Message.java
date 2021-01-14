package system;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import model.User;

//classe message pour toutes les communications tcp en clavardage

public class Message implements Serializable {
	enum typemsg {DECONNEXION, CONNEXION, ENVOIMSG, CHANGEMENTPSEUDO};
	private User sender;
	private User receiver;
	private String data;
	private Date time; //variable pour l'horodatage
	private typemsg type;
	
	public Message(User from, User to, String msg) {
		this.setSender(from);
		this.setReceiver(to);
		this.setData(msg);
		this.setTime(new Date());
		this.setType(typemsg.ENVOIMSG);
	}
	
	public Message(User from, User to, String msg, String daae) {
		this.setSender(from);
		this.setReceiver(to);
		this.setData(msg);
		this.setTime(new Date());
		this.setType(typemsg.ENVOIMSG);
	}
	
	public Message(User from, User to, String msg, typemsg type) {
		this.setSender(from);
		this.setReceiver(to);
		this.setData(msg);
		this.setTime(new Date());
		this.setType(type);
	}
	
	@Override
	public String toString() {
	    DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
		String smsg= "Sender: "+this.getSender()+" \n"+"Receiver:  "+this.getReceiver()+"  \n"
	+"Time:  "+ shortDateFormat.format(this.getTime())+" \n"+"Type:  "+this.getType()+" \n"+
	"Data:  "+this.getData()+" \n";
		return smsg;	
	}
	
	public Message toMesssage(String smsg) {
		String[] paramsg=smsg.split("\n");
		User sender= User.toUser(paramsg[0].split(":")[1]);
		User receiver= User.toUser(paramsg[1].split(":")[1]);
		typemsg type=toTypemsg(paramsg[3].split(":")[1]);
		String [] tabdata=paramsg[4].split(":");
		String data="";
		for (int i=1;i<tabdata.length;i++) {
			data+=tabdata[i];
		}
		return new Message(sender,receiver,data,type);
	
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

	public Date getTime() {
		return time;
	}
	
	public String getTimeString() {
		return time.toString();
	}

	public void setTime(Date date) {
		this.time = date;
	}
	
	public void setTimeString (String date) {
		//this.time= date.;
	}

	public typemsg getType() {
		return type;
	}

	public void setType(typemsg type) {
		this.type = type;
	}

}
