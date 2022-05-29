package app.tabellone.casella.concrete.special;

import java.awt.Color;
import java.awt.Graphics;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public class CasellaScala extends CasellaAstratta {
	
	public CasellaScala(int numeroCella) {
		super(numeroCella);
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(Color.BLUE.brighter());
		this.setForeground(Color.BLACK);
	}

	
//	@Override public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawLine(this.getLocation().x, this.getLocation().y, 
//				this.getCasellaArrivo()[0], this.getCasellaArrivo()[1]);
//	}
	
}
