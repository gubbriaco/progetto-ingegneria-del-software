package gui.window.finestraprincipale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.difficolta.Difficolta;
import app.esecuzione.Esecuzione;
import app.esecuzione.EsecuzioneAutomatica;
import app.esecuzione.EsecuzioneManuale;
import app.esecuzione.giocatore.Giocatore;
import app.esecuzione.giocatore.Pedina;
import app.modalita.Modalita;
import app.tabellone.Tabellone;
import app.tabellone.TabelloneAstratto;
import app.tabellone.casella.CasellaAstratta;
import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.graphic.border.RoundedBorder;
import gui.graphic.panel.LegendaPanel;
import gui.window.FinestraAstratta;
import gui.window.FinestraIF;
import gui.window.finestraterminale.concrete.FinestraTerminale;
import gui.window.pannello.concrete.PannelloConfigurazione;

@SuppressWarnings({"serial", "unused"})
public abstract class FinestraPrincipaleAstratta extends FinestraAstratta {
	
	/**terminale che verra' inizializzato per la sessione di gioco in questione*/
	private FinestraFactoryIF terminaleFactory;
	protected FinestraIF terminale;
	
	/** variabili utilizzate in ambito di configurazione ed inizializzazione 
	 * della sessione di gioco */
	protected Modalita.Mod modalita; 
	protected int numeroGiocatori;
	
	protected int[] dimensioniTabellone;
	protected int nrRighe, nrColonne;
	
	/**File in cui verra' salvata la nuova sessione di gioco o usato come 
	 * ripristino di una sessione di gioco salvata sul calcolatore*/
	private File file;

	/** raggio per arrotondare i bordi */
	protected static final int raggio = 5;
	
	protected LinkedList<Giocatore> giocatoriInGioco;
	protected Esecuzione esecuzione;
	
	protected JButton salva;
	
	
	/**
	 * Costruttore utile per inizializzare una nuova sessione di gioco.
	 * @param modalita Modalita' di gioco (Automatica o Manuale)
	 * @param numeroGiocatori Numero di giocatori che interaggiranno con la 
	 *        nuova sessione di gioco
	 * @param difficolta Difficolta' con cui verra' inizializzato il gioco.
	 */
	public FinestraPrincipaleAstratta(Modalita.Mod modalita, int numeroGiocatori, int[] dimensioniTabellone ) {
		this.modalita = modalita;
		this.numeroGiocatori = numeroGiocatori;
		this.dimensioniTabellone = dimensioniTabellone;
		nrRighe = dimensioniTabellone[0];
		nrColonne = dimensioniTabellone[1];
		 
		/**creo il terminale tramite il factory method*/
		terminaleFactory = new FinestraFactory();
		terminale = terminaleFactory.createFinestra("FinestraTerminaleAstratta", "", null, -1, dimensioniTabellone);
		/**inizializzo la finestra terminale tramite il template method*/
		terminale.inizializzaFinestra();
		
		turnoCorrente = "Turno 1";
		turno = new JLabel(turnoCorrente);
		
	}
	
	/**Costruttore utile al ripristino di una sessione di gioco salvata sul 
	 * calcolatore*/
	public FinestraPrincipaleAstratta(File file) {
		/**Viene salvato il file per ripristinare la sessione di gioco 
		 * precedente*/
		this.file = file;
	}
	
	
	/**
	 * Restituisce il tabellone instanziato per la sessione di gioco in questione.
	 * @return Tabellone
	 */
	public CasellaAstratta[][] getMatriceTabellone() {
		return matriceTabellone;
	}
	
	/**
	 * Restituisce la prima casella del tabellone.
	 * @return Prima casella del tabellone.
	 */
	public CasellaAstratta getPrimaCasella() {
		for(int i=0;i<matriceTabellone.length;++i)
			for(int j=0;j<matriceTabellone[i].length;++j)
				if(matriceTabellone[i][j].getNumeroCasella() == 1)
					return matriceTabellone[i][j];
		return null;
	}
	
	
	protected TabelloneAstratto tabellone;
	protected CasellaAstratta[][] matriceTabellone;
	
	/**
	 * Inizializza un nuovo tabellone o un tabellone gia' inizializzato in una 
	 * sessione di gioco salvata sul calcolatore ad un numero di righe 
	 * {@link FinestraPrincipaleAstratta#nrRighe} e ad un numero di colonne
	 * {@link FinestraPrincipaleAstratta#nrColonne}
	 * @param nrRighe Numero di righe del tabellone
	 * @param nrColonne Numero di colonne del tabellone
	 */
    protected void inizializzaTabellone(int nrRighe, int nrColonne) {
    	tabellone = new Tabellone(nrRighe, nrColonne);
		matriceTabellone = tabellone.getTabellone();
    }
	
    
	@Override protected void inizializzaLayoutCENTER() {
		pCENTER = new JPanel();
		pCENTER.setBackground(new Color(0,153,153));
		pCENTER.setBorder(new RoundedBorder(raggio));
		this.add(pCENTER, BorderLayout.CENTER);
		
		inizializzaLayoutTabellone();
	}
	

	private GridLayout gl;
	
	/**
	 * Permette di inizializzare un nuovo tabellone che verra' aggiunto al 
	 * {@link JPanel} {@link FinestraAstratta#pCENTER}
	 */
	private void inizializzaLayoutTabellone() {
		int nrRighe, nrColonne;
		
		nrRighe = matriceTabellone.length;
		nrColonne = matriceTabellone[0].length;
		gl = new GridLayout(nrRighe, nrColonne);
		pCENTER.setLayout(gl);
		
		for(int i=0;i<nrRighe;++i)
			for(int j=0;j<nrColonne;++j)
				pCENTER.add(matriceTabellone[i][j], BorderLayout.CENTER);
	}
	
	/**
	 * Imposta un nuovo turno e aggiorna il {@link JLabel} corrispondente.
	 * @param nuovoTurno Nuovo turno
	 */
	public void setNuovoTurno(int nuovoTurno) {
		this.turnoCorrente = "Turno " + nuovoTurno;
		turno.setText(turnoCorrente);
		turno.revalidate();
		turno.repaint();
	}
	
	/**
	 * Restituisce il turno corrente.
	 * @return Turno corrente
	 */
	public String getTurnoCorrente() {
		return turnoCorrente;
	}
	
	// JComponent utili per l'inizializzazione del JPanel pNORTH
	protected JLabel titoloGioco;
	protected String turnoCorrente;
	protected JLabel turno;
	
	
	private JLabel titoloLegenda; 
	private JPanel legenda;
	
	@Override protected void inizializzaLayoutSOUTH() {
		pSOUTH = new JPanel();
		pSOUTH.setBorder(new RoundedBorder(raggio));
		pSOUTH.setBackground(Color.LIGHT_GRAY);
		legenda = new LegendaPanel();
		legenda.setBackground(Color.LIGHT_GRAY);
		titoloLegenda = new JLabel("Legenda");
		titoloLegenda.setOpaque(true);
		titoloLegenda.setBackground(Color.BLACK);
		titoloLegenda.setForeground(Color.WHITE);
		titoloLegenda.setBorder(new RoundedBorder(5));
		pSOUTH.add(titoloLegenda);
		pSOUTH.add(legenda);
		this.add(pSOUTH, BorderLayout.SOUTH);
	}
	
	
	protected void gestisciSalvataggio() {
		salva.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if(e.getSource()==salva) {
					salvaConfigurazioneGioco();
				}
			}
		});
	}
	
	/**
	 * Salva la configurazione della sessione di gioco corrente.
	 */
	private void salvaConfigurazioneGioco() {
		/**Il file che conterra' i dati della configurazione della sessione di 
		 * gioco che si intende salvare verra' salvato sul Desktop*/
		file = null;
		/** ad ogni tipologia di dato associo il suo valore */
		Map<String, String> dati = new HashMap<String, String>();
		aggiungiDati(dati);
		
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
		chooser.setFileFilter(new FileNameExtensionFilter("*.properties", new String[] { "properties" }));
		try {
			if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
				file = chooser.getSelectedFile();
				if((new File(file+".properties").exists()) || file.exists()) {
					int ans = JOptionPane.showConfirmDialog(null, "Sovrascrivere " + file.getAbsolutePath()+".properties" + "?");
					if( ans == 0 ) {
						salva( file.getAbsolutePath(), dati );
						salva.setEnabled(false);
					}
					else
						JOptionPane.showMessageDialog(null, "Nessun salvataggio!");
				}
				else {
					salva( file.getAbsolutePath()+".properties", dati );
					salva.setEnabled(false);
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Nessun salvataggio!");
		}catch(Exception exc) {
			exc.printStackTrace();
		}

	}
	
	private void salva( String nome, Map<String,String> dati ) throws IOException {
		PrintWriter pw = new PrintWriter( new FileWriter(nome) );
		
		String key="", value="";
		for( Map.Entry<String, String> entry : dati.entrySet() ) {
			key = entry.getKey();
			value = entry.getValue();
			
			pw.print(key);
			pw.print(" ");
			pw.print(value);
			
			pw.println();
		}		
		pw.close();
	}

	private void aggiungiDati(Map<String, String> dati) {
		dati.put( "Modalita", "" + this.modalita );
		
		dati.put( "NumeroRighe", "" + this.nrRighe );
		dati.put( "NumeroColonne", "" + this.nrColonne );
		dati.put( "NumeroGiocatori", "" + this.numeroGiocatori  );
		
		dati.put( "NumeroDadi", "" + PannelloConfigurazione.numeroDadi  );
		dati.put( "DoppioSei", "" + PannelloConfigurazione.doppioSeiINSIDE  );
		
		dati.put( "CaselleUnSoloDado", ""+PannelloConfigurazione.caselleUnSoloDadoINSIDE );
		dati.put( "CasellePescaUnaCarta", "" + PannelloConfigurazione.casellePescaUnaCartaINSIDE );
		dati.put( "CasellePremio", "" + PannelloConfigurazione.casellePremioINSIDE );
		dati.put( "CaselleSosta", "" + PannelloConfigurazione.caselleSostaINSIDE  );
		dati.put( "CaselleScala", "" + PannelloConfigurazione.scaleINSIDE  );
		dati.put( "CaselleSerpente", "" + PannelloConfigurazione.serpentiINSIDE  );
	}
	
	
}
