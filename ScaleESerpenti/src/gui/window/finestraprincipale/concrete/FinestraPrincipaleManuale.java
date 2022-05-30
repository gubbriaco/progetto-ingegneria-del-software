package gui.window.finestraprincipale.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.esecuzione.EsecuzioneManuale;
import app.esecuzione.giocatore.Giocatore;
import app.esecuzione.giocatore.Pedina;
import app.modalita.Modalita;
import app.tabellone.Tabellone;
import gui.graphic.border.RoundedBorder;
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
		
		
		
		esecuzione = new EsecuzioneManuale(giocatoriInGioco, (Tabellone) tabellone, 
				                           this, (FinestraTerminale) terminale);
		esecuzione.inizializzaGioco();
		
	}
	
	public FinestraPrincipaleManuale(File file) {
		super(file);	
	}
	
	
	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		pNORTH.setBorder(new RoundedBorder(raggio));
		pNORTH.setBackground(Color.LIGHT_GRAY);
		
		titoloGioco = new JLabel("Scale e Serpenti");
		titoloGioco.setForeground(Color.BLACK);
		font = new Font("Helvetica", Font.BOLD, 14);
		titoloGioco.setFont(font);
		pNORTH.add(titoloGioco, BorderLayout.CENTER);
		
		turno = new JLabel(turnoCorrente);
		turno.setOpaque(true);
		turno.setBackground(Color.LIGHT_GRAY);
		turno.setForeground(Color.BLACK);
		turno.setBorder(new RoundedBorder(raggio));
		pNORTH.add(turno);
		
		prossimoTurno = new JButton("Prossimo turno");
		prossimoTurno.setOpaque(true);
		prossimoTurno.setBackground(Color.GRAY.brighter());
		prossimoTurno.setForeground(Color.BLACK);
		prossimoTurno.setBorder(new RoundedBorder(raggio));
		pNORTH.add(prossimoTurno);

		
		gestisciProssimoTurno();
	
	
		this.add(pNORTH, BorderLayout.NORTH);
		
	}
	
	private void gestisciProssimoTurno() {
		prossimoTurno.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if( ((EsecuzioneManuale)esecuzione).victory==true )
					return;
				else
					esecuzioneManuale();
			}
		});
	}
	
	
	private void esecuzioneManuale() {
		
		esecuzione.esegui();
		this.validate();
		this.repaint();
	}
	
	

	
	
	
	
	@Override protected void inizializzaLayoutWEST() {}

}
