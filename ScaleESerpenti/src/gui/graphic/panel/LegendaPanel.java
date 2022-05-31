package gui.graphic.panel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.graphic.border.RoundedBorder;
@SuppressWarnings("serial")
public class LegendaPanel extends JPanel {
	
	
	private final static int raggio = 5;
	
	private JLabel standard, unSoloDado, sostaPanchina, sostaLocanda, premioDadi,
			       premioMolla, pescaUnaCarta, scala, serpente;
	
	
	public LegendaPanel() {
		
		aggiungiLegende();
		
	}
	
	
	private void aggiungiLegende() {
		
		standard = new JLabel("Standard");
		standard.setOpaque(true);
		standard.setBackground(Color.WHITE);
		standard.setForeground(Color.BLACK.darker());
		standard.setBorder(new RoundedBorder(raggio));
		this.add( standard);
		
		unSoloDado = new JLabel("Un Solo Dado");
		unSoloDado.setOpaque(true);
		unSoloDado.setBackground(Color.GRAY);
		unSoloDado.setForeground(Color.BLACK.darker());
		unSoloDado.setBorder(new RoundedBorder(raggio));
		this.add( unSoloDado );
		
		sostaPanchina = new JLabel("Sosta Panchina");
		sostaPanchina.setOpaque(true);
		sostaPanchina.setBackground(Color.RED.brighter());
		sostaPanchina.setForeground(Color.BLACK.darker());
		sostaPanchina.setBorder(new RoundedBorder(raggio));
		this.add( sostaPanchina );
		
		sostaLocanda = new JLabel("Sosta Locanda");
		sostaLocanda.setOpaque(true);
		sostaLocanda.setBackground(Color.RED.darker());
		sostaLocanda.setForeground(Color.BLACK.darker());
		sostaLocanda.setBorder(new RoundedBorder(raggio));
		this.add( sostaLocanda );
		
		premioDadi = new JLabel("Premio Dadi");
		premioDadi.setOpaque(true);
		premioDadi.setBackground(Color.GREEN.darker());
		premioDadi.setForeground(Color.BLACK.darker());
		premioDadi.setBorder(new RoundedBorder(5));
		this.add( premioDadi );
	
		premioMolla = new JLabel("Premio Molla");
		premioMolla.setOpaque(true);
		premioMolla.setBackground(Color.GREEN.brighter());
		premioMolla.setForeground(Color.BLACK.darker());
		premioMolla.setBorder(new RoundedBorder(raggio));
		this.add( premioMolla );
		
		pescaUnaCarta = new JLabel("Pesca Una Carta");
		pescaUnaCarta.setOpaque(true);
		pescaUnaCarta.setBackground(Color.ORANGE);
		pescaUnaCarta.setForeground(Color.BLACK.darker());
		pescaUnaCarta.setBorder(new RoundedBorder(raggio));
		this.add(pescaUnaCarta);
		
		scala = new JLabel("Scala");
		scala.setOpaque(true);
		scala.setBackground(Color.BLUE.brighter());
		scala.setForeground(Color.BLACK.darker());
		scala.setBorder(new RoundedBorder(raggio));
		this.add( scala );
		
		serpente = new JLabel("Serpente");
		serpente.setOpaque(true);
		serpente.setBackground(new Color(102,51,0));
		serpente.setForeground(Color.BLACK.darker());
		serpente.setBorder(new RoundedBorder(raggio));
		this.add( serpente );
		
	}


}
