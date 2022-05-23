package gui.factory;

import app.difficolta.Difficolta;
import app.modalita.Modalita;
import gui.decorator.concrete.ManualeExecButtonDecorator;
import gui.window.FinestraIF;
import gui.window.finestraerrore.concrete.FinestraErrore;
import gui.window.finestraprincipale.concrete.FinestraPrincipaleAutomatica;
import gui.window.finestraprincipale.concrete.FinestraPrincipaleManuale;
import gui.window.finestraterminale.concrete.FinestraTerminale;
import gui.window.pannello.concrete.PannelloConfigurazione;
import gui.window.pannello.concrete.PannelloScelte;

public class FinestraFactory implements FinestraFactoryIF {

	
	@Override public FinestraIF createFinestra(String tipologiaFinestra, String offset, 
			Modalita.Mod modalita, int numeroGiocatori, Difficolta difficolta) {
		
		if(tipologiaFinestra.equalsIgnoreCase("FinestraPrincipaleAstratta")) {
			
	        if(offset.equalsIgnoreCase("FinestraPrincipaleAutomatica"))
	        	return new FinestraPrincipaleAutomatica(modalita, numeroGiocatori, difficolta);
	        else if(offset.equalsIgnoreCase("FinestraPrincipaleManuale"))
	        	return (FinestraIF) new ManualeExecButtonDecorator(new FinestraPrincipaleManuale(modalita, numeroGiocatori, difficolta));
	        	//new FinestraPrincipaleManuale();
	        	// TODO il cast
		}else if(tipologiaFinestra.equalsIgnoreCase("PannelloAstratto")) {
			
	        if(offset.equalsIgnoreCase("PannelloConfigurazione"))
	        	return new PannelloConfigurazione();
	        else if(offset.equalsIgnoreCase("PannelloScelte"))
	        	return new PannelloScelte();
	        
		}else if(tipologiaFinestra.equalsIgnoreCase("FinestraErroreAstratta"))
				return new FinestraErrore();
		
		else if(tipologiaFinestra.equalsIgnoreCase("FinestraTerminaleAstratta"))
	        	return new FinestraTerminale();

		return new FinestraErrore();
	}
	

}
