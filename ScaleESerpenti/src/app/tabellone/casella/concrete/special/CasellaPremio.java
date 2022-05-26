package app.tabellone.casella.concrete.special;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public abstract class CasellaPremio extends CasellaAstratta  {
	
	public enum CasellaPremioTipologia{DADI, MOLLA}

	public CasellaPremio(int numeroCella) {
		super(numeroCella);
	}

}
