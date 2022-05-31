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
	
	
	public int getTesta() {
		return testa.getNumeroCasella();
	}
	
	public int getCoda() {
		return coda.getNumeroCasella();
	}
	
	
}
