package app.tabellone.casella;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public abstract class CasellaAstratta extends JLabel {
	
	
	private int numeroCasella;
	private Border border;
	
	public CasellaAstratta(int numeroCasella) {
		super(""+numeroCasella,SwingConstants.CENTER);
		this.numeroCasella = numeroCasella;
		border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
		this.setBorder(border);
	}
	
	
	public int getNumeroCasella() {
		return numeroCasella;
	}
	
	
	public abstract void draw();


}
