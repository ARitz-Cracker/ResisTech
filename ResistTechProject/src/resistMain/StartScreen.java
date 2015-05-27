package resistMain;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartScreen extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void createNumberOfResistors() {
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen frame = new StartScreen();
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
	
	GUICalc gridMode;
	
	private void CreateGUICalc() {
		gridMode = new GUICalc();		
		gridMode.setVisible(false);
	}
	
	public StartScreen() {
		CreateGUICalc();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth() / 2 -  450 / 2, gd
				.getDisplayMode().getHeight() / 2 - 300 / 2, 450, 300);
		//setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblResistechCalculator = new JLabel("ResisTech Calculator");
		lblResistechCalculator.setHorizontalAlignment(SwingConstants.CENTER);
		lblResistechCalculator.setBounds(116, 11, 200, 50);
		contentPane.add(lblResistechCalculator);
		JButton btnQuick = new JButton("Quick Series");
		btnQuick.setBounds(263, 63, 114, 42);
		contentPane.add(btnQuick);
		btnQuick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean invalid = true;
				while (invalid) {
					try {
						String numberOfResistors = JOptionPane.showInputDialog("Please enter the number of resistors");
						if (numberOfResistors != null) {
							int popUps = Integer.parseInt(numberOfResistors);
							int sumOfResistors = 0;
							for (int i = 1; i < popUps + 1; i++) {
								String currentResistors = JOptionPane.showInputDialog("Enter the resistance of resistor " + i);
								sumOfResistors = sumOfResistors += Integer.parseInt(currentResistors);
							}
							JOptionPane.showMessageDialog(null, sumOfResistors);
						} 
						invalid = false;
					} catch (NumberFormatException e) {
						System.out.println("Invalid Input");
						JOptionPane.showMessageDialog(null,
								"Input must be integer values.");
						
					}
				}
			}
		});
		JButton btnNewButton = new JButton("Grid Mode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gridMode.setVisible(true);
			}
		});
		btnNewButton.setBounds(161, 141, 114, 42);
		contentPane.add(btnNewButton);
		JLabel lblCalculatesResistanceFrom = new JLabel(
				"Calculates resistance from user text input (Series only)");
		lblCalculatesResistanceFrom
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalculatesResistanceFrom.setFont(new Font("Times New Roman",
				Font.PLAIN, 10));
		lblCalculatesResistanceFrom.setBounds(104, 99, 235, 50);
		contentPane.add(lblCalculatesResistanceFrom);
		JLabel lblDragAndDrop = new JLabel("Drag and drop components");
		lblDragAndDrop.setHorizontalAlignment(SwingConstants.CENTER);
		lblDragAndDrop.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblDragAndDrop.setBounds(116, 179, 205, 50);
		contentPane.add(lblDragAndDrop);
		
		JButton btnQuickParallel = new JButton("Quick Parallel");
		btnQuickParallel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numberOfResistorsParallel = JOptionPane.showInputDialog("Please enter the number of resistors");
				
				if (numberOfResistorsParallel != null) {
					int popUpsParallel = Integer.parseInt(numberOfResistorsParallel);
					double parallelResistance = 0;
					double parallelResistanceTotal = 0;
					int parallelNumber = 0;
					for (int q = 1; q < popUpsParallel + 1; q++) {
						String currentResistorsParallel = JOptionPane.showInputDialog("Enter the resistance of resistor " + q);
						parallelNumber += Integer.parseInt(currentResistorsParallel);
						
						parallelResistance = 1/2;// parallelNumber;
						parallelResistanceTotal = 1/parallelResistance;
					}
					JOptionPane.showMessageDialog(null, parallelResistance);
				} 
				
				
			}
		});
		btnQuickParallel.setBounds(50, 63, 114, 42);
		contentPane.add(btnQuickParallel);

		/*
		 * imageLabel = new JLabel(""); Image img = new
		 * ImageIcon(this.getClass()
		 * .getResource("/images/yourImageName.extension")).getImage();
		 * imageLabel.setIcon(new ImageIcon(img)); imageLabel.setBounds(168, 35,
		 * 46, 14); contentPane.add(imageLabel);
		 */
	}
}