package app.esecuzione.mazzo.carte.concrete;

import app.esecuzione.mazzo.carte.Carta;
import app.esecuzione.mazzo.carte.TipologiaCarta;

public class CartaLocanda extends Carta {
	
	public CartaLocanda() {
		super();
		this.tipologiaCarta = TipologiaCarta.LOCANDA;
	}
	
	@Override public String toString() {
		return super.toString() + "Locanda";
	}
	
	

}
