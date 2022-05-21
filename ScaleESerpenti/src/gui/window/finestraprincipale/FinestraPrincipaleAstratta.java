package gui.window.finestraprincipale;

import java.io.File;

import app.difficolta.Difficolta;
import app.modalita.Modalita;
import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.window.FinestraIF;

public abstract class FinestraPrincipaleAstratta {
	
	/**terminale che verra' inizializzato per la sessione di gioco in questione*/
	private FinestraFactoryIF terminaleFactory;
	private FinestraIF terminale;
	
	private Modalita.Mod modalita; 
	private int numeroGiocatori;
	private Difficolta difficolta;
	
	/**File in cui verra' salvata la nuova sessione di gioco o usato come 
	 * ripristino di una sessione di gioco salvata sul calcolatore*/
	private File file;
	
	
	/**
	 * Costruttore utile per inizializzare una nuova sessione di gioco.
	 * @param modalita Modalita' di gioco (Automatica o Manuale)
	 * @param numeroGiocatori Numero di giocatori che interaggiranno con la 
	 *        nuova sessione di gioco
	 * @param difficolta Difficolta' con cui verra' inizializzato il gioco.
	 */
	public FinestraPrincipaleAstratta(Modalita.Mod modalita, int numeroGiocatori, Difficolta difficolta ) {
		this.modalita = modalita;
		this.numeroGiocatori = numeroGiocatori;
		this.difficolta = difficolta;
		 
		/**creo il terminale tramite il factory method*/
		terminaleFactory = new FinestraFactory();
		terminale = terminaleFactory.createFinestra("FinestraTerminaleAstratto", "");
		/**inizializzo la finestra terminale tramite il template method*/
		terminale.inizializzaFinestra();
		
		/**Il file che conterra' i dati della nuova sessione di gioco verra' 
		 * salvato sul Desktop*/
		file = new File(System.getProperty("user.home") + "/Desktop");
		
	}
	
	/**Costruttore utile al ripristino di una sessione di gioco salvata sul 
	 * calcolatore*/
	public FinestraPrincipaleAstratta(File file) {
		/**Viene salvato il file per ripristinare la sessione di gioco 
		 * precedente*/
		this.file = file;
	}
	
	

}
