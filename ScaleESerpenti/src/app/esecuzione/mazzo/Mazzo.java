package app.esecuzione.mazzo;

import app.esecuzione.mazzo.carte.Carta;

public abstract class Mazzo {
	
	protected Carta[] mazzo;
	
	protected int in=0, out=0;
	
	
	public Mazzo(int dimensione) {
		mazzo = new Carta[dimensione];
	}
	
	public abstract void put(Carta carta);
	
	public abstract Carta get();
	
	public abstract void creaMazzo();

}
