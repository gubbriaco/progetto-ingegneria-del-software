package app.tabellone.casella.strategy.creators.special;

import app.tabellone.Tabellone;
import app.tabellone.casella.factory.CasellaFactory;
import app.tabellone.casella.factory.CasellaFactoryIF;
import app.tabellone.casella.strategy.CasellaCreator;

public abstract class SostaCreator implements CasellaCreator {
	
	protected int nrRiga;
	protected Tabellone t;
	
	protected CasellaFactoryIF casellaFactory = new CasellaFactory(); 
	
	public SostaCreator(int nrRiga, Tabellone t) {
		this.nrRiga = nrRiga;
		this.t = t;
	}
	

}
