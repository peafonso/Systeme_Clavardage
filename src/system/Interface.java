package system;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class Interface {
	
	//Fenêtre avant connexion de bienvenue avec register/login
	//Fenêtre connexion refusée
	//Fenêtre connexion en chargement au logiciel
	//Fenêtre principale avec liste users et historique des connexions
	//Fenêtre chat avec un user
	//Fenêtre deconnexion
	public static void bienvenue () {
		JFrame frame = new JFrame("CleverChat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(400, 400);
    	JMenuBar mb= new JMenuBar();
    	JMenu m1 =new JMenu("File");
    	JMenu m2= new JMenu("Help");
    	mb.add(m1);
    	mb.add(m2);
    	ImageIcon reg = new ImageIcon();
    	reg = createImageIcon("/images/LOGIN(1).png");
        JButton register = new JButton("register",reg);

        /*JPanel panneau = new JPanel();
        panneau.add(bouton);
        setContentPane(panneau);
        setSize(200,100);*/
    	
    	JButton login= new JButton("Login");
    	JPanel panel= new JPanel();
    	panel.add(register);
    	panel.add(login);
    	frame.getContentPane().add(BorderLayout.CENTER, panel);
    	
    	//centrer la fenêtre au milieu de l'écran
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - frame.getWidth()/2, dim.height/2 - frame.getHeight()/2);
        
    	frame.setVisible(true);
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
    
	public static void main(String[] args) {
		/*JFrame frame= new JFrame("Chat Frame");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(400, 400);
    	JMenuBar mb= new JMenuBar();
    	JMenu m1 =new JMenu("File");
    	JMenu m2= new JMenu("Help");
    	mb.add(m1);
    	mb.add(m2);
    	JMenuItem m11= new JMenuItem("Open");
    	JMenuItem m22= new JMenuItem("Save as");
    	m1.add(m11);
    	m2.add(m22);
    	JPanel panel= new JPanel();
    	JLabel label= new JLabel("Enter text");
    	JTextField tf= new JTextField(10);
    	JButton send = new JButton("Send");
    	JButton reset= new JButton("Reset");
    	panel.add(label);
    	panel.add(label);
    	panel.add(tf);
    	panel.add(send);
    	panel.add(reset);
    	JTextArea ta =new JTextArea();
    	frame.getContentPane().add(BorderLayout.SOUTH, panel);
    	frame.getContentPane().add(BorderLayout.NORTH, mb);
    	frame.getContentPane().add(BorderLayout.CENTER, tf);
    	frame.setVisible(true);*/
		bienvenue();
		
		
	}
	
}
