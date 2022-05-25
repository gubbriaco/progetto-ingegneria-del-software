package app.tabellone.cella.factory;

import app.tabellone.cella.CasellaAstratta;

public interface CasellaFactoryIF {
	
	
	CasellaAstratta createCella(String tipologia, int numeroCella);

}
