package app.tabellone.cella.strategy.creators;

import app.tabellone.cella.CasellaAstratta;
import app.tabellone.cella.strategy.CasellaCreator;

public class StandardCreator implements CasellaCreator {
	
	

	@Override public CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random) {
		
		return tabellone;
	}

}
