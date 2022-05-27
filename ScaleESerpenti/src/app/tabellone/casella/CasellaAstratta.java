package app.tabellone.casella;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import gui.graphic.border.RoundedBorder;

@SuppressWarnings("serial")
public abstract class CasellaAstratta extends JLabel {
	
	
	private int numeroCasella;
	
	
	public CasellaAstratta(int numeroCasella) {
		super(""+numeroCasella,SwingConstants.CENTER);
		this.numeroCasella = numeroCasella;
		this.setBorder(new RoundedBorder(5));
	}
	
	/**
	 * Restituisce il numero di casella della Casella in questione.
	 * @return Numero della casella
	 */
	public int getNumeroCasella() {
		return numeroCasella;
	}
	
	/**
	 * Rappresenta graficamente la casella in questione.
	 */
	public abstract void draw();


}
