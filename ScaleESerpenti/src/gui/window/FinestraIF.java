package gui.window;

import java.awt.Font;

public interface FinestraIF {
	
	String titolo = "";
	
	int dimX=800, dimY=600;
	
	Font font = new Font("Helvetica", Font.BOLD, 14);
	
	/**
	 * Permette di inizializzare la Finestra e di visualizzarla in maniera 
	 * grafica. Tramite due template method al suo interno, quindi, permette di
	 * visualizzare la Finestra al centro dello schermo e di inizializzare ogni 
	 * {@link JPanel} di cui la Finestra in questione ha bisogno.
	 */
	void inizializzaFinestra();

}
