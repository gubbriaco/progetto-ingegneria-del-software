package app.tabellone.casella;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.esecuzione.giocatore.Giocatore;
import app.tabellone.casella.concrete.TipologiaCasella;
import gui.graphic.border.RoundedBorder;

@SuppressWarnings("serial")
public abstract class CasellaAstratta extends JPanel {
	
	private int numeroCasella;
	
	public TipologiaCasella tipologiaCasella;
	
	
	@SuppressWarnings("rawtypes")
	public JComboBox elencoGiocatori;
	
	private LinkedList<String> giocatori;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CasellaAstratta(int numeroCasella) {
		//super(""+numeroCasella,SwingConstants.CENTER);
		this.add(new JLabel(""+numeroCasella), BorderLayout.CENTER);
		this.numeroCasella = numeroCasella;
		this.setBorder(new RoundedBorder(5));
		
		giocatori = new LinkedList<>();
		elencoGiocatori = new JComboBox(giocatori.toArray());
		elencoGiocatori.setBackground(Color.BLACK);
		elencoGiocatori.setForeground(Color.BLACK);
		elencoGiocatori.setOpaque(true);
	}
	
	/**
	 * Restituisce i giocatori presenti nella casella in questione.
	 * @return Giocatori presenti sulla casella
	 */
	public LinkedList<String> getGiocatori() {
		return giocatori;
	}
	
	@SuppressWarnings("rawtypes")
	public JComboBox getElencoGiocatori() {
		return elencoGiocatori;
	}
	
	/**
	 * Aggiunge il giocatore che ha raggiunto con una determinata combinazione 
	 * di dadi la casella in questione.
	 * @param giocatore Giocatore che ha raggiunto la casella in questione.
	 */
	public void aggiungiGiocatore(Giocatore giocatore) {
		for(int i=0;i<giocatori.size();++i)
			if(giocatori.get(i).equalsIgnoreCase(giocatore.toString()))
				return;
		giocatori.add(giocatore.toString());
	}
	
	/**
	 * Rimuove il giocatore che si e' spostato verso una nuova casella tramite 
	 * una nuova combinazione di dadi.
	 * @param giocatore Giocatore che si e' spostato verso una nuova casella.
	 */
	public void rimuoviGiocatore(Giocatore giocatore) {
		for(int i=0;i<giocatori.size();++i)
			if(giocatori.get(i).equalsIgnoreCase(giocatore.toString()))
				giocatori.remove(i);
		//giocatori.remove(giocatore);
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
		elencoGiocatori = new JComboBox(giocatori.toArray());
		elencoGiocatori.setBackground(this.getBackground());
		elencoGiocatori.setForeground(Color.BLACK);
		if(giocatori.size()!=0)
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

//	
//	protected int xArrivo, yArrivo;
//	
//	public void setArrivo(int x, int y) {
//		xArrivo = x;
//		yArrivo = y;
//	}
//	
//	public int[] getCasellaArrivo() {
//		int[] posizioni = {xArrivo, yArrivo};
//		return posizioni;
//	}
//	
	
	@Override public String toString() {
		return "";
	}
	

}
