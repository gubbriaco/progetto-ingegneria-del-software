package app.tabellone.cella.concrete.special;

import java.awt.Color;

import app.tabellone.cella.CasellaAstratta;

@SuppressWarnings("serial")
public class CasellaPescaUnaCarta extends CasellaAstratta  {

	public CasellaPescaUnaCarta(int numeroCella) {
		super(numeroCella);
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.ORANGE);
		this.setForeground(Color.BLACK.darker());
	}

}
