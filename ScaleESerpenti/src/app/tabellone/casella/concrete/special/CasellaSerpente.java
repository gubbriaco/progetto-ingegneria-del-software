package app.tabellone.casella.concrete.special;

import java.awt.Color;

import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.TipologiaCasella;
import app.tabellone.object.Serpente;

@SuppressWarnings("serial")
public class CasellaSerpente extends CasellaAstratta {
	
	private Serpente serpente;
	
	public CasellaSerpente(int numeroCella) {
		super(numeroCella);
		tipologiaCasella = TipologiaCasella.SERPENTE;
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(new Color(102,51,0));
		this.setForeground(Color.BLACK);
	}
	
	
	public void setSerpente(Serpente serpente) {
		this.serpente = serpente;
	}
	
	public Serpente getSerpente() {
		return serpente;
	}
	
	@Override public String toString() {
		return super.toString() + "Serpente " + this.getNumeroCasella();
	}


}
