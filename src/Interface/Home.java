package Interface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.Application;
import historique.Conversations;
import system.Conversation;
import system.InteractiveChatSystem;
import system.Message;
import system.SocketClient;
import system.SocketServer;
import system.UDPListener;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import model.User;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Home {

	private static Application app;
	private static JFrame frame;
	private JTextField textField;
	private static JPanel panel;
	private JButton btnSend;
	private static JEditorPane textArea;
	private JScrollPane scrolltextArea;
	private static JTextArea talkingto;
	private static JTextArea txtrB;
	private static JTextPane notification;
	private static JList<String> usersconnected;
	static UDPListener udpListen = new UDPListener();


	/**
	 * Create the application.
	 */
	public Home(Application app) {
		setApp(app);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new BackgroundJFrame("Home");
		frame.setBackground(new Color(240, 240, 240));
		//frame.setBounds(100, 100, 1640, 920);
    	//centrer la fenetre au milieu de l'ecran
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	frame.setBounds(0, 0, dim.width,dim.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new windowClosingListener());


		//frame.setSize(1600,900);
		ImageIcon homePicture = new ImageIcon();
		homePicture=createImageIcon("/images/ACCUEIL_FOND2.jpg");
		

        frame.setLocation(dim.width/3 - frame.getWidth()/3, dim.height/3 - frame.getHeight()/3);
    	frame.getContentPane().setBounds(0, 0, dim.width,dim.height);
        frame.pack();
        
        JMenuBar menuBar = new JMenuBar();
        //menuBar.setMargin(new Insets(1000, 0, 1000, 0));
        frame.setJMenuBar(menuBar);
        
        //Bouton Home
        JButton btnNewButton = new JButton("Home");
        btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        btnNewButton.setBackground(new Color(153, 153, 153));
        menuBar.add(btnNewButton);
        
        
        //Bouton Settings
        JMenu mntmNewMenuItem_1 = new JMenu("Settings");
    	JMenuItem mPseudo= new JMenuItem("Change Pseudo");
    	mPseudo.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
    	mPseudo.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		new Settings(getApp()); 
        	}
        	}
        );
    	
    	JMenuItem clearConv= new JMenuItem("Clear conversation");
    	clearConv.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
    	clearConv.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		new DeleteConv(getApp()); 
        	}
        	}
        );
    	
        mntmNewMenuItem_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.add(mPseudo);
        mntmNewMenuItem_1.add(clearConv);
        
        //Boutton Deconnexion
        JButton btnDeconnexion = new JButton("Disconnect");
        btnDeconnexion.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        btnDeconnexion.setBackground(new Color(153, 153, 153));
        menuBar.add(btnDeconnexion);
        btnDeconnexion.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		new Disconnect(getApp());
        	}
        	}
        );
        
        panel = new JPanel();
        panel.setBounds(151, 137, 820, 472);
        panel.setBackground(new Color(211, 211, 211));
        panel.setLayout(null);
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(990, 88, 307, 552 );//690
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.setBackground(new Color(95, 158, 160));
        panel_1.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(41, 45, 224, (panel.getHeight()-5));
        panel_1.add(scrollPane_1);
        

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(95, 158, 160), new Color(95, 158, 160)));
        panel_2.setBackground(new Color(0, 128, 128));
        //panel_2.setBounds(1000, 66, (panel_1.getWidth()-40), (panel_1.getHeight()-40) );//690
        scrollPane_1.setViewportView(panel_2);
        
        JLabel lblcontacts = new JLabel("USERS CONNECTED\r\n");
        lblcontacts.setBackground(new Color(192, 192, 192));
        lblcontacts.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        lblcontacts.setBounds(73, 11, 165, 23);
        panel_1.add(lblcontacts);
        
    	ImageIcon profil_pic = new ImageIcon();
    	profil_pic = createImageIcon("/images/profil_picture.png");
        frame.getContentPane().add(panel_1);
        

        //TextField pour rédiger son message
        textField = new JTextField();
        textField.setBackground(Color.WHITE);
        textField.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		textField.setBounds(80, 407, 453, 33);
		textField.setColumns(10);
		
		//Bouton Send
    	btnSend = new JButton("send");
    	btnSend.setBackground(SystemColor.activeCaption);
    	btnSend.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
    	btnSend.setBounds(558, 403, 76, 38);
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 96, 664, 296);
		panel.add(scrollPane);
		
		textArea = new JEditorPane();
		scrollPane.setViewportView(textArea);
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setEditable(false);
		
		JLabel lblTalkingwith = new JLabel("Talking with");
		lblTalkingwith.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		lblTalkingwith.setBounds(60, 57, 126, 31);
		setTalkingto(new JTextArea());
		getTalkingto().setBackground(new Color(211, 211, 211));
		getTalkingto().setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 20));
		getTalkingto().setBounds(174, 60, 126, 25);
		frame.getContentPane().setLayout(null);

		notification= new JTextPane();
		notification.setBounds(420, 22, 279, 20);
		notification.setBackground(new Color(211, 211, 211));
		notification.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		
		panel.add(notification);
		panel.add(getTalkingto());
		panel.add(lblTalkingwith);
		
		panel.add(btnSend);
		panel.add(textField);
		
    	/*ImageIcon profil_pic = new ImageIcon();
    	profil_pic = createImageIcon("/images/profil_picture.png");*/
    	
		txtrB = new JTextArea();
		txtrB.setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 20));
		txtrB.setBackground(new Color(211, 211, 211));
		txtrB.setBounds(97, 35, 126, 25);
		txtrB.setText(app.getMe().getPseudo());
		panel.add(txtrB);
		
		JLabel lblNewLabel=new JLabel();
		lblNewLabel.setBounds(45, 28, 48, 28);
		lblNewLabel.setIcon(new ImageIcon(profil_pic.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		panel.add(lblNewLabel);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panel_1);
		
		//panel.add(scrolltextArea);
		//panel.add(textArea);
		
		usersconnected= new JList<String>(getApp().getFriends().getListPseudo());
        usersconnected.setBounds(0, 646, 272, -599);
        usersconnected.setBackground(new Color(95, 158, 160));
        //usersconnected.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		usersconnected.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		    	  if(evt.getValueIsAdjusting()) {
						 int userselect = usersconnected.getSelectedIndex();
						 if(userselect != -1) {
						 String usertalk = usersconnected.getSelectedValue();
						 loadconvo(getApp().getFriends().getUserfromPseudo(usertalk));
						 getTalkingto().append(""); 
						 Chats(getApp().getFriends().getUserfromPseudo(usersconnected.getSelectedValue()));
						 }
		    	  }

		        }
		      }
		);

        panel_2.add(usersconnected);

        frame.getContentPane().add(panel_1);


		
		frame.setVisible(true);
		udpListen.start();
	 	miseAJourContact();
	 	SocketServer.Receive(getApp().getMe().getPort());
		
	}
	


	/** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imageURL = Interface.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: "
                               + path);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
    }
	
    public static ImageIcon scaleImage(ImageIcon icon, int w, int h){
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > w){
          nw = w;
          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > h){
          nh = h;
          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
    

    
    //ouverture d'une communication
    public void Chats (User u2) {
    	System.out.println("talking to"+ u2.getPseudo());
  
    	
    	getTalkingto().append(u2.getPseudo()); //pour afficher à qui on parle
    	btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String msg=textField.getText();
            	SocketClient.SendMessage(msg,u2.getIP(),u2.getPort());
    			Home.getApp().getDb().addMessage(u2.getIP(), new Message(msg));
            	textField.setText("");          
            	loadconvo(u2);
            }
        });
    	
    	textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String msg=textField.getText();
            	SocketClient.SendMessage(msg,u2.getIP(),u2.getPort());
    			Home.getApp().getDb().addMessage(u2.getIP(), new Message(msg));
            	textField.setText(""); 
            	loadconvo(u2);
            }
        });
    	
    }


    public static void loadconvo(User u2) {
		ArrayList<Message> history= getApp().getDb().recupHistory(u2.getIP());
		String messages="";
		for(Message msg : history) {
			
			// Message envoye par moi
			if(msg.getSender().equals(getApp().getMe())) {
				messages+=msg.getData()+"  "+msg.getTime()+"  \n";
			}
			// Message envoye par l'autre utilisateur
			else {
				messages+="             "+msg.getData()+"  "+msg.getTime()+"  \n";

			}
			
		}

		// Affichage des messages
		textArea.setText(messages);
		
		// Scroll a la fin des messages
		textArea.setCaretPosition(textArea.getDocument().getLength());
		
	}
		
		
	

	public static void miseAJourContact() {
    	usersconnected.setListData(getApp().getFriends().getListPseudo());
    	//garder le pointeur du getSelectedValue même si qqn part 
    }
    
	//pour afficher le pseudo de l'utilisateur
	public static void pseudoModif() {
		txtrB.setText(app.getMe().getPseudo());
	}
 
	//pour afficher les erreurs
	public static void Error(String error) {
		JOptionPane.showMessageDialog(null, error, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	//fermer la page home
	public static void dispose() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UDPListener.setOuvert(false);
		frame.dispose();
	}

	public static Application getApp() {
		return app;
	}

	public static void setApp(Application app) {
		Home.app = app;
	}

	
	
	public static void display (String msg, String friend) {
		//textArea.append(Conversations.read_msg(friend));
		//textArea.append("\n"+msg);
	}
	
	public static void displayNotification(String IPfrom) {
		notification.setText("vous avez reçu un message de "+getApp().getFriends().getPseudofromIP(IPfrom));
		
	}
	
	
	public static JTextArea getTalkingto() {
		return talkingto;
	}

	public void setTalkingto(JTextArea talkingto) {
		this.talkingto = talkingto;
	}


	public class windowClosingListener implements WindowListener {
		
		public void windowClosing(WindowEvent e) {
			UDPListener.setOuvert(false);
			app.getcSystem().Deconnexion();
	        //Conversations.delete_historique();
		}
		
		public void windowOpened(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowActivated(WindowEvent arg0) {}
		public void windowDeactivated(WindowEvent arg0) {}

	}
}
