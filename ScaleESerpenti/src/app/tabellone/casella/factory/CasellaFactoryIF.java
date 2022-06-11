package app.tabellone.casella.factory;

import app.tabellone.casella.CasellaAstratta;

public interface CasellaFactoryIF {
	
	/**
	 * Utilizzando il design pattern Factory Method riesco ad implementare una
	 * casella di una tipologia specificata in input ed avente un numero di cella
	 * specificato in input.
	 * @param tipologia Tipologia della casella
	 * @param numeroCella Numero della casella
	 * @return Casella
	 */
	CasellaAstratta createCella(String tipologia, int numeroCella);

}
