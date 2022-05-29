package app.tabellone.object;

import javax.swing.JComponent;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public abstract class OggettoTrasferimento extends JComponent {
	
	protected CasellaAstratta testa, coda;
	
	public OggettoTrasferimento(CasellaAstratta testa, CasellaAstratta coda) {
		this.testa = testa;
		this.coda = coda;
	}
	
//	@Override public void paintComponent(Graphics graphics) {
//		super.paintComponent(graphics);
//		draw(graphics, testa, coda);
//	}
	
//	protected abstract void draw(Graphics graphics, CasellaAstratta testa, CasellaAstratta coda);
	

}
