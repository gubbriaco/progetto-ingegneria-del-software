package gui.window.finestraprincipale.concrete;

import java.io.File;

import app.modalita.Modalita;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;

@SuppressWarnings("serial")
public final class FinestraPrincipaleManuale extends FinestraPrincipaleAstratta {

	public FinestraPrincipaleManuale(Modalita.Mod modalita, int numeroGiocatori, int[] dimensioniTabellone) {
		super(modalita, numeroGiocatori, dimensioniTabellone);
		
	}
	
	public FinestraPrincipaleManuale(File file) {
		super(file);
		
	}
	
	
	private FinestraPrincipaleManuale() {super();}
	
	private static FinestraPrincipaleManuale INSTANCE = null;
	
	public static synchronized FinestraPrincipaleManuale getInstance() {
		if(INSTANCE == null)
			INSTANCE = new FinestraPrincipaleManuale();
		return INSTANCE;
	}

	@Override protected void inizializzaTabellone(int nrRighe, int nrColonne) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inizializzaLayoutNORTH() {
		
	}

	@Override
	protected void inizializzaLayoutCENTER() {
		
	}

	@Override
	protected void inizializzaLayoutSOUTH() {
		
	}

	@Override
	protected void inizializzaLayoutWEST() {
		
	}

	@Override
	protected void inizializzaLayoutEAST() {
		
	}

	

	

}
