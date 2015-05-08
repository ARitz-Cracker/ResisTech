package resistMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Quick2 extends JFrame {

	private JPanel contentPane;
	private JTextField resistor1;
	private JTextField resistor2;
	private JTextField resistor3;

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
		
		resistor1 = new JTextField();
		resistor1.setBounds(174, 26, 86, 20);
		contentPane.add(resistor1);
		resistor1.setColumns(10);
		
		resistor2 = new JTextField();
		resistor2.setColumns(10);
		resistor2.setBounds(174, 54, 86, 20);
		contentPane.add(resistor2);
		
		resistor3 = new JTextField();
		resistor3.setColumns(10);
		resistor3.setBounds(174, 85, 86, 20);
		contentPane.add(resistor3);
		
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
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCalculate.setBounds(155, 173, 127, 36);
		contentPane.add(btnCalculate);
	}

}
