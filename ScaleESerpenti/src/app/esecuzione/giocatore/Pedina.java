package app.esecuzione.giocatore;

import app.esecuzione.dadi.Dado;
import app.esecuzione.mazzo.carte.Carta;
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
	
	
	@Override public int movementRequest(int casellaCorrente, int combinazioneDadi) {
		
		this.setCombinazioneDadi(combinazioneDadi);
		this.setCasellaCorrente(casellaCorrente);
		
		CasellaAstratta tmp = getCasella(matriceTabellone, casellaCorrente);
		int nuovaCasella = gestisciUNSOLODADO(tmp);
		
		if( nuovaCasella > matriceTabellone[0][0].getNumeroCasella() ) {
			
			nuovaCasella = indietreggia( casellaCorrente, this.getCombinazioneDadi() );
			System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
			
			tmp = getCasella(matriceTabellone, nuovaCasella);
			
			nuovaCasella = manageRules( tmp );
			
			while( verificaUlterioreMovimento( getCasella(matriceTabellone, nuovaCasella) ) ) {
				CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaCasella);
				nuovaCasella = manageRules(nuovaCasellaC);
			}
			
			movement( casellaCorrente, this, nuovaCasella );
			
			return nuovaCasella;
			
		}else {
			
			tmp = getCasella(matriceTabellone, nuovaCasella);
			
			nuovaCasella = manageRules( tmp );
			
			while( verificaUlterioreMovimento( getCasella(matriceTabellone, nuovaCasella) ) ) {
				CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaCasella);
				nuovaCasella = manageRules(nuovaCasellaC);
			}
			
			movement( casellaCorrente, this, nuovaCasella );
			
			return nuovaCasella;
		}
		
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
	
	
	private int gestisciUNSOLODADO(CasellaAstratta tmp) {
		int nuovaCasella;
		
		if(tmp.tipologiaCasella == TipologiaCasella.UNSOLODADADO) {
			System.out.println(this.toString() + " e' in casella UN SOLO DADO");
			System.out.println("-------------------------" + this.getLancioDeiDadi()[1]);
			int tmpDadi = this.getCombinazioneDadi()-(this.getLancioDeiDadi()[1]);
			this.setCombinazioneDadi(tmpDadi);
			System.out.println("LA NUOVA COMBINAZIONE DEI DADI PER UN SOLO DADO E' :" + this.getCombinazioneDadi());
			nuovaCasella = this.getCasellaCorrente() + this.getCombinazioneDadi();
			System.out.println(this.toString() + " e' in " + nuovaCasella);
			return nuovaCasella;
		}
		else {
			 nuovaCasella = this.getCasellaCorrente() + this.getCombinazioneDadi();
			 return nuovaCasella;
		}
		
	}
	
	
	private int indietreggia(int casellaCorrente, int combinazioneDadi) {
		
		int nuovaCasella = casellaCorrente + combinazioneDadi,	
			residuo=0, retroDadi=0;
		
//		System.out.println("SONO OLTRE!!!");
		/**
		 * calcolo il residuo di posizioni che mancano per raggiungere il traguardo
		 * dalla casella corrente
		 */
		residuo = matriceTabellone[0][0].getNumeroCasella() - casellaCorrente;

		/**
		 * calcolo di quanto devo retrocedere dopo che ho raggiunto il traguardo -> ->
		 * raggiungo il traguardo e dopo indietreggio di una quantita' pari a retroDadi,
		 * cioe' la differenza tra la combinazione dei dadi ottenuta e il residuo di
		 * posizioni che gli e' servito per arrivare al traguardo
		 */
		retroDadi = combinazioneDadi - residuo;

		System.out.println("deve indietreggiare di " + retroDadi);

		/**
		 * pertanto calcolo il numero della nuova casella in cui si trovera' dopo aver
		 * indietreggiato di un numero di posizioni pari a retroDadi
		 */
		nuovaCasella = matriceTabellone[0][0].getNumeroCasella() - retroDadi;
		
		return nuovaCasella;
	}
	
	
	/**
	 * Gestisce le regole basi del gioco Scale e Serpenti. Nel particolare, tale
	 * metodo prende in input la casella che deve essere raggiunta, verifica che
	 * tale casella sia una casella speciale su cui applicare le regole del gioco, 
	 * gestisce le regole corrispondenti e restituisce il numero della casella 
	 * raggiunto dopo aver applicato le regole del gioco.
	 * @param prossimaCasella Prossima casella che deve essere raggiunta
	 * @return Numero della casella raggiunta
	 */
	private int manageRules(CasellaAstratta prossimaCasella) {
		
		int nuovaCasella = prossimaCasella.getNumeroCasella();
		
		System.out.println(this.toString() + " è in " + prossimaCasella.tipologiaCasella);
		
		if(prossimaCasella.tipologiaCasella == TipologiaCasella.SOSTAPANCHINA) {
			
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
			System.out.println("premio dadi e' " + lancio1 + " " + lancio2);
			
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
			
			if( serpente.getTesta() == prossimaCasella.getNumeroCasella() ) {
				System.out.println("SERPENTEEEEE-----------------------");
				nuovaCasella = serpente.getCoda();
			}
			
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


	
	/**
	 * Restituisce la casella corrispondente al numero di casella passato in 
	 * input al metodo.
	 * @param matriceTabellone Matrice del tabellone di gioco
	 * @param numeroProssimaCasella Numero della casella
	 * @return Casella
	 */
	private CasellaAstratta getCasella(CasellaAstratta[][] matriceTabellone, int numeroProssimaCasella) {
		
		for(int i=0;i<matriceTabellone.length;++i)
			for(int j=0;j<matriceTabellone[i].length;++j)
				if(matriceTabellone[i][j].getNumeroCasella()==numeroProssimaCasella)
					return matriceTabellone[i][j];
		return null;
		
	}
	
	
	
	@Override public void pescaUnaCarta() {
		// TODO Auto-generated method stub
		
	}


	@Override public void conservaCarta(Carta carta) {
		// TODO Auto-generated method stub
		
	}
	
	@Override public String toString() {
		return nomePedina;
	}
	
}