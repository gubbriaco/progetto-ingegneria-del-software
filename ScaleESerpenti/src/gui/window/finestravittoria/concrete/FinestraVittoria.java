package gui.window.finestravittoria.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.graphic.border.RoundedBorder;
import gui.window.FinestraIF;
import gui.window.finestravittoria.FinestraVittoriaAstratta;
import gui.window.pannello.concrete.PannelloConfigurazione;

@SuppressWarnings("serial")
public class FinestraVittoria extends FinestraVittoriaAstratta {

	private JLabel labelTitle;
	
	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		pNORTH.setBackground(Color.LIGHT_GRAY);
		
		labelTitle = new JLabel("Vincitore della sessione di gioco");
		labelTitle.setBackground(Color.GREEN.darker());
		labelTitle.setForeground(Color.BLACK);
		labelTitle.setBorder(new RoundedBorder(10));
		labelTitle.setOpaque(true);
		pNORTH.add(labelTitle, BorderLayout.CENTER);
		
		this.add(pNORTH, BorderLayout.NORTH);
	}
	

	@Override protected void inizializzaLayoutCENTER() {
		pCENTER = new JPanel();
		pCENTER.setBackground(Color.LIGHT_GRAY);
		
		this.add(pCENTER, BorderLayout.CENTER);
	}

	
	private JButton esci, newGame;
	
	@Override protected void inizializzaLayoutSOUTH() {
		pSOUTH = new JPanel();
		pSOUTH.setBorder(new RoundedBorder(5));
		
		esci = new JButton("ABBANDONA");
		esci.setForeground(Color.BLACK);
		esci.setBackground(Color.RED);
		esci.setBorder(new RoundedBorder(10));
		esci.setOpaque(true);
		pSOUTH.add(esci, BorderLayout.CENTER);
		
		newGame = new JButton("NUOVA SESSIONE DI GIOCO");
		newGame.setForeground(Color.BLACK);
		newGame.setBackground(Color.GREEN);
		newGame.setBorder(new RoundedBorder(10));
		newGame.setOpaque(true);
		pSOUTH.add(newGame, BorderLayout.CENTER);
		
		gestisciButton();
		
		this.add(pSOUTH, BorderLayout.SOUTH);
	}
	
	
	protected FinestraFactoryIF nuovoGiocoFactory = new FinestraFactory();
	protected FinestraIF nuovoGioco;
	
	/**
	 * Gestisce i {@link JButton} {@link FinestraVittoria#esci} e 
	 * {@link FinestraVittoria#newGame}.
	 */
	private void gestisciButton() {
		
		esci.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if( consensoUscita() )
					System.exit(0);
			}
		});
		
		newGame.addActionListener(new ActionListener() {
			
			@Override public void actionPerformed(ActionEvent e) {
				
				/** Salvo tutte le finestre presenti attualmente in esecuzione in
				 *  un array temporanero. */
				Window[] frame = getWindows();
				Window[] tmp = new Window[frame.length];
				for(int i=0;i<frame.length;++i)
					tmp[i] = frame[i];
				
				/** Imposto nuovamente le variabili booleane che gestiscono la 
				 *  configurazione della sessione di gioco pari a false. */
				PannelloConfigurazione.casellePescaUnaCartaINSIDE = false;
				PannelloConfigurazione.casellePremioINSIDE = false;
				PannelloConfigurazione.caselleSostaINSIDE = false;
				PannelloConfigurazione.caselleUnSoloDadoINSIDE = false;
				PannelloConfigurazione.doppioSeiINSIDE = false;
				PannelloConfigurazione.scaleINSIDE = false;
				PannelloConfigurazione.serpentiINSIDE = false;
				
				/** Inizializzo un nuovo pannello delle scelte per permettere una
				 *  configurazione per una nuova sessione di gioco o per 
				 *  ripristinarne una gia' salvata sul calcolatore. */ 
				nuovoGioco = nuovoGiocoFactory.createFinestra("PannelloAstratto", "PannelloScelte", null, -1, null);
				
				/** Chiudo tutte le finestre della sessione di gioco precedente. */
				for(int i=0;i<frame.length;++i)
					tmp[i].dispose();;
				
				/** Inizializzo il nuovo pannello delle scelte. */
				nuovoGioco.inizializzaFinestra();
			}
		});
		
	}

	
	@Override protected void inizializzaLayoutWEST() {}
	@Override protected void inizializzaLayoutEAST() {}

}
