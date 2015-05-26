package resistMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JProgressBar;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUICalc extends JFrame {
	//JFrame
	private JPanel contentPane;
	private JProgressBar progressBar;
	
	//Shape Creation Variables
	private boolean dragging = false;
	private int createPosX = 0;
	private int createPosY = 0;
	private JButton addLoadButt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUICalc frame = new GUICalc();
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
	public GUICalc() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 920, 480);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (!dragging){return;}
				int length = e.getX() - createPosX;
				int height = e.getY() - createPosY;
				if (Math.abs(length) > Math.abs(height)){
					if (length < 0){
						progressBar.setBounds(createPosX+length, createPosY, -length, 8);
					}else{
						progressBar.setBounds(createPosX, createPosY, length, 8);
					}
				}else{
					if (height < 0){
						progressBar.setBounds(createPosX, createPosY+height, 8, -height);
					}else{
						progressBar.setBounds(createPosX, createPosY, 8, height);
					}
				}
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO: CreateShape
				if (!dragging){
					createPosX = arg0.getX();
					createPosY = arg0.getY();
					progressBar.setBounds(createPosX, createPosY, 8, 8);
				}
				dragging = !dragging;
			}

		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		progressBar = new JProgressBar();
		progressBar.setBounds(230, 188, 146, 14);
		contentPane.add(progressBar);
		
		JButton addLineButt = new JButton("Add Lines");
		addLineButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addLineButt.setBounds(0, 0, 125, 29);
		contentPane.add(addLineButt);
		
		addLoadButt = new JButton("Add Load");
		addLoadButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addLoadButt.setBounds(125, 0, 125, 29);
		contentPane.add(addLoadButt);
	}
}