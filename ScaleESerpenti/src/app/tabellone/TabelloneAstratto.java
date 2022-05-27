package app.tabellone;

import java.util.Random;

import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.special.CasellaPescaUnaCarta;
import app.tabellone.casella.concrete.special.CasellaUnSoloDado;
import app.tabellone.casella.concrete.special.premio.CasellaPremioDadi;
import app.tabellone.casella.concrete.special.premio.CasellaPremioMolla;
import app.tabellone.casella.concrete.special.sosta.CasellaSostaLocanda;
import app.tabellone.casella.concrete.special.sosta.CasellaSostaPanchina;
import app.tabellone.casella.factory.CasellaFactory;
import app.tabellone.casella.factory.CasellaFactoryIF;

public abstract class TabelloneAstratto {
	
	
	protected int nrRighe, nrColonne;
	protected CasellaAstratta[][] tabellone;
	
	public boolean[][] scale, serpenti, lancioDiUnDado, sosta, premio, pescaUnaCarta;
	protected Random randomScala, randomSerpente, randomSosta,
				   randomPremio, randomPescaUnaCarta;
	
	/** Intervallo del numero di celle che devono essere inizializzate come 
	 * caselle speciali di tipologia {@link CasellaUnSoloDado}*/
	public static int[] CELLE_UN_SOLO_DADO;
	
	private CasellaFactoryIF casellaFactory = new CasellaFactory(); 
	
	
	/**
	 * Inizializza il Tabellone generico avente un numero di righe pari a nrRighe
	 * ed un numero di colonne pari a nrColonne. Inoltre, vengono inizializzate 
	 * le matrici di controllo booleane, relative alle possibili caselle speciali, 
	 * utili all'algoritmo di assegnazione delle caselle speciali 
	 * {@link TabelloneAstratto#aggiungiCaselleSpeciali()}.
	 * @param nrRighe
	 * @param nrColonne
	 */
	public TabelloneAstratto(int nrRighe, int nrColonne) {
		
		this.nrRighe = nrRighe;
		this.nrColonne = nrColonne;
		
		CELLE_UN_SOLO_DADO = new int[2];
		CELLE_UN_SOLO_DADO[0] = (nrRighe*nrColonne)-6;
		CELLE_UN_SOLO_DADO[1] = (nrRighe*nrColonne)-1;
		
		tabellone = new CasellaAstratta[nrRighe][nrColonne];
		
		scale = new boolean[nrRighe][nrColonne];
		serpenti = new boolean[nrRighe][nrColonne];
		lancioDiUnDado = new boolean[nrRighe][nrColonne];
		sosta = new boolean[nrRighe][nrColonne];
		premio = new boolean[nrRighe][nrColonne];
		pescaUnaCarta = new boolean[nrRighe][nrColonne];
		
		randomScala = new Random();
		randomSerpente = new Random();
		randomSosta = new Random();
		randomPremio = new Random();
		randomPescaUnaCarta = new Random();
		
	}
	

	/**
	 * Restituisce il tabellone per la nuova sessione di gioco.
	 * @return Tabellone per la nuova sessione di gioco.
	 */
	public CasellaAstratta[][] getTabellone() {
		
		tabellone = costruisciTabellone();
		
		inizializzaMatriciBooleaneDiControllo();
		 
		aggiungiCaselleSpeciali();
		
		/** Faccio in modo che non siano presenti tante caselle speciali 
		 * all'interno del tabellone inizializzato secondo un determinato 
		 * algoritmo*/
		sfoltisciTabellone();
		
	
		
		aggiungiGraficaAlleCaselle();
		
//		for(int i=0;i<tabellone.length;++i) {
//			for(int j=0;j<tabellone[i].length;++j) {
//				System.out.print(tabellone[i][j].getClass() + "   ");
//			}
//			System.out.println();
//		}
		 
		return tabellone;
		
	}
	
	
	
	
	/**
	 * Sfoltisce il tabellone dalle troppe caselle speciali seguendo un 
	 * determinato algoritmo.
	 */
	private void sfoltisciTabellone() {
		
		/** impongo che per ogni riga i-esima del tabellone siano presenti al 
		 * massimo un NUMERO_MAX_CASELLE_SPECIALI */
		final int NUMERO_MAX_CASELLE_SPECIALI_RIGA = nrColonne/2;
		int numeroCorrenteCaselleSpecialiPerRiga = 0;
		
		for(int i=0;i<nrRighe;++i) {
			numeroCorrenteCaselleSpecialiPerRiga = 0;
			for(int j=0;j<nrColonne;++j) {
				/** verifico che non si tratti di una casella di tipologia Un 
				 * Solo Dado essendo che quest'ultime sono pre-fissate*/
				if(tabellone[i][j].getNumeroCasella() >= Tabellone.CELLE_UN_SOLO_DADO[0] &&
				tabellone[i][j].getNumeroCasella() <= Tabellone.CELLE_UN_SOLO_DADO[1])
					continue;
				if(isSpeciale(tabellone[i][j].getClass())) {
					numeroCorrenteCaselleSpecialiPerRiga = numeroCorrenteCaselleSpecialiPerRiga+1;
					if(numeroCorrenteCaselleSpecialiPerRiga > NUMERO_MAX_CASELLE_SPECIALI_RIGA) {
						tabellone[i][j] = casellaFactory.createCella
						("Standard", tabellone[i][j].getNumeroCasella());
					}else
						break;
				}
			}
		}
	}
	
	/**
	 * Permette di verificare se la casella passata come parametro al metodo e'
	 * una casella speciale. Per la precisione viene passato come parametro la 
	 * classe della casella.
	 * @param tipologiaCasella
	 * @return La casella e' speciale
	 */
	private boolean isSpeciale(Class<? extends CasellaAstratta> tipologiaCasella) {
		return tipologiaCasella.equals(CasellaPescaUnaCarta.class) ||
				tipologiaCasella.equals(CasellaPremioDadi.class) ||
				tipologiaCasella.equals(CasellaPremioMolla.class) ||
				tipologiaCasella.equals(CasellaSostaLocanda.class) ||
				tipologiaCasella.equals(CasellaSostaPanchina.class) ;
				//tipologiaCasella.equals(CasellaUnSoloDado.class) ;
	}
	
	
	/**
	 * Inizializza le matrici booleane corrispondenti ad ogni casella speciale
	 * tale da poter essere consultate durante l'aggiunta delle caselle speciali
	 * all'interno del tabellone.
	 */
	private void inizializzaMatriciBooleaneDiControllo() {
		
		for(int i=0;i<tabellone.length;++i) {
			for(int j=0;j<tabellone[i].length;++j) {
				
				/** Tali caselle vengono inizializzate a true poiche' non devo 
				 * essere assegnate secondo un certo algoritmo dal momento che 
				 * sono assegnate di default per alcuni numeri di casella*/
				if(tabellone[i][j].getNumeroCasella() >= CELLE_UN_SOLO_DADO[0] &&
				tabellone[i][j].getNumeroCasella() <= CELLE_UN_SOLO_DADO[1])
					lancioDiUnDado[i][j] = true;
				
				/** Tali caselle vengono inizializzate a false poiche' 
				 * successivamente secondo un certo algoritmo tali caselle 
				 * speciali verranno assegnate per un certo numero di casella e
				 * poi impostate al valore booleano true*/
				scale[i][j] = false;
				serpenti[i][j] = false;
				lancioDiUnDado[i][j] = false;
				sosta[i][j] = false;
				premio[i][j] = false;
				pescaUnaCarta[i][j] = false;

			}
		}
		
	}
	
	
	/**
	 * Aggiunge la rappresentazione grafica alle caselle del tabellone della 
	 * nuova sessione di gioco.
	 */
	private void aggiungiGraficaAlleCaselle() {
		for(int i=0;i<nrRighe;++i)
			for(int j=0;j<nrColonne;++j)
				tabellone[i][j].draw();
	}
	
	/**
	 * Inizializza il tabellone numerandolo secondo l'ordine specificato dal 
	 * gioco Scala e Serpenti
	 * @return Tabellone senza caselle speciali
	 */
	private CasellaAstratta[][] costruisciTabellone() {
		
		int numeroCasella = nrRighe * nrColonne;
		
		boolean sensoOrario = true;

		for (int i = 0; i < tabellone.length; ++i) {
			for (int j = 0; j < tabellone[i].length; ++j) {
				if (sensoOrario) {
					tabellone[i][j] = casellaFactory.createCella
					("Standard", numeroCasella);
					numeroCasella = numeroCasella - 1;
				} else {
					tabellone[i][j] = casellaFactory.createCella
							("Standard", numeroCasella);
					numeroCasella = numeroCasella + 1;
				}
			}

			boolean tmp = !sensoOrario;
			sensoOrario = tmp;
			
			int tmpi = numeroCasella;
			if (!sensoOrario)
				tmpi = tmpi - nrColonne + 1;
			else
				tmpi = tmpi - nrColonne - 1;
			numeroCasella = tmpi;
		}
		
		return tabellone;
	}
	
	/**
	 * Utilizza il design pattern Strategy al suo interno per aggiungere, 
	 * tramite vari "creators" di caselle, le caselle speciali secondo un 
	 * determinato algoritmo.
	 */
	protected abstract void aggiungiCaselleSpeciali();
	
	
	/**
	 * Verifica che la cella alla riga i e alla colonna j non e' una cella 
	 * speciale
	 * @param i Riga della cella da verificare
	 * @param j Colonna della cella da verificare
	 * @return Cella non speciale
	 */
	public boolean verificaCellaNonSpeciale(int i, int j) {
		return scale[i][j] == false && serpenti[i][j] == false &&
			   lancioDiUnDado[i][j] == false && sosta[i][j] == false &&
			   premio[i][j] == false && pescaUnaCarta[i][j] == false;
	}
	

}
