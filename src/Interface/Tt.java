package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Font;

public class Tt {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tt window = new Tt();
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
	public Tt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 204));
		frame.setBackground(SystemColor.activeCaption);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Tt.class.getResource("/images/ACCUEIL_FOND.png")));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 885, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textField = new JTextField();

		textField.setBounds(176, 458, 270, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enter pseudonym");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pseudo=textField.getText();
        		if (pseudo_ok(pseudo)) {
        			home();
        		}       		
        		
        	}
        });
		
		btnNewButton.setBounds(473, 446, 169, 61);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(204, 204, 204));
		lblNewLabel.setIcon(new ImageIcon(Tt.class.getResource("/images/Fond.png")));
		lblNewLabel.setBounds(162, 53, 504, 346);
		frame.getContentPane().add(lblNewLabel);

	}
	
    public static boolean pseudo_ok(String pseudo) {
    	return true;
    }
    
    private void home () {
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
    	
    	accueil.getContentPane().add(BorderLayout.NORTH, menu);

		accueil.setVisible(true);
    }
    
    
}
