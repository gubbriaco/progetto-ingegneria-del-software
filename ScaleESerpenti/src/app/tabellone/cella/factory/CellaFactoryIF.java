package app.tabellone.cella.factory;

import app.tabellone.cella.CellaAstratta;

public interface CellaFactoryIF {
	
	
	CellaAstratta createCella(String tipologia, int numeroCella);

}
