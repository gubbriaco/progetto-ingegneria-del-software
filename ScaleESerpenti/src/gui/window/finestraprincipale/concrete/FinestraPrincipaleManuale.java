package gui.window.finestraprincipale.concrete;

import java.io.File;

import app.modalita.Modalita;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;

@SuppressWarnings("serial")
public class FinestraPrincipaleManuale extends FinestraPrincipaleAstratta {
	
	
	
	public FinestraPrincipaleManuale(Modalita.Mod modalita, int numeroGiocatori, int[] dimensioniTabellone) {
		super(modalita, numeroGiocatori, dimensioniTabellone);
	
		//inizializzaTabellone(nrRighe, nrColonne);
		
	}
	
	public FinestraPrincipaleManuale(File file) {
		super(file);

		
	}
	
	@Override protected void inizializzaLayoutWEST() {}

}
