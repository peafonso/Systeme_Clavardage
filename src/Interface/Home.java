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
import java.io.File;
import java.io.IOException;

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
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.Application;
import system.Conversation;
import system.InteractiveChatSystem;
import system.SocketClient;
import system.SocketServer;
import system.UDPListener;

import java.awt.Font;
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

public class Home {

	private static Application app;
	private static JFrame frame;
	private JTextField textField;
	private JPanel panel;
	private JButton btnSend;
	private static JTextArea textArea;
	private JScrollPane scrolltextArea;
	private JTextArea talkingto;
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//frame.setSize(1600,900);
		ImageIcon homePicture = new ImageIcon();
		homePicture=createImageIcon("/images/ACCUEIL_FOND2.jpg");
		
    	//centrer la fenetre au milieu de l'ecran
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	frame.setSize(dim);
        frame.setLocation(dim.width/3 - frame.getWidth()/3, dim.height/3 - frame.getHeight()/3);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setMargin(new Insets(1000, 0, 1000, 0));
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
    	
        mntmNewMenuItem_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.add(mPseudo);
        frame.getContentPane().setLayout(null);
        
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
        panel.setBackground(new Color(211, 211, 211));
        panel.setBounds(100, 101, 724, 430);
        panel.setLayout(null);

        frame.getContentPane().add(panel);
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(95, 158, 160));
        panel_1.setBounds(1000, 0, 307, 690);
        
        JLabel lblcontacts = new JLabel("users connected\r\n");
        lblcontacts.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        lblcontacts.setBounds(64, 0, 161, 32);
        panel_1.add(lblcontacts);
        
        usersconnected= new JList<String>(getApp().getFriends().getListPseudo());
        usersconnected.setBounds(0, 646, 272, -599);
		usersconnected.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		      		talkingto.append(""); 
		           Chats(getApp().getFriends().getUserfromPseudo(usersconnected.getSelectedValue()));
		        }
		      }
		);
    
        panel_1.add(usersconnected);
        frame.getContentPane().add(panel_1);
        
        //TextField pour rédiger son message
        textField = new JTextField();
        textField.setBackground(Color.WHITE);
        textField.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		textField.setBounds(80, 373, 453, 33);
		textField.setColumns(10);
		
		//Bouton Send
    	btnSend = new JButton("send");
    	btnSend.setBackground(SystemColor.activeCaption);
    	btnSend.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
    	btnSend.setBounds(558, 371, 76, 38);
    	
    	textArea = new JTextArea();
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setBounds(58, 53, 591, 296);
		textArea.setEditable(false);
		
		scrolltextArea = new JScrollPane();
		scrolltextArea.setViewportView(textArea);
		scrolltextArea.setVisible(true);

		JLabel lblTalkingwith = new JLabel("Talking with");
		lblTalkingwith.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblTalkingwith.setBounds(60, 11, 126, 31);
		talkingto = new JTextArea();
		talkingto.setBackground(new Color(211, 211, 211));
		talkingto.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		talkingto.setBounds(177, 12, 76, 25);

		notification= new JTextPane();
		notification.setBounds(420, 22, 279, 20);
		notification.setBackground(new Color(211, 211, 211));
		notification.setFont(new Font("Bahnschrift", Font.PLAIN, 8));
		
		panel.add(notification);
		panel.add(talkingto);
		panel.add(lblTalkingwith);
		
		panel.add(btnSend);
		panel.add(textField);
		

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
  
    	talkingto.append(u2.getPseudo()); //pour afficher à qui on parle
    	btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String msg=textField.getText();
            	SocketClient.SendMessage(msg,u2.getIP(),u2.getPort());
            	textField.setText("");          
            	display(msg);
            }
        });
    	
    	textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String msg=textField.getText();
            	SocketClient.SendMessage(msg,u2.getIP(),u2.getPort());
            	textField.setText(""); 
            	display(msg);
            }
        });
    	panel.add(scrolltextArea);
		panel.add(textArea);
    }

    public static void miseAJourContact() {
    	usersconnected.setListData(getApp().getFriends().getListPseudo());
    }
    
  
    
	//pour afficher les erreurs
	public static void Error(String error) {
		JOptionPane.showMessageDialog(null, error, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	//fermer la page home
	public static void dispose() {
		//app.getcSystem().Deconnexion();
		frame.dispose();
	}

	public static Application getApp() {
		return app;
	}

	public static void setApp(Application app) {
		Home.app = app;
	}

	public static void display (String msg) {
		textArea.append("\n"+msg);
	}
	
	public static void displayNotification(String IPfrom) {
		notification.setText("vous avez reçu un message de "+getApp().getFriends().getUserfromIP(IPfrom));
	}
}
