package app.tabellone.casella.concrete.special;

import java.awt.Color;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public class CasellaUnSoloDado extends CasellaAstratta {

	public CasellaUnSoloDado(int numeroCella) {
		super(numeroCella);
		
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.GRAY);
		this.setForeground(Color.BLACK.darker());
	}

	

}
