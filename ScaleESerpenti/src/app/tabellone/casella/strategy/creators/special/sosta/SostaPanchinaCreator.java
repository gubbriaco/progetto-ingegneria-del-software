package app.tabellone.casella.strategy.creators.special.sosta;

import app.tabellone.Tabellone;
import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.strategy.creators.special.SostaCreator;

public class SostaPanchinaCreator extends SostaCreator {

	public SostaPanchinaCreator(int nrRiga, Tabellone t) {
		super(nrRiga, t);
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
					
						
					tabellone[nrRiga][j] = casellaFactory.createCella(
					"SostaPanchina", tabellone[nrRiga][j].getNumeroCasella());
					
					
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
