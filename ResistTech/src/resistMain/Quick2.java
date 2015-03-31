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

public class Quick2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quick2 frame = new Quick2();
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
	public Quick2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(174, 26, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(174, 54, 86, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(174, 85, 86, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(174, 116, 86, 20);
		contentPane.add(textField_3);
		
		JLabel lblResistor = new JLabel("Resistor 1");
		lblResistor.setHorizontalAlignment(SwingConstants.CENTER);
		lblResistor.setBounds(224, 11, 200, 50);
		contentPane.add(lblResistor);
		
		JLabel lblResistor_1 = new JLabel("Resistor 2");
		lblResistor_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblResistor_1.setBounds(224, 39, 200, 50);
		contentPane.add(lblResistor_1);
		
		JLabel lblResistor_2 = new JLabel("Resistor 3");
		lblResistor_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblResistor_2.setBounds(224, 70, 200, 50);
		contentPane.add(lblResistor_2);
		
		JLabel lblResistor_3 = new JLabel("Resistor 4");
		lblResistor_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblResistor_3.setBounds(224, 101, 200, 50);
		contentPane.add(lblResistor_3);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(155, 173, 127, 36);
		contentPane.add(btnCalculate);
	}

}
