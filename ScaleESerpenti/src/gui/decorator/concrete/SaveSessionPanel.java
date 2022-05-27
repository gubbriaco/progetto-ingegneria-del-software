package gui.decorator.concrete;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.decorator.PanelDecorator;
import gui.graphic.border.RoundedBorder;

@SuppressWarnings("serial")
public class SaveSessionPanel extends PanelDecorator {
	
	private JButton salva;
	private JLabel titoloSalva;
	
	private final static int raggio = 5;
	
	public SaveSessionPanel(JPanel toDecorate) {
		super(toDecorate);
	}

	@Override public JPanel decorate() {
		
		titoloSalva = new JLabel("Salva la sessione di gioco");
		titoloSalva.setOpaque(true);
		titoloSalva.setBackground(Color.LIGHT_GRAY);
		titoloSalva.setForeground(Color.BLACK);
		titoloSalva.setBorder(new RoundedBorder(raggio));
		
		salva = new JButton("SALVA");
		salva.setOpaque(true);
		salva.setBackground(Color.YELLOW.darker().darker());
		salva.setForeground(Color.BLACK);
		salva.setBorder(new RoundedBorder(raggio));
		
		toDecorate.add(titoloSalva);
		toDecorate.add(salva);
		
		return toDecorate;
	}

}
