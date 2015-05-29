package resistMain;

import java.awt.event.MouseAdapter;

public class MouseAdapterThatTakesInGoddamnArguments extends MouseAdapter {
	int argInt = -1;
	String argString = "";


	public MouseAdapterThatTakesInGoddamnArguments(int param) {
		this.argInt = param;
	}

	public MouseAdapterThatTakesInGoddamnArguments(String param) {
		this.argString = param;
	}
}
