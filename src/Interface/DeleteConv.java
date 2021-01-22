package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.Application;
import model.User;
import system.InteractiveChatSystem;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.SwingConstants;

//Fenetre de changement de pseudo
public class DeleteConv extends JFrame {

	private Application app;
	private JPanel contentPane;
	private JTextPane textPane;
	private JScrollPane scrollPane ;
	private JTextField txtYourHistoryOf;
	private JList<String> usersconnected;
	private JButton btnNewButton;
	private String userTalk;
	private JEditorPane editorPane;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public DeleteConv(Application app) {
		this.app=app;
		initialize();
	}
	
	public void initialize() {
		setTitle("Delete Conversations");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Label New Pseudo
		JLabel lblNewLabel = new JLabel("Delete conversation with");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel.setBounds(88, 23, 228, 43);
		contentPane.add(lblNewLabel);
		JPanel panel = new JPanel();

		//usersconnected= new JList<String>(app.getFriends().getListPseudo());
		//si historique vide
		//if ((usersconnected).getModel().getSize() == 0) {
			lblNewLabel_1 = new JLabel("Your history of conversations has been cleared.");
			lblNewLabel_1.setForeground(new Color(95, 158, 160));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel_1.setBounds(42, 202, 244, 23);
			panel.add(lblNewLabel_1);
		/*}else {	
			usersconnected.setBounds(65, 60, 260, 125);
			usersconnected.addListSelectionListener(new ListSelectionListener() {
			      public void valueChanged(ListSelectionEvent evt) {
			    	  if(evt.getValueIsAdjusting()) {
							 int userselect = usersconnected.getSelectedIndex();
							 if(userselect != -1) {
								 userTalk = usersconnected.getSelectedValue();

							 }
			    	  }
			      		
			        }
			      }
			);
			panel.add(usersconnected);
			//contentPane.add(usersconnected);
		}*/
		
		
		btnNewButton = new JButton("okay");
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnNewButton.setBounds(158, 210, 89, 23);	
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(userTalk.isEmpty())) {
					User userToForget;
					userToForget=app.getFriends().getUserfromPseudo(userTalk);
					app.getDb().deleteConvo(userToForget.getIP());
			    	System.out.println("Delete conv with"+ userTalk);
					dispose(); //ferme la fenetre

				}else{
					if ((usersconnected).getModel().getSize() == 0) {
						dispose(); //ferme la fenetre
					}
				}
			}
		});
		contentPane.add(btnNewButton);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 64, 264, 127);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(panel);
		

		
		setVisible(true);	

	}
}
