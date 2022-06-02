package app.esecuzione.mazzo.carte.concrete;

import app.esecuzione.mazzo.carte.Carta;
import app.esecuzione.mazzo.carte.TipologiaCarta;

public class CartaMolla extends Carta {
	
	public CartaMolla() {
		super();
		this.tipologiaCarta = TipologiaCarta.MOLLA;
	}
		
	@Override public String toString() {
		return super.toString() + "Molla";
	}

}
