package app.tabellone.casella.strategy.creators.special;

import app.tabellone.Tabellone;
import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.special.CasellaSosta.CasellaSostaTipologia;
import app.tabellone.casella.factory.CasellaFactory;
import app.tabellone.casella.factory.CasellaFactoryIF;
import app.tabellone.casella.strategy.CasellaCreator;

public class SostaCreator implements CasellaCreator {
	
	
	private int nrRiga, numeroPanchine, numeroLocande;
	private Tabellone t;
	
	private CasellaFactoryIF casellaFactory = new CasellaFactory(); 
	
	public SostaCreator(int nrRiga, Tabellone t, int numeroPanchine, int numeroLocande) {
		this.nrRiga = nrRiga;
		this.t = t;
		this.numeroPanchine = numeroPanchine;
		this.numeroLocande = numeroLocande;
	}
	

	@Override public CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random) {

		if(random == tabellone[0][0].getNumeroCasella())
			return tabellone;
		
		for(int j=0;j<tabellone[nrRiga].length;++j){
			
			if( tabellone[nrRiga][j].getNumeroCasella()==random ) {
				
				/** verifico che non si tratti di una casella di tipologia Un 
				 * Solo Dado essendo che quest'ultime sono pre-fissate*/
				if(tabellone[nrRiga][j].getNumeroCasella() >= Tabellone.CELLE_UN_SOLO_DADO[0] &&
				tabellone[nrRiga][j].getNumeroCasella() <= Tabellone.CELLE_UN_SOLO_DADO[1])
					break;
				
				/** controllo che tale casella sia una casella non speciale */
				if( t.verificaCellaNonSpeciale(nrRiga, j) ) {
					
					t.sosta[nrRiga][j] = true;
					
					/** Controllo che il numero di panchine e di locande sia 
					 *  uguale. In questa maniera nel tabellone finale, se siamo
					 *  fortunati, saranno presenti lo stesso numero di panchine 
					 *  e di locande o nel caso peggiore il numero di una delle 
					 *  due avra' uno scarto di un'unita' rispetto al numero 
					 *  dell'altra. Inoltre, in base a quale variabile contatore
					 *  viene incrementata, creo di conseguenza la Casella Sosta
					 *  corrispondente.*/
					if( numeroPanchine<numeroLocande ) {
						t.aggiungiTipologiaSosta(CasellaSostaTipologia.PANCHINA);
						
						tabellone[nrRiga][j] = casellaFactory.createCella(
						"SostaPanchina", tabellone[nrRiga][j].getNumeroCasella());
					}else {
						t.aggiungiTipologiaSosta(CasellaSostaTipologia.LOCANDA);
						
						tabellone[nrRiga][j] = casellaFactory.createCella(
						"SostaLocanda", tabellone[nrRiga][j].getNumeroCasella());
					}
					
					/** e' stata assegnata quindi per la politica di una sola
					 * casella della tipologia in questione mi fermo con 
					 * l'assegnazione di nuove caselle della tipologia in 
					 * questione*/
					break;
				}
			}
		}
		
		return tabellone;
	}

	
}
