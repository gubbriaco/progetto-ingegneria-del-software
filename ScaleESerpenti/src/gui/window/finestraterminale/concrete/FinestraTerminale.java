package gui.window.finestraterminale.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.window.finestraterminale.FinestraTerminaleAstratta;

@SuppressWarnings("serial")
public class FinestraTerminale extends FinestraTerminaleAstratta {

	private JLabel label;
	@SuppressWarnings("rawtypes")
	private JList elencoAttivita;
	private LinkedList<String> attivitaSvolte;
	private JScrollPane sp;
	
	public FinestraTerminale() {
		titolo = "Terminale";
		this.setTitle(titolo);
		
		attivitaSvolte = new LinkedList<>();
		attivitaSvolte.add( "--- NUOVA SESSIONE DI GIOCO ---" );
		attivitaSvolte.add( "--- Turno 1 ---" );
	}
	
	
	/**
	 * Il metodo viene ridefinito cosi' da aggiungere l'operazione 
	 * {@link java.awt.Frame#setResizable(boolean)} e da non permettere 
	 * all'utente di ridimensionare la finestra essendo comunque una finestra, 
	 * in entrambi i casi {@link PannelloConfigurazione} e 
	 * {@link PannelloScelte}, di configurazione per la sessione di gioco 
	 * inizializzata.
	 */
	@Override protected void visualizzaFinestra() {
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		pNORTH.setBackground(Color.GREEN.darker());
		this.add(pNORTH, BorderLayout.NORTH);
		
		label = new JLabel("Attività svolte durante la sessione di gioco corrente:");
		label.setForeground(Color.WHITE);
		pNORTH.add(label, BorderLayout.CENTER);
		
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override protected void inizializzaLayoutCENTER() {
		pCENTER = new JPanel();
		this.add(pCENTER, BorderLayout.CENTER);
		
		elencoAttivita = new JList(attivitaSvolte.toArray());
		//pCENTER.add(elencoAttivita, BorderLayout.CENTER);
		sp = new JScrollPane(elencoAttivita);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pCENTER.add(sp);
	}
	
	

	@Override public void espandiAttivita(String attivita) {
		attivitaSvolte.add(attivita);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override public void repaintTerminale() {
		/**elimino la vecchia lista di attivita' */
		pCENTER.remove(sp);
		
		/**aggiungo la nuova lista di attivita'*/
		elencoAttivita = new JList(attivitaSvolte.toArray());
		sp = new JScrollPane(elencoAttivita);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pCENTER.add(sp, BorderLayout.CENTER);
		repaint();
		this.pack();
	}

	
	@Override protected void inizializzaLayoutSOUTH() {}
	@Override protected void inizializzaLayoutWEST() {}
	@Override protected void inizializzaLayoutEAST() {}

}
