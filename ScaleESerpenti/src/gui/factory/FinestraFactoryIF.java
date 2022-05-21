package gui.factory;

import gui.window.FinestraIF;

public interface FinestraFactoryIF {
	
	
	/**
	 * Utilizzando il design pattern Factory Method riesco ad implementare una
	 * {@link Finestra} di una tipologia specificata come parametro ed, inoltre,
	 * e' possibile implementare una tipologia di {@Finestra} specifica tramite 
	 * il parametro offset.
	 * @param tipologiaFinestra
	 * @param offset
	 * @return
	 */
	FinestraIF createFinestra(String tipologiaFinestra, String offset);
	

}
