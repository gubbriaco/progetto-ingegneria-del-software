package app.tabellone.object;

import java.awt.Color;
import java.awt.Graphics;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public class Serpente extends OggettoTrasferimento {

	
	public Serpente(CasellaAstratta testaSerpente, CasellaAstratta codaSerpente) {
		super(testaSerpente, codaSerpente);
		//System.out.println(testaSerpente.getNumeroCasella() + "," + codaSerpente.getNumeroCasella());	
	}


	@Override protected void draw(Graphics graphics, CasellaAstratta testa, CasellaAstratta coda) {
		graphics.setColor(new Color(102,51,0));
		graphics.drawLine(testa.getLocation().x, testa.getLocation().y,
				          coda.getLocation().x, coda.getLocation().y);
	}
	
	
}
