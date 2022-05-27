package gui.window;

public interface FinestraIF {
	
	
	/**
	 * Permette di inizializzare la Finestra e di visualizzarla in maniera 
	 * grafica. Tramite un metodo protected ed un template method protected al 
	 * suo interno, quindi, permette di visualizzare la Finestra al centro dello 
	 * schermo e di inizializzare ogni {@link JPanel} di cui la Finestra in 
	 * questione ha bisogno.
	 */
	void inizializzaFinestra();
	

}
