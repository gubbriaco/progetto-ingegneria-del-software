package app.tabellone;

import app.tabellone.cella.CasellaAstratta;
import app.tabellone.cella.concrete.special.CasellaPremio.CasellaPremioTipologia;
import app.tabellone.cella.concrete.special.CasellaSosta.CasellaSostaTipologia;
import app.tabellone.cella.strategy.CasellaCreator;
import app.tabellone.cella.strategy.creators.ScalaCreator;
import app.tabellone.cella.strategy.creators.SerpenteCreator;
import app.tabellone.cella.strategy.creators.special.PescaUnaCartaCreator;
import app.tabellone.cella.strategy.creators.special.PremioCreator;
import app.tabellone.cella.strategy.creators.special.SostaCreator;
import app.tabellone.cella.strategy.creators.special.UnSoloDadoCreator;

public class Tabellone extends TabelloneAstratto {
	
	
	private CasellaCreator casellaCreator;
	
	private int numeroPanchine, numeroLocande, numeroDadi, numeroMolle;
	
	
	public Tabellone(int nrRighe, int nrColonne) {
		super(nrRighe, nrColonne);
		numeroPanchine = 0;
		numeroLocande = 0;
	}

	
	protected void aggiungiCaselleSpeciali(){
		
		casellaCreator = new UnSoloDadoCreator();
		tabellone = casellaCreator.createCasella(tabellone, -1);
		
		
		for(int i=0;i<tabellone.length;++i) {
			
			/** calcoliamo i bounds, cioe' il numero della prima casella della
			 * riga i-esima e il numero dell'ultima casella della riga i-esima
			 * cosi' da calcolare il numero random, cioe' il numero di cella 
			 * su cui posizionare la testa di una scala o un serpente*/
			int[] bounds = boundsRigaIesima(tabellone[i]);
			
			/** numero della prima cella della riga i-esima*/
			int base = -1, 
			/** numero dell'ultima cella della riga i-esima*/
			    limite = -1;
			
			/** Controllo che il numero della prima casella non sia maggiore del
			 * numero dell'ultima casella della riga i-esima altrimenti verrebbe
			 * sollevata l'eccezzione IllegalArgumentException dovuto al fatto 
			 * che {@link Random#nextInt()} riceve due argomenti dove il primo
			 * deve essere minore del secondo essendo un intervallo di valori.
			 * Nel caso in cui il primo e' maggiore del secondo allora li 
			 * scambio. */
			if(bounds[0]>bounds[1]) {
				base = bounds[1];
				limite = bounds[0];
			} else {
				base = bounds[0];
				limite = bounds[1];
			}
			
			
			
			/** numero di cella randomica su cui posizionare la casella speciale
			 *  Sosta */
			int randSosta = randomSosta.nextInt(base, limite+1);
			casellaCreator = new SostaCreator(i, this, numeroPanchine, numeroLocande);
			tabellone = casellaCreator.createCasella(tabellone, randSosta);
			
			/** numero di cella randomica su cui posizionare la casella speciale
			 *  Premio */
			int randPremio = randomPremio.nextInt(base, limite+1);
			casellaCreator = new PremioCreator(i, this, numeroDadi, numeroMolle);
			tabellone = casellaCreator.createCasella(tabellone, randPremio);
			
			/** numero di cella randomica su cui posizionare la casella speciale
			 *  Pesca Una Carta */
			int randPescaUnaCarta = randomPescaUnaCarta.nextInt(base, limite+1);
			casellaCreator = new PescaUnaCartaCreator(i, this);
			tabellone = casellaCreator.createCasella(tabellone, randPescaUnaCarta);
			
			/** numero di cella randomica su cui posizionare la testa della 
			 *  scala */
			int randScala = randomScala.nextInt(base, limite+1);
			casellaCreator = new ScalaCreator(i, this);
			tabellone = casellaCreator.createCasella(tabellone, randScala);
			
			/** numero di cella randomica su cui posizionare la testa del 
			 *  serpente */
			int randSerpente = randomSerpente.nextInt(base, limite+1);
			casellaCreator = new SerpenteCreator(i, this);
			tabellone = casellaCreator.createCasella(tabellone, randSerpente);
			
		}
		
	}
	
	
	/**
	 * Calcola i bounds, cioe' il numero della prima casella della riga i-esima 
	 * e il numero dell'ultima casella riga i-esima.
	 * @return Bounds della riga i-esima
	 */
	private int[] boundsRigaIesima(CasellaAstratta[] rigaIesima){
		int[] bounds = new int[2];
		
		bounds[0] = rigaIesima[0].getNumeroCasella();
		bounds[1] = rigaIesima[rigaIesima.length-1].getNumeroCasella();
		
		return bounds;
	}
	
	/**
	 * Permette di incrementare le variabili contatore {@link Tabellone#numeroPanchine}
	 * e {@link Tabellone#numeroLocande} cosi' da mantenere un numero effettivo
	 * di {@link CasellaSostaPanchina} e {@link CasellaSostaLocanda} presenti 
	 * nel tabellone.
	 * @param tipologia Tipologia di Casella Sosta
	 */
	public void aggiungiTipologiaSosta(CasellaSostaTipologia tipologia) {
		if(CasellaSostaTipologia.PANCHINA == tipologia)
			numeroPanchine = numeroPanchine+1;
		else
			numeroLocande = numeroLocande+1;
	}
	
	/**
	 * Permette di incrementare le variabili contatore {@link Tabellone#numeroPanchine}
	 * e {@link Tabellone#numeroLocande} cosi' da mantenere un numero effettivo
	 * di {@link CasellaSostaPanchina} e {@link CasellaSostaLocanda} presenti 
	 * nel tabellone.
	 * @param tipologia Tipologia di Casella Sosta
	 */
	public void aggiungiTipologiaPremio(CasellaPremioTipologia tipologia) {
		if(CasellaPremioTipologia.DADI == tipologia)
			numeroDadi = numeroDadi+1;
		else
			numeroMolle = numeroMolle+1;
	}

}
