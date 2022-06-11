package main;

import app.game.Applicazione;
import app.game.ScaleESerpenti;

public class Main {
	
	
	public static void main(String...strings) {
		
		/** Applico il design pattern Singleton essendo che deve esistere 
		 * esattamente solo un'istanza della classe Applicazione.*/
		ScaleESerpenti sep = Applicazione.getIstance();
		sep.start();
		
	}
	

}
