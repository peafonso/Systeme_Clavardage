package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Image;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;


public class Messenger extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Messenger frame = new Messenger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Messenger() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(10, 11, 290, 539);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 128));
		panel_2.setForeground(new Color(204, 204, 153));
		panel_2.setBounds(-15, -27, 77, 641);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon (createImageIcon("/images/home-page.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)); 
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(10, 35, 67, 57);
		panel_2.add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(195, 166, -76, -65);
		panel.add(table);
				
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 204, 153));
		panel_1.setBounds(310, 11, 611, 539);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 128));
		panel_3.setBounds(0, 0, 611, 498);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(new Color(211, 211, 211));
		textField.setBounds(10, 509, 514, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("send\r\n");
		btnNewButton.setBounds(534, 509, 67, 19);
		panel_1.add(btnNewButton);
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
