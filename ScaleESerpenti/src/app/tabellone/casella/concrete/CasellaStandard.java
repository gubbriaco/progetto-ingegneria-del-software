package app.tabellone.casella.concrete;

import java.awt.Color;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public class CasellaStandard extends CasellaAstratta {
	
	public CasellaStandard(int numeroCella) {
		super(numeroCella);
		tipologiaCasella = TipologiaCasella.STANDARD;
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
	}

	@Override public String toString() {
		return super.toString() + "Standard " + this.getNumeroCasella();
	}

}
