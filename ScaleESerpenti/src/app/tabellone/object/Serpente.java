package app.tabellone.object;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public class Serpente extends OggettoTrasferimento {

	private JLabel testaSerpenteLabel, codaSerpenteLabel;
	
	public Serpente(CasellaAstratta testaSerpente, CasellaAstratta codaSerpente) {
		super(testaSerpente, codaSerpente);
		
		testaSerpenteLabel = new JLabel("TESTA: " + testa.getNumeroCasella());
		
		testaSerpenteLabel.setForeground(Color.WHITE);
		testaSerpenteLabel.setBackground(new Color(102,51,0));
		testaSerpenteLabel.setOpaque(true);
		codaSerpenteLabel = new JLabel("CODA: " + coda.getNumeroCasella());
		
		codaSerpenteLabel.setForeground(Color.WHITE);
		codaSerpenteLabel.setBackground(new Color(102,51,0));
		codaSerpenteLabel.setOpaque(true);
		
		if(testaSerpente.getNumeroCasella()>codaSerpente.getNumeroCasella()) {
			testa.add(codaSerpenteLabel, BorderLayout.NORTH);
			coda.add(testaSerpenteLabel,BorderLayout.NORTH);
		}
		else {
			testa.add(testaSerpenteLabel, BorderLayout.NORTH);
			coda.add(codaSerpenteLabel,BorderLayout.NORTH);
		}
		
		
	}

	
	
}
