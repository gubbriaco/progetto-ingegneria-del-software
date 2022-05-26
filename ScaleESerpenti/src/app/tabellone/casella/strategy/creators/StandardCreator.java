package app.tabellone.casella.strategy.creators;

import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.strategy.CasellaCreator;

public class StandardCreator implements CasellaCreator {
	
	

	@Override public CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random) {
		
		return tabellone;
	}

}
