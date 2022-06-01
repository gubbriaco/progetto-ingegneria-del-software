package app.esecuzione.giocatore;

import java.util.LinkedList;

import app.esecuzione.mazzo.carte.Carta;
import app.tabellone.Tabellone;

public abstract class PedinaAstratta implements Giocatore {
	
	protected String nomePedina;
	
	private int combinazioneDadi, turniFermo;
	
	protected LinkedList<Carta> carteConservate;
	
	@SuppressWarnings("unused")
	private Tabellone tabellone;
	
	private int casellaCorrente;
	
	private int[] lancioDeiDadi;
	
	/** variabile per contare di quante caselle bisogna indietreggiare nel caso
	 * in cui si supera il traguardo */
	protected int caselleRimanenti;
	
	public PedinaAstratta(String nomePedina, Tabellone tabellone) {
		this.nomePedina = nomePedina;
		this.tabellone = tabellone;
		casellaCorrente = 1;
		carteConservate = new LinkedList<>();
		
		caselleRimanenti = 0;
		
		turniFermo = 0;
		combinazioneDadi = 0;
		
		lancioDeiDadi=new int[2];
	}
	
	@Override public void setCasellaCorrente(int casellaNuova) {
		this.casellaCorrente = casellaNuova;
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
	
	@Override public int[] getLancioDeiDadi() {
		return lancioDeiDadi;
	}
	
	@Override public void setLancioDeiDadi(int lancio1, int lancio2) {
		lancioDeiDadi[0] = lancio1;
		lancioDeiDadi[1] = lancio2;
	}
	
	
}
