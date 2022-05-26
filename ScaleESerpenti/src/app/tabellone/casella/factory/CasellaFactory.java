package app.tabellone.casella.factory;

import app.exception.FactoryException;
import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.CasellaStandard;
import app.tabellone.casella.concrete.special.CasellaPescaUnaCarta;
import app.tabellone.casella.concrete.special.CasellaScala;
import app.tabellone.casella.concrete.special.CasellaSerpente;
import app.tabellone.casella.concrete.special.CasellaUnSoloDado;
import app.tabellone.casella.concrete.special.premio.CasellaPremioDadi;
import app.tabellone.casella.concrete.special.premio.CasellaPremioMolla;
import app.tabellone.casella.concrete.special.sosta.CasellaSostaLocanda;
import app.tabellone.casella.concrete.special.sosta.CasellaSostaPanchina;

public class CasellaFactory implements CasellaFactoryIF {

	@Override public CasellaAstratta createCella(String tipologia, int numeroCella) {
		
		if(tipologia.equalsIgnoreCase("Standard"))
			return new CasellaStandard(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("UnSoloDado"))
			return new CasellaUnSoloDado(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("SostaPanchina"))
			return new CasellaSostaPanchina(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("SostaLocanda"))
			return new CasellaSostaLocanda(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("PremioDadi"))
			return new CasellaPremioDadi(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("PremioMolla"))
			return new CasellaPremioMolla(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("PescaUnaCarta"))
			return new CasellaPescaUnaCarta(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("Scala"))
			return new CasellaScala(numeroCella);
		
		else if(tipologia.equalsIgnoreCase("Serpente"))
			return new CasellaSerpente(numeroCella);
		
		throw new FactoryException();
		
	}

}
