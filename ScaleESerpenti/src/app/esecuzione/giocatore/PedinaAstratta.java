package app.esecuzione.giocatore;

import java.util.LinkedList;

import app.esecuzione.dadi.Dado;
import app.esecuzione.mazzo.carte.Carta;
import app.tabellone.Tabellone;
import gui.window.finestraterminale.FinestraTerminaleAstratta;
import gui.window.pannello.concrete.PannelloConfigurazione;

public abstract class PedinaAstratta implements Giocatore {
	
	protected String nomePedina;
	protected int combinazioneDadi;
	private int turniFermo;
	protected LinkedList<Carta> carteConservate;
	protected Tabellone tabellone;
	private int casellaCorrente, nrDadi;
	protected Dado[] dadi;
	protected FinestraTerminaleAstratta terminale;
	
	/** variabile per contare di quante caselle bisogna indietreggiare nel caso
	 * in cui si supera il traguardo */
	protected int caselleRimanenti;
	
	public PedinaAstratta(String nomePedina, Tabellone tabellone, FinestraTerminaleAstratta terminale) {
		this.nomePedina = nomePedina;
		this.tabellone = tabellone;
		casellaCorrente = 1;
		carteConservate = new LinkedList<>();
		
		caselleRimanenti = 0;
		
		turniFermo = 0;
		combinazioneDadi = 0;
		
		nrDadi = PannelloConfigurazione.numeroDadi;
		
		dadi = new Dado[nrDadi];
		
		this.terminale = terminale;
	}
	
	@Override public void setCasellaCorrente(int casellaCorrente) {
		this.casellaCorrente = casellaCorrente;
	}
	
	@Override public int getCasellaCorrente() {
		return casellaCorrente;
	}
	
	
	@Override public int getTurniFermo() {
		return turniFermo;
	}
	
	@Override public void setTurniFermo(int turniFermo) {
		this.turniFermo = turniFermo;
	}
	
	
	@Override public int getCombinazioneDadi() {
		return combinazioneDadi;
	}
	
	@Override public void setCombinazioneDadi(int combinazioneDadi) {
		this.combinazioneDadi = combinazioneDadi;
	}
	
	@Override public Dado[] getLancioDeiDadi() {
		return dadi;
	}
	
	@Override public void setLancioDeiDadi(Dado[] dadi) {
		for(int i=0;i<dadi.length;++i)
			this.dadi[i] = dadi[i];
	}
	
	
}
