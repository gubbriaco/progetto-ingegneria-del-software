package app.tabellone.casella.concrete.special.sosta;

import java.awt.Color;

import app.tabellone.casella.concrete.special.CasellaSosta;

@SuppressWarnings("serial")
public class CasellaSostaLocanda extends CasellaSosta {

	public CasellaSostaLocanda(int numeroCella) {
		super(numeroCella);
		
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.RED.darker());
		this.setForeground(Color.BLACK);
	}

}
