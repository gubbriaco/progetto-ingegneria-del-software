package app.esecuzione.mazzo;

import app.esecuzione.mazzo.carte.Carta;

public class MazzoBuffer extends Mazzo {

	public MazzoBuffer(int dimensione) {
		super(dimensione);
	}

	
	@Override public void put(Carta carta) {
		mazzo[in] = carta;
		
		in = (in+1)%mazzo.length;
	}
	

	@Override public Carta get() {
		Carta carta = mazzo[out];
		
		out = (out+1)%mazzo.length;
		
		return carta;
	}


	@Override public void creaMazzo() {
		
		// TODO
		
	}

}
