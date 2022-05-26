package app.tabellone.object;

import java.awt.Color;
import java.awt.Graphics;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public class Scala extends OggettoTrasferimento {
	
	
	public Scala(CasellaAstratta testaScala, CasellaAstratta codaScala) {
		super(testaScala, codaScala);
		//System.out.println(testaScala.getNumeroCasella() + "," + codaScala.getNumeroCasella());	
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
