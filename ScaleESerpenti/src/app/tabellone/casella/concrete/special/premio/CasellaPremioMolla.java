package app.tabellone.casella.concrete.special.premio;

import java.awt.Color;

import app.tabellone.casella.concrete.special.CasellaPremio;

@SuppressWarnings("serial")
public class CasellaPremioMolla extends CasellaPremio {

	public CasellaPremioMolla(int numeroCella) {
		super(numeroCella);
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.GREEN.brighter());
		this.setForeground(Color.BLACK);
	}

}
