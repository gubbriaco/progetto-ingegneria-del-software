package gui.window.finestraerrore;

import gui.window.FinestraAstratta;

@SuppressWarnings("serial")
public abstract class FinestraErroreAstratta extends FinestraAstratta {
	
	/**
	 * Il metodo viene ridefinito cosi' da aggiungere l'operazione 
	 * {@link java.awt.Frame#setResizable(boolean)} e da non permettere 
	 * all'utente di ridimensionare la finestra essendo comunque una finestra di
	 * errore.
	 */
	@Override protected void visualizzaFinestra() {
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

}
