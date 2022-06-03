package app.tabellone.casella.concrete.special.sosta;

import java.awt.Color;

import app.tabellone.casella.concrete.TipologiaCasella;
import app.tabellone.casella.concrete.special.CasellaSosta;

@SuppressWarnings("serial")
public class CasellaSostaLocanda extends CasellaSosta {

	public CasellaSostaLocanda(int numeroCella) {
		super(numeroCella);
		tipologiaCasella = TipologiaCasella.SOSTALOCANDA;
		
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.RED.darker());
		this.setForeground(Color.BLACK);
	}

	
	@Override public String toString() {
		return super.toString() + "Sosta locanda " + this.getNumeroCasella();
	}
	
}
