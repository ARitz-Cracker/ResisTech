package resistMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	
	//Shape Creation Variables
	final private int MODE_NONE = 0;
	final private int MODE_LINE = 1;
	final private int MODE_LOAD = 2;
	final private int MODE_DELETE = 3;
	final private int MODE_CALC_START = 4;
	final private int MODE_CALC_END = 5;
	
	private int mode = MODE_NONE;
	private boolean dragging = false;
	private int createPosX = 0;
	private int createPosY = 0;
	private JButton addLoadButt;
	
	private CircutLine[] lines = fnLineArray(32);
	private int creatingLine = -1;
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
						lines[creatingLine].setBounds(createPosX+length, createPosY, -length, 8);
						lines[creatingLine].SetOrientation((byte) 2);
						//System.out.println("2");
					}else{
						lines[creatingLine].setBounds(createPosX, createPosY, length, 8);
						lines[creatingLine].SetOrientation((byte) 0);
						//System.out.println("0");
					}
				}else{
					if (height < 0){
						lines[creatingLine].setBounds(createPosX, createPosY+height, 8, -height);
						lines[creatingLine].SetOrientation((byte) 3);
						//System.out.println("3");
					}else{
						lines[creatingLine].setBounds(createPosX, createPosY, 8, height);
						lines[creatingLine].SetOrientation((byte) 1);
						//System.out.println("1");
					}
				}
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO: CreateShape
				switch(mode){
				case MODE_NONE:
					break;
				case MODE_LINE:
					if (!dragging){
						createPosX = arg0.getX();
						createPosY = arg0.getY();
						for (int i=0;i<lines.length;i+=1){
							if (lines[i] == null){
								creatingLine = i;
								lines[i] = new CircutLine();
								lines[i].setBounds(createPosX, createPosY, 8, 8);
								contentPane.add(lines[i]);
								break;
							}
						}
					}
					dragging = !dragging;
					break;
				case MODE_LOAD:
					break;
				case MODE_DELETE:
					break;
				case MODE_CALC_START:
					break;
				case MODE_CALC_END:
					break;
				default:
					JOptionPane.showMessageDialog(null,
						    "Invalid mode during mousePressed.",
						    "This should never happen",
						    JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
			}

		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton addLineButt = new JButton("Add Circuitry");
		addLineButt.setToolTipText("Click to start a wire, and click again to end it.");
		addLineButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mode = MODE_LINE;
			}
		});
		addLineButt.setBounds(0, 0, 125, 29);
		contentPane.add(addLineButt);
		
		addLoadButt = new JButton("Add Load");
		addLoadButt.setToolTipText("Click on a line to add a resistor");
		addLoadButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == MODE_LINE){dragging = false;}
				mode = MODE_LOAD;
			}
		});
		addLoadButt.setBounds(125, 0, 125, 29);
		contentPane.add(addLoadButt);
		
		JButton delButt = new JButton("Delete");
		delButt.setToolTipText("Click on objects to delete a resistor/wire.");
		delButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mode == MODE_LINE){dragging = false;}
				mode = MODE_DELETE;
			}
		});
		delButt.setBounds(250, 0, 125, 29);
		contentPane.add(delButt);
	}
	private CircutLine[] fnLineArray(int m) {
		CircutLine arrButtons[] = new CircutLine[m];
		for (int i = 0; i < m; i += 1) {
			arrButtons[i].removeAll();
			arrButtons[i] = null;
		}
		return arrButtons;
	}
}
