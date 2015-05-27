package resistMain;

import javax.swing.JButton;

public class Resistor extends JButton {
	private int id;
	private int load;
	
	public void SetID(int val) {
		id = val;
	}

	public int GetID() {
		return id;
	}

	public int GetLoad(){
		return load;
	}
	
	public void SetLoad(int val){
		load = val;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8034845604833830878L;

}
