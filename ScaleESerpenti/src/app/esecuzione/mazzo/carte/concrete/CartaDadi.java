package app.esecuzione.mazzo.carte.concrete;

import app.esecuzione.mazzo.carte.Carta;
import app.esecuzione.mazzo.carte.TipologiaCarta;

public class CartaDadi extends Carta {
	
	public CartaDadi() {
		super();
		this.tipologiaCarta = TipologiaCarta.DADI;
	}
	
	@Override public String toString() {
		return super.toString() + "Dadi";
	}

}
