package app.tabellone.casella.concrete.special;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public abstract class CasellaSosta extends CasellaAstratta  {
	
	public enum CasellaSostaTipologia{PANCHINA, LOCANDA}
	
	
	public CasellaSosta(int numeroCella) {
		super(numeroCella);	
	}


}
