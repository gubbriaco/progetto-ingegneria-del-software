package app.tabellone.cella.concrete.special;

import app.tabellone.cella.CasellaAstratta;

@SuppressWarnings("serial")
public abstract class CasellaSosta extends CasellaAstratta  {
	
	public enum CasellaSostaTipologia{PANCHINA, LOCANDA}
	

	public CasellaSosta(int numeroCella) {
		super(numeroCella);	
	}

	

}
