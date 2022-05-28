package gui.window.finestraterminale;

import gui.window.FinestraAstratta;

@SuppressWarnings("serial")
public abstract class FinestraTerminaleAstratta extends FinestraAstratta  {

	/** TEMPLATE METHOD
	 *  Aumenta la dimensione dell'array di 1 per aggiungere la nuova attivita 
	 *  svolta durante la sessione di gioco corrente.
	 * @param attivita Nuova attivita' svolta nella sessione di gioco corrente
	 */
	public void templateAggiungiAttivita(String attivita) {
		espandiAttivita(attivita);
		repaintTerminale();
	}
	
	/**
	 * Aggiunge una nuova attivita' alla Finestra Terminale avvenuta durante la 
	 * sessione di gioco. 
	 * @param attivita
	 */
	public abstract void espandiAttivita(String attivita);
	
	/**
	 * Aggiorna graficamente la Finestra Terminale.
	 */
	public abstract void repaintTerminale();
	
}
