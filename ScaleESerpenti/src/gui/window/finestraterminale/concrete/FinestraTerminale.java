package gui.window.finestraterminale.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import gui.window.finestraterminale.FinestraTerminaleAstratta;

@SuppressWarnings("serial")
public class FinestraTerminale extends FinestraTerminaleAstratta {

	private JLabel label;
	@SuppressWarnings("rawtypes")
	private JList elencoAttivita;
	
	private int numeroAttivitaSvolte;
	private String[] attivitaSvolte;
	
	public FinestraTerminale() {
		titolo = "Terminale";
		this.setTitle(titolo);
		
		numeroAttivitaSvolte = 1;
		attivitaSvolte = new String[numeroAttivitaSvolte];
		attivitaSvolte[0] = "--- NUOVA SESSIONE DI GIOCO ---";
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
		
		elencoAttivita = new JList(attivitaSvolte);
		pCENTER.add(elencoAttivita, BorderLayout.CENTER);
	}
	
	

	@Override protected void espandiAttivita(String attivita) {
		numeroAttivitaSvolte = numeroAttivitaSvolte + 1;
		attivitaSvolte = Arrays.copyOf(attivitaSvolte, numeroAttivitaSvolte);

		attivitaSvolte[numeroAttivitaSvolte] = attivita;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override protected void repaintTerminale() {
		/**elimino la vecchia lista di attivita' */
		pCENTER.remove(elencoAttivita);
		
		/**aggiungo la nuova lista di attivita'*/
		elencoAttivita = new JList(attivitaSvolte);
		repaint();
	}

	
	@Override protected void inizializzaLayoutSOUTH() {}
	@Override protected void inizializzaLayoutWEST() {}
	@Override protected void inizializzaLayoutEAST() {}

}
