package gui.window.pannello.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import app.difficolta.Difficolta;
import app.modalita.Modalita;
import app.modalita.Modalita.Mod;
import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.graphic.border.RoundedBorder;
import gui.window.FinestraIF;
import gui.window.pannello.PannelloAstratto;

@SuppressWarnings("serial")
public class PannelloConfigurazione extends PannelloAstratto {
	
	private JLabel label, titoloModalita, titoloDifficolta, titoloTextField,
				   titoloNrRighe, titoloNrColonne;
	private JButton ok;
	private JRadioButton automatica, manuale, facile, media, difficile;
	private JTextField numeroGiocatori, nrRighe, nrColonne;
	private JPanel pSOUTH1, pSOUTH2;
	
	private FinestraFactoryIF errore = new FinestraFactory(),
							  fPrincipale = new FinestraFactory();
	private Modalita.Mod modalita;
	private int numGiocatori;
	//private Difficolta difficolta;
	
	protected int[] dimensioniTabellone;
 	

	public PannelloConfigurazione() {
		titolo = "Pannello Configurazione";
		this.setTitle(titolo);
	}

	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		this.add(pNORTH, BorderLayout.NORTH);
		
		font = new Font("Helvetica", Font.BOLD, 14);
		label = new JLabel("Impostare la configurazione di gioco desiderata");
		label.setForeground(Color.BLACK.darker());
		label.setFont(font);
		pNORTH.add(label, BorderLayout.CENTER);
		ok = new JButton("OK");
		ok.setBorder(new RoundedBorder(5));
		ok.setForeground(Color.BLACK.darker());
		ok.setBackground(Color.GRAY.brighter());
		pNORTH.add(ok);
		
		/**poiche' se si salva il {@link JButton} {@link PannelloConfigurazione#ok}
		 * significa che la configurazione della nuova sessione di gioco e' stata
		 * completata*/
		gestisciConfigurazioneCompletata();
	}

	@Override protected void inizializzaLayoutCENTER() {
		pCENTER = new JPanel();
		this.add(pCENTER, BorderLayout.CENTER); pCENTER.setBackground(Color.GRAY.brighter());
		
		pCENTER.setBackground(Color.GRAY); pCENTER.setBorder(new RoundedBorder(1));
		titoloModalita = new JLabel("Modalità:"); titoloModalita.setForeground(Color.BLACK);
		pCENTER.add(titoloModalita);
		automatica = new JRadioButton("Automatica"); 
		automatica.setForeground(Color.BLACK.brighter()); automatica.setBackground(Color.GRAY);
		pCENTER.add(automatica,BorderLayout.WEST);
		manuale = new JRadioButton("Manuale"); 
		manuale.setForeground(Color.BLACK.brighter()); manuale.setBackground(Color.GRAY);
		pCENTER.add(manuale,BorderLayout.EAST);
		
		gestisciRadioButtonModalita();
	}

	@Override protected void inizializzaLayoutSOUTH() {
		pSOUTH = new JPanel();
		this.add(pSOUTH,BorderLayout.SOUTH); pSOUTH.setBackground(Color.GRAY.brighter());
		
		pSOUTH1 = new JPanel(); 
		pSOUTH.add(pSOUTH1, BorderLayout.WEST);
		pSOUTH1.setBorder(new RoundedBorder(5));
		pSOUTH1.setBackground(Color.GRAY);
		titoloTextField = new JLabel("Nr. giocatori:");
		pSOUTH1.add(titoloTextField, BorderLayout.WEST);
		titoloTextField.setForeground(Color.BLACK);titoloTextField.setBackground(Color.GRAY);
		numeroGiocatori = new JTextField();
		numeroGiocatori.setColumns(2);
		numeroGiocatori.setToolTipText("Nr. giocatori");
		pSOUTH1.add(numeroGiocatori,BorderLayout.EAST);
		
		
		pSOUTH2 = new JPanel();
		pSOUTH2.setBorder(new RoundedBorder(5));
		pSOUTH2.setBackground(Color.GRAY.brighter());
		pSOUTH.add(pSOUTH2, BorderLayout.EAST);
		
		titoloNrRighe = new JLabel("Nr. righe");
		titoloNrRighe.setForeground(Color.BLACK);
		pSOUTH2.add(titoloNrRighe);
		nrRighe = new JTextField();
		nrRighe.setColumns(2);
		nrRighe.setToolTipText("Numero Righe");
		pSOUTH2.add(nrRighe);
		
		titoloNrColonne = new JLabel("Nr. colonne");
		titoloNrColonne.setForeground(Color.BLACK);
		pSOUTH2.add(titoloNrColonne);
		nrColonne = new JTextField();
		nrColonne.setColumns(2);
		nrColonne.setToolTipText("Numero Colonne");
		pSOUTH2.add(nrColonne);
		
		dimensioniTabellone = new int[2];
		
		
		
		
		
		
		
		
//		pSOUTH2.setBorder(new RoundedBorder(3));
//		pSOUTH2.setBackground(Color.GRAY);
//		titoloDifficolta = new JLabel("Difficoltà:"); titoloDifficolta.setForeground(Color.BLACK);
//		pSOUTH2.add(titoloDifficolta,BorderLayout.NORTH);
//		facile = new JRadioButton("Facile"); 
//		facile.setForeground(Color.GREEN); facile.setBackground(Color.GRAY);
//		pSOUTH2.add(facile,BorderLayout.SOUTH);
//		media = new JRadioButton("Media"); 
//		media.setForeground(Color.ORANGE); media.setBackground(Color.GRAY);
//		pSOUTH2.add(media,BorderLayout.SOUTH);
//		difficile = new JRadioButton("Difficile");
//		difficile.setForeground(Color.RED); difficile.setBackground(Color.GRAY);
//		pSOUTH2.add(difficile, BorderLayout.SOUTH);
		
		
	//	gestisciRadioButtonDifficolta();
	}
	
	
	

	
//	private void gestisciRadioButtonDifficolta() {
//		facile.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				media.setSelected(false);
//				difficile.setSelected(false);
//			}
//		});
//		media.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				facile.setSelected(false);
//				difficile.setSelected(false);
//			}
//		});
//		difficile.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				facile.setSelected(false);
//				media.setSelected(false);
//			}
//		});
//	}
	
	private void gestisciRadioButtonModalita() {
		automatica.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				manuale.setSelected(false);
			}
		});
		manuale.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				automatica.setSelected(false);
			}
		});
	}
	
	
	
	
	
	
	/**
	 * Gestisce la ricezione degli ActioneEvent per il {@link JButton}
	 * {@link Configurazione#esegui}. Per la precisione, appena cliccato 
	 * permettera' la chiusura della finestra in questione e l'inizializzazione
	 * della finestra principale per l'avvio del gioco. 
	 */
	private void gestisciConfigurazioneCompletata() {
		ok.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				/** verifico che la configurazione sia stata effettuata 
				 * correttamente */
				if( verificaScelte() ) {
					gestisciInput();
					disposeWindow();
					
					FinestraIF finestraPrincipale = fPrincipale.createFinestra
							("FinestraPrincipaleAstratta", "FinestraPrincipaleAutomatica",
									modalita, numGiocatori, dimensioniTabellone);
					finestraPrincipale.inizializzaFinestra();
				}
				else {
					FinestraIF finestraErrore = errore.createFinestra
												("FinestraErroreAstratta", "", null, -1, null);
					finestraErrore.inizializzaFinestra();
				}
			}
		});
	}
	
	/**
	 * Gestisce gli input ricevuti dall'utente nella finestra di configurazione 
	 * in questione cosi' da essere in grado di creare la finestra principale 
	 * per la nuova sessione di gioco.
	 */
	private void gestisciInput() {
		gestisciNumeroGiocatoriScelto();
		gestisciDimensioniScelte();
		//gestisciDifficoltaScelta();
		gestisciModalitaScelta();
	}
	
	private void gestisciDimensioniScelte() {
		dimensioniTabellone[0] = Integer.valueOf(nrRighe.getText());
		dimensioniTabellone[1] = Integer.valueOf(nrColonne.getText());
	}
	
	/**
	 * Salva il numero di giocatori scelto tale da essere passato in input alla 
	 * finestra principale che verra' creata per la nuova sessione di gioco in 
	 * questione
	 */
	private void gestisciNumeroGiocatoriScelto() {
		numGiocatori = Integer.valueOf(numeroGiocatori.getText());
	}
	
//	/**
//	 * Salva la difficolta scelta tale da essere passata in input alla finestra 
//	 * principale che verra' creata per la nuova sessione di gioco in 
//	 * questione
//	 */
//	private void gestisciDifficoltaScelta() {
//		if(facile.isSelected())
//			difficolta = Difficolta.FACILE;
//		else if(media.isSelected())
//			difficolta = Difficolta.MEDIA;
//		else
//			difficolta = Difficolta.DIFFICILE;
//	}
	
	/**
	 * Salva la modalita scelta tale da essere passata in input alla finestra 
	 * principale che verra' creata per la nuova sessione di gioco in 
	 * questione
	 */
	private void gestisciModalitaScelta() {
		if(automatica.isSelected())
			modalita = Mod.AUTOMATICA;
		else
			modalita = Mod.MANUALE;
	}
	
	/**
	 * Verifica la correttezza generale della configurazione scelta.
	 * @return
	 */
	private boolean verificaScelte() {
		if( modalitaScelta() && nrGiocatoriInserito() && dimensioniScelte() )
			return true;
		return false;
	}
	
	
	private boolean dimensioniScelte() {
		return (!nrRighe.getText().equals("") && stringaNumerica(nrRighe.getText()) && 
				Integer.valueOf(nrRighe.getText())>=10 ) && 
			   (!nrColonne.getText().equals("") && stringaNumerica(nrColonne.getText()) &&
					   Integer.valueOf(nrColonne.getText())>=10);
	}
	
	
	/**
	 * Verifica la correttezza della modalita' scelta per il gioco in questione.
	 * @return
	 */
	private boolean modalitaScelta() {
		return automatica.isSelected() || manuale.isSelected();
	}
	
	/**
	 * Verifica la correttezza del numero dei giocatori inserito nel 
	 * {@link JTextField} corrispondente.
	 * @return
	 */
	private boolean nrGiocatoriInserito() {
		return !numeroGiocatori.getText().equals("") &&
				stringaNumerica(numeroGiocatori.getText());
	}
	
	/**
	 * Verifica che il numero dei giocatori in input sia effettivamente un 
	 * numero e non una sequenza di caratteri.
	 * @param numeroGiocatori numero giocatori
	 * @return 
	 */
	private boolean stringaNumerica(String numeroGiocatori) {
		try {
			Integer.parseInt(numeroGiocatori);
		}catch(NumberFormatException nfe) {
			return false;
		}
		/**Infine verifico che il numero di giocatori inserito sia maggiore o
		 * uguale di 2 */
		return Integer.parseInt(numeroGiocatori) >= 2;
	}
	
	/**
	 * Verifica la correttezza della difficolta' scelta per il gioco in 
	 * questione.
	 * @return
	 */
	private boolean difficoltaScelta() {
		return facile.isSelected() || media.isSelected() || difficile.isSelected();
	}
	
	
	@Override protected void inizializzaLayoutWEST() {}
	@Override protected void inizializzaLayoutEAST() {}

}
