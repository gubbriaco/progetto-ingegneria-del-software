package gui.window.finestraprincipale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.difficolta.Difficolta;
import app.modalita.Modalita;
import app.tabellone.Tabellone;
import app.tabellone.TabelloneAstratto;
import app.tabellone.casella.CasellaAstratta;
import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.graphic.border.RoundedBorder;
import gui.graphic.panel.PanelAbstract;
import gui.graphic.panel.concrete.CombinazioneDadiCorrentePanel;
import gui.graphic.panel.concrete.GiocatoreCorrentePanel;
import gui.graphic.panel.concrete.LegendaPanel;
import gui.window.FinestraAstratta;
import gui.window.FinestraIF;

@SuppressWarnings({"serial", "unused"})
public abstract class FinestraPrincipaleAstratta extends FinestraAstratta {
	
	/**terminale che verra' inizializzato per la sessione di gioco in questione*/
	private FinestraFactoryIF terminaleFactory;
	private FinestraIF terminale;
	
	/** variabili utilizzate in ambito di configurazione ed inizializzazione 
	 * della sessione di gioco */
	protected Modalita.Mod modalita; 
	protected int numeroGiocatori;
	
	protected JLabel titoloGioco;
	
	protected int[] dimensioniTabellone;
	protected int nrRighe, nrColonne;
	
	private TabelloneAstratto tabellone;
	protected CasellaAstratta[][] matriceTabellone;
	private GridLayout gl;
	
	protected JLabel titoloLegenda, titoloGiocatoreCorrente, titoloCombinazioneDadi;
	
	public String giocatoreCorrente, combinazioneDadiCorrente;
	
	private PanelAbstract legenda;
	protected PanelAbstract giocatoreCorrenteLabel;
	protected PanelAbstract combinazioneDadiCorrenteLabel;
	
	protected JPanel decoratorePEAST, decoratorePNORTH;
	
	/**File in cui verra' salvata la nuova sessione di gioco o usato come 
	 * ripristino di una sessione di gioco salvata sul calcolatore*/
	private File file;
	
	
	protected String turnoCorrente;
	
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
		
		/**Il file che conterra' i dati della nuova sessione di gioco verra' 
		 * salvato sul Desktop*/
		file = new File(System.getProperty("user.home") + "/Desktop");
		
		
		//TODO 
		giocatoreCorrente = "";
		combinazioneDadiCorrente = "";
		
		// TODO
		turnoCorrente = "Turno 1";
		
	}
	
	/**Costruttore utile al ripristino di una sessione di gioco salvata sul 
	 * calcolatore*/
	public FinestraPrincipaleAstratta(File file) {
		/**Viene salvato il file per ripristinare la sessione di gioco 
		 * precedente*/
		this.file = file;
	}
	
	
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
		this.add(pCENTER, BorderLayout.CENTER);
		
		inizializzaLayoutTabellone();
	}
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
	
	public void setGiocatoreCorrente(String giocatoreCorrente) {
		this.giocatoreCorrente = giocatoreCorrente;
		
		this.inizializzaLayoutEAST();
		this.repaint();
	}
	
	public void setDadi(String combinazioneDadiCorrente) {
		this.combinazioneDadiCorrente = combinazioneDadiCorrente;
		
		this.inizializzaLayoutEAST();
		this.repaint();
	}
	

	@Override protected void inizializzaLayoutSOUTH() {
		pSOUTH = new JPanel();
		legenda = new LegendaPanel();
		titoloLegenda = new JLabel("Legenda");
		titoloLegenda.setOpaque(true);
		titoloLegenda.setBackground(Color.BLACK);
		titoloLegenda.setForeground(Color.WHITE);
		titoloLegenda.setBorder(new RoundedBorder(5));
		pSOUTH.add(titoloLegenda);
		pSOUTH.add(legenda);
		this.add(pSOUTH, BorderLayout.SOUTH);
	}

	

}
