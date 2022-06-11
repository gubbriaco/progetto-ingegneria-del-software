package app.esecuzione.mazzo.carte.concrete;

import app.esecuzione.mazzo.carte.Carta;
import app.esecuzione.mazzo.carte.TipologiaCarta;

public class CartaDivietoDiSosta extends Carta {
	
	public CartaDivietoDiSosta() {
		super();
		this.tipologiaCarta = TipologiaCarta.DIVIETODISOSTA;
	}
	
	@Override public String toString() {
		return super.toString() + "Divieto di Sosta";
	}

}
