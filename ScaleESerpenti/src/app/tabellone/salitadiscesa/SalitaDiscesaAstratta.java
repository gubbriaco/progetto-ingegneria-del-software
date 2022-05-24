package app.tabellone.salitadiscesa;

public abstract class SalitaDiscesaAstratta {

	private int casellaPartenza, casellaArrivo;
	
	public SalitaDiscesaAstratta(int casellaPartenza, int casellaArrivo) {
		this.casellaPartenza = casellaPartenza;
		this.casellaArrivo = casellaArrivo;
	}
	
	public int getCasellaPartenza() {
		return casellaPartenza;
	}
	
	public int getCasellaArrivo() {
		return casellaArrivo;
	}
	
	
}
