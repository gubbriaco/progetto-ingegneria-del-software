package gui.window.finestraprincipale.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.modalita.Modalita.Mod;
import app.tabellone.Tabellone;
import app.tabellone.TabelloneAstratto;
import app.tabellone.cella.CasellaAstratta;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;

@SuppressWarnings("serial")
public final class FinestraPrincipaleAutomatica extends FinestraPrincipaleAstratta{
	
	private CasellaAstratta[][] matriceTabellone;
	
	private JLabel label;
	
	private TabelloneAstratto tabellone;
	private GridLayout gl;
	

	public FinestraPrincipaleAutomatica(Mod modalita, int numeroGiocatori, int[] dimensioniTabellone) {
		super(modalita, numeroGiocatori, dimensioniTabellone);
		
		inizializzaTabellone(nrRighe, nrColonne);
	}
	
	public FinestraPrincipaleAutomatica(File file) {
		super(file);
	}
	
	
	private FinestraPrincipaleAutomatica() {super();}
	
	private static FinestraPrincipaleAutomatica INSTANCE = null;
	
	public static synchronized FinestraPrincipaleAutomatica getInstance() {
		if(INSTANCE == null)
			INSTANCE = new FinestraPrincipaleAutomatica();
		return INSTANCE;
	}

	
	@Override protected void inizializzaTabellone(int nrRighe, int nrColonne) {
		
		tabellone = new Tabellone(nrRighe, nrColonne);
		
		//matriceTabellone = new CasellaStandard[nrRighe][nrColonne];
		matriceTabellone = tabellone.getTabellone();
		
		
	}

	

	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		pNORTH.setBackground(Color.GREEN.darker());
		this.add(pNORTH, BorderLayout.NORTH);
		
		label = new JLabel("Scale e Serpenti");
		label.setForeground(Color.WHITE);
		pNORTH.add(label, BorderLayout.CENTER);
		font = new Font("Helvetica", Font.BOLD, 14);
		label.setFont(font);
	}

	@Override protected void inizializzaLayoutCENTER() {
		pCENTER = new JPanel();
		this.add(pCENTER, BorderLayout.CENTER);
		
		inizializzaLayoutTabellone();
	}
	
	
	private void inizializzaLayoutTabellone() {
		int nrRighe, nrColonne;
		
		nrRighe = matriceTabellone.length;
		nrColonne = matriceTabellone[0].length;
		gl = new GridLayout(nrRighe, nrColonne);
		pCENTER.setLayout(gl);
		
		for(int i=0;i<nrRighe;++i)
			for(int j=0;j<nrColonne;++j)
				pCENTER.add(matriceTabellone[i][j],BorderLayout.CENTER);
		
	}


	@Override protected void inizializzaLayoutSOUTH() {
		//TODO
	}

	@Override protected void inizializzaLayoutWEST() {}

	@Override protected void inizializzaLayoutEAST() {}


}
