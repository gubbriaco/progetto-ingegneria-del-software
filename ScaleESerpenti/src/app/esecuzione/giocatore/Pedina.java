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
			differenza;
		
		/** gestisce il caso particolare nel caso in cui la combinazione di dadi
		 *  induce un movimento che fa spostare la pedina oltre il traguardo ->
		 *  -> pertanto, la pedina indietreggia di un numero di caselle pari 
		 *     alla di caselle con cui ha superato il traguardo */
		if(nuovaCasella>matriceTabellone[0][0].getNumeroCasella()) {
			differenza = nuovaCasella - (matriceTabellone[0][0].getNumeroCasella());
			
			/** indietreggia fino ad arrivare al numero di casella pari a caselleRimanenti*/
			movement(casellaCorrente, this, (casellaCorrente+differenza) );
			return caselleRimanenti;
		}
		
		else if(nuovaCasella == matriceTabellone[0][0].getNumeroCasella()) {
			// TODO
			//victory();
		}
		
		/** gestisce il movimento generico della casella */
		else {
			movement(casellaCorrente, this, combinazioneDadi);
			return nuovaCasella;
		}
		
		return nuovaCasella;
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
					matriceTabellone[i][j].rimuoviGiocatore(giocatore);
					matriceTabellone[i][j].repaintCasella();
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
