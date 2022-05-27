package gui.window.finestraprincipale.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.modalita.Modalita;
import app.modalita.Modalita.Mod;
import gui.decorator.concrete.ExecManualePanel;
import gui.decorator.concrete.ProssimoTurnoPanel;
import gui.decorator.concrete.SaveSessionPanel;
import gui.graphic.border.RoundedBorder;
import gui.graphic.panel.concrete.CombinazioneDadiCorrentePanel;
import gui.graphic.panel.concrete.GiocatoreCorrentePanel;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;

@SuppressWarnings("serial")
public class FinestraPrincipaleAutomatica extends FinestraPrincipaleAstratta{
	

	public FinestraPrincipaleAutomatica(Mod modalita, int numeroGiocatori, int[] dimensioniTabellone) {
		super(modalita, numeroGiocatori, dimensioniTabellone);
		
		inizializzaTabellone(nrRighe, nrColonne);
	}
	
	public FinestraPrincipaleAutomatica(File file) {
		super(file);
	}
	
	
	

	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		pNORTH.setBackground(Color.GREEN.darker());
		
		titoloGioco = new JLabel("Scale e Serpenti");
		titoloGioco.setForeground(Color.WHITE);
		
		font = new Font("Helvetica", Font.BOLD, 14);
		titoloGioco.setFont(font);

		pNORTH.add(titoloGioco, BorderLayout.CENTER);
		
		decoratorePNORTH = new ProssimoTurnoPanel(pNORTH, turnoCorrente, modalita).decorate();
		pNORTH = decoratorePNORTH;
		

		this.add(pNORTH, BorderLayout.NORTH);
		
	}
	
	
	
	@Override protected void inizializzaLayoutEAST() {
		
		pEAST = new JPanel();
		
		final int raggio = 5;
		
		pEAST.setLayout(new GridLayout(4,1));
		
		giocatoreCorrenteLabel = new GiocatoreCorrentePanel(giocatoreCorrente);
		titoloGiocatoreCorrente = new JLabel("Giocatore corrente");
		titoloGiocatoreCorrente.setOpaque(true);
		titoloGiocatoreCorrente.setBackground(Color.BLACK);
		titoloGiocatoreCorrente.setForeground(Color.WHITE);
		titoloGiocatoreCorrente.setBorder(new RoundedBorder(raggio));
		
		pEAST.add(titoloGiocatoreCorrente);
		pEAST.add(giocatoreCorrenteLabel);
		
		
		titoloCombinazioneDadi = new JLabel("Combinazione dadi");
		titoloCombinazioneDadi.setOpaque(true);
		titoloCombinazioneDadi.setBackground(Color.BLACK);
		titoloCombinazioneDadi.setForeground(Color.WHITE);
		titoloCombinazioneDadi.setBorder(new RoundedBorder(raggio));
		
		combinazioneDadiCorrenteLabel = new CombinazioneDadiCorrentePanel(combinazioneDadiCorrente);
		
		pEAST.add(titoloCombinazioneDadi);
		pEAST.add(combinazioneDadiCorrenteLabel);
		
		
		decoratorePEAST = new ExecManualePanel( pEAST, Modalita.Mod.MANUALE ).decorate();
		pEAST = decoratorePEAST;
		
		decoratorePEAST = new SaveSessionPanel( pEAST ).decorate();
		pEAST = decoratorePEAST;

		this.add(pEAST, BorderLayout.EAST);
		
	}


	
	@Override protected void inizializzaLayoutWEST() {}
	



}
