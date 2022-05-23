package gui.window.finestraprincipale.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.difficolta.Difficolta;
import app.modalita.Modalita.Mod;
import app.tabellone.Tabellone;
import app.tabellone.cella.CellaAstratta;
import app.tabellone.cella.concrete.Cella;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;

@SuppressWarnings("serial")
public class FinestraPrincipaleAutomatica extends FinestraPrincipaleAstratta{
	
	
	private CellaAstratta[][] matrice;
	
	private JLabel label;
	private GridLayout gl;
	

	public FinestraPrincipaleAutomatica(Mod modalita, int numeroGiocatori, Difficolta difficolta) {
		super(modalita, numeroGiocatori, difficolta);
		
		inizializzaTabellone(difficolta);
	}
	
	public FinestraPrincipaleAutomatica(File file) {
		super(file);
	}
	
	
	private void inizializzaTabellone(Difficolta difficolta) {
		if(difficolta == Difficolta.FACILE) {
			matrice = new Cella[Tabellone.dimensioniFACILE[0]][Tabellone.dimensioniFACILE[1]];
			matrice = Tabellone.getTabellone("TabelloneFACILE");
		}else if(difficolta == Difficolta.MEDIA) {
			matrice = new Cella[Tabellone.dimensioniMEDIA[0]][Tabellone.dimensioniMEDIA[1]];
			matrice = Tabellone.getTabellone("TabelloneMEDIA");
		}else {
			matrice = new Cella[Tabellone.dimensioniDIFFICILE[0]][Tabellone.dimensioniDIFFICILE[1]];
			matrice = Tabellone.getTabellone("TabelloneDIFFICILE");
		}
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
		
		nrRighe = matrice.length;
		nrColonne = matrice[0].length;
		gl = new GridLayout(nrRighe, nrColonne);
		pCENTER.setLayout(gl);
		
		for(int i=0;i<nrRighe;++i)
			for(int j=0;j<nrColonne;++j)
				pCENTER.add(matrice[i][j],BorderLayout.CENTER);
		
		inizializzaTabellone();
	}
	
	private void inizializzaTabellone() {
		
	}

	@Override protected void inizializzaLayoutSOUTH() {
	
	}

	@Override protected void inizializzaLayoutWEST() {
		
	}

	@Override protected void inizializzaLayoutEAST() {
	
	}


}
