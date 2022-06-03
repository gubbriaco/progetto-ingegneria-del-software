package app.esecuzione.giocatore;

import app.esecuzione.dadi.Dado;
import app.esecuzione.mazzo.carte.Carta;
import app.esecuzione.mazzo.carte.TipologiaCarta;
import app.tabellone.Tabellone;
import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.CasellaStandard;
import app.tabellone.casella.concrete.TipologiaCasella;
import app.tabellone.casella.concrete.special.CasellaSerpente;
import app.tabellone.casella.concrete.special.CasellaScala;
import app.tabellone.object.Scala;
import app.tabellone.object.Serpente;
import gui.window.pannello.concrete.PannelloConfigurazione;

public class Pedina extends PedinaAstratta {
	
	public CasellaAstratta[][] matriceTabellone;
	
	public Pedina(String nomePedina, Tabellone tabellone) {
		super(nomePedina, tabellone);
		this.matriceTabellone = tabellone.getTabellone();
	}
	
	
	@Override public int movementRequest(int casellaCorrente, int combinazioneDadi) {
		
		this.setCombinazioneDadi(combinazioneDadi);
		this.setCasellaCorrente(casellaCorrente);
		
		/** All'interno del tabellone prendo la casella corrispondente al numero
		 *  di casella casellaCorrente */
		CasellaAstratta tmp = getCasella(matriceTabellone, casellaCorrente);
		System.out.println("TMP " + tmp.getNumeroCasella());
		
		/** se la pedina si trova su una casella di tipologia un solo dado allora
		 *  usera' un solo dado altrimenti due dadi come nella configurazione 
		 *  normale*/
		int nuovaCasella = gestisciUNSOLODADO(tmp);
		
		/** se supera il numero massimo di casella possibile allora lo faccio 
		 *  indietreggiare */
		if( nuovaCasella > matriceTabellone[0][0].getNumeroCasella() ) {
			/** faccio indietreggiare la pedina */
			nuovaCasella = indietreggia( casellaCorrente, this.getCombinazioneDadi() );
			this.setCasellaCorrente(nuovaCasella);
			System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
			
			/** essendo che la pedina ha indietreggiato, nel caso peggiore in cui
			 *  deve indietreggiare di 6 caselle, finira' sempre su una casella 
			 *  di tipologia UN SOLO DADO quindi non dovro' fare ulteriori 
			 *  movimenti relativi alle regole del gioco */
			movement( casellaCorrente, this, nuovaCasella );
			
			return nuovaCasella;
			
		}
		else { /** significa che il numero della nuova casella e' minore del 
		        massimo numero di casella possibile */
			
			tmp = getCasella(matriceTabellone, nuovaCasella);
			
			/** gestisco le regole */
			nuovaCasella = manageRules( tmp );
			this.setCasellaCorrente(nuovaCasella);
			/** verifico se il numero di casella ottenuto dopo aver gestito le 
			 *  regole non sia maggiore del massimo numero di casella possibile.
			 *  Se si allora faccio indietreggiare la pedina e restituisco il 
			 *  nuovo numero di casella che sara' sicuramente o un numero di 
			 *  casella corrispondente ad un numero di casella di tipologia
			 *  UN SOLO DADO oppure il massimo numero di casella possibile e 
			 *  pertanto il giocatore avra' vinto*/
			if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
				/** faccio indietreggiare la pedina */
				nuovaCasella = indietreggia(casellaCorrente, this.getCombinazioneDadi());
				System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
				this.setCasellaCorrente(nuovaCasella);
				movement(casellaCorrente, this, nuovaCasella);
				return nuovaCasella;
			}
			
			/** se, invece, il numero di casella e' minore del massimo numero di 
			 * casella possibile, verifico che la casella corrispondente al nuovo
			 * numero di casella ottenuto non sia un'altra casella tale per cui 
			 * bisogna applicare le regole del gioco.  Se si allora finche' non
			 * bisognera' piu' applicarle propago la richiesta sempre verso la 
			 * nuova casella calcolata. Ovviamente controllo sempre che il numero
			 * di casella ottenuto sia minore del massimo numero di casella possibile
			 * altrimenti faccio indietreggiare la pedina e restituisco il numero
			 * di casella ottetuto.*/
			while( verificaUlterioreMovimento( getCasella(matriceTabellone, nuovaCasella) ) ) {
				CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaCasella);
				nuovaCasella = manageRules(nuovaCasellaC);
				this.setCasellaCorrente(nuovaCasella);
				
				if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
					/** faccio indietreggiare la pedina */
					nuovaCasella = indietreggia(casellaCorrente, this.getCombinazioneDadi());
					System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
					this.setCasellaCorrente(nuovaCasella);
					movement(casellaCorrente, this, nuovaCasella);
					return nuovaCasella;
				}
			}
					
			/** Se la modalita' doppio sei e' attiva allora gestisco questa modalita' */
			if(PannelloConfigurazione.doppioSeiINSIDE) {
				
				/** Controllo che la pedina non sia finita su una casella di 
				 *  tipologia Sosta con i movimenti di pedina precedenti. Se si
				 *  allora restituisco il numero della casella perche' la pedina
				 *  dovra' rimanere ferma per un certo numero di turni*/
				CasellaAstratta nuovaCasellaConcrete = getCasella(matriceTabellone, nuovaCasella);
				if(nuovaCasellaConcrete.tipologiaCasella==TipologiaCasella.SOSTAPANCHINA ||
				   nuovaCasellaConcrete.tipologiaCasella==TipologiaCasella.SOSTALOCANDA) {
					this.setCasellaCorrente(nuovaCasella);
					movement(casellaCorrente, this, nuovaCasella);
					return nuovaCasella;
				}
				
				/** altrimenti gestisco la modalita doppio sei tramite il metodo
				 * gestisciDoppioSei(int casella) */
				int tmpNuovaCasella = nuovaCasella;
				nuovaCasella = gestisciDoppioSei(tmpNuovaCasella);
				this.setCasellaCorrente(nuovaCasella);
				
				/** Verifico se con il nuovo lancio dei dadi non sia andato oltre
				 *  il massimo numero di casella possibile. Se si faccio indietreggiare
				 *  la pedina e restituisco il numero di casella ottenuto */
				if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
					/** faccio indietreggiare la pedina */
					nuovaCasella = indietreggia(casellaCorrente, this.getCombinazioneDadi());
					System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
					this.setCasellaCorrente(nuovaCasella);
					movement(casellaCorrente, this, nuovaCasella);
					return nuovaCasella;
				}
				

				/** se, invece, il numero di casella e' minore del massimo numero di 
				 * casella possibile, verifico che la casella corrispondente al nuovo
				 * numero di casella ottenuto non sia un'altra casella tale per cui 
				 * bisogna applicare le regole del gioco.  Se si allora finche' non
				 * bisognera' piu' applicarle propago la richiesta sempre verso la 
				 * nuova casella calcolata. Ovviamente controllo sempre che il numero
				 * di casella ottenuto sia minore del massimo numero di casella possibile
				 * altrimenti faccio indietreggiare la pedina e restituisco il numero
				 * di casella ottetuto.*/
				while( verificaUlterioreMovimento( getCasella(matriceTabellone, nuovaCasella) ) ) {
					CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaCasella);
					nuovaCasella = manageRules(nuovaCasellaC);
					this.setCasellaCorrente(nuovaCasella);
					
					if(nuovaCasellaC.tipologiaCasella==TipologiaCasella.SOSTAPANCHINA ||
					   nuovaCasellaC.tipologiaCasella==TipologiaCasella.SOSTALOCANDA)
						return nuovaCasella;
					
					if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
						/** faccio indietreggiare la pedina */
						nuovaCasella = indietreggia(casellaCorrente, this.getCombinazioneDadi());
						System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
						this.setCasellaCorrente(nuovaCasella);
						movement(casellaCorrente, this, nuovaCasella);
						return nuovaCasella;
					}
				}
				
			}
			this.setCasellaCorrente(nuovaCasella);
			movement( casellaCorrente, this, nuovaCasella );
			
			return nuovaCasella;
		}
		
	}
	
	/**
	 * Verifica che la pedina ha ottenuto un doppio sei. Se ha ottenuto un doppio
	 * sei allora permette alla pedina di rilanciare i dadi e spostarsi nella 
	 * nuova casella.
	 * @param casella Casella corrente
	 * @return Nuova casella
	 */
	private int gestisciDoppioSei(int casella) {
		
		int numeroSei = 0;
		for(int i=0;i<dadi.length;++i)
			if(dadi[i].getLancio()==6)
				numeroSei = numeroSei+1;
		
		if(numeroSei<2)
			return casella;
	
		System.out.println("-----DOPPIO SEI----");
		
		for(int i=0;i<dadi.length;++i)
			dadi[i] = new Dado(6);
		
		int lancio = 0, combinazioneDadiDoppioSei=0;
		
		for(int i=0;i<dadi.length;++i) {
			lancio = dadi[i].lancio();
			combinazioneDadiDoppioSei =  combinazioneDadiDoppioSei + lancio;
		}
		
		String attivita="";
		
		for(int w=0;w<dadi.length;++w)
			attivita += dadi[w];
		
		
		System.out.println("La combinazione per il DOPPIO SEI e' " + attivita);
		
		int nuovaCasella = casella + combinazioneDadiDoppioSei;
		
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
	
	
	private int gestisciUNSOLODADO(CasellaAstratta tmp) {
		int nuovaCasella;
		
		if(tmp.tipologiaCasella == TipologiaCasella.UNSOLODADADO) {
			System.out.println(this.toString() + " e' in casella UN SOLO DADO");
			System.out.println("-------------------------" + this.getLancioDeiDadi()[0]);
			int tmpDadi = this.getLancioDeiDadi()[0].getLancio();
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
		
		if(prossimaCasella.tipologiaCasella == TipologiaCasella.SOSTAPANCHINA 
				|| cartaPanchina) {
			
			if(cartaPanchina)
				cartaPanchina = false;
			
			if(!carteConservate.isEmpty()) {
				carteConservate.remove();
				return nuovaCasella;
			}
			
			System.out.println(this.toString() + " TURNI="+this.getTurniFermo());
			
			int turniRimanentiDaFermo = this.getTurniFermo() + 1;
			this.setTurniFermo(turniRimanentiDaFermo);
			
			System.out.println(this.toString() + " TURNI="+this.getTurniFermo());
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.SOSTALOCANDA
				|| cartaLocanda) {
			
			if(cartaLocanda)
				cartaLocanda = false;
			
			if(!carteConservate.isEmpty()) {
				carteConservate.remove();
				return nuovaCasella;
			}
			
			System.out.println(this.toString() + " TURNI="+this.getTurniFermo());
			
			int turniRimanentiDaFermo = this.getTurniFermo() + 3;
			this.setTurniFermo(turniRimanentiDaFermo);
		
			System.out.println(this.toString() + " TURNI="+this.getTurniFermo());
			
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PREMIODADI 
				|| cartaDadi) {
			
			for(int i=0;i<dadi.length;++i)
				dadi[i] = new Dado(6);
			
			int lancio = 0, combinazioneDadiPremioDadi=0;
			
			for(int i=0;i<dadi.length;++i) {
				lancio = dadi[i].lancio();
				combinazioneDadiPremioDadi =  combinazioneDadiPremioDadi + lancio;
			}
			
			String attivita="";
			
			for(int w=0;w<dadi.length;++w)
				attivita += dadi[w];
			
			System.out.println("premio dadi e' " + attivita);
			
			this.setCombinazioneDadi( combinazioneDadiPremioDadi );
			
			System.out.println(this.toString() + " è in " + this.getCasellaCorrente());
			
			nuovaCasella = prossimaCasella.getNumeroCasella()+this.getCombinazioneDadi();
//			this.setCasellaCorrente(nuovaCasella);
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PREMIOMOLLA 
				|| cartaMolla) {
			
			System.out.println(this.toString() + " è in " + this.getCasellaCorrente());
			
			nuovaCasella = prossimaCasella.getNumeroCasella()+this.getCombinazioneDadi();
//			this.setCasellaCorrente(nuovaCasella);
			return nuovaCasella;
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PESCAUNACARTA) {

			nuovaCasella = this.pescaUnaCarta();
			this.setCasellaCorrente(nuovaCasella);
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
	 * @param numeroCasella Numero della casella
	 * @return Casella
	 */
	private CasellaAstratta getCasella(CasellaAstratta[][] matriceTabellone, int numeroCasella) {
		CasellaAstratta ret = new CasellaStandard(numeroCasella);
		
		for(int i=0;i<matriceTabellone.length;i++)
			for(int j=0;j<matriceTabellone[i].length;j++)
				if(matriceTabellone[i][j].getNumeroCasella()==numeroCasella) {
					ret = matriceTabellone[i][j];
				}
		System.out.println("----**------**--" + ret.toString());
		return ret;
		
	}
	
	private boolean cartaDadi, cartaMolla, cartaPanchina, cartaLocanda;
	
	@Override public int pescaUnaCarta() {
		Carta carta = tabellone.getMazzo().get();
		if(carta.tipologiaCarta == TipologiaCarta.DIVIETODISOSTA) {
			System.out.println(this.toString() + " ha pescato una carta DIVIETO DI SOSTA");
			this.conservaCarta(carta);
			return this.getCasellaCorrente();
		}
		else {
			int nuovaCasella = this.getCasellaCorrente();
			
			if(carta.tipologiaCarta == TipologiaCarta.DADI) {
				System.out.println(this.toString() + " ha pescato una carta DADI");
				cartaDadi = true;
			}
			
			if(carta.tipologiaCarta == TipologiaCarta.MOLLA) {
				System.out.println(this.toString() + " ha pescato una carta MOLLA");
				cartaMolla = true;
			}
			
			if(carta.tipologiaCarta == TipologiaCarta.PANCHINA) {
				System.out.println(this.toString() + " ha pescato una carta PANCHINA");
				cartaPanchina = true;
				nuovaCasella = this.getCasellaCorrente()+combinazioneDadi;
			}
			
			if(carta.tipologiaCarta == TipologiaCarta.LOCANDA) {
				System.out.println(this.toString() + " ha pescato una carta LOCANDA");
				cartaLocanda = true;
				nuovaCasella = this.getCasellaCorrente()+combinazioneDadi;
				
			}
			
			

			CasellaAstratta tmp = getCasella(matriceTabellone, nuovaCasella);
			
			/** gestisco le regole */
			nuovaCasella = manageRules( tmp );
			
			/** verifico se il numero di casella ottenuto dopo aver gestito le 
			 *  regole non sia maggiore del massimo numero di casella possibile.
			 *  Se si allora faccio indietreggiare la pedina e restituisco il 
			 *  nuovo numero di casella che sara' sicuramente o un numero di 
			 *  casella corrispondente ad un numero di casella di tipologia
			 *  UN SOLO DADO oppure il massimo numero di casella possibile e 
			 *  pertanto il giocatore avra' vinto*/
			if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
				/** faccio indietreggiare la pedina */
				nuovaCasella = indietreggia(this.getCasellaCorrente(), this.getCombinazioneDadi());
				this.setCasellaCorrente(nuovaCasella);
				System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
				movement(this.getCasellaCorrente(), this, nuovaCasella);
				return nuovaCasella;
			}
			
			/** se, invece, il numero di casella e' minore del massimo numero di 
			 * casella possibile, verifico che la casella corrispondente al nuovo
			 * numero di casella ottenuto non sia un'altra casella tale per cui 
			 * bisogna applicare le regole del gioco.  Se si allora finche' non
			 * bisognera' piu' applicarle propago la richiesta sempre verso la 
			 * nuova casella calcolata. Ovviamente controllo sempre che il numero
			 * di casella ottenuto sia minore del massimo numero di casella possibile
			 * altrimenti faccio indietreggiare la pedina e restituisco il numero
			 * di casella ottetuto.*/
			while( verificaUlterioreMovimento( getCasella(matriceTabellone, nuovaCasella) ) ) {
				CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaCasella);
				nuovaCasella = manageRules(nuovaCasellaC);
				this.setCasellaCorrente(nuovaCasella);
				
				if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
					/** faccio indietreggiare la pedina */
					nuovaCasella = indietreggia(this.getCasellaCorrente(), this.getCombinazioneDadi());
					System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
					this.setCasellaCorrente(nuovaCasella);
					movement(this.getCasellaCorrente(), this, nuovaCasella);
					return nuovaCasella;
				}
			}
			
//			if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
//				/** faccio indietreggiare la pedina */
//				nuovaCasella = indietreggia(casellaCorrente, this.getCombinazioneDadi());
//				System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
//				movement(casellaCorrente, this, nuovaCasella);
//				return nuovaCasella;
//			}
			
			
			/** Se la modalita' doppio sei e' attiva allora gestisco questa modalita' */
			if(PannelloConfigurazione.doppioSeiINSIDE) {
				
				/** Controllo che la pedina non sia finita su una casella di 
				 *  tipologia Sosta con i movimenti di pedina precedenti. Se si
				 *  allora restituisco il numero della casella perche' la pedina
				 *  dovra' rimanere ferma per un certo numero di turni*/
				CasellaAstratta nuovaCasellaConcrete = getCasella(matriceTabellone, nuovaCasella);
				if(nuovaCasellaConcrete.tipologiaCasella==TipologiaCasella.SOSTAPANCHINA ||
				   nuovaCasellaConcrete.tipologiaCasella==TipologiaCasella.SOSTALOCANDA) {
					this.setCasellaCorrente(nuovaCasella);
					movement(this.getCasellaCorrente(), this, nuovaCasella);
					return nuovaCasella;
				}
				
				/** altrimenti gestisco la modalita doppio sei tramite il metodo
				 * gestisciDoppioSei(int casella) */
				int tmpNuovaCasella = nuovaCasella;
				nuovaCasella = gestisciDoppioSei(tmpNuovaCasella);
				this.setCasellaCorrente(nuovaCasella);
				
				/** Verifico se con il nuovo lancio dei dadi non sia andato oltre
				 *  il massimo numero di casella possibile. Se si faccio indietreggiare
				 *  la pedina e restituisco il numero di casella ottenuto */
				if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
					/** faccio indietreggiare la pedina */
					nuovaCasella = indietreggia(this.getCasellaCorrente(), this.getCombinazioneDadi());
					System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
					this.setCasellaCorrente(nuovaCasella);
					movement(this.getCasellaCorrente(), this, nuovaCasella);
					return nuovaCasella;
				}
				

				/** se, invece, il numero di casella e' minore del massimo numero di 
				 * casella possibile, verifico che la casella corrispondente al nuovo
				 * numero di casella ottenuto non sia un'altra casella tale per cui 
				 * bisogna applicare le regole del gioco.  Se si allora finche' non
				 * bisognera' piu' applicarle propago la richiesta sempre verso la 
				 * nuova casella calcolata. Ovviamente controllo sempre che il numero
				 * di casella ottenuto sia minore del massimo numero di casella possibile
				 * altrimenti faccio indietreggiare la pedina e restituisco il numero
				 * di casella ottetuto.*/
				while( verificaUlterioreMovimento( getCasella(matriceTabellone, nuovaCasella) ) ) {
					CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaCasella);
					nuovaCasella = manageRules(nuovaCasellaC);
					this.setCasellaCorrente(nuovaCasella);
					
					if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
						/** faccio indietreggiare la pedina */
						nuovaCasella = indietreggia(this.getCasellaCorrente(), this.getCombinazioneDadi());
						System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
						this.setCasellaCorrente(nuovaCasella);
						movement(this.getCasellaCorrente(), this, nuovaCasella);
						return nuovaCasella;
					}
				}
				
//				if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
//					/** faccio indietreggiare la pedina */
//					nuovaCasella = indietreggia(casellaCorrente, this.getCombinazioneDadi());
//					System.out.println("sta indietreggiando e va a finire in " + nuovaCasella);
//					movement(casellaCorrente, this, nuovaCasella);
//					return nuovaCasella;
//				}
				
			}
			this.setCasellaCorrente(nuovaCasella);
			movement( this.getCasellaCorrente(), this, nuovaCasella );
			
			return nuovaCasella;
			
		}
		
	}


	@Override public void conservaCarta(Carta carta) {
		carteConservate.add(carta);
		
	}
	
	@Override public String toString() {
		return nomePedina;
	}
	
}