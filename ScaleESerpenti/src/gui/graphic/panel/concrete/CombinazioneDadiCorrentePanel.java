package gui.graphic.panel.concrete;

import java.awt.Color;

import javax.swing.JLabel;

import gui.graphic.border.RoundedBorder;
import gui.graphic.panel.PanelAbstract;

@SuppressWarnings("serial")
public class CombinazioneDadiCorrentePanel extends PanelAbstract  {


	private final static int raggio = 5;
	
	@SuppressWarnings("unused")
	private String combinazioneDadiCorrente;
	
	private JLabel combinazioneDadiCorrenteLabel;
	
	
	public CombinazioneDadiCorrentePanel(String combinazioneDadiCorrente) {
		this.combinazioneDadiCorrente = combinazioneDadiCorrente;
	
		combinazioneDadiCorrenteLabel = new JLabel(combinazioneDadiCorrente);
		combinazioneDadiCorrenteLabel.setOpaque(true);
		combinazioneDadiCorrenteLabel.setBackground(Color.GRAY.darker());
		combinazioneDadiCorrenteLabel.setForeground(Color.BLACK);
		combinazioneDadiCorrenteLabel.setBorder(new RoundedBorder(raggio));
		
		this.add(combinazioneDadiCorrenteLabel);
	}
	
}
