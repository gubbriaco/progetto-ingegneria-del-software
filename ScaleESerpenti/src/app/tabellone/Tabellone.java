package app.tabellone;

import java.util.Random;

import app.tabellone.cella.CellaAstratta;
import app.tabellone.cella.concrete.CellaStandard;

public class Tabellone {
	
	
	private int nrRighe, nrColonne;
	private CellaAstratta[][] tabellone;
	
	private boolean[][] scale, serpenti, lancioDiUnDado, sosta, premio, pescaUnaCarta;
	private Random randomScala, randomSerpente, randomSosta,
				   randomPremio, randomPescaUnaCarta;
	
	private final static int[] CELLE_UN_SOLO_DADO = {94, 99};
	
	
	public Tabellone(int nrRighe, int nrColonne) {
		
		this.nrRighe = nrRighe;
		this.nrColonne = nrColonne;
		
		tabellone = new CellaAstratta[nrRighe][nrColonne];
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
		
		for(int i=0;i<tabellone.length;++i) {
			for(int j=0;j<tabellone[i].length;++j) {
				scale[i][j] = false;
				serpenti[i][j] = false;
				lancioDiUnDado[i][j] = false;
			}
		}
		
	}
	
	

	/**
	 * Restituisce il tabellone per la nuova sessione di gioco.
	 * @param nrRighe Numero di righe del tabellone
	 * @param nrColonne Numero di colonne del tabellone
	 * @return Tabellone per la nuova sessione di gioco
	 */
	public CellaAstratta[][] getTabellone(int nrRighe, int nrColonne){
		
		/** Inizializza il tabellone numerandolo secondo l'ordine specificato 
		 * dal gioco Scala e Serpenti */
		
		int ID = nrRighe * nrColonne;
		
		boolean sensoOrario = true;

		for (int i = 0; i < tabellone.length; ++i) {
			for (int j = 0; j < tabellone[i].length; ++j) {
				if (sensoOrario) {
					tabellone[i][j] = new CellaStandard(ID);
					ID = ID - 1;
				} else {
					tabellone[i][j] = new CellaStandard(ID);
					ID = ID + 1;
				}
			}

			boolean tmp = !sensoOrario;
			sensoOrario = tmp;
			
			int tmpi = ID;
			if (!sensoOrario)
				tmpi = tmpi - nrColonne + 1;
			else
				tmpi = tmpi - nrColonne - 1;
			ID = tmpi;
		}
		
		
		
		/** Inizializzo le caselle speciali */
		
		/**
		 * Pressuppongo che ogni riga del tabellone abbia la testa di una scala 
		 * o di un serpente oppure la coda di una scala o di un serpente.
		 */
		
		/** riempio il tabellone con scale e serpenti e le celle speciali */
		riempiConCaselleSpeciali();
		

		return tabellone;
			
	}
	
	
	/**
	 * Riempie il tabellone con le scale e i serpenti ed, inoltre, con le celle
	 * speciali.
	 */
	private void riempiConCaselleSpeciali(){
		
		/**
		 * Inizializzo al valore booleano true tutte le celle di tipologia
		 * "Lancio di un solo dado". Tale operazione viene fatta all'inizio 
		 * perche' comunque sono celle gia prefissate, cioe' di cui si sa gia'
		 * la cella con il numero di cella compreso tra 94 e 99 (inclusi) a
		 * differenza delle altre celle speciali che, invece, devono essere 
		 * disposte in maniera randomica.
		 */
		riempiCelleUnSoloDado();
		
		
		for(int i=0;i<tabellone.length;++i) {
			
			/** calcoliamo i bounds, cioe' il numero della prima casella della
			 * riga i-esima e il numero dell'ultima casella della riga i-esima
			 * cosi' da calcolare il numero random, cioe' il numero di cella 
			 * su cui posizionare la testa di una scala o un serpente*/
			int[] bounds = boundsRigaIesima(tabellone[i]);
			/** numero della prima cella della riga i-esima*/
			int base = bounds[0];
			/** numero dell'ultima cella della riga i-esima*/
			int limite = bounds[1];
			
			
			/** imposto a true la cella nella riga i-esima ad una certa colonna
			 * j-esima randomica */
			int randSosta = randomSosta.nextInt(base, limite+1);
			riempiConSosta(i, randSosta);
			
			/** imposto a true la cella nella riga i-esima ad una certa colonna
			 * j-esima randomica */
			int randPremio = randomPremio.nextInt(base, limite+1);
			riempiConPremio(i, randPremio);
			
			/** imposto a true la cella nella riga i-esima ad una certa colonna
			 * j-esima randomica */
			int randPescaUnaCarta = randomPescaUnaCarta.nextInt(base, limite+1);
			riempiConPescaUnaCarta(i, randPescaUnaCarta);
			
			
			/** numero di cella randomica su cui posizionare la testa della 
			 * scala */
			int randScala = randomScala.nextInt(base, limite+1);
			/** imposto a true la cella nella riga i-esima corrispondente alla
			 * testa della scala*/
			riempiConScale(i, randScala);
			
			/** numero di cella randomica su cui posizionare la testa del 
			 * serpente */
			int randSerpente = randomSerpente.nextInt(base, limite+1);
			/** imposto a true la cella nella riga i-esima corrispondente alla
			 * testa del serpente*/
			riempiConSerpenti(i, randSerpente);
			
		}
		
		
		
		
	}
	
	
	/**
	 * Inizializza al valore booleano true la cella corrispondente al numero di
	 * cella randPremio.
	 * @param nrRiga Numero di riga della possibile cella che sara' 
	 * inizializzata come cella speciale di tipo "Pesca una carta"
	 * @param randPescaUnaCarta Numero di cella che potrebbe essere 
	 * inizializzata come cella speciale di tipo "Pesca una carta"
	 */
	private void riempiConPescaUnaCarta(int nrRiga, int randPescaUnaCarta) {
		
		for(int j=0;j<tabellone[nrRiga].length;++j)
			
			if( tabellone[nrRiga][j].getNumeroCella()==randPescaUnaCarta ) {
				if( verificaCellaNonSpeciale(nrRiga, j) ) {
					pescaUnaCarta[nrRiga][j] = true;
					// TODO request-creation tramite factory celle per creare la
					// cella speciale corrispondente
					// TODO aggiungere parte grafica
				}
				else
					return;
			}
					
	}
	
	
	/**
	 * Inizializza al valore booleano true la cella corrispondente al numero di
	 * cella randPremio.
	 * @param nrRiga Numero di riga della possibile cella che sara' 
	 * inizializzata come cella speciale di tipo "Premio"
	 * @param randPremio Numero di cella che potrebbe essere inizializzata come 
	 * cella speciale di tipo "Premio"
	 */
	private void riempiConPremio(int nrRiga, int randPremio) {
		
		for(int j=0;j<tabellone[nrRiga].length;++j)
			
			if( tabellone[nrRiga][j].getNumeroCella()==randPremio ) {
				if( verificaCellaNonSpeciale(nrRiga, j) ) {
					premio[nrRiga][j] = true;
					// TODO request-creation tramite factory celle per creare la
					// cella speciale corrispondente
					// TODO aggiungere parte grafica
				}
				else
					return;
			}
					
	}
	
	
	/**
	 * Inizializza al valore booleano true la cella corrispondente al numero di
	 * cella randSosta.
	 * @param nrRiga Numero di riga della possibile cella che sara' 
	 * inizializzata come cella speciale di tipo "Sosta"
	 * @param randSosta Numero di cella che potrebbe essere inizializzata come 
	 * cella speciale di tipo "Sosta"
	 */
	private void riempiConSosta(int nrRiga, int randSosta) {
		
		for(int j=0;j<tabellone[nrRiga].length;++j)
			
			if( tabellone[nrRiga][j].getNumeroCella()==randSosta ) {
				if( verificaCellaNonSpeciale(nrRiga, j) ) {
					sosta[nrRiga][j] = true;
					// TODO request-creation tramite factory celle per creare la
					// cella speciale corrispondente
					// TODO aggiungere parte grafica
				}
				else
					return;
			}
					
	}
	
	
	/**
	 * Imposta al valore booleano true la cella speciale che prevede il lancio 
	 * di un solo dado.
	 */
	private void riempiCelleUnSoloDado() {
		for(int i=0;i<tabellone.length;++i)
			for(int j=0;j<tabellone[i].length;++j)
				if(tabellone[i][j].getNumeroCella() >= CELLE_UN_SOLO_DADO[0] ||
				   tabellone[i][j].getNumeroCella() <= CELLE_UN_SOLO_DADO[1]) {
					lancioDiUnDado[i][j] = true;
					// TODO request-creation tramite factory celle per creare la
					// cella speciale corrispondente
					// TODO aggiungere parte grafica
				}
	}
	
	
	/**
	 * Verifica che la cella alla riga i e alla colonna j non e' una cella 
	 * speciale
	 * @param i Riga della cella da verificare
	 * @param j Colonna della cella da verificare
	 * @return Cella non speciale
	 */
	private boolean verificaCellaNonSpeciale(int i, int j) {
		return scale[i][j] == false && serpenti[i][j] == false &&
				lancioDiUnDado[i][j] == false && sosta[i][j] == false &&
				premio[i][j] == false && pescaUnaCarta[i][j] == false;
	}
	
	
	/**
	 * Imposta la cella al valore booleano true per indicare che essa e' la 
	 * testa di una scala
	 * @param tmp Tabellone
	 * @param nrRiga Numero di riga i-esima su cui deve essere posizionata la 
	 * testa della scala
	 * @param randScala Numero randomico del numero della cella che conterra' 
	 * la testa della scala
	 */
	private void riempiConScale(int nrRiga, int randScala) {
		
		for(int j=0;j<tabellone[nrRiga].length;++j)
			if(tabellone[nrRiga][j].getNumeroCella() == randScala) {
				if( verificaCellaNonSpeciale(nrRiga, j) ) {
					scale[nrRiga][j] = true;
					// TODO request-creation tramite factory celle per creare la
					// cella speciale corrispondente
					// TODO aggiungere parte grafica
				}
				else /** semplicemente non la posiziono */
					return;
			}
		
		Random rigaCodaScala = new Random();
		int randCoda = rigaCodaScala.nextInt(nrRiga, tabellone.length-nrRiga);
		
		
		/** assegno la coda della scala corrispondente alla cella booleana che 
		 * soddisfa la condizione*/
		for(int j=0;j<tabellone[randCoda].length;++j)
			if( verificaCellaNonSpeciale(nrRiga, j) ) {
				scale[randCoda][j] = true;
				// TODO request-creation tramite factory celle per creare la
				// cella speciale corrispondente
				// TODO aggiungere parte grafica
			}
		
	}
	
	
	/**
	 * Imposta la cella al valore booleano true per indicare che essa e' la 
	 * testa di un serpente
	 * @param tmp Tabellone
	 * @param nrRiga Numero di riga i-esima su cui deve essere posizionata la 
	 * testa del serpente
	 * @param randScala Numero randomico del numero della cella che conterra' 
	 * la testa del serpente
	 */
	private void riempiConSerpenti(int nrRiga, int randSerpente) {
		
		for(int j=0;j<tabellone[nrRiga].length;++j)
			if(tabellone[nrRiga][j].getNumeroCella() == randSerpente) {
				if( verificaCellaNonSpeciale(nrRiga, j) ) {
					serpenti[nrRiga][j] = true;
					// TODO request-creation tramite factory celle per creare la
					// cella speciale corrispondente
					// TODO aggiungere parte grafica
				}
				else /** semplicemente non la posiziono */
					return;
			}
		
		Random rigaCodaSerpente = new Random();
		int randCoda = rigaCodaSerpente.nextInt(nrRiga, tabellone.length-nrRiga);
		
		/** assegno la coda della scala corrispondente alla cella booleana che 
		 * soddisfa la condizione*/
		for(int j=0;j<tabellone[randCoda].length;++j)
			if( verificaCellaNonSpeciale(nrRiga, j) ) {
				serpenti[randCoda][j] = true;
				// TODO request-creation tramite factory celle per creare la
				// cella speciale corrispondente
				// TODO aggiungere parte grafica
			}
		
	}

	
	/**
	 * Calcola i bounds, cioe' il numero della prima casella della riga i-esima 
	 * e il numero dell'ultima casella riga i-esima.
	 * @return Bounds della riga i-esima
	 */
	private int[] boundsRigaIesima(CellaAstratta[] rigaIesima){
		int[] bounds = new int[2];
		
		bounds[0] = rigaIesima[0].getNumeroCella();
		bounds[1] = rigaIesima[rigaIesima.length-1].getNumeroCella();
		
		return bounds;
	}
	

}
