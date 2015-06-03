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
	private int oldLine = -1;
	private int newLine = -1;
	private CircutLine[] lines = fnLineArray(32);
	private int creatingLine = -1;
	private int startLine = -1;
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
					//oldLine
					if (dragging){
						if (oldLine == -1 || lines[oldLine].AddNext(lines[newLine].GetOrientation(), newLine)){
							dragging = false;
						}else{
							JOptionPane.showMessageDialog(null,
								    "You can't have lines overlap.",
								    "Something happened",
								    JOptionPane.ERROR_MESSAGE);
						}
						
					}else{
						newLine = StartLineCreation(arg0.getX(),arg0.getY(),-1);
						if (newLine == -1){
							JOptionPane.showMessageDialog(null,
								    "Too many lines, probably.",
								    "Something happened",
								    JOptionPane.ERROR_MESSAGE);
						}else{
							dragging = true;
						}
					}
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
				
				/*
				double seriesLOL = 0;
				double[]whatIsASeriesArray = {};
				double whatIsParallelCuzIDK = 0;
				double whatIsResistanceCuzIDK = 0;
				int len = 0;
				for(int w = 0;  w<lines.length; w++ ){
					len = lines[w].GetResistorCount();
					for(int u = 0;  u<len; u++ ){
						double gettingValues = lines[w].GetResistor(u).GetLoad();
						seriesLOL += gettingValues;
						whatIsASeriesArray[w] = seriesLOL;
					}
				}
				
				for(int t = 0; t < whatIsASeriesArray.length; t++){
					whatIsParallelCuzIDK += (double)1/(double)whatIsASeriesArray[t];
					whatIsResistanceCuzIDK = (double)1/(double) whatIsParallelCuzIDK;
				}
				JOptionPane.showMessageDialog(null, whatIsResistanceCuzIDK + "Ω");
				*/
				
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
		
		JButton selButt = new JButton("Select");
		selButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mode == MODE_LINE){dragging = false;}
				mode = MODE_NONE;
			}
		});
		selButt.setToolTipText("Click on objects to delete them.");
		selButt.setBounds(375, 0, 125, 29);
		contentPane.add(selButt);
		
		JButton calcButt = new JButton("Calculate!");
		calcButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				double total = Calculate(startLine,-1);
				System.out.println("Total value is:"+total);
				JOptionPane.showMessageDialog(null,
						"The total resistance is " + total + "Ω");
				}catch(RuntimeException e){
					JOptionPane.showMessageDialog(null,
						    e.getMessage(),
						    "Something happened",
						    JOptionPane.ERROR_MESSAGE);
				}catch(StackOverflowError e){
					JOptionPane.showMessageDialog(null,
						    "Infinite loop!",
						    "Nooooooo",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		calcButt.setToolTipText("Click on objects to delete them.");
		calcButt.setBounds(502, 0, 125, 29);
		contentPane.add(calcButt);
	}
	private CircutLine[] fnLineArray(int m) {
		CircutLine arrButtons[] = new CircutLine[m];
		for (int i = 0; i < m; i += 1) {
			//arrButtons[i].removeAll();
			arrButtons[i] = null;
		}
		return arrButtons;
	}
	
	private int GetEndOfBranch(int b1,int b2){
		int result = -1;
		int[] b1branch = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		byte b1branchlen = 0;
		while (b1 != -1){
			System.out.println("b1 = "+b1);
			b1branch[b1branchlen] = b1;
			b1 = lines[b1].GetNext();
			b1branchlen += 1;
		}
		while (b2 != -1 && result == -1){
			for (byte i=0;i<b1branchlen;i+=1){
				System.out.println("b2 = "+b2+"; b1branch[i] = "+b1branch[i]+"; i = "+i);
				if (b2 == b1branch[i]){
					result = b1branch[i];
					break;
				}
			}
			b2 = lines[b2].GetNext();
		}
		if (result==-1){
			throw new RuntimeException("There are some branches that exist which do not reconnect.");
		}
		return result;
		
	}
	private int GetEndOfBranch(int b1,int b2,int b3){
		int result = -1;
		int[] b1branch = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		byte b1branchlen = 0;
		while (b1 != -1){
			b1branch[b1branchlen] = b1;
			b1 = lines[b1].GetNext();
			b1branchlen += 1;
		}
		
		int[] b2branch = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		byte b2branchlen = 0;
		
		while (b2 != -1){
			for (byte i=0;i<b1branchlen;i+=1){
				if (b2 == b1branch[i]){
					b2branch[b2branchlen] = b1branch[i];
					b2branchlen += 1;
					break;
				}
			}
			b2 = lines[b2].GetNext();
		}
		
		while (b3 != -1 && result != -1){
			for (byte i=0;i<b2branchlen;i+=1){
				if (b3 == b2branch[i]){
					result = b2branch[i];
					break;
				}
			}
			b3 = lines[b3].GetNext();
		}
		
		if (result==-1){
			throw new RuntimeException("There are some branches that exist which do not reconnect.");
		}
		return result;
		
	}
	
	
	private double Calculate(int lin,int end){
		if (lin==end){return 0.0;}
		double result = lines[lin].GetTotalResistance();
		System.out.println("Line #"+lin+" has a load value of "+result);
		byte nextlinesplace = 0;
		int[] nextlines = {-1,-1,-1,-1};
		for (byte i=0;i<4;i+=1){
			int nex = lines[lin].GetNext(i);
			if (nex != -1){
				System.out.println("nex = "+nex);
				nextlines[nextlinesplace] = nex;
				nextlinesplace += 1;
			}
		}
		double parallelNumber = 0;
		int en = 0;
		switch(nextlinesplace){
		case 0:
			// Do nothing
			break;
		case 1:
			result = result + Calculate(nextlines[0],end);
			break;
		case 2:
			parallelNumber = 0;
			en = GetEndOfBranch(nextlines[0],nextlines[1]);
			for (int q = 0; q < 2; q++) {
				parallelNumber += (double) 1 / Calculate(nextlines[q],en);
			}
			result = result + (double) 1/ (double) parallelNumber + Calculate(en,end);
			break;
		default: // case 3:
			parallelNumber = 0;
			en = GetEndOfBranch(nextlines[0],nextlines[1],nextlines[2]);
			for (int q = 0; q < 3; q++) {
				parallelNumber += (double) 1 / Calculate(nextlines[q],en);
			}
			result = result + (double) 1/ (double) parallelNumber + Calculate(en,end);
	
		}

		
		
		return result;
		
	}
	
	
	private int StartLineCreation(int mx,int my,int curline){
		oldLine = curline;
		int result = -1;
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
							case MODE_NONE:
								//Custom button text
								if (JOptionPane.showOptionDialog(null,
								    "Would you like this line to be the starting point?",
								    "Start Line",
								    JOptionPane.YES_NO_OPTION,
								    JOptionPane.QUESTION_MESSAGE, null, null, null) == 0){
									startLine = argInt;
									System.out.println("New starting point is "+startLine);
								}
								break;
							case MODE_LINE:
								if (dragging){
									System.out.println("oldLine == "+oldLine);
									boolean valid = oldLine == -1 || lines[oldLine].AddNext(lines[newLine].GetOrientation(), newLine);
									//lines[argInt]
									for (byte i=0;i<4;i+=1){
										int nex = lines[argInt].GetNext(i);
										if (nex != -1){
											valid = valid && lines[newLine].AddNext(i, nex);
										}
									}									
									if (valid){
										dragging = false;
									}else{
										JOptionPane.showMessageDialog(null,
											    "You can't have lines overlap.",
											    "Something happened",
											    JOptionPane.ERROR_MESSAGE);
									}
								}else{
									int xpos = lines[argInt].getX() + arg0.getX();
									int ypos = lines[argInt].getY() + arg0.getY();
									switch(lines[argInt].GetOrientation()){
									case 0:
										xpos = lines[argInt].getX() + lines[argInt].getWidth();
										break;
									case 1:
										ypos = lines[argInt].getY() + lines[argInt].getHeight();
										break;
									case 2:
										xpos = lines[argInt].getX();
										break;
									case 3:
										ypos = lines[argInt].getY();
										break;
										default:
											JOptionPane.showMessageDialog(null,
												    "Circut has an invalid orientation. ",
												    "This should never happen",
												    JOptionPane.ERROR_MESSAGE);
											System.exit(1);
									}
									newLine = StartLineCreation(xpos, ypos,argInt);
									System.out.println("Old line: "+argInt);
									if (newLine == -1){
										JOptionPane.showMessageDialog(null,
											    "Too many lines, probably.",
											    "Something happened",
											    JOptionPane.ERROR_MESSAGE);
									}else{
										
										dragging = true;
									}
								}
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
													input = JOptionPane.showInputDialog("What do you want the resistance to be?");
													if (input == null) { // User has clicked cancel
														needinput = false;
													} else {
														
														lines[lineid].GetResistor(resistorid).SetLoad(Integer.parseInt(input));
														needinput = false;
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
					
					result = i;
					System.out.println(result);
					contentPane.add(lines[i]);
					break;
				}
			}
		}
		System.out.println(result);
		return result;
	}
}
