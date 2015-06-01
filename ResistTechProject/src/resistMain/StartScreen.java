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
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth() / 2 - 450 / 2, gd
				.getDisplayMode().getHeight() / 2 - 300 / 2, 450, 300);
		// setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnQuick = new JButton("Quick Series");
		btnQuick.setBounds(260, 157, 114, 42);
		contentPane.add(btnQuick);
		btnQuick.setToolTipText("Calculate the resistance, in series, of multiple resistors");
		btnQuick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean invalid = true;
				while (invalid) {
					try {
						String numberOfResistors = JOptionPane
								.showInputDialog("Please enter the number of resistors");
						if (numberOfResistors != null) {
							int popUps = Integer.parseInt(numberOfResistors);
							int sumOfResistors = 0;
							for (int i = 1; i < popUps + 1; i++) {
								String currentResistors = JOptionPane
										.showInputDialog("Enter the resistance of resistor "
												+ i);
								sumOfResistors = sumOfResistors += Integer
										.parseInt(currentResistors);
							}
							JOptionPane.showMessageDialog(null, sumOfResistors
									+ " Ω");
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
		btnNewButton
				.setToolTipText("Calculate the resistance of a circuit in a GUI");
		btnNewButton.setBounds(165, 218, 114, 42);
		contentPane.add(btnNewButton);

		JButton btnQuickParallel = new JButton("Quick Parallel");
		btnQuickParallel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean errorCheck = true;
				while (errorCheck) {
					try {
						String numberOfResistorsParallel = JOptionPane
								.showInputDialog("Please enter the number of resistors");

						if (numberOfResistorsParallel != null) {
							int popUpsParallel = Integer
									.parseInt(numberOfResistorsParallel);
							double parallelResistance = 0;
							double parallelNumber = 0;
							for (int q = 1; q < popUpsParallel + 1; q++) {
								String currentResistorsParallel = JOptionPane
										.showInputDialog("Enter the resistance of resistor "
												+ q);
								parallelNumber += (double) 1
										/ Double.parseDouble(currentResistorsParallel);

								parallelResistance = (double) 1
										/ (double) parallelNumber;

							}
							JOptionPane.showMessageDialog(null,
									parallelResistance + "Ω");
						}

						errorCheck = false;
					} catch (IllegalArgumentException e1) {
						System.out.println("Invalid Input");
						JOptionPane.showMessageDialog(null,
								"Input must be a number.");

					}
				}

			}
		});
		btnQuickParallel
				.setToolTipText("Calculate the resistance, in parallel, of multiple resistors");
		btnQuickParallel.setBounds(70, 157, 114, 42);
		contentPane.add(btnQuickParallel);
		try{
			BufferedImage buttonIcon;
			buttonIcon = ImageIO.read(new File("resources/resistech.png"));
			JButton btnNewButton_1 = new JButton(new ImageIcon(buttonIcon));
			
			btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
			btnNewButton_1.setContentAreaFilled(false);
			btnNewButton_1.setBounds(0, 0, 444, 152);
			contentPane.add(btnNewButton_1);
			btnNewButton_1.setBorderPainted(false);
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(null,
					"Cannot find resistech.png");
		}

		/*
		 * imageLabel = new JLabel(""); Image img = new
		 * ImageIcon(this.getClass()
		 * .getResource("/images/yourImageName.extension")).getImage();
		 * imageLabel.setIcon(new ImageIcon(img)); imageLabel.setBounds(168, 35,
		 * 46, 14); contentPane.add(imageLabel);
		 */
	}
}

/*double[]whatIsAnArray = {2,69,200,690};
double whatIsParallelCuzIDK = 0;
double whatIsResistanceCuzIDK = 0;
for(int p = 0; p < whatIsAnArray.length; p++){
	whatIsParallelCuzIDK += (double)1/(double)whatIsAnArray[p];
	whatIsResistanceCuzIDK = (double)1/(double) whatIsParallelCuzIDK;
}
JOptionPane.showMessageDialog(null, whatIsResistanceCuzIDK + "Ω");*/

/*double[]whatIsASeriesArray = {2,69,200,690};
double seriesLOL = 0;
for(int t = 0; t < whatIsASeriesArray.length; t++){
	seriesLOL += whatIsASeriesArray[t];
}
JOptionPane.showMessageDialog(null, seriesLOL + "Ω");*/

