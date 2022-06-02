package app.esecuzione.mazzo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import app.esecuzione.mazzo.carte.Carta;
import app.esecuzione.mazzo.carte.TipologiaCarta;
import app.esecuzione.mazzo.carte.concrete.CartaDadi;
import app.esecuzione.mazzo.carte.concrete.CartaDivietoDiSosta;
import app.esecuzione.mazzo.carte.concrete.CartaLocanda;
import app.esecuzione.mazzo.carte.concrete.CartaMolla;
import app.esecuzione.mazzo.carte.concrete.CartaPanchina;

public class MazzoBuffer extends Mazzo {

	public MazzoBuffer() {
		super();
	}

	
	@Override public void put(Carta carta) {
		mazzo.offer(carta);
	}
	

	@Override public Carta get() {
		Carta carta = mazzo.peek();
		
		if(carta.tipologiaCarta == TipologiaCarta.DIVIETODISOSTA) {
			mazzo.poll();
		}
		
		return carta;
	}


	@Override public void creaMazzo() {

		/** Considero un mazzo avente due carte per ogni tipologia. Quindi, 
		 * essendo che le tipologie sono 5 allora la dimensione sara' 10. */
		
		mazzo.offer(new CartaPanchina());
		mazzo.offer(new CartaPanchina());
		mazzo.offer(new CartaLocanda());
		mazzo.offer(new CartaLocanda());
		mazzo.offer(new CartaDadi());
		mazzo.offer(new CartaDadi());
		mazzo.offer(new CartaMolla());
		mazzo.offer(new CartaMolla());
		mazzo.offer(new CartaDivietoDiSosta());
		mazzo.offer(new CartaDivietoDiSosta());
		
		/** Mischio il mazzo di carte in maniera random. */
		List<Carta> mazzoDaMischiare = new LinkedList<>(mazzo);
		Collections.shuffle(mazzoDaMischiare);
		mazzo = new LinkedList<>(mazzoDaMischiare);
		
	}

}
