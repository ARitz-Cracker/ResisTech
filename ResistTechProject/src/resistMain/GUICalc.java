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
				switch(mode){
				case MODE_NONE:
					break;
				case MODE_LINE:
					StartLineCreation(arg0.getX(),arg0.getY());
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
		delButt.setToolTipText("Click on objects to delete them.");
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
			//arrButtons[i].removeAll();
			arrButtons[i] = null;
		}
		return arrButtons;
	}
	private void StartLineCreation(int mx,int my){
		if (!dragging){
			createPosX = mx;
			createPosY = my;
			for (int i=0;i<lines.length;i+=1){
				if (lines[i] == null){
					creatingLine = i;
					lines[i] = new CircutLine();
					lines[i].setBounds(createPosX, createPosY, 8, 8);
					lines[i].SetPanel(contentPane);
					lines[i].addMouseListener(new MouseAdapterThatTakesInGoddamnArguments(i) {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							switch(mode){
							case MODE_LINE:
								StartLineCreation(lines[argInt].getX() + arg0.getX(), lines[argInt].getY() + arg0.getY());
								break;
							
							case MODE_DELETE:

								for (int i=lines[argInt].GetResistorCount()-1;i>=0;i-=1){
									lines[argInt].RemoveResistor(i);
								}
								lines[argInt].setVisible(false); // TODO: Figure out how to do this better.
								lines[argInt].removeAll();
								lines[argInt] = null;
								
								break;
							case MODE_LOAD:
								System.out.println(contentPane.getX());
								Resistor rstr = lines[argInt].SetResistor(lines[argInt].getX() + arg0.getX(), lines[argInt].getY() + arg0.getY());
								rstr.setText("0Ω");
								rstr.setActionCommand(Integer.toString(argInt)+"|"+Integer.toString(rstr.GetID())); //Pass the id to the button action so we know what button they actually pressed!
								rstr.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										System.out.println(arg0.getActionCommand());
										String[] strs = arg0.getActionCommand().split("|");
										for (int i=0;i<strs.length;i+=1){
											System.out.println(i+ " => "+strs[i]);
										}
										int lineid = Integer.parseInt(strs[0]);
										int resistorid = Integer.parseInt(strs[2]);
										switch(mode){
										case MODE_DELETE:
											lines[lineid].RemoveResistor(resistorid);
											break;
											default:
												boolean needinput = true;
												String input;
												while (needinput) {
													try{
													input = JOptionPane.showInputDialog("You got a high score! Enter your name so everyone can see good you are at clicking things!");
													if (input == null) { // User has clicked cancel
														needinput = false;
													} else {
														
														lines[lineid].GetResistor(resistorid).SetLoad(Integer.parseInt(input));
													}
													}catch(NumberFormatException e){
														JOptionPane.showMessageDialog(null,
																"Input must be integer values.");
													}
												}
										}
										
									}
								});
								default:
							}
						}
					});
					
					contentPane.add(lines[i]);
					break;
				}
			}
		}
	}
}
