package app.esecuzione;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JComboBox;

import app.esecuzione.giocatore.Giocatore;
import app.modalita.Modalita;
import app.tabellone.Tabellone;
import app.tabellone.TabelloneAstratto;
import app.tabellone.casella.CasellaAstratta;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;
import gui.window.finestraterminale.FinestraTerminaleAstratta;
import gui.window.finestraterminale.concrete.FinestraTerminale;

public abstract class Esecuzione {
	
	protected LinkedList<Giocatore> giocatoriInGioco;
	@SuppressWarnings("unused")
	private TabelloneAstratto tabellone;
	private Modalita.Mod modalita;
	protected FinestraPrincipaleAstratta finestraPrincipale;
	protected FinestraTerminaleAstratta terminale;
	
	
	
	public Esecuzione(LinkedList<Giocatore> giocatoriInGioco, Tabellone tabellone, FinestraPrincipaleAstratta finestraPrincipale,	
	                  FinestraTerminale terminale, Modalita.Mod modalita) {
		this.giocatoriInGioco = giocatoriInGioco;
		this.tabellone = tabellone;
		this.finestraPrincipale = finestraPrincipale;
		this.terminale = terminale;
		this.modalita = modalita;
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
		JComboBox giocatori;
		CasellaAstratta primaCasella = finestraPrincipale.getPrimaCasella();
		String giocatore = "";
		
		/** stampo su terminale  */
		for(int i=0;i<giocatoriInGioco.size();++i) {
			giocatore = giocatoriInGioco.get(i).toString();
			terminale.espandiAttivita( giocatore + " entra nella sessione di gioco!");
			terminale.repaintTerminale();
			System.out.println(giocatore + " entra nella sessione di gioco!");
		}
		giocatori = new JComboBox(giocatoriInGioco.toArray());
		
		primaCasella.add(giocatori, BorderLayout.SOUTH);
	}

	/**
	 * Esegue una nuova sessione di gioco.
	 */
	public abstract void esegui() ;


}
