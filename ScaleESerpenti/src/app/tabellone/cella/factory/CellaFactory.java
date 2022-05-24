package app.tabellone.cella.factory;

import app.exception.FactoryException;
import app.tabellone.cella.CellaAstratta;
import app.tabellone.cella.concrete.CellaStandard;
import app.tabellone.cella.concrete.special.CellaUnSoloDado;
import app.tabellone.cella.concrete.special.CellaPescaUnaCarta;
import app.tabellone.cella.concrete.special.CellaPremio;
import app.tabellone.cella.concrete.special.CellaSosta;

public class CellaFactory implements CellaFactoryIF {

	@Override public CellaAstratta createCella(String tipologia, int numeroCella) {
		
		if(tipologia.equalsIgnoreCase("Standard"))
			return new CellaStandard(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("UnSoloDado"))
			return new CellaUnSoloDado(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("Sosta"))
			return new CellaSosta(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("Premio"))
			return new CellaPremio(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("PescaUnaCarta"))
			return new CellaPescaUnaCarta(numeroCella);
		
		throw new FactoryException();
		
	}

}
