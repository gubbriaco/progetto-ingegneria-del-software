package app.tabellone.casella.factory;

import app.tabellone.casella.CasellaAstratta;

public interface CasellaFactoryIF {
	
	
	CasellaAstratta createCella(String tipologia, int numeroCella);

}
