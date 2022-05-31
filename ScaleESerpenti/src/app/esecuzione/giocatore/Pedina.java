package app.esecuzione.giocatore;

import app.esecuzione.dadi.Dado;
import app.esecuzione.mazzo.Carta;
import app.tabellone.Tabellone;
import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.TipologiaCasella;
import app.tabellone.casella.concrete.special.CasellaSerpente;
import app.tabellone.casella.concrete.special.CasellaScala;
import app.tabellone.object.Scala;
import app.tabellone.object.Serpente;

public class Pedina extends PedinaAstratta {
	
	public CasellaAstratta[][] matriceTabellone;
	

	public Pedina(String nomePedina, Tabellone tabellone) {
		super(nomePedina, tabellone);
		this.matriceTabellone = tabellone.getTabellone();
	}

	private CasellaAstratta getCasella(CasellaAstratta[][] matriceTabellone, int numeroProssimaCasella) {
		
		for(int i=0;i<matriceTabellone.length;++i)
			for(int j=0;j<matriceTabellone[i].length;++j)
				if(matriceTabellone[i][j].getNumeroCasella()==numeroProssimaCasella)
					return matriceTabellone[i][j];
		return null;
		
	}
	
	private int manageRules(CasellaAstratta prossimaCasella) {
		
		int nuovaCasella = prossimaCasella.getNumeroCasella();
		
		System.out.println(this.toString() + " è in " + prossimaCasella.tipologiaCasella);
		
		if(prossimaCasella.tipologiaCasella == TipologiaCasella.UNSOLODADADO) {
			// TODO
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.SOSTAPANCHINA) {
			
			System.out.println(this.toString() + " TURNI="+this.getTurniFermo());
			
			int turniRimanentiDaFermo = this.getTurniFermo() + 1;
			this.setTurniFermo(turniRimanentiDaFermo);
			
			System.out.println(this.toString() + " TURNI="+this.getTurniFermo());
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.SOSTALOCANDA) {
			
			System.out.println(this.toString() + " TURNI="+this.getTurniFermo());
			
			int turniRimanentiDaFermo = this.getTurniFermo() + 3;
			this.setTurniFermo(turniRimanentiDaFermo);
		
			System.out.println(this.toString() + " TURNI="+this.getTurniFermo());
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PREMIODADI) {
			
			Dado dado1 = new Dado(6), dado2 = new Dado(6);
			int lancio1 = dado1.lancio(), lancio2 = dado2.lancio();
			
			int nuovaCombinazioneDadi = lancio1 + lancio2;
			
			this.setCombinazioneDadi( nuovaCombinazioneDadi );
			
			System.out.println(this.toString() + " è in " + this.getCasellaCorrente());
		
			
			nuovaCasella = prossimaCasella.getNumeroCasella()+this.getCombinazioneDadi();
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PREMIOMOLLA) {
			
			System.out.println(this.toString() + " è in " + this.getCasellaCorrente());
			
			
			nuovaCasella = prossimaCasella.getNumeroCasella()+this.getCombinazioneDadi();
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PESCAUNACARTA) {

			// TODO
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.SERPENTE) {
			
			Serpente serpente = ((CasellaSerpente)prossimaCasella).getSerpente();
			
			if( serpente.getTesta() == prossimaCasella.getNumeroCasella() )
				nuovaCasella = serpente.getCoda();
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.SCALA) {
			
			
			Scala scala = ((CasellaScala)prossimaCasella).getScala();
			
			if( scala.getCoda() == prossimaCasella.getNumeroCasella() )
				nuovaCasella = scala.getTesta();
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.STANDARD) {
			return nuovaCasella;
		}
		
		return nuovaCasella;
		
	}
	
	
	private boolean verificaUlterioreMovimento(CasellaAstratta nuovaCasella) {
		boolean scala = nuovaCasella.tipologiaCasella==TipologiaCasella.SCALA 
				&& nuovaCasella.getNumeroCasella()==((CasellaScala)nuovaCasella).getScala().getCoda(), 
				serpente = nuovaCasella.tipologiaCasella==TipologiaCasella.SERPENTE
				&& nuovaCasella.getNumeroCasella()== ((CasellaSerpente)nuovaCasella).getSerpente().getTesta(), 
				premioDadi=nuovaCasella.tipologiaCasella==TipologiaCasella.PREMIODADI , 
				premioMolla=nuovaCasella.tipologiaCasella==TipologiaCasella.PREMIOMOLLA;
		if( scala || serpente || premioDadi || premioMolla )
			return true;
		return false;
	}
	
	
	private int indietreggiaPedina(int casellaCorrente, int combinazioneDadi) {
		
		int nuovaCasella = casellaCorrente + combinazioneDadi,
				
			residuo=0, retroDadi=0;
		
			/** rimuovo il giocatore dall'elenco dei giocatori presenti nella
			 *  casella corrente essendo che dopo effettuo uno spostamento 
			 *  "fantasma" della pedina */
			for(int i=0;i<matriceTabellone.length;++i)
				for(int j=0;j<matriceTabellone[i].length;++j)
					if(matriceTabellone[i][j].getNumeroCasella()==casellaCorrente) {
						matriceTabellone[i][j].rimuoviGiocatore(this);
						matriceTabellone[i][j].repaintCasella();
					}
			
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
			/** gestisco le regole della casella in cui finira' la pedina dopo 
			 *  che indietreggia di un certo numero di caselle*/
			CasellaAstratta prossimaCasella = getCasella(matriceTabellone, nuovaCasella);
			nuovaCasella = manageRules(prossimaCasella);
			
			/** effettuo un movimento "fantasma", cioe', anziche' scrivere un 
			 *  ulteriore funzione, faccio finta che la casella corrente sia una
			 *  casella avente come numero di casella la differenza tra il
			 *  numero della nuovaCasella e la quantita' di posizioni di cui devo
			 *  indietreggiare. Quindi, invoco un movimento della pedina in 
			 *  questione dalla casella "fantasma" appena descritta con una 
			 *  combinazione di dadi pari a retroDadi */
			movement(nuovaCasella-retroDadi, this, nuovaCasella);
			return nuovaCasella;
	
	}
	
	
	private int gestisciRegole(int nuovaCasella) {
		
		CasellaAstratta prossimaCasella = getCasella(matriceTabellone, nuovaCasella);
		
		int nuovaC = manageRules(prossimaCasella);
		
		CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaC);
		while( verificaUlterioreMovimento(nuovaCasellaC) ) {
			nuovaC = manageRules(nuovaCasellaC);
			nuovaCasellaC = getCasella(matriceTabellone, nuovaC);
		}
		
		if(nuovaCasellaC.tipologiaCasella == TipologiaCasella.SOSTAPANCHINA) {
			int turniRimanentiDaFermo = this.getTurniFermo() + 1;
			this.setTurniFermo(turniRimanentiDaFermo);
		}
		else if(nuovaCasellaC.tipologiaCasella == TipologiaCasella.SOSTALOCANDA) {
			int turniRimanentiDaFermo = this.getTurniFermo() + 3;
			this.setTurniFermo(turniRimanentiDaFermo);
		}
		else if(nuovaCasellaC.tipologiaCasella == TipologiaCasella.PESCAUNACARTA) {
			// TODO
		}
		else if(nuovaCasellaC.tipologiaCasella == TipologiaCasella.UNSOLODADADO) {
			// TODO
		}
		
		return nuovaC;
	}
	

	@Override public int movementRequest(int casellaCorrente, int combinazioneDadi) {
		
		/** calcolo la nuova casella della pedina corrispondente */
		int nuovaCasella = casellaCorrente + combinazioneDadi;
		
		if(nuovaCasella>matriceTabellone[0][0].getNumeroCasella())
			nuovaCasella = indietreggiaPedina(casellaCorrente, combinazioneDadi);
		
		int tmp = nuovaCasella;
		nuovaCasella = gestisciRegole(tmp);
		
		
		/** gestisce il caso particolare nel caso in cui la combinazione di dadi
		 *  induce un movimento che fa spostare la pedina oltre il traguardo ->
		 *  -> pertanto, la pedina indietreggia di un numero di caselle pari 
		 *     alla di caselle con cui ha superato il traguardo */
		if(nuovaCasella>matriceTabellone[0][0].getNumeroCasella()) {
			nuovaCasella = indietreggiaPedina(casellaCorrente, combinazioneDadi);
			tmp = nuovaCasella;
			nuovaCasella = gestisciRegole(tmp);
			return nuovaCasella;
		}
		
		else if(nuovaCasella == matriceTabellone[0][0].getNumeroCasella()) {
			movement(casellaCorrente, this, nuovaCasella);
			return nuovaCasella;
			
		}
		
		/** gestisce il movimento generico della casella */
		else {
			movement(casellaCorrente, this, nuovaCasella);
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
	private void movement(int casellaCorrente, Giocatore giocatore, int nuovaCasella) {
		/** rimuovo il giocatore dalla casella corrente essendo che deve essere
		 * spostato verso una nuova casella pari a combinazioneDadi+casellaCorrente */
		for(int i=0;i<matriceTabellone.length;++i)
			for(int j=0;j<matriceTabellone[i].length;++j)
				if(matriceTabellone[i][j].getNumeroCasella() == casellaCorrente) {
						matriceTabellone[i][j].rimuoviGiocatore(giocatore);
						matriceTabellone[i][j].repaintCasella();
				}
		
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
