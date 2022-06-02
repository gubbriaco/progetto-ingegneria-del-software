package app.esecuzione.mazzo.carte.concrete;

import app.esecuzione.mazzo.carte.Carta;
import app.esecuzione.mazzo.carte.TipologiaCarta;

public class CartaPanchina extends Carta {
	
	public CartaPanchina() {
		super();
		this.tipologiaCarta = TipologiaCarta.PANCHINA;
	}
		
	@Override public String toString() {
		return super.toString() + "Panchina";
	}
	
}
