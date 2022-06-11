package gui.factory;

import app.modalita.Modalita;
import gui.window.FinestraIF;

public interface FinestraFactoryIF {
	
	
	/**
	 *  Utilizzando il design pattern Factory Method riesco ad implementare una
	 *  {@link FinestraIF} di una tipologia specificata come parametro ed, 
	 *  inoltre, e' possibile implementare una tipologia di {@link FinestraIF} 
	 *  ancora piu' specifica tramite il parametro offset.
	 * @param tipologiaFinestra Tipologia della finestra
	 * @param offset Tipologia piu' specifica della finestra
	 * @param modalita Modalita' di esecuzione
	 * @param numeroGiocatori Numero di giocatori
	 * @param dimensioniTabellone Dimensioni del tabellone
	 * @return Finestra
	 */
	FinestraIF createFinestra(String tipologiaFinestra, String offset, 
			Modalita.Mod modalita, int numeroGiocatori, int[] dimensioniTabellone);
	

}
