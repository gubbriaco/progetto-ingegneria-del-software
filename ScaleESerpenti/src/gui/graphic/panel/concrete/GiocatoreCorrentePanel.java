package gui.graphic.panel.concrete;

import java.awt.Color;

import javax.swing.JLabel;

import gui.graphic.border.RoundedBorder;
import gui.graphic.panel.PanelAbstract;

@SuppressWarnings("serial")
public class GiocatoreCorrentePanel extends PanelAbstract {
	
	
	private final static int raggio = 5;
	
	@SuppressWarnings("unused")
	private String giocatoreCorrente;
	private JLabel giocatoreCorrenteLabel;
	
	public GiocatoreCorrentePanel(String giocatoreCorrente) {
		
		this.giocatoreCorrente = giocatoreCorrente;
		
		giocatoreCorrenteLabel = new JLabel(giocatoreCorrente);
		giocatoreCorrenteLabel.setOpaque(true);
		giocatoreCorrenteLabel.setBackground(Color.GRAY.darker());
		giocatoreCorrenteLabel.setForeground(Color.BLACK);
		giocatoreCorrenteLabel.setBorder(new RoundedBorder(raggio));
		
		this.add(giocatoreCorrenteLabel);
		
	}
	

}
