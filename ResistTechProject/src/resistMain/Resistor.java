package resistMain;

import javax.swing.JButton;

public class Resistor extends JButton {
	private int id;
	private double load = 0;
	
	public void SetID(int val) {
		id = val;
	}

	public int GetID() {
		return id;
	}

	public double GetLoad(){
		return load;
	}
	
	public void SetLoad(double val){
		this.setText(val+"Ω");
		load = val;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8034845604833830878L;

}
