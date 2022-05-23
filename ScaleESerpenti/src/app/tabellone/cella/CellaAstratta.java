package app.tabellone.cella;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public abstract class CellaAstratta extends JLabel {
	
	
	private int ID;
	
	public CellaAstratta(int ID) {
		super(""+ID,SwingConstants.CENTER);
		this.ID = ID;
	}
	
	
	public int getID() {
		return ID;
	}


}
