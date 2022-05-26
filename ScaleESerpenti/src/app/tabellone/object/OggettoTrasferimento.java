package app.tabellone.object;

import java.awt.Graphics;

import javax.swing.JComponent;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public abstract class OggettoTrasferimento extends JComponent {
	
	private CasellaAstratta testa, coda;
	
	public OggettoTrasferimento(CasellaAstratta testa, CasellaAstratta coda) {
		this.testa = testa;
		this.coda = coda;
	}
	
	@Override public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		draw(graphics, testa, coda);
	}
	
	protected abstract void draw(Graphics graphics, CasellaAstratta testa, CasellaAstratta coda);
	

}
