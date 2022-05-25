package app.tabellone.cella.factory;

import app.exception.FactoryException;
import app.tabellone.cella.CasellaAstratta;
import app.tabellone.cella.concrete.CasellaStandard;
import app.tabellone.cella.concrete.special.CasellaUnSoloDado;
import app.tabellone.cella.concrete.special.premio.CasellaPremioDadi;
import app.tabellone.cella.concrete.special.premio.CasellaPremioMolla;
import app.tabellone.cella.concrete.special.sosta.CasellaSostaLocanda;
import app.tabellone.cella.concrete.special.sosta.CasellaSostaPanchina;
import app.tabellone.cella.concrete.special.CasellaPescaUnaCarta;
import app.tabellone.cella.concrete.special.CasellaScala;
import app.tabellone.cella.concrete.special.CasellaSerpente;

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
