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

public class QuickCalc extends JFrame {
	private JPanel contentPane;
	private JTextField resistor1;
	private JTextField resistor2;
	private JTextField resistor3;

	
	int resistNumber = 15;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuickCalc frame = new QuickCalc();
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
	public QuickCalc() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450,resistNumber*30+100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		int[] resistors = {};
		
		for(int i=0; i < resistNumber ; i++){
			JTextField textField = new JTextField(String.valueOf(7));
		    textField.setBounds(150, 20 + i * 25, 86, 20);
		    String resistvalue = textField.getText();  
		    int resistvalueint = Integer.parseInt(resistvalue);
		       
		    resistors[i] = resistvalueint;
			
		    contentPane.add(textField);
		    
		    /*JLabel lblResistor[i] = new JLabel("Resistor"+i);
			lblResistor[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblResistor[i].setBounds(224, 20 + i * 25, 200, 50);
			contentPane.add(lblResistor[i]);*/
		}
		
		JButton btnCalculate = new JButton("Calculate");
		
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				//System.out.println(resistFinal);
			}
		});
		btnCalculate.setBounds(155, 173, 127, 36);
		contentPane.add(btnCalculate);
	}
}