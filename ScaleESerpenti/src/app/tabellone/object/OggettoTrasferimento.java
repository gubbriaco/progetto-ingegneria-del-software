package app.tabellone.object;

import javax.swing.JComponent;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public abstract class OggettoTrasferimento extends JComponent {
	
	/** Permette la creazione dell'oggetto che permette il "trasferimento" della
	 *  pedina dalla testa dell'oggetto alla coda nel caso di un serpente o 
	 *  dalla coda dell'oggetto alla testa nel caso di una scala. */
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
