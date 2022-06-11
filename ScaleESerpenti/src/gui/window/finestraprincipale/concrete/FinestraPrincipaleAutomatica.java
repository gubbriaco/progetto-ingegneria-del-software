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

import app.esecuzione.EsecuzioneAutomatica;
import app.esecuzione.giocatore.Giocatore;
import app.esecuzione.giocatore.Pedina;
import app.modalita.Modalita.Mod;
import app.tabellone.Tabellone;
import gui.graphic.border.RoundedBorder;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;
import gui.window.finestraterminale.FinestraTerminaleAstratta;
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
			nuovoGiocatore = new Pedina("Giocatore " + (i+1), (Tabellone)tabellone, (FinestraTerminaleAstratta)terminale);
		  	giocatoriInGioco.add( nuovoGiocatore );
		}
		
		esecuzione = new EsecuzioneAutomatica(giocatoriInGioco, (Tabellone) tabellone,
                                              this, (FinestraTerminale) terminale);
		esecuzione.inizializzaGioco();
		
	}
	
	public FinestraPrincipaleAutomatica(File file) {
		super(file);
	}
	
	
	public JButton iniziaEsecuzioneAutomatica;
	
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

		iniziaEsecuzioneAutomatica = new JButton("Esegui automaticamente");
		iniziaEsecuzioneAutomatica.setOpaque(true);
		iniziaEsecuzioneAutomatica.setBackground(Color.GREEN.darker().darker());
		iniziaEsecuzioneAutomatica.setForeground(Color.WHITE);
		iniziaEsecuzioneAutomatica.setBorder(new RoundedBorder(raggio));
		pNORTH.add(iniziaEsecuzioneAutomatica);
	
		gestisciEsecuzioneAutomatica();
		
		
		salva = new JButton("SALVA");
		salva.setOpaque(true);
		salva.setBackground(Color.YELLOW.darker().darker());
		salva.setForeground(Color.BLACK);
		salva.setBorder(new RoundedBorder(raggio));
		pNORTH.add(salva);
		
		gestisciSalvataggio();
		
		this.add(pNORTH, BorderLayout.NORTH);
		
	}
	
	/**
	 * Cattura l'evento di "click" sul pulsante 
	 * {@link FinestraPrincipaleAutomatica#iniziaEsecuzioneAutomatica}.
	 */
	private void gestisciEsecuzioneAutomatica() {
		iniziaEsecuzioneAutomatica.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				esecuzioneAutomatica();
			}
		});
	}
	
	/**
	 * Invoca l'esecuzione automatica della sessione di gioco.
	 */
	private void esecuzioneAutomatica() {
		esecuzione.esegui();
		this.validate();
		this.repaint();
	}
	
	@Override protected void inizializzaLayoutEAST() {}
	@Override protected void inizializzaLayoutWEST() {}
	
	
}
