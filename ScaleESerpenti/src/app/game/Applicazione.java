package app.game;

import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.window.FinestraIF;

public class Applicazione implements ScaleESerpenti {
	
	private FinestraFactoryIF pannelloScelta = new FinestraFactory();
	
	
	public Applicazione() {}

	/**Inizializza l'esecuzione dell'applicazione in questione. <p>
	 * Inizialmente comparira' un pannello (il Pannello delle Scelte) che 
	 * permettera' all'utente di scegliere se inizializzare una nuova sessione 
	 * di gioco o riprendere una sessione di gioco salvata sul calcalatore. 
	 * Successivamente in base alla scelta effettuata, nel primo caso comparira'
	 * un ulteriore pannello (il Pannello di Configurazione) che permettera' di 
	 * configurare la sessione di gioco secondo alcuni parametri (numero di 
	 * giocatori, difficolta' ed esecuzione automatica o manuale). Dopo di che
	 * comparira' la finestra di gioco principale che permettera' di 
	 * visualizzareed graficamente l'evoluzione del gioco ed un'ulteriore 
	 * finestra (il Terminale) che permettera' di visualizzare l'evoluzione del 
	 * gioco in maniera testuale. Nel caso in cui al Pannello delle Scelte si 
	 * dovesse scegliere di riprendere una sessione di gioco salvata sul proprio
	 * calcolatore, allora comparira' una finestra di selezione proprio per 
	 * selezionare il file in cui e' salvata la sessione di gioco, e
	 * successivamente compariranno la finestra di gioco e il terminale 
	 * ripristinati per l'appunto alla vecchia sessione di gioco*/
	@Override public void start() {
		/**creo il pannello scelte tramite il factory method*/
		FinestraIF pannelloScelte = pannelloScelta.createFinestra("PannelloAstratto", "PannelloScelte");
		/**inizializzo il pannello delle scelte tramite il template method*/
		pannelloScelte.inizializzaFinestra();
	}
	
	
	

}
