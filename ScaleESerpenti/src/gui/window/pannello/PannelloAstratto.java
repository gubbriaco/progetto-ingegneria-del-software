package gui.window.pannello;

import gui.window.FinestraAstratta;

@SuppressWarnings("serial")
public abstract class PannelloAstratto extends FinestraAstratta {
	
	
	public PannelloAstratto() {
		
	}
	

	/**
	 * Il metodo viene ridefinito cosi' da aggiungere l'operazione 
	 * {@link java.awt.Frame#setResizable(boolean)} e da non permettere 
	 * all'utente di ridimensionare la finestra essendo comunque una finestra, 
	 * in entrambi i casi {@link PannelloConfigurazione} e 
	 * {@link PannelloScelte}, di configurazione per la sessione di gioco 
	 * inizializzata.
	 */
	@Override protected void visualizzaFinestra() {
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
