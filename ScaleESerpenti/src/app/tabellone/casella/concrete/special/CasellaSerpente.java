package app.tabellone.casella.concrete.special;

import java.awt.Color;
import java.awt.Graphics;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public class CasellaSerpente extends CasellaAstratta {

	private int xArrivo, yArrivo;
	
	public CasellaSerpente(int numeroCella) {
		super(numeroCella);
	}

	@Override public void draw() {
		this.setOpaque(true);
		this.setBackground(new Color(102,51,0));
		this.setForeground(Color.BLACK);
	}
	
	
//	@Override public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawLine(this.getLocation().x, this.getLocation().y, 
//				this.getCasellaArrivo()[0], this.getCasellaArrivo()[1]);
//	}

}
