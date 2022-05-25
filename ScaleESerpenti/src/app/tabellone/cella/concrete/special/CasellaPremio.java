package app.tabellone.cella.concrete.special;

import app.tabellone.cella.CasellaAstratta;

@SuppressWarnings("serial")
public abstract class CasellaPremio extends CasellaAstratta  {
	
	public enum CasellaPremioTipologia{DADI, MOLLA}

	public CasellaPremio(int numeroCella) {
		super(numeroCella);
	}

}
