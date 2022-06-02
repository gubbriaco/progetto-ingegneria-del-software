package app.esecuzione;

import java.util.LinkedList;

import app.esecuzione.dadi.Dado;
import app.esecuzione.giocatore.Giocatore;
import app.tabellone.Tabellone;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;
import gui.window.finestraprincipale.concrete.FinestraPrincipaleAutomatica;
import gui.window.finestraterminale.concrete.FinestraTerminale;
import gui.window.finestravittoria.concrete.FinestraVittoria;

public class EsecuzioneAutomatica extends Esecuzione {

	public EsecuzioneAutomatica(LinkedList<Giocatore> giocatoriInGioco, Tabellone tabellone,
			FinestraPrincipaleAstratta finestraPrincipale, FinestraTerminale terminale) {
		super(giocatoriInGioco, tabellone, finestraPrincipale, terminale);
	}

	
	@Override public void esegui() {
		
		
		while( !victory ) {
			
			System.out.println(finestraPrincipale.getTurnoCorrente());

			
			int nuovaCasella;
			Giocatore giocatoreCorrente;
			
			/** creo i dadi */
			for(int i=0;i<dadi.length;++i)
				dadi[i] = new Dado(6);
			
			int combinazioneDadi=0,lancio=0;
			
			for(int i=0;i<giocatoriInGioco.size();++i) {
				
				combinazioneDadi = 0;
				lancio = 0;
				
				giocatoreCorrente = giocatoriInGioco.get(i);
				
				/** se il giocatore deve ancora rimanere fermo per un certo numero di 
				 *  turni allora decremento il turno e passo al giocatore successivo*/
				if(giocatoreCorrente.getTurniFermo()>0) {
					
					int turniRimanenti = giocatoreCorrente.getTurniFermo();
					turniRimanenti = turniRimanenti-1;
					giocatoreCorrente.setTurniFermo(turniRimanenti);
					
					continue;
				}
				
				
				for(int k=0;k<dadi.length;++k) {
					lancio = dadi[k].lancio();
					combinazioneDadi = combinazioneDadi + lancio;
				}
				
				giocatoriInGioco.get(i).setLancioDeiDadi(dadi);
				giocatoriInGioco.get(i).setCombinazioneDadi(combinazioneDadi);
				
				
				
				/** la pedina si muove verso la nuova casella */
				nuovaCasella = giocatoreCorrente.movementRequest(
						giocatoreCorrente.getCasellaCorrente(), combinazioneDadi);
				giocatoreCorrente.setCasellaCorrente(nuovaCasella);
				finestraPrincipale.repaint();
				
				String attivita = giocatoreCorrente.toString() + " ha lanciato i dadi:";
				
				for(int w=0;w<dadi.length;++w)
					attivita += dadi[w];
				
				terminale.espandiAttivita(attivita);
				System.out.println(attivita);
		
				giocatoreCorrente.setCasellaCorrente(nuovaCasella);
				
				terminale.espandiAttivita(giocatoreCorrente.toString() + " e' nella casella " 
										 + nuovaCasella );
				System.out.println(giocatoreCorrente.toString() + " e' nella casella " 
						 + nuovaCasella);
				terminale.repaintTerminale();
				
				finestraPrincipale.revalidate();
				finestraPrincipale.repaint();
				
				if(nuovaCasella == finestraPrincipale.getMatriceTabellone()[0][0].getNumeroCasella()) {
					finestraPrincipale.repaint();
					victory = true;
				}
				

			}
			
			
			if(victory) {
				
				victoryWindow = victoryFactory.createFinestra("FinestraVittoriaAstratta", "", null, -1, null);
				victoryWindow.inizializzaFinestra();
				
				((FinestraVittoria)victoryWindow).setGiocatoriVincenti
				( finestraPrincipale.getMatriceTabellone()[0][0].getGiocatori(), (FinestraVittoria)victoryWindow );
				
				((FinestraPrincipaleAutomatica)finestraPrincipale).iniziaEsecuzioneAutomatica.setEnabled(false);
				
				break;
			}
			else {
				turno = turno+1;
				finestraPrincipale.setNuovoTurno(turno);
				finestraPrincipale.repaint();
				
				System.out.println("********* TURNO : " + turno + " *********");
				terminale.espandiAttivita("--- " + "Turno " + turno + " ---");
				terminale.repaintTerminale();
			}
		}
		
		
	}


}
