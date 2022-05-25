package app.tabellone.cella.concrete;

import java.awt.Color;

import app.tabellone.cella.CasellaAstratta;

@SuppressWarnings("serial")
public class CasellaStandard extends CasellaAstratta {

	
	public CasellaStandard(int numeroCella) {
		super(numeroCella);
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK.darker());
	}

	
	

}
