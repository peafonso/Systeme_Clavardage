package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Application;
import system.UDPListener;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JLabel;

public class Disconnect extends JFrame {

	private Application app;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Disconnect(Application app) {
		this.app=app;
		initialize();
	}
		
	public void initialize() {
		setTitle("Disconnect");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("yes");
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnNewButton.setBounds(118, 107, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UDPListener.setOuvert(false);
				app.getcSystem().Deconnexion();;
				dispose();
				Home.dispose();
				
			}
		});

		
		JLabel lblNewLabel = new JLabel("Are you certain you want to disconnect? ");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 19));
		lblNewLabel.setBounds(30, 43, 377, 72);
		contentPane.add(lblNewLabel);
		
		JButton btnNo = new JButton("no");
		btnNo.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnNo.setBounds(217, 107, 89, 23);
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		contentPane.add(btnNo);
		
		setVisible(true);
	}
}
