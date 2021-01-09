package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Application;
import system.InteractiveChatSystem;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.SystemColor;

//Fenetre de changement de pseudo
public class Settings extends JFrame {

	private Application app;
	private JPanel contentPane;
	private JTextField textField;
	private JTextPane textPane;

	/**
	 * Create the frame.
	 */
	public Settings(Application app) {
		this.app=app;
		initialize();
	}
	
	public void initialize() {
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Label New Pseudo
		JLabel lblNewLabel = new JLabel("Your new pseudo\r\n");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel.setBounds(118, 47, 266, 43);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(35, 108, 214, 36);
		contentPane.add(textField);
		textField.setColumns(10);
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
		
		JButton btnNewButton = new JButton("okay");
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnNewButton.setBounds(277, 116, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pseudo=textField.getText();
				if(pseudo.length()>8) {
					JTextPane txtlongpseudo = new JTextPane();
					txtlongpseudo.setText("Only 8 caracters are allowed");
					txtlongpseudo.setForeground(new Color(255, 51, 51));
					txtlongpseudo.setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 11));
					txtlongpseudo.setBackground(SystemColor.menu);
					txtlongpseudo.setBounds(103, 80, 204, 14);
					contentPane.add(txtlongpseudo);
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
					if (app.getcSystem().ChangePseudo(pseudo, 4445)) {
						app.getMe().setPseudo(pseudo);
						dispose(); //ferme la fenetre
					} else {
						JTextPane txtpnPseudonymAlreadyIn = new JTextPane();
						txtpnPseudonymAlreadyIn.setText("Pseudonym already in use. Try Again.");
						txtpnPseudonymAlreadyIn.setBackground(SystemColor.menu);
						txtpnPseudonymAlreadyIn.setForeground(new Color(255, 51, 51));
						txtpnPseudonymAlreadyIn.setFont(new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 11));
						txtpnPseudonymAlreadyIn.setBounds(103, 80, 204, 14);

						contentPane.add(txtpnPseudonymAlreadyIn);
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
		});
		contentPane.add(btnNewButton);
		setVisible(true);
	}
}
