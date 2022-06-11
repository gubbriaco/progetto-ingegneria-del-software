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
import gui.window.finestraterminale.FinestraTerminaleAstratta;
import gui.window.pannello.concrete.PannelloConfigurazione;

public class Pedina extends PedinaAstratta {
	
	public CasellaAstratta[][] matriceTabellone;
	
	public Pedina(String nomePedina, Tabellone tabellone, FinestraTerminaleAstratta terminale) {
		super(nomePedina, tabellone, terminale);
		this.matriceTabellone = tabellone.getTabellone();
	}
	
	String attivita = "";
	@Override public int movementRequest(int casellaCorrente, int combinazioneDadi) {
		
		this.setCombinazioneDadi(combinazioneDadi);
		this.setCasellaCorrente(casellaCorrente);
		
		/** All'interno del tabellone prendo la casella corrispondente al numero
		 *  di casella casellaCorrente */
		CasellaAstratta tmp = getCasella(matriceTabellone, casellaCorrente);
		
		/** se la pedina si trova su una casella di tipologia un solo dado allora
		 *  usera' un solo dado altrimenti due dadi come nella configurazione 
		 *  normale*/
		int nuovaCasella = gestisciUNSOLODADO(tmp);
		
		if(nuovaCasella<=matriceTabellone[0][0].getNumeroCasella()) {
			attivita = this.toString() + " si e' spostato nella casella " + nuovaCasella;
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		
		/** se supera il traguardo allora lo faccio indietreggiare */
		if( nuovaCasella > matriceTabellone[0][0].getNumeroCasella() ) {
			return gestisciOltreTabellone(casellaCorrente, nuovaCasella);
			
		}
		else { /** significa che il numero della nuova casella e' minore del 
		        massimo numero di casella possibile (traguardo) */
			
			tmp = getCasella(matriceTabellone, nuovaCasella);
			this.setCasellaCorrente(nuovaCasella);
			/** gestisco le regole */
			nuovaCasella = manageRules( tmp );
			this.setCasellaCorrente(nuovaCasella);
			/** verifico se il numero di casella ottenuto dopo aver gestito le 
			 *  regole non sia maggiore del massimo numero di casella possibile.
			 *  Se si allora faccio indietreggiare la pedina e restituisco il 
			 *  nuovo numero di casella che sara' sicuramente o un numero di 
			 *  casella corrispondente ad un numero di casella di tipologia
			 *  UN SOLO DADO oppure il massimo numero di casella possibile (traguardo) 
			 *  e pertanto il giocatore avra' vinto*/
			if (nuovaCasella > matriceTabellone[0][0].getNumeroCasella()) {
				return gestisciOltreTabellone(casellaCorrente, nuovaCasella);
			}
			
			/** calcola la nuova casella gestendo la prossima casella fin quando
			 *  risulta essere di tipo speciale */
			nuovaCasella = gestisciFinoAQuandoESpeciale(casellaCorrente, nuovaCasella);
			
			/** verifica la scelta da parte dell'osservatore di aver configurato
			 * 	la sessione di gioco con la modalita' doppio sei e caso mai la
			 *  gestisce di conseguenza */
			nuovaCasella = verificaEGestisciDoppioSei(casellaCorrente, nuovaCasella);
					
			/** imposta come casella corrente della pedina la nuova casella 
			 * calcolata */
			this.setCasellaCorrente(nuovaCasella);
			/** sposta la pedina nella nuova casella */
			movement( casellaCorrente, this, nuovaCasella );
			
			return nuovaCasella;
		}
		
	}
	
	/**
	 * Gestisce il caso in cui la pedina arriva su una casella di tipologia UN 
	 * SOLO DADO oppure il caso generale di movimento della pedina.
	 * @param tmp Casella di arrivo
	 * @return Numero della nuova casella
	 */
	private int gestisciUNSOLODADO(CasellaAstratta tmp) {
		int nuovaCasella;
		/** verifica che la casella in questione e' di tipologia UN SOLO DADO */
		if(tmp.tipologiaCasella == TipologiaCasella.UNSOLODADADO) {
			
			attivita = this.toString() + " e' nella casella di tipologia UN SOLO DADO.";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
			
			/** considera soltanto un dado in questa tipologia di casella */
			int tmpDadi = this.getLancioDeiDadi()[0].getLancio();
			this.setCombinazioneDadi(tmpDadi);
			attivita = this.toString() + " ha lanciato il dado: " + tmpDadi;
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
			
			/** calcola la nuova casella considerando il lancio di un dado */
			nuovaCasella = this.getCasellaCorrente() + this.getCombinazioneDadi();

			return nuovaCasella;
		}
		else {
			/** verifica quanti dadi ha scelto l'osservatore durante la 
			 * configurazione della sessione di gioco*/
			if(dadi.length==1)
				attivita = this.toString() + " ha lanciato il dado:";
			else
				attivita = this.toString() + " ha lanciato i dadi:";
			
			
			for(int w=0;w<dadi.length;++w)
				attivita += dadi[w];
			
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
			
			/** calcola la nuova casella considerando il lancio dei dadi/dado */
			nuovaCasella = this.getCasellaCorrente() + this.getCombinazioneDadi();
			
			return nuovaCasella;
		}
	}
	
	
	/**
	 * Gestisce il caso in cui la combinazione dei dadi porta la pedina oltre il
	 * numero massimo di casella consentito all'interno del tabellone facendola
	 * indietreggiare (ha superato il traguardo e, pertanto indietreggia).
	 * @param casellaCorrente Casella corrente
	 * @param nuovaCasella Possibile nuova casella
	 * @return Nuova casella dopo aver indietreggiato
	 */
	private int gestisciOltreTabellone(int casellaCorrente, int nuovaCasella) {
		/** faccio indietreggiare la pedina */
		nuovaCasella = indietreggia( casellaCorrente, this.getCombinazioneDadi() );
		this.setCasellaCorrente(nuovaCasella);
		
		attivita = this.toString() + " sta indietreggiando...";
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		attivita = this.toString() + " si e' spostato nella casella " + nuovaCasella;
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		/** essendo che la pedina ha indietreggiato, nel caso peggiore in cui
		 *  deve indietreggiare di 6 caselle, finira' sempre su una casella 
		 *  di tipologia UN SOLO DADO quindi non dovro' fare ulteriori 
		 *  movimenti relativi alle regole del gioco */
		movement( casellaCorrente, this, nuovaCasella );
		
		return nuovaCasella;
	}
	
	/**
	 * Gestisce il caso in cui la pedina corrente deve indietreggiare di un certo
	 * numero di posizioni avendo superato il numero massimo di casella del
	 * tabellone corrente.
	 * @param casellaCorrente Numero della casella corrente
	 * @param combinazioneDadi Combinazione dei dadi
	 * @return Numero della nuova casella dopo aver indietreggiato
	 */
	private int indietreggia(int casellaCorrente, int combinazioneDadi) {
		
		int nuovaCasella = casellaCorrente + combinazioneDadi,	
			residuo=0, retroDadi=0;
		
		attivita = this.toString() + " e' oltre il limite massimo del numero di casella";
		terminale.espandiAttivita(attivita);
		
		/**
		 * calcolo il residuo di posizioni che mancano per raggiungere il traguardo
		 * dalla casella corrente
		 */
		residuo = matriceTabellone[0][0].getNumeroCasella() - casellaCorrente;

		/**
		 * calcolo di quanto devo retrocedere dopo che ho raggiunto il traguardo ->
		 * raggiungo il traguardo e dopo indietreggio di una quantita' pari a retroDadi,
		 * cioe' la differenza tra la combinazione dei dadi ottenuta e il residuo di
		 * posizioni che gli e' servito per arrivare al traguardo
		 */
		retroDadi = combinazioneDadi - residuo;
		attivita = this.toString() + " deve indietreggiare di " + retroDadi;
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();

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
		
		if(prossimaCasella.tipologiaCasella == TipologiaCasella.SOSTAPANCHINA 
				|| cartaPanchina) {
			return gestisciSostaPanchina(nuovaCasella);
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.SOSTALOCANDA
				|| cartaLocanda) {
			return gestisciSostaLocanda(nuovaCasella);
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PREMIODADI 
				|| cartaDadi) {
			return gestisciPremioDadi(prossimaCasella, nuovaCasella);
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PREMIOMOLLA 
				|| cartaMolla) {
			return gestisciPremioMolla(prossimaCasella, nuovaCasella);
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.PESCAUNACARTA) {
			return gestisciPescaUnaCarta(nuovaCasella);
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.SERPENTE) {
			return gestisciSerpente(prossimaCasella, nuovaCasella);
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.SCALA) {
			return gestisciScala(prossimaCasella, nuovaCasella);
		}
		
		else if(prossimaCasella.tipologiaCasella == TipologiaCasella.STANDARD) {
			return nuovaCasella;
		}
		
		return nuovaCasella;
	}
	
	/**
	 * Gestisce il caso in cui una pedina dopo aver effettuato un movimento da 
	 * una casella ad un'altra, la nuova casella risulta essere nuovamente 
	 * speciale. Pertanto, fin quando la pedina ricadra' in una nuova casella 
	 * speciale, la situazione verra' gestita di conseguenza.
	 * @param casellaCorrente Casella corrente 
	 * @param nuovaCasella Prossima casella
	 * @return Nuova casella
	 */
	private int gestisciFinoAQuandoESpeciale(int casellaCorrente, int nuovaCasella) {
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
			if(nuovaCasellaC.tipologiaCasella == TipologiaCasella.SOSTAPANCHINA ||
			   nuovaCasellaC.tipologiaCasella == TipologiaCasella.SOSTALOCANDA) {
				this.setCasellaCorrente(nuovaCasella);
				nuovaCasella = manageRules(nuovaCasellaC);
				this.setCasellaCorrente(nuovaCasella);
				return this.getCasellaCorrente();
			}
			this.setCasellaCorrente(nuovaCasella);
			nuovaCasella = manageRules(nuovaCasellaC);
			this.setCasellaCorrente(nuovaCasella);
			
			/** Verifica che la casella corrente sia una casella di tipologia
			 *  sosta. Se si allora si ferma con il movimento ad oltranza della 
			 *  pedina perche' dovra' rimanere ferma per dei turni. Ovviamente,
			 *  considera anche il caso in cui la casella corrente sia una 
			 *  casella speciale di tipologia PESCA UNA CARTA. Pertanto, viene 
			 *  verificato che la pedina abbia pescato una carta di tipologia 
			 *  "panchina" o "locanda" tale che dovra' fermarsi per un certo 
			 *  numero di turni. */
			if(nuovaCasellaC.tipologiaCasella==TipologiaCasella.SOSTAPANCHINA ||
			   nuovaCasellaC.tipologiaCasella==TipologiaCasella.SOSTALOCANDA|| 
			   cartaPanchina || cartaLocanda)
				return nuovaCasella;
			
			/** se supera il numero massimo di casella possibile allora lo faccio 
			 *  indietreggiare */
			if( nuovaCasella > matriceTabellone[0][0].getNumeroCasella() ) {
				return gestisciOltreTabellone(casellaCorrente, nuovaCasella);
			}
		}
		return nuovaCasella;
	}
	
	/**
	 * Verifica che la modalita' doppio sei sia stata configurata dall'osservatore.
	 * Se si allora viene gestita di conseguenza la situazione in cui un giocatore
	 * ottenga dal lancio dei dadi un doppio sei.
	 * @param casellaCorrente Numero della casella corrente
	 * @param nuovaCasella Numero della prossima casella
	 * @return Numero della nuova casella
	 */
	private int verificaEGestisciDoppioSei(int casellaCorrente, int nuovaCasella) {
		/** Se la modalita' doppio sei e' attiva allora gestisco questa modalita' */
		if(PannelloConfigurazione.doppioSeiINSIDE) {
				
			/** Controllo che la pedina non sia finita su una casella di 
			 *  tipologia Sosta con i movimenti di pedina precedenti. Se si
			 *  allora restituisco il numero della casella perche' la pedina
			 *  dovra' rimanere ferma per un certo numero di turni*/
			CasellaAstratta nuovaCasellaConcrete = getCasella(matriceTabellone, nuovaCasella);
			if(nuovaCasellaConcrete.tipologiaCasella==TipologiaCasella.SOSTAPANCHINA ||
			   nuovaCasellaConcrete.tipologiaCasella==TipologiaCasella.SOSTALOCANDA || 
			   cartaPanchina || cartaLocanda) {
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
			if( nuovaCasella > matriceTabellone[0][0].getNumeroCasella() ) {
				return gestisciOltreTabellone(casellaCorrente, nuovaCasella);
				
			}
			/** verifico e gestisco di conseguenza il caso in cui la pedina si 
			 * sia spostata in una casella speciale dopo aver gestito la situazione 
			 * del doppio sei e, quindi, di un ulteriore lancio di dadi.*/
			nuovaCasella = gestisciFinoAQuandoESpeciale(casellaCorrente, nuovaCasella);
		}
		return nuovaCasella;
	}
	
	/**
	 * Verifica che la pedina ha ottenuto un doppio sei. Se ha ottenuto un doppio
	 * sei allora permette alla pedina di rilanciare i dadi e spostarsi nella 
	 * nuova casella.
	 * @param casella Casella corrente
	 * @return Nuova casella
	 */
	private int gestisciDoppioSei(int casella) {
		/** calcolo il numero dei sei che il giocatore ha ottenuto dal lancio 
		 * dei dadi */
		int numeroSei = 0;
		for(int i=0;i<dadi.length;++i)
			if(dadi[i].getLancio()==6)
				numeroSei = numeroSei+1;
		
		/** se il numero dei sei ottenuto durante il lancio dei dadi e' minore 
		 *  di 2 allora il giocatore non ha ottenuto il doppio sei e allora 
		 *  restituisco subito la casella corrente, cioe' la casella che il 
		 *  giocatore aveva gia' raggiunto con la combinazione dei dadi*/
		if(numeroSei<2) 
			return casella;
	
		attivita = this.toString() + " ha ottenuto un doppio sei!";
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		attivita = this.toString() + " sta lanciando nuovamente i dadi... ";
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		/** per la proprieta' del doppio sei, il giocatore lancia nuovamente i 
		 * dadi */
		for(int i=0;i<dadi.length;++i)
			dadi[i] = new Dado(6);
		
		int lancio = 0, combinazioneDadiDoppioSei=0;
		/** calcolo di quanto dovra' spostarsi nuovamente il giocatore */
		for(int i=0;i<dadi.length;++i) {
			lancio = dadi[i].lancio();
			combinazioneDadiDoppioSei =  combinazioneDadiDoppioSei + lancio;
		}
		
		attivita = this.toString() + " ha lanciato i dadi: " + dadi[0] + " " + dadi[1];
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		/** calcolo il numero di casella raggiunto dal giocatore in seguito al
		 *  lancio dei dadi per la proprieta' del doppio sei */
		int nuovaCasella = casella + combinazioneDadiDoppioSei;
		
		attivita = this.toString() + " si e' spostato nella casella " + nuovaCasella;
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		return nuovaCasella;
	}
	
	/**
	 * Verifico che la casella sia di tipologia speciale tale per cui il 
	 * giocatore dovra' effettuare ulteriori movimenti.
	 * @param nuovaCasella Casella da verificare
	 * @return La casella e' speciale
	 */
	private boolean verificaUlterioreMovimento(CasellaAstratta nuovaCasella) {
			
		boolean scala = nuovaCasella.tipologiaCasella==TipologiaCasella.SCALA 
				&& nuovaCasella.getNumeroCasella()==((CasellaScala)nuovaCasella).getScala().getCoda(), 
				serpente = nuovaCasella.tipologiaCasella==TipologiaCasella.SERPENTE
				&& nuovaCasella.getNumeroCasella()== ((CasellaSerpente)nuovaCasella).getSerpente().getTesta(), 
				premioDadi=nuovaCasella.tipologiaCasella==TipologiaCasella.PREMIODADI , 
				premioMolla=nuovaCasella.tipologiaCasella==TipologiaCasella.PREMIOMOLLA;
	
		if( scala || serpente || premioDadi || premioMolla)
			return true;
		return false;
	}
	
	/**
	 * Gestisce l'arrivo di una pedina in una casella di tipologia SOSTA PANCHINA
	 * o il caso in cui il giocatore corrente pesca una carta di tipologia
	 * SOSTA PANCHINA
	 * @param nuovaCasella Numero della casella SOSTA PANCHINA
	 * @return Numero della casella SOSTA PANCHINA
	 */
	private int gestisciSostaPanchina(int nuovaCasella) {
		/** gestisco il caso in cui il giocatore sia in una casella di tipologia
		 *  PESCA UNA CARTA e la sosta panchina sia dovuta ad una carta panchina
		 *  pescata dal mazzo */
		if(cartaPanchina) {
			attivita = " EFFETTO CARTA PANCHINA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		/** gestisco il caso in cui la sosta panchina e' dovuta al fatto che il
		 *  giocatore dopo un certo lancio di dadi sia capitato in una casella di
		 *  tipologia SOSTA PANCHINA */
		if(!cartaPanchina) { /** altrimenti anche nel caso della carta pescata dal mazzo 
		stampa come nel terminale che e' in una casella sosta panchina*/
			attivita = this.toString() + " e' nella casella di tipologia SOSTA PANCHINA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		/** Se la sosta panchina era dovuta ad una carta panchina pescata dal 
		 *  mazzo allora imposto tale variabile a false. Non l'ho impostata
		 *  precedentemente altrimenti non riuscivo a gestire il caso precedente
		 *  di sosta panchina dovuta alla casella SOSTA PANCHINA*/
		if(cartaPanchina)
			cartaPanchina = false;
		/** verifico se il giocatore ha precedentemente pescato una carta DIVIETO
		 * DI SOSTA cosi' da annullare immediatamente l'effetto della sosta panchina */
		if(!carteConservate.isEmpty()) {
			attivita = this.toString() + " usa la carta DIVIETO DI SOSTA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
			carteConservate.remove();
			
			return nuovaCasella;
		}
		
		/** calcolo per quanti turni il giocatore deve rimanere fermo */
		int turniRimanentiDaFermo = this.getTurniFermo() + 1;
		this.setTurniFermo(turniRimanentiDaFermo);
		
		attivita = this.toString() + " deve stare fermo per " + this.getTurniFermo() + " turno!";
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		return nuovaCasella;
	}
	
	/**
	 * Gestisce l'arrivo di una pedina in una casella di tipologia SOSTA LOCANDA
	 * o il caso in cui il giocatore corrente pesca una carta di tipologia
	 * SOSTA LOCANDA
	 * @param nuovaCasella Numero della casella SOSTA LOCANDA
	 * @return Numero della casella SOSTA LOCANDA
	 */
	private int gestisciSostaLocanda(int nuovaCasella) {
		/** gestisco il caso in cui il giocatore sia in una casella di tipologia
		 *  PESCA UNA CARTA e la sosta locanda sia dovuta ad una carta locanda
		 *  pescata dal mazzo */
		if(cartaLocanda) {
			attivita = " EFFETTO CARTA LOCANDA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		/** gestisco il caso in cui la sosta locanda e' dovuta al fatto che il
		 *  giocatore dopo un certo lancio di dadi sia capitato in una casella di
		 *  tipologia SOSTA LOCANDA */
		if(!cartaLocanda) { /** altrimenti anche nel caso della carta pescata dal mazzo 
			stampa come nel terminale che e' in una casella sosta locanda*/
			attivita = this.toString() + " e' nella casella di tipologia SOSTA LOCANDA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		/** Se la sosta locanda era dovuta ad una carta locanda pescata dal 
		 *  mazzo allora imposto tale variabile a false. Non l'ho impostata
		 *  precedentemente altrimenti non riuscivo a gestire il caso precedente
		 *  di sosta locanda dovuta alla casella SOSTA LOCANDA*/
		if(cartaLocanda)
			cartaLocanda = false;
		/** verifico se il giocatore ha precedentemente pescato una carta DIVIETO
		 * DI SOSTA cosi' da annullare immediatamente l'effetto della sosta locanda */
		if(!carteConservate.isEmpty()) {
			attivita = this.toString() + " usa la carta DIVIETO DI SOSTA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
			carteConservate.remove();
			return nuovaCasella;
		}
		
		/** calcolo per quanti turni il giocatore deve rimanere fermo */
		int turniRimanentiDaFermo = this.getTurniFermo() + 3;
		this.setTurniFermo(turniRimanentiDaFermo);
		
		attivita = this.toString() + " deve stare fermo per " + this.getTurniFermo() + " turni!";
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
	
		return nuovaCasella;
	}
	
	/**
	 * Gestisce l'arrivo di una pedina in una casella di tipologia PREMIO DADI
	 * o il caso in cui il giocatore corrente pesca una carta di tipologia
	 * PREMIO DADI
	 * @param prossimaCasella Casella PREMIO DADI 
	 * @param nuovaCasella Numero della casella PREMIO DADI
	 * @return Numero della casella PREMIO DADI
	 */
	private int gestisciPremioDadi(CasellaAstratta prossimaCasella, int nuovaCasella) {
		/** gestisco il caso in cui il giocatore sia in una casella di tipologia
		 *  PESCA UNA CARTA e il premio dadi sia dovuto ad una carta dadi
		 *  pescata dal mazzo */
		if(cartaDadi) {
			attivita = " EFFETTO CARTA DADI";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();	
		}
		/** gestisco il caso in cui il premio dadi e' dovuto al fatto che il
		 *  giocatore dopo un certo lancio di dadi sia capitato in una casella di
		 *  tipologia PREMIO DADI */
		if(!cartaDadi) { /** altrimenti anche nel caso della carta pescata dal mazzo 
			stampa come nel terminale che e' in una casella premio dadi*/
			attivita = this.toString() + " e' nella casella di tipologia PREMIO DADI";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		/** Se il premio dadi era dovuto ad una carta dadi pescata dal mazzo 
		 *  allora imposto tale variabile a false. Non l'ho impostata
		 *  precedentemente altrimenti non riuscivo a gestire il caso precedente
		 *  di premio dadi dovuto alla casella PREMIO DADI*/
		if(cartaDadi)
			cartaDadi = false;
		
		/** il giocatore effettua un nuovo lancio di dadi */
		for(int i=0;i<dadi.length;++i)
			dadi[i] = new Dado(6);
		
		int lancio = 0, combinazioneDadiPremioDadi=0;
		
		/** calcolo di quanto dovra' spostarsi il giocatore */
		for(int i=0;i<dadi.length;++i) {
			lancio = dadi[i].lancio();
			combinazioneDadiPremioDadi =  combinazioneDadiPremioDadi + lancio;
		}
		
		if(dadi.length==1)
			attivita = this.toString() + " ha lanciato il dado " + dadi[0].getLancio();
		else
			attivita = this.toString() + " ha lanciato i dadi: " + dadi[0] + " " + dadi[1];
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		this.setCombinazioneDadi( combinazioneDadiPremioDadi );
		
		/** calcolo la nuova casella in seguito al premio dadi */
		nuovaCasella =prossimaCasella.getNumeroCasella()+this.getCombinazioneDadi();
		
		this.setCasellaCorrente(nuovaCasella);
		
		attivita = this.toString() + " e' nella casella " + this.getCasellaCorrente();
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		/** gestisco il caso in cui la nuova casella sia di tipologia sosta */
		CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaCasella);
		if(nuovaCasellaC.tipologiaCasella == TipologiaCasella.SOSTAPANCHINA ||
		   nuovaCasellaC.tipologiaCasella == TipologiaCasella.SOSTALOCANDA) {
			this.setCasellaCorrente(nuovaCasella);
			nuovaCasella = manageRules(nuovaCasellaC);
			this.setCasellaCorrente(nuovaCasella);
			return this.getCasellaCorrente();
		}
		
		return nuovaCasella;
	}
	
	/**
	 * Gestisce l'arrivo di una pedina in una casella di tipologia PREMIO MOLLA
	 * o il caso in cui il giocatore corrente pesca una carta di tipologia
	 * PREMIO MOLLA
	 * @param prossimaCasella Casella PREMIO MOLLA
	 * @param nuovaCasella Numero della casella PREMIO MOLLA
	 * @return Numero della casella PREMIO MOLLA
	 */
	private int gestisciPremioMolla(CasellaAstratta prossimaCasella, int nuovaCasella) {
		/** gestisco il caso in cui il giocatore sia in una casella di tipologia
		 *  PESCA UNA CARTA e il premio molla sia dovuto ad una carta molla
		 *  pescata dal mazzo */
		if(cartaMolla) {
			attivita = " EFFETTO CARTA MOLLA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		/** gestisco il caso in cui il premio molla e' dovuto al fatto che il
		 *  giocatore dopo un certo lancio di dadi sia capitato in una casella di
		 *  tipologia PREMIO MOLLA */
		if(!cartaMolla) { /** altrimenti anche nel caso della carta pescata dal mazzo 
			stampa come nel terminale che e' in una casella premio molla*/
			attivita = this.toString() + " e' nella casella di tipologia PREMIO MOLLA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		/** Se il premio molla era dovuto ad una carta molla pescata dal mazzo 
		 *  allora imposto tale variabile a false. Non l'ho impostata
		 *  precedentemente altrimenti non riuscivo a gestire il caso precedente
		 *  di premio molla dovuto alla casella PREMIO MOLLA*/
		if(cartaMolla)
			cartaMolla = false;
		
		attivita = this.toString() + " avanza nuovamente della stessa combinazione di dadi...";
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		/** calcolo la nuova casella in seguito al premio molla (il giocatore
		 *  dovra' spostarsi della combinazione dei dadi ottenuta precedemente 
		 *  alla gestione del premio molla) */
		nuovaCasella =prossimaCasella.getNumeroCasella()+this.getCombinazioneDadi();
		
		this.setCasellaCorrente(nuovaCasella);
		
		attivita = this.toString() + " e' nella casella " + this.getCasellaCorrente();
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		/** gestisco il caso in cui la nuova casella sia di tipologia sosta */
		CasellaAstratta nuovaCasellaC = getCasella(matriceTabellone, nuovaCasella);
		if(nuovaCasellaC.tipologiaCasella == TipologiaCasella.SOSTAPANCHINA ||
		   nuovaCasellaC.tipologiaCasella == TipologiaCasella.SOSTALOCANDA) {
			this.setCasellaCorrente(nuovaCasella);
			nuovaCasella = manageRules(nuovaCasellaC);
			this.setCasellaCorrente(nuovaCasella);
			return this.getCasellaCorrente();
		}
		
		return nuovaCasella;
	}
	
	/**
	 * Gestisce l'arrivo di una pedina in una casella di tipologia PESCA UNA CARTA
	 * o il caso in cui il giocatore corrente pesca una carta di tipologia
	 * PESCA UNA CARTA
	 * @param nuovaCasella Numero della casella PESCA UNA CARTA
	 * @return Numero della casella PESCA UNA CARTA
	 */
	private int gestisciPescaUnaCarta(int nuovaCasella) {
		attivita = this.toString() + " e' nella casella di tipologia PESCA UNA CARTA";
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		/** calcola la nuova casella raggiunta dal giocatore pescando una determinata 
		 *  carta dal mazzo */
		nuovaCasella = this.pescaUnaCarta();
		this.setCasellaCorrente(nuovaCasella);
		
		return nuovaCasella;
	}
	
	/**
	 * Gestisce l'arrivo di una pedina in una casella di tipologia SERPENTE
	 * o il caso in cui il giocatore corrente pesca una carta di tipologia
	 * SERPENTE. Nel caso in cui la casella corrente corrisponde alla testa del
	 * serpente corrispondente, allora il giocatore corrispondente scivolera'
	 * sul serpente fin ad arrivare alla coda del serpente.
	 * @param prossimaCasella Casella SERPENTE
	 * @param nuovaCasella Numero della casella SERPENTE
	 * @return Numero della casella SERPENTE
	 */
	private int gestisciSerpente(CasellaAstratta prossimaCasella, int nuovaCasella) {
		/** prende il serpente associato alla casella */
		Serpente serpente = ((CasellaSerpente)prossimaCasella).getSerpente();
		/** Verifica che in tale casella sia contenuta la "testa" del serpente.
		 *  Se si allora fa scivolare il giocatore nella casella contenente la
		 *  "coda" del serpente */
		if( serpente.getTesta() == prossimaCasella.getNumeroCasella() ) {
			attivita = this.toString() + " sta scivolando sul serpente...";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
			
			nuovaCasella = serpente.getCoda();
			attivita = this.toString() + " si e' spostato nella casella " + nuovaCasella;
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		return nuovaCasella;
	}
	
	/**
	 * Gestisce l'arrivo di una pedina in una casella di tipologia SCALA
	 * o il caso in cui il giocatore corrente pesca una carta di tipologia
	 * SCALA. Nel caso in cui la casella corrente corrisponde alla coda della
	 * scala corrispondente, allora il giocatore corrispondente salira'
	 * sulla scala fin ad arrivare alla testa della scala.
	 * @param prossimaCasella Casella SCALA
	 * @param nuovaCasella Numero della casella SCALA
	 * @return Numero della casella SCALA
	 */
	private int gestisciScala(CasellaAstratta prossimaCasella, int nuovaCasella) {
		/** prende la scala associata alla casella */
		Scala scala = ((CasellaScala)prossimaCasella).getScala();
		/** Verifica che in tale casella sia contenuta la "coda" della scala.
		 *  Se si allora fa salire il giocatore nella casella contenente la
		 *  "testa" della scala */
		if( scala.getCoda() == prossimaCasella.getNumeroCasella() ) {
			attivita = this.toString() + " sta salendo sulla scala...";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
			
			nuovaCasella = scala.getTesta();
			attivita = this.toString() + " si e' spostato nella casella " + nuovaCasella;
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
		}
		return nuovaCasella;
	}
	
	/**
	 * Muove la pedina del giocatore Giocatore in questione dalla casella corrente 
	 * casellaCorrente alla nuova casella nuovaCasella.
	 * @param casellaCorrente Numero della casella corrente
	 * @param giocatore Giocatore
	 * @param nuovaCasella Numero della nuova casella
	 */
	private void movement(int casellaCorrente, Giocatore giocatore, int nuovaCasella) {
		/** rimuovo il giocatore dalla casella corrente essendo che deve essere
		 *  spostato verso una nuova casella */
		for(int i=0;i<matriceTabellone.length;++i)
			for(int j=0;j<matriceTabellone[i].length;++j)
				if(matriceTabellone[i][j].getNumeroCasella() == casellaCorrente) {
						matriceTabellone[i][j].rimuoviGiocatore(giocatore);
						matriceTabellone[i][j].repaintCasella();
				}
		/** aggiungo il giocatore corrispondente alla nuova casella */
		for(int i=0;i<matriceTabellone.length;++i)
			for(int j=0;j<matriceTabellone[i].length;++j) 
				if(matriceTabellone[i][j].getNumeroCasella() == nuovaCasella) {
					matriceTabellone[i][j].aggiungiGiocatore(giocatore);
					matriceTabellone[i][j].repaintCasella();
			}
	}

	private boolean cartaDadi, cartaMolla, cartaPanchina, cartaLocanda;
	@Override public int pescaUnaCarta() {
		/** il giocatore pesca una carta dal mazzo */
		Carta carta = tabellone.getMazzo().get();
		attivita = this.toString() + " sta pescando una carta dal mazzo...";
		terminale.espandiAttivita(attivita);
		terminale.repaintTerminale();
		
		/** se la carta e' una carta DIVIETO DI SOSTA allora il giocatore la 
		 *  conserva per un eventuale uso futuro (nel caso in cui capiti in una
		 *  casella di tipologia sosta)*/
		if(carta.tipologiaCarta == TipologiaCarta.DIVIETODISOSTA) {
			attivita = this.toString() + " ha pescato una carta DIVIETO DI SOSTA";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();

			attivita = this.toString() + " ha conservato la carta!";
			terminale.espandiAttivita(attivita);
			terminale.repaintTerminale();
			/** conserva la carta */
			this.conservaCarta(carta);
			return this.getCasellaCorrente();
		}
		else {
			
			int nuovaCasella = this.getCasellaCorrente();
			
			/** se la carta e' una carta DADI allora il giocatore dovra' rilanciare
			 *  i dadi e muoversi in nuova casella */
			if(carta.tipologiaCarta == TipologiaCarta.DADI) {
				attivita = this.toString() + " ha pescato una carta DADI";
				terminale.espandiAttivita(attivita);
				terminale.repaintTerminale();

				cartaDadi = true;/** verra' gestito in manageRules*/

			}
			/** se la carta e' una carta MOLLA allora il giocatore dovra' muoversi
			 *  della stessa combinazione di dadi ottenuta precedentemente */
			if(carta.tipologiaCarta == TipologiaCarta.MOLLA) {
				attivita = this.toString() + " ha pescato una carta MOLLA";
				terminale.espandiAttivita(attivita);
				terminale.repaintTerminale();

				cartaMolla = true; /** verra' gestito in manageRules*/
				
			}
			/** se la carta e' una carta PANCHINA allora il giocatore dovra'
			 *  restare fermo per un turno sulla casella PESCA UNA CARTA */
			if(carta.tipologiaCarta == TipologiaCarta.PANCHINA) {
				attivita = this.toString() + " ha pescato una carta PANCHINA";
				terminale.espandiAttivita(attivita);
				terminale.repaintTerminale();
				
				cartaPanchina = true;
				nuovaCasella = this.getCasellaCorrente();
			}
			/** se la carta e' una carta LOCANDA allora il giocatore dovra'
			 *  restare fermo per tre turni sulla casella PESCA UNA CARTA */
			if(carta.tipologiaCarta == TipologiaCarta.LOCANDA) {
				attivita = this.toString() + " ha pescato una carta LOCANDA";
				terminale.espandiAttivita(attivita);
				terminale.repaintTerminale();
				
				cartaLocanda = true;
				nuovaCasella = this.getCasellaCorrente();
				
			}
			
			CasellaAstratta tmp = getCasella(matriceTabellone, nuovaCasella);
			this.setCasellaCorrente(nuovaCasella);
			/** gestisco le regole */
			nuovaCasella = manageRules( tmp );
			
			/** verifico se il numero di casella ottenuto dopo aver gestito le 
			 *  regole non sia maggiore del massimo numero di casella possibile.
			 *  Se si allora faccio indietreggiare la pedina e restituisco il 
			 *  nuovo numero di casella che sara' sicuramente o un numero di 
			 *  casella corrispondente ad un numero di casella di tipologia
			 *  UN SOLO DADO oppure il massimo numero di casella possibile e 
			 *  pertanto il giocatore avra' vinto*/
			if( nuovaCasella > matriceTabellone[0][0].getNumeroCasella() ) {
				return gestisciOltreTabellone(this.getCasellaCorrente(), nuovaCasella);	
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
			nuovaCasella = gestisciFinoAQuandoESpeciale(this.getCasellaCorrente(), nuovaCasella);
			
			/** Se la modalita' doppio sei e' attiva allora gestisco questa modalita' */
			nuovaCasella = verificaEGestisciDoppioSei(this.getCasellaCorrente(), nuovaCasella);
			
			this.setCasellaCorrente(nuovaCasella);
			movement( this.getCasellaCorrente(), this, nuovaCasella );
			
			return nuovaCasella;
			
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

		return ret;
	}

	
	@Override public void conservaCarta(Carta carta) {
		carteConservate.add(carta);
	}
	
	
	@Override public String toString() {
		return nomePedina;
	}
	
	
}