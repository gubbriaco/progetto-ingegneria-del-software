package app.tabellone.casella.concrete.special;

import java.awt.Color;

import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.TipologiaCasella;
import app.tabellone.object.Scala;

@SuppressWarnings("serial")
public class CasellaScala extends CasellaAstratta {
	
	private Scala scala;
	
	public CasellaScala(int numeroCella) {
		super(numeroCella);
		tipologiaCasella = TipologiaCasella.SCALA;
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.BLUE.brighter());
		this.setForeground(Color.BLACK);
	}
	
	
	public void setScala(Scala scala) {
		this.scala = scala;
	}

	public Scala getScala() {
		return scala;
	}
	
	@Override public String toString() {
		return super.toString() + "Scala " + this.getNumeroCasella();
	}
	
}
