package gui.window.pannello.concrete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import app.modalita.Modalita;
import app.modalita.Modalita.Mod;
import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.graphic.border.RoundedBorder;
import gui.window.FinestraIF;
import gui.window.pannello.PannelloAstratto;

@SuppressWarnings("serial")
public class PannelloConfigurazione extends PannelloAstratto {
	
	private JLabel label, titoloModalita, titoloTextField,
				   titoloNrRighe, titoloNrColonne, titoloNrDadi;
	private JButton ok;
	private JRadioButton automatica, manuale;
	private JTextField numeroGiocatori, nrRighe, nrColonne, nrDadi;
	private JPanel pSOUTH1, pSOUTH2, pSOUTH3, pCENTER1, pCENTER2;
	
	private JCheckBox caselleUnSoloDado, doppioSei, caselleSosta, 
			          casellePremio, casellePescaUnaCarta, scale, serpenti ;
	
	public static boolean caselleUnSoloDadoINSIDE, caselleSostaINSIDE, 
	               casellePremioINSIDE, casellePescaUnaCartaINSIDE,
	               doppioSeiINSIDE, scaleINSIDE, serpentiINSIDE;
	
	private FinestraFactoryIF errore = new FinestraFactory(),
							  fPrincipale = new FinestraFactory();
	private Modalita.Mod modalita;
	private int numGiocatori;
	
	public static int numeroDadi;
	
	protected int[] dimensioniTabellone;
 	

	public PannelloConfigurazione() {
		titolo = "Pannello Configurazione";
		this.setTitle(titolo);
		
		caselleUnSoloDadoINSIDE = false;
		caselleSostaINSIDE = false;
        casellePremioINSIDE = false;
        casellePescaUnaCartaINSIDE = false;
        scaleINSIDE = false;
        serpentiINSIDE = false;
        
        doppioSeiINSIDE = false;
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
		
		pCENTER1 = new JPanel();
		pCENTER1.setBorder(new RoundedBorder(5));
		pCENTER1.setBackground(Color.GRAY);
		pCENTER.add(pCENTER1);
		titoloModalita = new JLabel("Modalità:"); titoloModalita.setForeground(Color.BLACK);
		pCENTER1.add(titoloModalita, BorderLayout.NORTH);
		automatica = new JRadioButton("Automatica"); 
		automatica.setForeground(Color.BLACK.brighter()); automatica.setBackground(Color.GRAY);
		pCENTER1.add(automatica,BorderLayout.WEST);
		manuale = new JRadioButton("Manuale"); 
		manuale.setForeground(Color.BLACK.brighter()); manuale.setBackground(Color.GRAY);
		pCENTER1.add(manuale,BorderLayout.EAST);

		gestisciRadioButtonModalita();
		
		
		pCENTER2 = new JPanel();
		pCENTER2.setBorder(new RoundedBorder(5));
		caselleUnSoloDado = new JCheckBox("Caselle un solo dado");
		caselleUnSoloDado.setForeground(Color.BLACK);
		caselleUnSoloDado.setBackground(Color.LIGHT_GRAY);
		pCENTER2.add(caselleUnSoloDado);
		caselleSosta = new JCheckBox("Caselle sosta");
		caselleSosta.setForeground(Color.BLACK);
		caselleSosta.setBackground(Color.LIGHT_GRAY);
		pCENTER2.add(caselleSosta);
		casellePremio = new JCheckBox("Caselle premio");
		casellePremio.setForeground(Color.BLACK);
		casellePremio.setBackground(Color.LIGHT_GRAY);
		pCENTER2.add(casellePremio);
		casellePescaUnaCarta = new JCheckBox("Caselle pesca una carta");
		casellePescaUnaCarta.setForeground(Color.BLACK);
		casellePescaUnaCarta.setBackground(Color.LIGHT_GRAY);
		pCENTER2.add(casellePescaUnaCarta);
		pCENTER2.setBackground(Color.LIGHT_GRAY);
		scale = new JCheckBox("Scale");
		scale.setForeground(Color.BLACK);
		scale.setBackground(Color.LIGHT_GRAY);
		pCENTER2.add(scale);
		pCENTER2.setBackground(Color.LIGHT_GRAY);
		serpenti = new JCheckBox("Serpenti");
		serpenti.setForeground(Color.BLACK);
		serpenti.setBackground(Color.LIGHT_GRAY);
		pCENTER2.add(serpenti);
		pCENTER2.setBackground(Color.LIGHT_GRAY);
		pCENTER.add(pCENTER2);
		
		
		gestisciCheckBoxCaselle();
		
	}
	
	/**
	 * Gestisce le scelte effettuate riguardo la modalita' di gioco per la nuova
	 * sessione di Scale e Serpenti.
	 */
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
	 * Gestisce le scelte effettuate riguardo le varianti delle caselle speciali
	 * da inserire all'interno della nuova sessione di gioco.
	 */
	private void gestisciCheckBoxCaselle() {
		
		caselleUnSoloDado.addItemListener(new ItemListener() {
			@Override public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					caselleUnSoloDadoINSIDE = true;
			}
		});

		caselleSosta.addItemListener(new ItemListener() {
			@Override public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					caselleSostaINSIDE = true;
			}
		});
		
		casellePremio.addItemListener(new ItemListener() {
			@Override public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					casellePremioINSIDE = true;
			}
		});
		
		casellePescaUnaCarta.addItemListener(new ItemListener() {
			@Override public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					casellePescaUnaCartaINSIDE = true;
			}
		});
		
		scale.addItemListener(new ItemListener() {
			@Override public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					scaleINSIDE = true;
			}
		});
		
		serpenti.addItemListener(new ItemListener() {
			@Override public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					serpentiINSIDE = true;
			}
		});
		
	}

	@Override protected void inizializzaLayoutSOUTH() {
		pSOUTH = new JPanel();
		this.add(pSOUTH,BorderLayout.SOUTH); pSOUTH.setBackground(Color.GRAY.brighter());
		
		pSOUTH1 = new JPanel(); 
		pSOUTH1.setBorder(new RoundedBorder(5));
		pSOUTH1.setBackground(Color.GRAY);
		titoloTextField = new JLabel("Nr. giocatori:");
		pSOUTH1.add(titoloTextField, BorderLayout.WEST);
		titoloTextField.setForeground(Color.BLACK);titoloTextField.setBackground(Color.GRAY);
		numeroGiocatori = new JTextField();
		numeroGiocatori.setColumns(2);
		numeroGiocatori.setToolTipText("Nr. giocatori");
		pSOUTH1.add(numeroGiocatori,BorderLayout.EAST);
		pSOUTH.add(pSOUTH1, BorderLayout.WEST);

		pSOUTH2 = new JPanel();
		pSOUTH2.setBorder(new RoundedBorder(5));
		pSOUTH2.setBackground(Color.GRAY.brighter());
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
		pSOUTH.add(pSOUTH2, BorderLayout.CENTER);
		
		dimensioniTabellone = new int[2];
		
		pSOUTH3 = new JPanel();
		pSOUTH3.setBorder(new RoundedBorder(5));
		pSOUTH3.setBackground(Color.LIGHT_GRAY);
		titoloNrDadi = new JLabel("Nr. dadi");
		titoloNrDadi.setForeground(Color.BLACK);
		pSOUTH3.add(titoloNrDadi);
		nrDadi = new JTextField();
		nrDadi.setColumns(2);
		nrDadi.setToolTipText("Numero Dadi");
		pSOUTH3.add(nrDadi);
		doppioSei = new JCheckBox("Modalita doppio sei");
		doppioSei.setForeground(Color.BLACK);
		doppioSei.setBackground(Color.LIGHT_GRAY);
		pSOUTH3.add(doppioSei);
		pSOUTH.add(pSOUTH3, BorderLayout.EAST);
		
		gestisciDadi();
		
	}
	
	private void gestisciDadi() {
		doppioSei.addItemListener(new ItemListener() {
			@Override public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					doppioSeiINSIDE = true;
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
					if(modalita == Modalita.Mod.AUTOMATICA) {
						FinestraIF finestraPrincipale = fPrincipale.createFinestra
						("FinestraPrincipaleAstratta", "FinestraPrincipaleAutomatica",
						modalita, numGiocatori, dimensioniTabellone);
						finestraPrincipale.inizializzaFinestra();
					}
					else {
						FinestraIF finestraPrincipale = fPrincipale.createFinestra
						("FinestraPrincipaleAstratta", "FinestraPrincipaleManuale",
						modalita, numGiocatori, dimensioniTabellone);
						finestraPrincipale.inizializzaFinestra();
					}
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
		gestisciModalitaScelta();
		gestisciNumeroDadi();
		
	}
	
	private void gestisciNumeroDadi() {
		numeroDadi = Integer.valueOf(nrDadi.getText());
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
		if( modalitaScelta() && nrGiocatoriInserito() && dimensioniScelte() &&
				nrDadiScelto() )
			return true;
		return false;
	}
	
	/**
	 * Verifica l'integrita' del numero dei dadi scelto all'interno del pannello
	 * di configurazione
	 * @return Numero dei dadi scelto corretto
	 */
	private boolean nrDadiScelto() {
		return (!nrDadi.getText().equals("") && stringaNumerica(nrDadi.getText())
				&& Integer.valueOf(nrDadi.getText())>=1 &&  Integer.valueOf(nrDadi.getText())<=2 );
	}
	
	/**
	 * Verifica l'integrita' delle dimensioni inserite all'interno del pannello 
	 * di configurazione.
	 * @return Dimensioni scelte corrette 
	 */
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
				stringaNumerica(numeroGiocatori.getText()) && 
				Integer.valueOf(numeroGiocatori.getText()) >= 2;
	}
	
	/**
	 * Verifica che il numero in input sia effettivamente un numero e non una 
	 * sequenza di caratteri.
	 * @param numero
	 * @return 
	 */
	private boolean stringaNumerica(String numero) {
		try {
			Integer.parseInt(numero);
		}catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	
	@Override protected void inizializzaLayoutWEST() {}
	@Override protected void inizializzaLayoutEAST() {}
	

}
