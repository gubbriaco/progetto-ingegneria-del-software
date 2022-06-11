package app.esecuzione.mazzo;

import java.util.LinkedList;
import java.util.Queue;

import app.esecuzione.mazzo.carte.Carta;

public abstract class Mazzo {
	
	/** si considera un mazzo tale per cui quando viene pescata una carta, se
	 *  non si tratta di una carta di tipologia DIVIETO DI SOSTA, allora la si
	 *  rimette in fondo al mazzo. */
	protected Queue<Carta> mazzo;
	
	protected int in=0, out=0;
	
	
	public Mazzo() {
		mazzo = new LinkedList<>();
	}
	
	/**
	 * Aggiunge una carta al mazzo.
	 * @param carta Carta da aggiungere al mazzo
	 */
	public abstract void put(Carta carta);
	
	/**
	 * Restituisce una carta presa dal mazzo.
	 * @return Carta
	 */
	public abstract Carta get();
	
	/**
	 * Crea il mazzo.
	 */
	public abstract void creaMazzo();

}
