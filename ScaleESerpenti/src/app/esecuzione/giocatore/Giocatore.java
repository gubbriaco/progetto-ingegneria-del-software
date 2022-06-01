package app.esecuzione.giocatore;

import app.esecuzione.mazzo.carte.Carta;

public interface Giocatore {

	/** Gestisce la richiesta di movimento della pedina corrispondente per una
	 *  determinata combinazione di dadi */
	int movementRequest(int casellaCorrente, int combinazioneDadi);
	
	/** Gestisce la pesca di una carta da un apposito mazzo */
	void pescaUnaCarta();
	
	/** Conserva la carta di tipologia "Divieto di Sosta" per essere utilizzata
	 *  successivamente per evitare la sosta in una casella di tipologia Sosta */
	void conservaCarta(Carta carta);
	
	/**
	 * Imposta la nuova casella corrente del giocatore al numero di casella casellaNuova
	 * @param casellaNuova Numemero della nuova casella raggiunta dal giocatore 
	 * in questione
	 */
	void setCasellaCorrente(int casellaNuova);
	
	/**
	 * Restituisce il numero della casella corrente in cui e' posizionata la pedina
	 * del giocatore in questione.
	 * @return Numero della casella in cui e' posizionato il giocatore.
	 */
	int getCasellaCorrente();
	
	/**
	 * Restituisce quanti turni deve stare fermo il giocatore.
	 * @return
	 */
	int getTurniFermo();
	
	/**
	 * Imposta quanti turni deve rimanere fermo il giocatore.
	 * @param turniFermo
	 */
	void setTurniFermo(int turniFermo);
	
	/**
	 * Restituisce la combinazione dei dadi ottenuta dal giocatore.
	 * @return Combinazione dei dadi
	 */
	int getCombinazioneDadi();
	
	/**
	 * Imposta la nuova combinazione dei dadi ottenuta dal giocatore.
	 * @param combinazioneDadi Nuova combinazione dei dadi
	 */
	void setCombinazioneDadi(int combinazioneDadi);
	
	/**
	 * Restituisce i lanci dei dadi effettuati dal giocatore.
	 * @return Lanci dei dadi effettuati
	 */
	int[] getLancioDeiDadi();
	
	/**
	 * Imposta i nuovi lanci effettuati dal giocatore.
	 * @param lancio1 Lancio del Dado 1
	 * @param lancio2 Lancio del Dado 2
	 */
	void setLancioDeiDadi(int lancio1, int lancio2);
	
	
}
