package app.tabellone.casella.concrete.special.premio;

import java.awt.Color;

import app.tabellone.casella.concrete.TipologiaCasella;
import app.tabellone.casella.concrete.special.CasellaPremio;

@SuppressWarnings("serial")
public class CasellaPremioMolla extends CasellaPremio {

	public CasellaPremioMolla(int numeroCella) {
		super(numeroCella);
		tipologiaCasella = TipologiaCasella.PREMIOMOLLA;
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.GREEN.brighter());
		this.setForeground(Color.BLACK);
	}

	@Override public String toString() {
		return super.toString() + "Premio molla " + this.getNumeroCasella();
	}

}
