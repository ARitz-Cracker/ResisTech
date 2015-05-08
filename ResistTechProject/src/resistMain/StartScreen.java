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
	public StartScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblResistechCalculator = new JLabel("ResisTech Calculator");
		lblResistechCalculator.setHorizontalAlignment(SwingConstants.CENTER);
		lblResistechCalculator.setBounds(116, 11, 200, 50);
		contentPane.add(lblResistechCalculator);
		JButton btnQuick = new JButton("Quick");
		btnQuick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Hello, world!");
			}
		});
		btnQuick.setBounds(161, 63, 114, 42);
		contentPane.add(btnQuick);
		JButton btnNewButton = new JButton("Grid Mode");
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
		/*
		 * imageLabel = new JLabel(""); Image img = new
		 * ImageIcon(this.getClass()
		 * .getResource("/images/yourImageName.extension")).getImage();
		 * imageLabel.setIcon(new ImageIcon(img)); imageLabel.setBounds(168, 35,
		 * 46, 14); contentPane.add(imageLabel);
		 */
	}
}