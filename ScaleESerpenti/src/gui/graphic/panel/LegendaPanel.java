package gui.graphic.panel;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LegendaPanel extends JPanel {
	
	private JLabel titolo;
	private String[] elencoLegenda;
	private JList elenco;
	
	public LegendaPanel() {
		titolo = new JLabel("Legenda");
		this.add(titolo, BorderLayout.NORTH);
		// TODO dimensione elenco
		elencoLegenda = new String[1];
		aggiungiLegende();
		
		elenco = new JList(elencoLegenda);
		
		this.add(elenco,BorderLayout.CENTER);
	}
	
	
	private void aggiungiLegende() {
		// TODO aggiungi le legende all'elenco legenda
	}

}
