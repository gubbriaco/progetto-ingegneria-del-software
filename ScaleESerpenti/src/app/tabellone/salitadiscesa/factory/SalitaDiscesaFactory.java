package app.tabellone.salitadiscesa.factory;

import app.exception.FactoryException;
import app.tabellone.salitadiscesa.SalitaDiscesaAstratta;
import app.tabellone.salitadiscesa.concrete.Scala;
import app.tabellone.salitadiscesa.concrete.Serpente;

public class SalitaDiscesaFactory implements SalitaDiscesaFactoryIF {
	
	

	@Override public SalitaDiscesaAstratta createSalitaDiscesa
				     (String tipologia,int casellaPartenza, int casellaArrivo) {
		
		if(tipologia.equalsIgnoreCase("Scala"))
			return new Scala(casellaPartenza, casellaArrivo);
		if(tipologia.equalsIgnoreCase("Serpente"))
			return new Serpente(casellaPartenza, casellaArrivo);
		
		throw new FactoryException();
		
	}
	
	
	

}
