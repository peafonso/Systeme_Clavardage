package system;

public class Message {
	private User sender;
	private User receiver;
	private String data;
	private int time; //variable pour l'horodatage
	
	public Message(User from, User to, String msg, int horodatage) {
		this.setSender(from);
		this.setReceiver(to);
		this.setData(msg);
		this.setTime(horodatage);
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
