package app.tabellone.cella.strategy;

import app.tabellone.cella.CasellaAstratta;

public interface CasellaCreator {

	
	CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random);
	

}
