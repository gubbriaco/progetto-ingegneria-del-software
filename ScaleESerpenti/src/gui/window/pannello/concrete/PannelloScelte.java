package gui.window.pannello.concrete;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.graphic.border.RoundedBorder;
import gui.window.FinestraIF;
import gui.window.pannello.PannelloAstratto;

@SuppressWarnings("serial")
public class PannelloScelte extends PannelloAstratto {
	
	private JLabel label;
	private JButton nuovo, riprendi;
	
	private FinestraFactoryIF pannelloConfigurazione = new FinestraFactory();
	
	
	public PannelloScelte() {
		titolo = "Pannello Scelte";
		this.setTitle(titolo);
	}
	
	

	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		this.add(pNORTH, BorderLayout.NORTH);
		
		font = new Font("Helvetica", Font.BOLD, 14);
		label = new JLabel("Vuoi iniziare una nuova sessione di gioco o ripristinare una precendente?");
		label.setFont(font);
		pNORTH.add(label, BorderLayout.CENTER);
	}

	@Override protected void inizializzaLayoutCENTER() {
		pCENTER = new JPanel();
		this.add(pCENTER, BorderLayout.CENTER);
		
		
		nuovo = new JButton("NUOVO");
		nuovo.setBorder(new RoundedBorder(4));
		pCENTER.add(nuovo, BorderLayout.WEST);
		
		riprendi = new JButton("RIPRENDI");
		riprendi.setBorder(new RoundedBorder(4));
		pCENTER.add(riprendi,BorderLayout.EAST);
		
		gestisciScelta();
	}

	
	private void gestisciScelta() {
		
		nuovo.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				disposeWindow();
				FinestraIF finestraConfigurazione = pannelloConfigurazione.createFinestra
						("PannelloAstratto", "PannelloConfigurazione", null, -1, null);
				finestraConfigurazione.inizializzaFinestra();
			}
		});
		
		riprendi.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	
	
	@Override protected void inizializzaLayoutSOUTH() {}
	@Override protected void inizializzaLayoutWEST() {}
	@Override protected void inizializzaLayoutEAST() {}

}
