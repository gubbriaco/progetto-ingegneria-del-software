package gui.window.finestravittoria;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JList;

import gui.graphic.border.RoundedBorder;
import gui.window.FinestraAstratta;

@SuppressWarnings("serial")
public abstract class FinestraVittoriaAstratta extends FinestraAstratta {
	
	@SuppressWarnings("unused")
	private LinkedList<String> vincitori;

	private JList<String> giocatoriVincitori;
	
	/**
	 * Imposta i giocatori vincitori della sessione di gioco in questione.
	 * @param vincitori Vincitori della sessione di gioco
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setGiocatoriVincenti(LinkedList<String> vincitori, FinestraVittoriaAstratta finestraVincitori ) {
	
		finestraVincitori.pCENTER = null;
		
		finestraVincitori.inizializzaLayoutCENTER();
		
		this.vincitori = vincitori;
		
		giocatoriVincitori= new JList(vincitori.toArray());
		giocatoriVincitori.setBorder(new RoundedBorder(10));
		
		pCENTER.add(giocatoriVincitori, BorderLayout.CENTER);
		
		this.revalidate();
		this.repaint();
		this.pack();
	}
	
	
	/**
	 * Il metodo viene ridefinito cosi' da aggiungere l'operazione 
	 * {@link java.awt.Frame#setResizable(boolean)} e da non permettere 
	 * all'utente di ridimensionare la finestra.
	 */
	@Override protected void visualizzaFinestra() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

}
