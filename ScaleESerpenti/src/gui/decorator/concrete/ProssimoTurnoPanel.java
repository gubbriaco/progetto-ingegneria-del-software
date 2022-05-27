package gui.decorator.concrete;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.modalita.Modalita;
import gui.decorator.PanelDecorator;
import gui.graphic.border.RoundedBorder;

@SuppressWarnings("serial")
public class ProssimoTurnoPanel extends PanelDecorator {

	private String turnoCorrente;
	private JLabel turno;
	private JButton prossimoTurno;
	private Modalita.Mod modalita;
	
	private final static int raggio = 5;
	
	public ProssimoTurnoPanel(JPanel toDecorate, String turnoCorrente, Modalita.Mod modalita) {
		super(toDecorate);
		this.turnoCorrente = turnoCorrente;
		this.modalita = modalita;
	}

	@Override public JPanel decorate() {
		
		turno = new JLabel(turnoCorrente);
		turno.setOpaque(true);
		turno.setBackground(Color.LIGHT_GRAY);
		turno.setForeground(Color.BLACK);
		turno.setBorder(new RoundedBorder(raggio));
		
		toDecorate.add(turno);
		
		
		if(modalita == Modalita.Mod.MANUALE) {
			
			prossimoTurno = new JButton("Prossimo turno");
			prossimoTurno.setOpaque(true);
			prossimoTurno.setBackground(Color.GRAY.brighter());
			prossimoTurno.setForeground(Color.BLACK);
			prossimoTurno.setBorder(new RoundedBorder(raggio));
			
			toDecorate.add(prossimoTurno);

		}
		
		return toDecorate;
	}
	
	

}
