package Interface;

import javax.swing.JComponent;

public class CompLocation {

	public static final int DEFAULT = 1, CENTER = 0, LEFT = 1, RIGHT = 2, TOP = 1, BOTTOM = 2;
	
	private JComponent comp;
	private int location;
	
	public CompLocation(JComponent comp, int location) {
		
		this.comp = comp;
		this.location = location;
	}
	
	public JComponent getComp() {
		
		return this.comp;
	}
	
	public int getLocation() {
		
		return this.location;
	}
	
}
