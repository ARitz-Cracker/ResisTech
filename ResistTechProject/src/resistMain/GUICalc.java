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

public class GUICalc extends JFrame {
	private JPanel contentPane;
	private JProgressBar progressBar;
	private boolean dragging = false;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 480);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (!dragging){return;}
				int length = e.getX() - progressBar.getX();
				int height = e.getY() - progressBar.getY();
				if (Math.abs(length) > Math.abs(height)){
					if (length < 0){
						progressBar.setBounds(progressBar.getX()+length, progressBar.getY(), -length, 8);
					}else{
						progressBar.setBounds(progressBar.getX(), progressBar.getY(), length, 8);
					}
				}else{
					if (height < 0){
						progressBar.setBounds(progressBar.getX(), progressBar.getY()+height, 8, -height);
					}else{
						progressBar.setBounds(progressBar.getX(), progressBar.getY(), 8, height);
					}
				}
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO: CreateShape
				if (!dragging){
					progressBar.setBounds(arg0.getX() - 4, arg0.getY() - 4, 8, 8);
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
	}
}