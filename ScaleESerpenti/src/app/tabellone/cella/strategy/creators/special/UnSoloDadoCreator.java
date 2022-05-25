package app.tabellone.cella.strategy.creators.special;

import app.tabellone.Tabellone;
import app.tabellone.cella.CasellaAstratta;
import app.tabellone.cella.factory.CasellaFactory;
import app.tabellone.cella.factory.CasellaFactoryIF;
import app.tabellone.cella.strategy.CasellaCreator;

public class UnSoloDadoCreator implements CasellaCreator {
	
	private CasellaFactoryIF casellaFactory = new CasellaFactory(); 
	
	
	@Override public CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random) {
		
		for(int i=0;i<tabellone.length;++i) {
			for(int j=0;j<tabellone[i].length;++j) {
				
				/** in questo caso non bisogna assegnare caselle perche' sono 
				 * caselle gia' prestabilite per determinate posizioni */
				if(tabellone[i][j].getNumeroCasella() >= Tabellone.CELLE_UN_SOLO_DADO[0] &&
				tabellone[i][j].getNumeroCasella() <= Tabellone.CELLE_UN_SOLO_DADO[1])
					
					tabellone[i][j] = casellaFactory.createCella(
					"UnSoloDado", tabellone[i][j].getNumeroCasella());
					
			}
		}
		
		return tabellone;
	}

}
