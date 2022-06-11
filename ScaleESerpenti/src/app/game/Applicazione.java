package app.game;

import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.window.FinestraIF;

public final class Applicazione implements ScaleESerpenti {
	
	private FinestraFactoryIF pannelloScelta = new FinestraFactory();
	
	/** Assicuro che la classe abbia una sola istanza e che tale istanza sia 
	 *  facilmente accessibile. Pertanto, la classe stessa assicura che 
	 *  nessun'altra istanza possa essere creata e, tramite il metodo statico e 
	 *  synchronized getIstance(), essa fornisce un modo semplice per accedere 
	 *  all'unica istanza creata. Tale tecnica utilizzata e' la cosidetta Lazy
	 *  Inizialization.*/
	private static Applicazione INSTANCE = null;
	
	private Applicazione() {}
	
	/**
	 * Permette di accedere all'unica istanza che si puo' creare della classe in
	 * questione. Esso e' reso synchronized per garantire l'atomicita' del 
	 * processo di creazione nel caso di accesso concorrente della classe in 
	 * questione.
	 * @return Istanza della classe {@link Applicazione}
	 */
	public static synchronized Applicazione getIstance() {
		if(INSTANCE == null)
			INSTANCE = new Applicazione();
		return INSTANCE;
	}

	/**Inizializza l'esecuzione dell'applicazione in questione. <p>
	 * Inizialmente comparira' un pannello (il Pannello delle Scelte) che 
	 * permettera' all'utente di scegliere se inizializzare una nuova sessione 
	 * di gioco o riprendere una sessione di gioco salvata sul calcalatore. 
	 * Successivamente in base alla scelta effettuata, nel primo caso comparira'
	 * un ulteriore pannello (il Pannello di Configurazione) che permettera' di 
	 * configurare la sessione di gioco secondo alcuni parametri (numero di 
	 * giocatori, difficolta' ed esecuzione automatica o manuale). Dopo di che
	 * comparira' la finestra di gioco principale che permettera' di 
	 * visualizzare graficamente l'evoluzione del gioco ed un'ulteriore 
	 * finestra (il Terminale) che permettera' di visualizzare l'evoluzione del 
	 * gioco in maniera testuale. Nel caso in cui al Pannello delle Scelte si 
	 * dovesse scegliere di riprendere una sessione di gioco salvata sul proprio
	 * calcolatore, allora comparira' una finestra di selezione proprio per 
	 * selezionare il file in cui e' salvata una configurazione di gioco, e
	 * successivamente compariranno la finestra di gioco e il terminale 
	 * ripristinati per con i parametri della precedente configurazione di gioco.*/
	@Override public void start() {
		/**creo il pannello scelte tramite il factory method*/
		FinestraIF pannelloScelte = pannelloScelta.createFinestra
				("PannelloAstratto", "PannelloScelte", null, -1, null);
		/**inizializzo il pannello delle scelte tramite il template method*/
		pannelloScelte.inizializzaFinestra();
		
	}
	
	
	

}
