package gui.window.finestraerrore.concrete;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.graphic.border.RoundedBorder;
import gui.window.finestraerrore.FinestraErroreAstratta;

@SuppressWarnings("serial")
public class FinestraErrore extends FinestraErroreAstratta {

	private JLabel label;
	private JButton ok;
	
	public FinestraErrore() {
		titolo = "ERRORE";
		this.setTitle(titolo);
	}

	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		this.add(pNORTH, BorderLayout.NORTH);
		
		label = new JLabel("ATTENZIONE! Qualcosa è andato storto!");
		pNORTH.add(label, BorderLayout.CENTER);
	}

	@Override protected void inizializzaLayoutCENTER() {
		pCENTER = new JPanel();
		this.add(pCENTER, BorderLayout.CENTER);
		
		ok = new JButton("OK");
		ok.setBorder(new RoundedBorder(4));
		pCENTER.add(ok, BorderLayout.CENTER);
		
		gestisci();
	}
	
	private void gestisci() {
		ok.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				disposeWindow();
			}
		});
	}

	
	@Override protected void inizializzaLayoutSOUTH() {}
	@Override protected void inizializzaLayoutWEST() {}
	@Override protected void inizializzaLayoutEAST() {}

}
