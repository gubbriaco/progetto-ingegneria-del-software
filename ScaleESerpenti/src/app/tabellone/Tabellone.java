package app.tabellone;

import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.CasellaStandard;
import app.tabellone.casella.concrete.TipologiaCasella;
import app.tabellone.casella.strategy.CasellaCreator;
import app.tabellone.casella.strategy.creators.ScalaCreator;
import app.tabellone.casella.strategy.creators.SerpenteCreator;
import app.tabellone.casella.strategy.creators.special.PescaUnaCartaCreator;
import app.tabellone.casella.strategy.creators.special.UnSoloDadoCreator;
import app.tabellone.casella.strategy.creators.special.premio.PremioDadiCreator;
import app.tabellone.casella.strategy.creators.special.premio.PremioMollaCreator;
import app.tabellone.casella.strategy.creators.special.sosta.SostaLocandaCreator;
import app.tabellone.casella.strategy.creators.special.sosta.SostaPanchinaCreator;
import gui.window.pannello.concrete.PannelloConfigurazione;

public class Tabellone extends TabelloneAstratto {
	
	private CasellaCreator casellaCreator;
	
	public Tabellone(int nrRighe, int nrColonne) {
		super(nrRighe, nrColonne);
	}

	@Override protected void aggiungiCaselleSpeciali(){
		
		if(PannelloConfigurazione.caselleUnSoloDadoINSIDE) {
			casellaCreator = new UnSoloDadoCreator();
			tabellone = casellaCreator.createCasella(tabellone, -1);
		}
		
		for(int i=0;i<tabellone.length;++i) {
			
			/** calcoliamo i bounds, cioe' il numero della prima casella della
			 * riga i-esima e il numero dell'ultima casella della riga i-esima
			 * cosi' da calcolare il numero random, cioe' il numero di casella 
			 * su cui posizionare la testa di una scala o un serpente*/
			int[] bounds = boundsRigaIesima(tabellone[i]);
			
			/** numero della prima casella della riga i-esima*/
			int base = -1, 
			/** numero dell'ultima casella della riga i-esima*/
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
				if(base==1) /** evito di assegnare la prima casella del tabellone
				come casella speciale */
					base=2;
				limite = bounds[0];
			} else {
				base = bounds[0];
				if(base==1)/** evito di assegnare la prima casella del tabellone
				come casella speciale */
					base=2;
				limite = bounds[1];
			}
			
			
			if(PannelloConfigurazione.caselleSostaINSIDE) {
				
				/** numero di casella randomica su cui posizionare la casella speciale
				 *  Sosta */
				int randSostaPanchina = randomSosta.nextInt(base, limite+1);
				casellaCreator = new SostaPanchinaCreator(i, this);
				tabellone = casellaCreator.createCasella(tabellone, randSostaPanchina);
				
				/** numero di casella randomica su cui posizionare la casella speciale
				 *  Sosta */
				int randSostaLocanda = randomSosta.nextInt(base, limite+1);
				casellaCreator = new SostaLocandaCreator(i, this);
				tabellone = casellaCreator.createCasella(tabellone, randSostaLocanda);
			}
			
			if(PannelloConfigurazione.casellePremioINSIDE) {
				/** numero di casella randomica su cui posizionare la casella speciale
				 *  Premio */
				int randPremioDadi = randomPremio.nextInt(base, limite+1);
				casellaCreator = new PremioDadiCreator(i, this);
				tabellone = casellaCreator.createCasella(tabellone, randPremioDadi);
				
				/** numero di casella randomica su cui posizionare la casella speciale
				 *  Premio */
				int randPremioMolla = randomPremio.nextInt(base, limite+1);
				casellaCreator = new PremioMollaCreator(i, this);
				tabellone = casellaCreator.createCasella(tabellone, randPremioMolla);
			}
			
			if(PannelloConfigurazione.casellePescaUnaCartaINSIDE) {
				/** numero di casella randomica su cui posizionare la casella speciale
				 *  Pesca Una Carta */
				int randPescaUnaCarta = randomPescaUnaCarta.nextInt(base, limite+1);
				casellaCreator = new PescaUnaCartaCreator(i, this);
				tabellone = casellaCreator.createCasella(tabellone, randPescaUnaCarta);
			}
			
			if(PannelloConfigurazione.scaleINSIDE) {
				/** numero di casella randomica su cui posizionare la testa della 
				 *  scala */
				int randScala = randomScala.nextInt(base, limite+1);
				casellaCreator = new ScalaCreator(i, this);
				if(randScala!=1)
					tabellone = casellaCreator.createCasella(tabellone, randScala);
				else;
			}
			
			if(PannelloConfigurazione.serpentiINSIDE) {
				/** numero di casella randomica su cui posizionare la testa del 
				 *  serpente */
				int randSerpente = randomSerpente.nextInt(base, limite+1);
				casellaCreator = new SerpenteCreator(i, this);
				if(randSerpente!=1)
					tabellone = casellaCreator.createCasella(tabellone, randSerpente);
				else;
			}
			
		}
		
		for(int i=0;i<tabellone.length;++i)
			for(int j=0;j<tabellone[i].length;++j)
				if(tabellone[i][j].getNumeroCasella()==1)
					if(tabellone[i][j].tipologiaCasella != TipologiaCasella.STANDARD) {
						int numero = tabellone[i][j].getNumeroCasella();
						tabellone[i][j] = new CasellaStandard(numero);
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


}
