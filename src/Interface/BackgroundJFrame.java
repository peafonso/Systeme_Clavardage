package system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BackgroundJFrame extends JFrame
	{
	  JButton b1;
	  JLabel l1;
	public BackgroundJFrame(String name)
	{
		super(name);
		//setTitle("Background Color for JFrame");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);


	// Another way
	setLayout(new BorderLayout());
	ImageIcon fond = new ImageIcon();
	if (name.contentEquals("CleverChat")) {
    	fond = createImageIcon("/images/Fond.png");
	}else {
		//setSize(1000,800);
	}
	setContentPane(new JLabel(fond));
	setLayout(new FlowLayout());

	// Just for refresh :) Not optional!
	   setSize(501,501);
	   setSize(500,500);
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

}
