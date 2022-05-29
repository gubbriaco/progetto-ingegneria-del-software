package gui.window.finestraprincipale.concrete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import app.esecuzione.EsecuzioneManuale;
import app.esecuzione.giocatore.Giocatore;
import app.esecuzione.giocatore.Pedina;
import app.modalita.Modalita;
import app.tabellone.Tabellone;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;
import gui.window.finestraterminale.concrete.FinestraTerminale;

@SuppressWarnings("serial")
public class FinestraPrincipaleManuale extends FinestraPrincipaleAstratta {
	
	
	
	public FinestraPrincipaleManuale(Modalita.Mod modalita, int numeroGiocatori, int[] dimensioniTabellone) {
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
		
		prossimoTurno.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				esecuzioneManuale();
			}
		});
		
		
		
	}
	
	public FinestraPrincipaleManuale(File file) {
		super(file);

		
	}
	
	
	private void esecuzioneManuale() {
		esecuzione = new EsecuzioneManuale(giocatoriInGioco, (Tabellone) tabellone, 
				this, (FinestraTerminale) terminale, modalita);
		esecuzione.start();
	}
	
	
	
	
	@Override protected void inizializzaLayoutWEST() {}

}
