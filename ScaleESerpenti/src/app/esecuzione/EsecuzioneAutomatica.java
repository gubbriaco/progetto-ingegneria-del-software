package app.esecuzione;

import java.util.LinkedList;

import app.esecuzione.dadi.Dado;
import app.esecuzione.giocatore.Giocatore;
import app.tabellone.Tabellone;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;
import gui.window.finestraprincipale.concrete.FinestraPrincipaleAutomatica;
import gui.window.finestraterminale.concrete.FinestraTerminale;
import gui.window.finestravittoria.FinestraVittoria;

public class EsecuzioneAutomatica extends Esecuzione {

	public EsecuzioneAutomatica(LinkedList<Giocatore> giocatoriInGioco, Tabellone tabellone,
			FinestraPrincipaleAstratta finestraPrincipale, FinestraTerminale terminale) {
		super(giocatoriInGioco, tabellone, finestraPrincipale, terminale);
	}

	
	@Override public void esegui() {
		
		while( !victory ) {

			System.out.println(finestraPrincipale.getTurnoCorrente());
			Dado dado1, dado2;
			int combinazioneDadi = 0, lancio1, lancio2, nuovaCasella;
			Giocatore giocatoreCorrente;
			
			for(int i=0;i<giocatoriInGioco.size();++i) {
				
				giocatoreCorrente = giocatoriInGioco.get(i);
				dado1 = new Dado(6); dado2 = new Dado(6);
				
				lancio1 = dado1.lancio();
				lancio2 = dado2.lancio();
				combinazioneDadi = lancio1 + lancio2;
				
				/** la pedina si muove verso la nuova casella */
				nuovaCasella = giocatoreCorrente.movementRequest(
						giocatoreCorrente.getCasellaCorrente(), combinazioneDadi);
				finestraPrincipale.repaint();
				
				terminale.espandiAttivita( giocatoreCorrente.toString() + 
									" ha lanciato i dadi: " + lancio1 + " " + lancio2);
				System.out.println(giocatoreCorrente.toString() + 
						" ha lanciato i dadi: " + lancio1 + " " + lancio2);
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
				
				terminale.espandiAttivita("--- " + "Turno " + turno + " ---");
				terminale.repaintTerminale();
			}
		}
		
		
	}


}
