package app.tabellone.casella.concrete.special.sosta;

import java.awt.Color;

import app.tabellone.casella.concrete.TipologiaCasella;
import app.tabellone.casella.concrete.special.CasellaSosta;

@SuppressWarnings("serial")
public class CasellaSostaPanchina extends CasellaSosta {

	public CasellaSostaPanchina(int numeroCella) {
		super(numeroCella);
		tipologiaCasella = TipologiaCasella.SOSTAPANCHINA;
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.RED.brighter());
		this.setForeground(Color.BLACK);
	}


}
