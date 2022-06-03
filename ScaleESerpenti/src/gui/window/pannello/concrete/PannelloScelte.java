package gui.window.pannello.concrete;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.modalita.Modalita;
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
		label = new JLabel("Vuoi iniziare una nuova sessione di gioco o ripristinare una precedente?");
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
				if(e.getSource()==riprendi)
					ripristinaConfigurazioneSessioneGioco();
			}
		});
		
	}
	
	private File fileRipristino;
	private void ripristinaConfigurazioneSessioneGioco() {
		
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
		chooser.setFileFilter(new FileNameExtensionFilter("*.properties", new String[] { "properties" }));
		try {
			if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
				if(chooser.getSelectedFile().exists()) {
					fileRipristino = chooser.getSelectedFile();
					ripristina();
				}
				if( !chooser.getSelectedFile().exists() )
					if( !(new File(chooser.getSelectedFile()+".properties").exists()) ) {
					JOptionPane.showMessageDialog(null, "File inesistente!");
				}
				else {
					fileRipristino = chooser.getSelectedFile();
					if(fileRipristino.getAbsolutePath().contains(".") && !fileRipristino.getAbsolutePath().contains(".properties"))
						JOptionPane.showMessageDialog(null, "Fallimento apertura. Formato del file non conforme. Selezionare un file con formato .properties!");
					
					else {
						fileRipristino = new File(chooser.getSelectedFile()+".properties");
						ripristina();
					}
					
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Nessuna apertura!");
		}catch( Exception exc ) {
			exc.printStackTrace();
		}
		
	}
	
	private void ripristina() {
		
		Properties p = new Properties();
		
		try(FileInputStream in = new FileInputStream(fileRipristino.getAbsolutePath())){
			
			p.load(in);
			
			String numeroGiocatori = p.getProperty("NumeroGiocatori"),
				   numeroRighe = p.getProperty("NumeroRighe"),
				   numeroColonne = p.getProperty("NumeroColonne"),
				   numeroDadi = p.getProperty("NumeroDadi"),
				   modalitaDoppioSei = p.getProperty("DoppioSei"),
				   modalita = p.getProperty("Modalita"),
				   caselleSerpente = p.getProperty("CaselleSerpente"),
				   caselleScala = p.getProperty("CaselleScala"),
				   casellePremio = p.getProperty("CasellePremio"),
				   caselleSosta = p.getProperty("CaselleSosta"),
				   caselleUnSoloDado = p.getProperty("CaselleUnSoloDado"),
				   casellePescaUnaCarta = p.getProperty("CasellePescaUnaCarta");
			
			int nrGiocatori = Integer.valueOf(numeroGiocatori),
				nrRighe = Integer.valueOf(numeroRighe),
				nrColonne = Integer.valueOf(numeroColonne),
				nrDadi = Integer.valueOf(numeroDadi);
			
			Modalita.Mod mod = ("Automatica".equalsIgnoreCase(modalita)?
					            Modalita.Mod.AUTOMATICA:Modalita.Mod.MANUALE);
			boolean doppioSei = Boolean.valueOf(modalitaDoppioSei),
					serpenti = Boolean.valueOf(caselleSerpente),
					scale = Boolean.valueOf(caselleScala),
					premi = Boolean.valueOf(casellePremio),
					soste = Boolean.valueOf(caselleSosta),
					unSoloDado = Boolean.valueOf(caselleUnSoloDado),
					pescaUnaCarta = Boolean.valueOf(casellePescaUnaCarta);
			
			int[] dimensioniTabellone = {nrRighe,nrColonne} ;
			
			PannelloConfigurazione.setNumeroDadi(nrDadi);
			PannelloConfigurazione.setDoppioSeiINSIDE(doppioSei);
			PannelloConfigurazione.setSerpentiINSIDE(serpenti);
			PannelloConfigurazione.setScaleINSIDE(scale);
			PannelloConfigurazione.setCasellePremioINSIDE(premi);
			PannelloConfigurazione.setCaselleSostaINSIDE(soste);
			PannelloConfigurazione.setCaselleUnSoloDadoINSIDE(unSoloDado);
			PannelloConfigurazione.setCasellePescaUnaCartaINSIDE(pescaUnaCarta);
			
			this.disposeWindow();
			
			FinestraFactoryIF fPrincipale = new FinestraFactory();
			
			if(mod == Modalita.Mod.AUTOMATICA) {
				FinestraIF finestraPrincipale = fPrincipale.createFinestra
				("FinestraPrincipaleAstratta", "FinestraPrincipaleAutomatica",
				mod, nrGiocatori, dimensioniTabellone );
				finestraPrincipale.inizializzaFinestra();
			}
			else {
				FinestraIF finestraPrincipale = fPrincipale.createFinestra
				("FinestraPrincipaleAstratta", "FinestraPrincipaleManuale",
				mod, nrGiocatori, dimensioniTabellone);
				finestraPrincipale.inizializzaFinestra();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override protected void inizializzaLayoutSOUTH() {}
	@Override protected void inizializzaLayoutWEST() {}
	@Override protected void inizializzaLayoutEAST() {}

	
}
