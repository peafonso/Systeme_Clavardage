package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import control.Application;
import system.Conversation;
import system.InteractiveChatSystem;
import system.UDPListener;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import model.User;

public class Home {

	private JFrame frame;
	private Application app;
	private JTextField textField;
	private JPanel panel;

	/**
	 * Create the application.
	 */
	public Home(Application app) {
		this.app=app;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new BackgroundJFrame("Home");
		frame.setBackground(new Color(240, 240, 240));
		//frame.setBounds(100, 100, 1640, 920);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
        JButton btnNewButton = new JButton("Home");
        btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        btnNewButton.setBackground(new Color(153, 153, 153));
        menuBar.add(btnNewButton);
        
  
        JMenu mntmNewMenuItem_1 = new JMenu("Settings");
    	JMenuItem mPseudo= new JMenuItem("Change Pseudo");
    	mPseudo.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        mntmNewMenuItem_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.add(mPseudo);
        frame.getContentPane().setLayout(null);
        
        JButton btnDeconnexion = new JButton("Deconnect");
        btnDeconnexion.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        btnDeconnexion.setBackground(new Color(153, 153, 153));
        menuBar.add(btnDeconnexion);
        btnDeconnexion.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		
        	}
        	}
        );
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(211, 211, 211));
        panel.setBounds(100, 101, 724, 430);
        frame.getContentPane().add(panel);
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(95, 158, 160));
        panel_1.setBounds(1000, 0, 307, 690);
        frame.getContentPane().add(panel_1);

        System.out.println(app.getFriends().getListPseudo());
        JList<String> list = new JList<String>(app.getFriends().getListPseudo());
        panel_1.add(list);
        

		/*accueil = new JFrame("CleverChat - Accueil");
    	accueil.setSize(1000, 800);
    	
    	//centrer la fenetre au milieu de l'ecran
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        accueil.setLocation(dim.width/2 - accueil.getWidth()/2, dim.height/2 - accueil.getHeight()/2);

    	JMenuBar menu= new JMenuBar();
    	JMenu m1 =new JMenu("HOME");
    	JMenu m2= new JMenu("CHAT");
    	menu.add(m1);
    	menu.add(m2);
    	
    	accueil.getContentPane().add(BorderLayout.NORTH, menu);*/

		frame.setVisible(true);
		UDPListener udpListen = new UDPListener();
		udpListen.start();
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
    	Conversation conv= new Conversation(u2,app);
    	textField = new JTextField();
		textField.setBackground(new Color(211, 211, 211));
		textField.setBounds(10, 509, 514, 19);
		//panel_1.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	conv.sendMessage(textField.getText());
                textField.setText("");   
            }
        });
    	JButton btnSend = new JButton("send\r\n");
    	btnSend.setBounds(534, 509, 67, 19);
    	btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	conv.sendMessage(textField.getText());
                textField.setText("");          
           }
        });
    }
     
    public void etablirContacts() {
    	//TODO
    }
    
    public void miseAJourContact() {
    	//TODO
    }
}
