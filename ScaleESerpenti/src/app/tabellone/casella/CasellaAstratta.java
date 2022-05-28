package app.tabellone.casella;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import app.esecuzione.giocatore.Giocatore;
import gui.graphic.border.RoundedBorder;

@SuppressWarnings("serial")
public abstract class CasellaAstratta extends JPanel {
	
	
	private int numeroCasella;
	
	
	@SuppressWarnings("rawtypes")
	public JList elencoGiocatori;
	
	private LinkedList<String> giocatori;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CasellaAstratta(int numeroCasella) {
		//super(""+numeroCasella,SwingConstants.CENTER);
		this.add(new JLabel(""+numeroCasella), BorderLayout.CENTER);
		this.numeroCasella = numeroCasella;
		this.setBorder(new RoundedBorder(5));
		
		giocatori = new LinkedList<>();
		elencoGiocatori = new JList(giocatori.toArray());
		
	}
	
	/**
	 * Aggiunge il giocatore che ha raggiunto con una determinata combinazione 
	 * di dadi la casella in questione.
	 * @param giocatore Giocatore che ha raggiunto la casella in questione.
	 */
	public void aggiungiGiocatore(Giocatore giocatore) {
		giocatori.add(giocatore.toString());
	}
	
	/**
	 * Rimuove il giocatore che si e' spostato verso una nuova casella tramite 
	 * una nuova combinazione di dadi.
	 * @param giocatore Giocatore che si e' spostato verso una nuova casella.
	 */
	public void rimuoviGiocatore(Giocatore giocatore) {
		giocatori.remove(giocatore);
	}
	
	/**
	 * Re-inizializza la grafica della casella in questione a seguito dell'arrivo
	 * di un giocatore in essa o a seguito dell'abbandono di un giocatore (presente 
	 * in tale casella) dopo aver ottenuto una combinazione di dati che l'ha 
	 * portato a spostarsi verso una nuova casella.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void repaintCasella() {
		this.remove(elencoGiocatori);
		elencoGiocatori = new JList(giocatori.toArray());
		elencoGiocatori.setBackground(this.getBackground());
		elencoGiocatori.setForeground(Color.BLACK);
		this.add(elencoGiocatori, BorderLayout.SOUTH);
		this.repaint();
	}
	
	/**
	 * Restituisce il numero di casella della Casella in questione.
	 * @return Numero della casella
	 */
	public int getNumeroCasella() {
		return numeroCasella;
	}
	
	
	/**
	 * Rappresenta graficamente la casella in questione.
	 */
	public abstract void draw();

	
	
	
	
	
	

}
