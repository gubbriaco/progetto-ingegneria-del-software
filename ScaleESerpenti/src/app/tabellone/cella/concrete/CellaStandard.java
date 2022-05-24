package app.tabellone.cella.concrete;

import app.tabellone.cella.CellaAstratta;

@SuppressWarnings("serial")
public class CellaStandard extends CellaAstratta {

	private CellaAstratta successor;
	
	public CellaStandard(int numeroCella) {
		super(numeroCella);
	}

	@Override
	public void draw() {
			
	}

	
	

}
