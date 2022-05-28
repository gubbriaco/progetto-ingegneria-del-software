package app.esecuzione.giocatore;

import java.util.LinkedList;

import app.esecuzione.mazzo.Carta;
import app.tabellone.Tabellone;

public abstract class PedinaAstratta implements Giocatore {
	
	protected String nomePedina;
	
	protected LinkedList<Carta> carteConservate;
	
	@SuppressWarnings("unused")
	private Tabellone tabellone;
	
	private int casellaCorrente;
	
	/** variabile per contare di quante caselle bisogna indietreggiare nel caso
	 * in cui si supera il traguardo */
	protected int caselleRimanenti;
	
	public PedinaAstratta(String nomePedina, Tabellone tabellone) {
		this.nomePedina = nomePedina;
		this.tabellone = tabellone;
		casellaCorrente = 1;
		carteConservate = new LinkedList<>();
		
		caselleRimanenti = 0;
	}
	
	@Override public void setCasellaCorrente(int casellaNuova) {
		this.casellaCorrente = casellaNuova;
	}
	
	@Override public int getCasellaCorrente() {
		return casellaCorrente;
	}
	
}
