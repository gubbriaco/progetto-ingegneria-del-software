package app.tabellone.object;

import java.awt.Color;
import java.awt.Graphics;

import app.tabellone.cella.CasellaAstratta;

@SuppressWarnings("serial")
public class Serpente extends OggettoTrasferimento {

	
	public Serpente(CasellaAstratta testa, CasellaAstratta coda) {
		super(testa, coda);
	}


	@Override protected void draw(Graphics graphics, CasellaAstratta testa, CasellaAstratta coda) {
		graphics.setColor(new Color(102,51,0));
		graphics.drawLine(testa.getLocation().x, testa.getLocation().y,
				          coda.getLocation().x, coda.getLocation().y);
	}
	
	
}
