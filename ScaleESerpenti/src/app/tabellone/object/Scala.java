package app.tabellone.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import app.tabellone.cella.CasellaAstratta;

@SuppressWarnings("serial")
public class Scala extends OggettoTrasferimento {
	
	
	public Scala(CasellaAstratta testaScala, CasellaAstratta codaScala) {
		super(testaScala, codaScala);
	}
	

	@Override protected void draw(Graphics graphics, CasellaAstratta testa, CasellaAstratta coda) {
//		Graphics2D g2 = (Graphics2D)graphics;
		graphics.setColor(Color.BLUE.brighter());
//		g2.setStroke(new BasicStroke(2f));
//		g2.draw(new Line2D.Double(testa.getLocation().x, testa.getLocation().y,
//     			  coda.getLocation().x, coda.getLocation().y));
		graphics.drawLine(testa.getLocation().x, testa.getLocation().y,
						  coda.getLocation().x, coda.getLocation().y);
	}
	

}
