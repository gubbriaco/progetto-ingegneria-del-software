package gui.window;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.decorator.FinestraServiceIF;

@SuppressWarnings("serial")
public abstract class FinestraAstratta extends JFrame implements FinestraIF,FinestraServiceIF {
	

	protected JPanel pNORTH, pSOUTH, pWEST, pEAST;
	public static JPanel pCENTER;
	protected Font font;
	
	protected String titolo;
	
	protected int dimX, dimY;

	
	
	@Override public void inizializzaFinestra() {
		
		defaultExitOperation();
		
		/**template method*/
		templateInizializzaLayout();
		
		visualizzaFinestra();
		
	}
	
	
	/**
	 * Permette la visualizzazione della Finestra tramite i seguenti metodi: <p>
	 *  - {@link JFrame#pack} che gli permette di venga ridimensionata per 
	 *    adattarsi alle dimensioni e ai layout preferiti dei suoi 
	 *    sottocomponenti. <p> 
	 *  - {@link JFrame#setLocationRelativeTo(java.awt.Component)} che gli 
	 *    permette di impostare la posizione della Finestra relativa al 
	 *    componente specificato. In questo caso essendo che il componente 
	 *    passato come parametro e' null, la Finestra verra' posizionata 
	 *    centrale allo schermo. <p>
	 *  - {@link JFrame#setVisible(boolean)} che gli permette di mostrare o 
	 *    nascondere la Finestra a seconda del valore del parametro booleano che
	 *    gli passiamo in input.
	 */
	protected void visualizzaFinestra() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	/** TEMPLATE METHOD
	* Tale metodo prevede l'inizializzazione del layout generale tramite 
	* chiamate a metodi piu' elementari che rispettivamente inizializzeranno i
	* {@link JPanel} corrispondenti: <p>
	* - {@link FinestraAstratta#inizializzaLayoutNORTH} che gli permette di 
	* 	inizializzare il {@link FinestraAstratta#pNORTH}<p>
	* - {@link FinestraAstratta#inizializzaLayoutCENTER} che gli permette di 
	* 	inizializzare il {@link FinestraAstratta#pCENTER}<p>
	* - {@link FinestraAstratta#inizializzaLayoutSOUTH} che gli permette di 
	* 	inizializzare il {@link FinestraAstratta#pSOUTH}<p>
	* - {@link FinestraAstratta#inizializzaLayoutWEST} che gli permette di 
	* 	inizializzare il {@link FinestraAstratta#pWEST}<p>
	* - {@link FinestraAstratta#inizializzaLayoutEAST} che gli permette di 
	* 	inizializzare il {@link FinestraAstratta#pEAST}<p>
	* 
	 */
	protected void templateInizializzaLayout() {
		inizializzaLayoutNORTH();
		inizializzaLayoutCENTER();
		inizializzaLayoutSOUTH();
		inizializzaLayoutWEST();
		inizializzaLayoutEAST();
	}
	
	// INIZIALIZZO TALI METODI CON BODY MANCENTE TALE CHE SARANNO INIZIALIZZATI 
	// TUTTI I LAYOUT PERCHE' POI E' LA CLASSE CONCRETA CHE SE HA BISOGNO DI UN
	// LAYOUT SPECIFICO DECIDERA' DI IMPLEMENTARLO COME DICE LEI
	
	/*** Permette di inizializzare il {@link FinestraAstratta#pNORTH} */
	protected abstract void inizializzaLayoutNORTH();
	/*** Permette di inizializzare il {@link FinestraAstratta#pCENTER} */
	protected abstract void inizializzaLayoutCENTER();
	/*** Permette di inizializzare il {@link FinestraAstratta#pSOUTH} */
	protected abstract void inizializzaLayoutSOUTH();
	/*** Permette di inizializzare il {@link FinestraAstratta#pWEST} */
	protected abstract void inizializzaLayoutWEST();
	/*** Permette di inizializzare il {@link FinestraAstratta#pEAST} */
	protected abstract void inizializzaLayoutEAST();
	

	@Override public void decorate() {
		//TODO
	}
	
	
	/**
	 * Permette di chiudere la finestra in questione a livello software.
	 */
	protected void disposeWindow() {
		this.dispose();
	}
	
	/**
	 * Permette di chiudere la finestra a livello grafico catturando il 
	 * {@link WindowEvent} corrispondente ed verificando se l'utente vuole 
	 * effettivamente chiudere tale finestra tramite un {@link JOptionPane}
	 */
	protected void defaultExitOperation() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				if( consensoUscita() )
					System.exit(0);
			}
		});
	}

	/**
	 * Permette, tramite una finestra {@link JOptionPane}, di scegliere 
	 * terminare la sessione di gioco corrente.
	 * @return consenso per uscire
	 */
	protected boolean consensoUscita() {
		int option = JOptionPane.showConfirmDialog(null,
				"Sei sicuro di voler uscire?", 
				"EXIT", 
				JOptionPane.YES_NO_OPTION);
		return option == JOptionPane.YES_OPTION;
	}
	
	
	

	
}
