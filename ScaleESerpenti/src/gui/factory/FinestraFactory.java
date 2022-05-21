package gui.factory;

import gui.window.FinestraIF;

public class FinestraFactory implements FinestraFactoryIF {

	
	@Override public FinestraIF createFinestra(String tipologiaFinestra, String offset) {
		
		if(tipologiaFinestra.equalsIgnoreCase("FinestraPrincipaleAstratta")) {
			
	        if(offset.equalsIgnoreCase("FinestraPrincipaleAutomatica"))
	        	new FinestraPrincipaleAutomatica();
	        else
	        	new FinestraPrincipaleManuale();
	        
		}else if(tipologiaFinestra.equalsIgnoreCase("PannelloAstratto")) {
			
	        if(offset.equalsIgnoreCase("PannelloConfigurazione"))
	        	new PannelloConfigurazione();
	        else
	        	new PannelloScelte();
	        
		}else if(tipologiaFinestra.equalsIgnoreCase("FinestraErroreAstratta")
				return new FinestraErrore();
		
		else if(tipologiaFinestra.equalsIgnoreCase("FinestraTerminaleAstratta")
	        	return new Terminale();
		
	}
	

}
