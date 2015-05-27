package resistMain;
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
