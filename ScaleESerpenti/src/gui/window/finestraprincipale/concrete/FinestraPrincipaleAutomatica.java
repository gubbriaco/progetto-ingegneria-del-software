package gui.window.finestraprincipale.concrete;

import java.io.File;
import java.util.LinkedList;

import app.esecuzione.EsecuzioneAutomatica;
import app.esecuzione.giocatore.Giocatore;
import app.esecuzione.giocatore.Pedina;
import app.modalita.Modalita.Mod;
import app.tabellone.Tabellone;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;
import gui.window.finestraterminale.concrete.FinestraTerminale;

@SuppressWarnings("serial")
public class FinestraPrincipaleAutomatica extends FinestraPrincipaleAstratta{
	

	public FinestraPrincipaleAutomatica(Mod modalita, int numeroGiocatori, int[] dimensioniTabellone) {
		super(modalita, numeroGiocatori, dimensioniTabellone);
		
		inizializzaTabellone(nrRighe, nrColonne);
		
		
		/** inizializzo la lista dei giocatori presenti nella nuova sessione di gioco*/
		giocatoriInGioco = new LinkedList<>();
		/** aggiungo i giocatori che intendono giocare nella nuova sessione di gioco */
		Giocatore nuovoGiocatore;
		for(int i=0;i<numeroGiocatori;++i){
			nuovoGiocatore = new Pedina("Giocatore " + (i+1), (Tabellone)tabellone);
		  	giocatoriInGioco.add( nuovoGiocatore );
		}
		esecuzione = new EsecuzioneAutomatica(giocatoriInGioco, (Tabellone) tabellone,
				this, (FinestraTerminale) terminale, modalita);
		esecuzione.start();
	}
	
	public FinestraPrincipaleAutomatica(File file) {
		super(file);
	}

	@Override protected void inizializzaLayoutWEST() {}
	
}
