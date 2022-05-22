package gui.window.finestraprincipale.concrete;

import java.io.File;

import app.difficolta.Difficolta;
import app.modalita.Modalita.Mod;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;

@SuppressWarnings("serial")
public class FinestraPrincipaleManuale extends FinestraPrincipaleAstratta {

	public FinestraPrincipaleManuale(Mod modalita, int numeroGiocatori, Difficolta difficolta) {
		super(modalita, numeroGiocatori, difficolta);
		// TODO Auto-generated constructor stub
	}
	
	public FinestraPrincipaleManuale(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	protected void inizializzaLayoutNORTH() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inizializzaLayoutCENTER() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inizializzaLayoutSOUTH() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inizializzaLayoutWEST() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inizializzaLayoutEAST() {
		// TODO Auto-generated method stub
		
	}

	

}
