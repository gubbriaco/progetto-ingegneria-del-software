package app.esecuzione.giocatore;

import app.esecuzione.mazzo.Carta;
import app.tabellone.Tabellone;
import app.tabellone.casella.CasellaAstratta;

public class Pedina extends PedinaAstratta {
	
	
	private CasellaAstratta[][] matriceTabellone;

	public Pedina(String nomePedina, Tabellone tabellone) {
		super(nomePedina, tabellone);
		this.matriceTabellone = tabellone.getTabellone();
	}

	

	@Override public int movementRequest(int casellaCorrente, int combinazioneDadi) {
		
		int nuovaCasella = casellaCorrente + combinazioneDadi,
			residuo=0, retroDadi=0;
		
		/** gestisce il caso particolare nel caso in cui la combinazione di dadi
		 *  induce un movimento che fa spostare la pedina oltre il traguardo ->
		 *  -> pertanto, la pedina indietreggia di un numero di caselle pari 
		 *     alla di caselle con cui ha superato il traguardo */
		if(nuovaCasella>matriceTabellone[0][0].getNumeroCasella()) {
//			System.out.println("SONO OLTRE!!!");
			/** calcolo il residuo di posizioni che mancano per raggiungere il 
			 * traguardo dalla casella corrente */
			residuo = matriceTabellone[0][0].getNumeroCasella() - casellaCorrente;
			
			/** calcolo di quanto devo retrocedere dopo che ho raggiunto il 
			 * traguardo ->
			 * -> raggiungo il traguardo e dopo indietreggio di una quantita'
			 * 	  pari a retroDadi, cioe' la differenza tra la combinazione dei 
			 * 	  dadi ottenuta e il residuo di posizioni che gli e' servito per
			 *    arrivare al traguardo */
			retroDadi = combinazioneDadi-residuo;
			
			/** pertanto calcolo il numero della nuova casella in cui si trovera' 
			 *  dopo aver indietreggiato di un numero di posizioni pari a retroDadi*/
			nuovaCasella = matriceTabellone[0][0].getNumeroCasella() - retroDadi;
			
			/** effettuo un movimento "fantasma", cioe', anziche' scrivere un 
			 *  ulteriore funzione, faccio finta che la casella corrente sia una
			 *  casella avente come numero di casella la differenza tra il
			 *  numero della nuovaCasella e la quantita' di posizioni di cui devo
			 *  indietreggiare. Quindi, invoco un movimento della pedina in 
			 *  questione dalla casella "fantasma" appena descritta con una 
			 *  combinazione di dadi pari a retroDadi */
			movement(nuovaCasella-retroDadi, this, retroDadi);

			return nuovaCasella;
		}
		
		else if(nuovaCasella == matriceTabellone[0][0].getNumeroCasella()) {
			movement(casellaCorrente, this, combinazioneDadi);
			return nuovaCasella;
			// TODO
			//victory();
		}
		
		/** gestisce il movimento generico della casella */
		else {
			movement(casellaCorrente, this, combinazioneDadi);
			return nuovaCasella;
		}
		
	}
	
	
	/**
	 * Muove la pedina del giocatore in questione dalla casella corrente casellaCorrente
	 * alla nuova casella data dalla somma tra il numero di casella corrente e la
	 * combinazione di dadi ottenuta.
	 * @param casellaCorrente Numero della casella corrente
	 * @param giocatore Giocatore che ha lanciato i dadi
	 * @param combinazioneDadi Combinazione dei dadi ottenuta
	 */
	private void movement(int casellaCorrente, Giocatore giocatore, int combinazioneDadi) {
		/** rimuovo il giocatore dalla casella corrente essendo che deve essere
		 * spostato verso una nuova casella pari a combinazioneDadi+casellaCorrente */
		for(int i=0;i<matriceTabellone.length;++i)
			for(int j=0;j<matriceTabellone[i].length;++j)
				if(matriceTabellone[i][j].getNumeroCasella() == casellaCorrente) {
					/** essendo che la casella con il primo numero del tabellone
					 *  e' stata gia' inizializzata */
					if(matriceTabellone[i][j].getNumeroCasella() == 1)
						continue;
					else {
						matriceTabellone[i][j].rimuoviGiocatore(giocatore);
						matriceTabellone[i][j].repaintCasella();
					}
				}
		
		/** il numero della nuova casella */
		int nuovaCasella = casellaCorrente+combinazioneDadi;
		for(int i=0;i<matriceTabellone.length;++i)
			for(int j=0;j<matriceTabellone[i].length;++j) 
				if(matriceTabellone[i][j].getNumeroCasella() == nuovaCasella) {
					matriceTabellone[i][j].aggiungiGiocatore(giocatore);
					matriceTabellone[i][j].repaintCasella();
			
			}
	}

	@Override public void pescaUnaCarta() {
		// TODO
	}

	@Override public void conservaCarta(Carta carta) {
		carteConservate.add(carta);
	}


	
	@Override public String toString() {
		return nomePedina;
	}
	
}
