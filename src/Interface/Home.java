package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(240, 240, 240));
		//frame.setBounds(100, 100, 1640, 920);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600,900);
		
		JLabel lblNewLabel = new JLabel("New label");
		ImageIcon homePicture = new ImageIcon();
		homePicture=createImageIcon("/images/ACCUEIL_FOND.png");
		scaleImage(homePicture, 900, 800);

		lblNewLabel.setIcon(homePicture);
		frame.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
    	//centrer la fenetre au milieu de l'ecran
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - frame.getWidth()/2, dim.height/2 - frame.getHeight()/2);
		
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

}
