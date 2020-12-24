package Interface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Interface implements ActionListener{
	
	//Fenetre avant connexion de bienvenue avec register/login
	//Fenetre connexion refusee
	//Fenetre connexion en chargement au logiciel
	//Fenetre principale avec liste users et historique des connexions
	//Fenetre chat avec un user
	//Fenetre deconnexion
	public static void bienvenue () {
		JFrame frame = new BackgroundJFrame("CleverChat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(500, 500);
    	JMenuBar mb= new JMenuBar();
    	JMenu m1 =new JMenu("File");
    	JMenu m2= new JMenu("Help");
    	mb.add(m1);
    	mb.add(m2);
    	
    	JPanel panel1= new JPanel();
   
        
    	//bouton login
    	ImageIcon log = new ImageIcon();
    	log = createImageIcon("/images/LOGIN_bis.png");
    	log = scaleImage (log, 200,100);
        JButton login = new JButton(log);
        JLabel identifier = new JLabel("PSEUDONYM");
		JTextField p = new JTextField(10);
		panel1.add(identifier);
		panel1.add(p);
		
		//ouverture fenetre accueil sur verification du login
        login.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		String pseudo=p.getText();
        		if (pseudo_ok(pseudo)) {
        			frame.setVisible(false);
        			JFrame accueil = new JFrame("CleverChat - Accueil");
        	    	accueil.setSize(1000, 800);
        	    	
        	    	//centrer la fenetre au milieu de l'ecran
        	    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        	        accueil.setLocation(dim.width/2 - accueil.getWidth()/2, dim.height/2 - accueil.getHeight()/2);

        	    	JMenuBar menu= new JMenuBar();
        	    	JMenu m1 =new JMenu("HOME");
        	    	JMenu m2= new JMenu("CHAT");
        	    	menu.add(m1);
        	    	menu.add(m2);
        	    	
        	    	ImageIcon fond_acc = new ImageIcon();
        	    	fond_acc = createImageIcon("/images/ACCUEIL_FOND.png");
        	    	fond_acc = scaleImage (fond_acc, 1000,700);
        	    	
        	    	JLabel welcome= new JLabel("WELCOME", JLabel.CENTER);
        	    	JLabel imageFond = new JLabel(fond_acc, JLabel.CENTER);
        	    	imageFond.add(welcome);
        	    	welcome.setFont(new Font("Serif", Font.BOLD, 100));
        	    	accueil.getContentPane().add(BorderLayout.NORTH, menu);
        	    	//accueil.getContentPane().add(BorderLayout.NORTH, welcome);
        	    	accueil.getContentPane().add(BorderLayout.CENTER, imageFond);

        			accueil.setVisible(true);
        		}       		
        		
        	}
        });
        
        panel1.add(login);
   	
        //panel2.add(label);
    	frame.getContentPane().add(BorderLayout.SOUTH, panel1);
    	//frame.getContentPane().add(BorderLayout.NORTH, panel2);

    	//centrer la fenetre au milieu de l'ecran
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
    
    public static boolean pseudo_ok(String pseudo) {
    	return true;
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
    	/*test bouton login
    	ImageIcon log = new ImageIcon();
    	log = createImageIcon("/images/LOGIN_bis.png");
    	log = scaleImage (log, 200,100);
        JButton login = new JButton(log);
        
    	if (e.getActionCommand().equals(login)) {
    		
    	}*/
		
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
