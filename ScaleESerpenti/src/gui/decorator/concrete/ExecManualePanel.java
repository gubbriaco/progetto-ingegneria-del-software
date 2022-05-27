package gui.decorator.concrete;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.modalita.Modalita;
import gui.decorator.PanelDecorator;
import gui.graphic.border.RoundedBorder;

@SuppressWarnings("serial")
public class ExecManualePanel extends PanelDecorator {
	
	private JButton pulsanteModalita;
	@SuppressWarnings("unused")
	private Modalita.Mod modalita;
	private String tipologiaModalita;
	private JLabel titoloModalita;
	
	private final static int raggio = 5;
	
	public ExecManualePanel(JPanel toDecorate) {
		super(toDecorate);
	}
	
	public ExecManualePanel(JPanel pNORTH, Modalita.Mod modalita) {
		super(pNORTH);
		
		this.modalita = modalita;
		
		if( Modalita.Mod.MANUALE == modalita )
			tipologiaModalita = "Manuale";
		else
			tipologiaModalita = "Automatica";
		
	}
	
	
	@Override public JPanel decorate() {
		
		titoloModalita = new JLabel("Cambia modalità di gioco");
		titoloModalita.setOpaque(true);
		titoloModalita.setBackground(Color.LIGHT_GRAY);
		titoloModalita.setForeground(Color.BLACK);
		titoloModalita.setBorder(new RoundedBorder(raggio));

		pulsanteModalita = new JButton(tipologiaModalita);
		pulsanteModalita.setOpaque(true);
		pulsanteModalita.setBackground(Color.ORANGE.darker());
		pulsanteModalita.setForeground(Color.BLACK);
		pulsanteModalita.setBorder(new RoundedBorder(raggio));
		
		toDecorate.add(titoloModalita);
		toDecorate.add(pulsanteModalita);
		
		return toDecorate;
	
	}
	

}
