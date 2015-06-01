package resistMain;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class CircutLine extends JProgressBar {
	//Functions that Create an array of objects
	final int maxResistors = 16;
	private byte orientation = 0;
	private int[] nextLine = {-1,-1,-1,-1};
	//East = 0
	//South = 1
	//West = 2
	// North = 3
	
	private Resistor resistors[] = fnResistorArray(maxResistors);
	JPanel contentplane = null; 
	public void SetPanel(JPanel pan){
		contentplane = pan;
	}
	
	public void SetOrientation(byte val){
		orientation = val;
	}
	public boolean addNext(byte orient,int val){
		if ((orient!=orientation && orient%1==orientation%1)||nextLine[orient] != -1){ 
			return false;
		}
		nextLine[orientation] = val;
		return true;
	}
	public int getNext(byte orient,int val){
		return nextLine[orient];
	}
	public int GetResistorCount(){
		int result = 0;
		for (int i=0;i<maxResistors;i+=1){
			if (resistors[i] == null){
				result = i;
				break;
			}
		}
		return result;
	}
	
	public Resistor GetResistor(int id){
		if (id < 0 || id > maxResistors){
			throw new ArrayIndexOutOfBoundsException();
		}
		if (resistors[id] == null){
			throw new ArrayIndexOutOfBoundsException();//Out of the bounds set by GetResistorCount(), anyway.
		}
		return resistors[id];
	}
	
	public void RemoveResistor(int id){
		if (id < 0 || id > maxResistors){
			throw new ArrayIndexOutOfBoundsException();
		}
		if (resistors[id] == null){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		resistors[id].setVisible(false); // TODO: Figure out how to properly remove something
		resistors[id].removeAll(); // Kill the specified resistor
		resistors[id] = null;
		if (id+1 < maxResistors){
			for (int i=id+1;i<maxResistors;i+=1){ // Move all the other resistors back to fill the gap
				if (resistors[i] == null){break;}
				resistors[i - 1] = resistors[i];
				resistors[i] = null;
			}
		}
	}
	
	public Resistor SetResistor(int xpos,int ypos){
		int len = GetResistorCount();
		int newid = 0;
		if (len == maxResistors){
			throw new ArrayIndexOutOfBoundsException();
		}
		switch(orientation){
		case 0:
			for (int i=0;i<len;i+=1){
				if (resistors[i].getX() < xpos){
					newid = i+1;
				}
			}
			break;
		case 1:
			for (int i=0;i<len;i+=1){
				if (resistors[i].getY() < ypos){
					newid = i+1;
				}
			}
			break;
		case 2:
			for (int i=0;i<len;i+=1){
				if (resistors[i].getX() > xpos){
					newid = i+1;
				}
			}
			break;
		case 3:
			for (int i=0;i<len;i+=1){
				if (resistors[i].getY() < ypos){
					newid = i+1;
				}
			}
			break;
			default:
				JOptionPane.showMessageDialog(null,
					    "Circut has an invalid orientation. ",
					    "This should never happen",
					    JOptionPane.ERROR_MESSAGE);
				System.exit(1);
		}
		for (int i=len;i>=newid;i-=1){
			resistors[i+1] = resistors[i];
		}
		resistors[newid] = new Resistor();
		
		resistors[newid].setBounds(xpos-40, ypos-10, 80, 20);
		resistors[newid].SetID(newid);
		contentplane.add(resistors[newid]);
		return resistors[newid];
	}
	
	private Resistor[] fnResistorArray(int m) {
		Resistor arrButtons[] = new Resistor[m];
		for (int i = 0; i < m; i += 1) {
			//arrButtons[i].removeAll();
			arrButtons[i] = null;
		}
		return arrButtons;
	}
}
