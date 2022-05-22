package gui.decorator.concrete;

import gui.decorator.FinestraDecorator;
import gui.decorator.FinestraServiceIF;

public class ManualeExecButtonDecorator extends FinestraDecorator  {

	public ManualeExecButtonDecorator(FinestraServiceIF finestraServiceIf) {
		super(finestraServiceIf);
	}
	
	
	@Override public void decorate() {
		super.decorate();
		decorateWithManualeExecButton();
		//TODO decora la finestra con un bottone per l'esecuzione manuale ->
		//-> per la FinestraPrincipaleManuale
	}
	
	private void decorateWithManualeExecButton() {
		
	}

}
