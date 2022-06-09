package app.tabellone.casella.strategy;

import app.tabellone.casella.CasellaAstratta;

public interface CasellaCreator {

	/**
	 * Utilizzando il design pattern Strategy definisco un algoritmo di creazione
	 * della casella delegando le sotto-classi per la sua implementazione, quindi 
	 * definisco una famiglia di algoritmi che risolvono lo stesso problema 
	 * (creazione della casella) per la tipologia di casella corrispondente.
	 * @param tabellone Tabellone
	 * @param random Numero random della casella 
	 * @return Casella
	 */
	CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random);
	

}
