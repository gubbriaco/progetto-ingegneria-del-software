package app.esecuzione.dadi;

import java.util.Random;

public class Dado {
	
	@SuppressWarnings("unused")
	private int numeroFacce;
	
	private Random random;
	private int valoreMAX, valoreMIN;
	
	private int lancio;
	
	public Dado(int numeroFacce) {
		
		this.numeroFacce = numeroFacce;
		
		/** valoreMAX = numeroFacce+1 poiche' la funzione nextInt(valoreMIN, valoreMAX) 
		 * considera l'intervallo di valori compresi tra valoreMIN (incluso) e 
		 * valoreMAX (escluso), pertanto considero il valore con un'unita' in piu'
		 * per tenerlo in conto nell'intervallo dei valori possibili*/
		valoreMAX = numeroFacce+1;
		valoreMIN = 1;
		
		random = new Random();
		
	}
	
	public int lancio() {
		
		lancio = random.nextInt(valoreMIN, valoreMAX);
		
		return lancio;
	}

}
