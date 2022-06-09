package gui.factory;

import app.modalita.Modalita;
import gui.window.FinestraIF;

public interface FinestraFactoryIF {
	
	
	/**
	  * Utilizzando il design pattern Factory Method riesco ad implementare una
	 *  {@link ComponentServiceIF} di una tipologia specificata come parametro ed, 
	 *  inoltre, e' possibile implementare una tipologia di {@Finestra} specifica
	 *  tramite il parametro offset.
	 * @param tipologiaFinestra Tipologia della finestra
	 * @param offset Tipologia piu' specifica della finestra
	 * @param modalita Modalita' di esecuzione
	 * @param numeroGiocatori Numero di giocatori
	 * @param dimensioniTabellone Dimensioni del tabellone
	 * @return
	 */
	FinestraIF createFinestra(String tipologiaFinestra, String offset, 
			Modalita.Mod modalita, int numeroGiocatori, int[] dimensioniTabellone);
	

}
