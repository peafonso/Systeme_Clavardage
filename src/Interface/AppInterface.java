package Interface;

import control.*;
import model.*;
import system.*;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import java.awt.ScrollPane;
import javax.swing.JPasswordField;
import java.awt.Label;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AppInterface {
	
	private Application app;
	private JFrame frame;
	private JPanel panel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppInterface window = new AppInterface();
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
	public AppInterface() {
		User u1= new User();
		app= new Application(u1);
		app.setcSystem(new InteractiveChatSystem(app));
		app.setDb(new Database(app));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new BackgroundJFrame("connexion");
		frame.setForeground(new Color(26, 104, 104));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(26, 104, 104));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 885, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
    	//centrer la fenetre au milieu de l'ecran
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - frame.getWidth()/2, dim.height/2 - frame.getHeight()/2);
		
        panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(102, 153, 153));
		panel.setBounds(260, 62, 365, 401);
		frame.getContentPane().add(panel);
		
		
    	ImageIcon fond_conn = new ImageIcon();
    	fond_conn = createImageIcon("/images/LOGONetwork.png");
		JLabel label = new JLabel(fond_conn);
		label.setBounds(78, 7, 213, 213);
		
		textField = new JTextField("Enter pseudonym");
		textField.setBounds(53, 252, 270, 37);
		textField.setBackground(new Color(204, 204, 204));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		textField.setColumns(10);
		
		textField.getFont().deriveFont(Font.ITALIC);
		textField.setForeground(Color.gray);
		textField.addMouseListener(new MouseListener() {           
			@Override
			public void mouseReleased(MouseEvent e) {}         
			@Override
			public void mousePressed(MouseEvent e) {}          
			@Override
			public void mouseExited(MouseEvent e) {}           
			@Override
			public void mouseEntered(MouseEvent e) {}          
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField texteField = ((JTextField)e.getSource());
				texteField.setText("");
				texteField.getFont().deriveFont(Font.PLAIN);
				texteField.setForeground(Color.black);
				texteField.removeMouseListener(this);
			}
		});
		
		//pour qu'on puisse rentrer en apuyant sur la touche entree aussi 
		textField.addActionListener(new Connect());
		
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBounds(103, 307, 169, 61);
		btnNewButton.setBackground(new Color(204, 204, 204));
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new Connect());
		
		panel.setLayout(null);
		panel.add(label);
		panel.add(textField);
		panel.add(btnNewButton);
		

	}
	
	
	//--Action Listener sur le boutton et touche entrée pour tester le pseudo et se connecter
	public class Connect implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String pseudo=textField.getText();
			
			if(pseudo.length()>12) {
				JTextPane txtlongpseudo = new JTextPane();
				txtlongpseudo.setBackground(new Color(102, 153, 153));
				txtlongpseudo.setText("Only 12 caracters are allowed");
				txtlongpseudo.setForeground(new Color(255, 51, 51));
				txtlongpseudo.setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 11));
				txtlongpseudo.setBounds(88, 231, 203, 20);
				panel.add(txtlongpseudo);
				textField.addMouseListener(new MouseListener() {           
					@Override
					public void mouseReleased(MouseEvent e) {}         
					@Override
					public void mousePressed(MouseEvent e) {}          
					@Override
					public void mouseExited(MouseEvent e) {}           
					@Override
					public void mouseEntered(MouseEvent e) {}          
					@Override
					public void mouseClicked(MouseEvent e) {
						JTextField texteField = ((JTextField)e.getSource());
						texteField.setText("");
						texteField.getFont().deriveFont(Font.PLAIN);
						texteField.setForeground(Color.black);
						texteField.removeMouseListener(this);
					}
				});
			}
			else {
				//connexion
				if (app.getcSystem().Connexion(pseudo)) {
					app.getMe().setPseudo(pseudo);
					openHome();
				} else {
					JTextPane txtpnPseudonymAlreadyIn = new JTextPane();
					txtpnPseudonymAlreadyIn.setBackground(new Color(102, 153, 153));
					txtpnPseudonymAlreadyIn.setText("Pseudonym already in use. Try Again.");
					txtpnPseudonymAlreadyIn.setForeground(new Color(255, 51, 51));
					txtpnPseudonymAlreadyIn.setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 11));
					txtpnPseudonymAlreadyIn.setBounds(88, 231, 203, 20);
					panel.add(txtpnPseudonymAlreadyIn);
					textField.addMouseListener(new MouseListener() {           
						@Override
						public void mouseReleased(MouseEvent e) {}         
						@Override
						public void mousePressed(MouseEvent e) {}          
						@Override
						public void mouseExited(MouseEvent e) {}           
						@Override
						public void mouseEntered(MouseEvent e) {}          
						@Override
						public void mouseClicked(MouseEvent e) {
							JTextField texteField = ((JTextField)e.getSource());
							texteField.setText("");
							texteField.getFont().deriveFont(Font.PLAIN);
							texteField.setForeground(Color.black);
							texteField.removeMouseListener(this);
						}
					});
				}
    		
			}
		}
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

    
    private void openHome () {
    	frame.setVisible(false);
		new Home(app);
		frame.dispose();
    }

    

    
}
