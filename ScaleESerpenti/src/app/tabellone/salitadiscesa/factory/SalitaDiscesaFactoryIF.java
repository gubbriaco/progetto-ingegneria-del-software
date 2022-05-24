package app.tabellone.salitadiscesa.factory;

import app.tabellone.salitadiscesa.SalitaDiscesaAstratta;

public interface SalitaDiscesaFactoryIF {
	
	
	SalitaDiscesaAstratta createSalitaDiscesa
		 			      (String tipologia, int casellaPartenza, int casellaArrivo);
	

}
