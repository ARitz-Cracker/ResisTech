package resistMain;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class CircutLine extends JProgressBar {
	//Functions that Create an array of objects
	final int maxResistors = 16;
	private byte orientation = 0;
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
			throw new ArrayIndexOutOfBoundsException();
		}
		return resistors[id];
	}
	
	public void SetResistor(int xpos,int ypos){
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
			for (int i=len;i>=newid;i-=1){
				resistors[i+1] = resistors[i];
			}
			resistors[newid] = new Resistor();
			resistors[newid].setBounds(xpos-10, ypos-4, 20, 8);
			contentplane.add(resistors[newid]);
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
			default:
				JOptionPane.showMessageDialog(null,
					    "Circut has an invalid orientation. ",
					    "This should never happen",
					    JOptionPane.ERROR_MESSAGE);
				System.exit(1);
		}
	}
	
	private Resistor[] fnResistorArray(int m) {
		Resistor arrButtons[] = new Resistor[m];
		for (int i = 0; i < m; i += 1) {
			arrButtons[i].removeAll();
			arrButtons[i] = null;
		}
		return arrButtons;
	}
}
