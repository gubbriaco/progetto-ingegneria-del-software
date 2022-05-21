package app.modalita;

public abstract class Modalita{
	
	public enum Mod {AUTOMATICA, MANUALE;}
	
	//UTILIZZO IL TEMPLATE METHOD 
	// GUARDA FinestraPrincipale#esegui
	
	public void templateModalita() {
		automatica();
		manuale();	
	}
	
	protected abstract void automatica();
	
	protected abstract void manuale();
	
	
}
