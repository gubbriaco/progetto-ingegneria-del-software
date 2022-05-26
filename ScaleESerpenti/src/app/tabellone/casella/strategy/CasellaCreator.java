package app.tabellone.casella.strategy;

import app.tabellone.casella.CasellaAstratta;

public interface CasellaCreator {

	
	CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random);
	

}
