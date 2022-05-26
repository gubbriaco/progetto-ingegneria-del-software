package gui.graphic.panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class LegendaPanel extends PanelAbstract {
	
	private final int dimensioneLegenda = 9;
	/** Standard, UnSoloDado, SostaPanchina, SostaLocanda, PremioDadi, 
	 *  PremioMolla, PescaUnaCarta, Scala, Serpente */
	
	private GridLayout gl;
	
	
	public LegendaPanel() {
		
		gl = new GridLayout(dimensioneLegenda-1,0);
		aggiungiLegende();
	
		this.setLayout(gl);
		this.add(new JScrollPane());
		
	}
	
	
	private void aggiungiLegende() {
		
		JLabel standard = new JLabel("Standard");
		standard.setOpaque(true);
		standard.setBackground(Color.WHITE);
		standard.setForeground(Color.BLACK.darker());
		this.add( standard);
		
		JLabel unSoloDado = new JLabel("Un Solo Dado");
		unSoloDado.setOpaque(true);
		unSoloDado.setBackground(Color.GRAY);
		unSoloDado.setForeground(Color.BLACK.darker());
		this.add( unSoloDado );
		
		JLabel sostaPanchina = new JLabel("Sosta Panchina");
		sostaPanchina.setOpaque(true);
		sostaPanchina.setBackground(Color.RED.brighter());
		sostaPanchina.setForeground(Color.BLACK.darker());
		this.add( sostaPanchina );
		
		JLabel sostaLocanda = new JLabel("Sosta Locanda");
		sostaLocanda.setOpaque(true);
		sostaLocanda.setBackground(Color.RED.darker());
		sostaLocanda.setForeground(Color.BLACK.darker());
		this.add( sostaLocanda );
		
		JLabel premioDadi = new JLabel("Premio Dadi");
		premioDadi.setOpaque(true);
		premioDadi.setBackground(Color.GREEN.darker());
		premioDadi.setForeground(Color.BLACK.darker());
		this.add( premioDadi );
	
		JLabel premioMolla = new JLabel("Premio Molla");
		premioMolla.setOpaque(true);
		premioMolla.setBackground(Color.GREEN.brighter());
		premioMolla.setForeground(Color.BLACK.darker());
		this.add( premioMolla );
		
		JLabel pescaUnaCarta = new JLabel("Pesca Una Carta");
		pescaUnaCarta.setOpaque(true);
		pescaUnaCarta.setBackground(Color.ORANGE);
		pescaUnaCarta.setForeground(Color.BLACK.darker());
		this.add(pescaUnaCarta);
		
		JLabel scala = new JLabel("Scala");
		scala.setOpaque(true);
		scala.setBackground(Color.BLUE.brighter());
		scala.setForeground(Color.BLACK.darker());
		this.add( scala );
		
		JLabel serpente = new JLabel("Serpente");
		serpente.setOpaque(true);
		serpente.setBackground(new Color(102,51,0));
		serpente.setForeground(Color.BLACK.darker());
		this.add( serpente );
		
	}

//	@Override public JComponent decorate() {
//		return this;
//	}
//


}
