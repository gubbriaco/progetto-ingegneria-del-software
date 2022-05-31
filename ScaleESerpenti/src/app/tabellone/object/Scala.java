package app.tabellone.object;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

import app.tabellone.casella.CasellaAstratta;

@SuppressWarnings("serial")
public class Scala extends OggettoTrasferimento {
	
	private JLabel testaScalaLabel, codaScalaLabel;
	
	public Scala(CasellaAstratta testaScala, CasellaAstratta codaScala) {
		super(testaScala, codaScala);
		
		testaScalaLabel = new JLabel("TESTA: " + testa.getNumeroCasella());
		testaScalaLabel.setOpaque(true);
		testaScalaLabel.setForeground(Color.WHITE);
		testaScalaLabel.setBackground(Color.BLUE.brighter());
		codaScalaLabel = new JLabel("CODA: " + coda.getNumeroCasella());
		codaScalaLabel.setOpaque(true);
		codaScalaLabel.setForeground(Color.WHITE);
		codaScalaLabel.setBackground(Color.BLUE.brighter());
		
		if(testaScala.getNumeroCasella()>codaScala.getNumeroCasella()) {
			testa.add(codaScalaLabel, BorderLayout.NORTH);
			coda.add(testaScalaLabel,BorderLayout.NORTH);
		}
		else {
			testa.add(testaScalaLabel, BorderLayout.NORTH);
			coda.add(codaScalaLabel,BorderLayout.NORTH);
		}
		
		//System.out.println(testaScala.getNumeroCasella() + "," + codaScala.getNumeroCasella());	
	}

	
}
