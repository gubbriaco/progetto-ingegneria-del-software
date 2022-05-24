package app.tabellone.cella;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public abstract class CellaAstratta extends JLabel {
	
	
	private int numeroCella;
	
	public CellaAstratta(int numeroCella) {
		super(""+numeroCella,SwingConstants.CENTER);
		this.numeroCella = numeroCella;
	}
	
	
	public int getNumeroCella() {
		return numeroCella;
	}
	
	
	public abstract void draw();


}
