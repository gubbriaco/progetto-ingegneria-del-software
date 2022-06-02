package app.tabellone.casella.concrete.special;

import java.awt.Color;

import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.TipologiaCasella;

@SuppressWarnings("serial")
public class CasellaUnSoloDado extends CasellaAstratta {

	public CasellaUnSoloDado(int numeroCella) {
		super(numeroCella);
		tipologiaCasella = TipologiaCasella.UNSOLODADADO;
		
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.GRAY);
		this.setForeground(Color.BLACK);
	}

	@Override public String toString() {
		return super.toString() + "Un solo dado " + this.getNumeroCasella();
	}

}
