package app.esecuzione;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JComboBox;

import app.esecuzione.dadi.Dado;
import app.esecuzione.giocatore.Giocatore;
import app.tabellone.Tabellone;
import app.tabellone.TabelloneAstratto;
import app.tabellone.casella.CasellaAstratta;
import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.window.FinestraIF;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;
import gui.window.finestraterminale.FinestraTerminaleAstratta;
import gui.window.finestraterminale.concrete.FinestraTerminale;
import gui.window.pannello.concrete.PannelloConfigurazione;

public abstract class Esecuzione {
	
	protected LinkedList<Giocatore> giocatoriInGioco;
	@SuppressWarnings("unused")
	private TabelloneAstratto tabellone;
	protected FinestraPrincipaleAstratta finestraPrincipale;
	protected FinestraTerminaleAstratta terminale;
	
	public boolean victory = false;
	public int turno = 1;
	
	protected int nrDadi;
	protected Dado[] dadi;
	
	protected FinestraFactoryIF victoryFactory = new FinestraFactory();
	protected FinestraIF victoryWindow;
	
	
	public Esecuzione(LinkedList<Giocatore> giocatoriInGioco, Tabellone tabellone, FinestraPrincipaleAstratta finestraPrincipale,	
	                  FinestraTerminale terminale) {
		this.giocatoriInGioco = giocatoriInGioco;
		this.tabellone = tabellone;
		this.finestraPrincipale = finestraPrincipale;
		this.terminale = terminale;
		
		this.nrDadi = PannelloConfigurazione.numeroDadi;
		dadi = new Dado[nrDadi];
		
		
	}
	
	
	/**
	 * Inizializza una nuova sessione di gioco.
	 */
	public void start() {
		inizializzaGioco();
		esegui();
	}
	
	/**
	 * Inizializza i giocatori presenti nella sessione di gioco in questione 
	 * nella prima casella del tabellone.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void inizializzaGioco() {
		
		System.out.println("********* TURNO : " + turno + " *********");
		JComboBox giocatori;
		CasellaAstratta primaCasella = finestraPrincipale.getPrimaCasella();
		String giocatore = "";
		
		/** stampo su terminale  */
		for(int i=0;i<giocatoriInGioco.size();++i) {
			giocatore = giocatoriInGioco.get(i).toString();
			terminale.espandiAttivita( giocatore + " entra nella sessione di gioco!");
			terminale.repaintTerminale();
			System.out.println(giocatore + " entra nella sessione di gioco!");
			giocatoriInGioco.get(i).setCasellaCorrente(primaCasella.getNumeroCasella());
		}
		giocatori = new JComboBox(giocatoriInGioco.toArray());
		
		primaCasella.add(giocatori, BorderLayout.SOUTH);
		
		
	}

	/**
	 * Esegue una nuova sessione di gioco.
	 */
	public abstract void esegui() ;


}
