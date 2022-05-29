package app.tabellone.casella.concrete.special.premio;

import java.awt.Color;

import app.tabellone.casella.concrete.special.CasellaPremio;

@SuppressWarnings("serial")
public class CasellaPremioDadi extends CasellaPremio {

	public CasellaPremioDadi(int numeroCella) {
		super(numeroCella);
		
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.GREEN.darker());
		this.setForeground(Color.BLACK);
	}

}
