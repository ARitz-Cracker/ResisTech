package resistMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Quick extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quick frame = new Quick();
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
	public Quick() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(174, 68, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNumberOfResistors = new JLabel("Number of Resistors");
		lblNumberOfResistors.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfResistors.setBounds(224, 53, 200, 50);
		contentPane.add(lblNumberOfResistors);
		
		JButton btnNewButton = new JButton("Continue");
		btnNewButton.setBounds(153, 137, 127, 44);
		contentPane.add(btnNewButton);
	}
}
