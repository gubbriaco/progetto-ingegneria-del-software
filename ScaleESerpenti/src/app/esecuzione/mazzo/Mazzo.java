package app.esecuzione.mazzo;

import java.util.LinkedList;
import java.util.Queue;

import app.esecuzione.mazzo.carte.Carta;

public abstract class Mazzo {
	
	protected Queue<Carta> mazzo;
	
	protected int in=0, out=0;
	
	
	public Mazzo() {
		mazzo = new LinkedList<>();
	}
	
	public abstract void put(Carta carta);
	
	public abstract Carta get();
	
	public abstract void creaMazzo();

}
