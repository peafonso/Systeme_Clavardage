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
		setTitle("Background Color for JFrame");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);


	// Another way
	setLayout(new BorderLayout());
	setContentPane(new JLabel(new ImageIcon("C:\\Users\\coumba\\Documents\\insa\\4A\\Cours\\COO\\Systeme_Clavardage\\src\\images\\Fond.png")));
	setLayout(new FlowLayout());

	// Just for refresh :) Not optional!
	   setSize(499,499);
	   setSize(500,500);
	   }

}
