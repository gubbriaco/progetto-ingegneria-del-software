package gui.window.finestraprincipale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.difficolta.Difficolta;
import app.esecuzione.Esecuzione;
import app.esecuzione.EsecuzioneAutomatica;
import app.esecuzione.giocatore.Giocatore;
import app.esecuzione.giocatore.Pedina;
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
import gui.window.finestraterminale.concrete.FinestraTerminale;

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
	private static final int raggio = 5;
	
	protected LinkedList<Giocatore> giocatoriInGioco;
	protected Esecuzione esecuzione;
	
	
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
	
//	public FinestraIF getTerminale() {
//		return terminale;
//	}
//	
	
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
	
	
	public String giocatoreCorrente, combinazioneDadiCorrente;
	
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
	
	public void setNuovoTurno(int nuovoTurno) {
		this.turnoCorrente = "Turno " + nuovoTurno;
		this.inizializzaLayoutNORTH();
		this.repaint();
	}
	
	public String getTurnoCorrente() {
		return turnoCorrente;
	}
	
	
	private JLabel titoloGioco;
	protected String turnoCorrente;
	private JLabel turno;
	private JButton prossimoTurno;

	@Override protected void inizializzaLayoutNORTH() {
		pNORTH = new JPanel();
		pNORTH.setBorder(new RoundedBorder(raggio));
		pNORTH.setBackground(Color.LIGHT_GRAY);
		
		titoloGioco = new JLabel("Scale e Serpenti");
		titoloGioco.setForeground(Color.BLACK);
		font = new Font("Helvetica", Font.BOLD, 14);
		titoloGioco.setFont(font);
		pNORTH.add(titoloGioco, BorderLayout.CENTER);
		
		turno = new JLabel(turnoCorrente);
		turno.setOpaque(true);
		turno.setBackground(Color.LIGHT_GRAY);
		turno.setForeground(Color.BLACK);
		turno.setBorder(new RoundedBorder(raggio));
		pNORTH.add(turno);
		
		if(modalita == Modalita.Mod.MANUALE) {
			
			prossimoTurno = new JButton("Prossimo turno");
			prossimoTurno.setOpaque(true);
			prossimoTurno.setBackground(Color.GRAY.brighter());
			prossimoTurno.setForeground(Color.BLACK);
			prossimoTurno.setBorder(new RoundedBorder(raggio));
			pNORTH.add(prossimoTurno);

		}
	
		this.add(pNORTH, BorderLayout.NORTH);
		
	}
	
	
	private JLabel titoloLegenda; 
	private PanelAbstract legenda;
	
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
	
	
	private PanelAbstract giocatoreCorrenteLabel;
	private PanelAbstract combinazioneDadiCorrenteLabel;
	private JLabel titoloGiocatoreCorrente, titoloCombinazioneDadi;
	private String tipologiaModalita;
	private JLabel titoloModalita;
	private JButton pulsanteModalita;
	private JButton salva;
	private JLabel titoloSalva;
	
	@Override protected void inizializzaLayoutEAST() {
		pEAST = new JPanel();
		pEAST.setBorder(new RoundedBorder(raggio));
		pEAST.setLayout(new GridLayout(4,1));
		pEAST.setBackground(Color.LIGHT_GRAY);
		
		giocatoreCorrenteLabel = new GiocatoreCorrentePanel(giocatoreCorrente);
		titoloGiocatoreCorrente = new JLabel("Giocatore corrente");
		titoloGiocatoreCorrente.setOpaque(true);
		titoloGiocatoreCorrente.setBackground(Color.BLACK.brighter());
		titoloGiocatoreCorrente.setForeground(Color.WHITE);
		titoloGiocatoreCorrente.setBorder(new RoundedBorder(raggio));
		pEAST.add(titoloGiocatoreCorrente);
		pEAST.add(giocatoreCorrenteLabel);
		
		titoloCombinazioneDadi = new JLabel("Combinazione dadi");
		titoloCombinazioneDadi.setOpaque(true);
		titoloCombinazioneDadi.setBackground(Color.BLACK.brighter());
		titoloCombinazioneDadi.setForeground(Color.WHITE);
		titoloCombinazioneDadi.setBorder(new RoundedBorder(raggio));
		combinazioneDadiCorrenteLabel = new CombinazioneDadiCorrentePanel(combinazioneDadiCorrente);
		
		pEAST.add(titoloCombinazioneDadi);
		pEAST.add(combinazioneDadiCorrenteLabel);
		
		if(modalita == Modalita.Mod.AUTOMATICA) {
			tipologiaModalita = "Esecuzione Manuale";
		}else {
			tipologiaModalita = "Esecuzione Automatica";
		}
		
		titoloModalita = new JLabel("Cambia modalità di gioco");
		titoloModalita.setOpaque(true);
		titoloModalita.setBackground(Color.BLACK.brighter());
		titoloModalita.setForeground(Color.WHITE);
		titoloModalita.setBorder(new RoundedBorder(raggio));
		pulsanteModalita = new JButton(tipologiaModalita);
		pulsanteModalita.setOpaque(true);
		pulsanteModalita.setBackground(Color.ORANGE.darker());
		pulsanteModalita.setForeground(Color.BLACK);
		pulsanteModalita.setBorder(new RoundedBorder(raggio));
		pEAST.add(titoloModalita);
		pEAST.add(pulsanteModalita);
		
		titoloSalva = new JLabel("Salva la sessione di gioco");
		titoloSalva.setOpaque(true);
		titoloSalva.setBackground(Color.BLACK.brighter());
		titoloSalva.setForeground(Color.WHITE);
		titoloSalva.setBorder(new RoundedBorder(raggio));
		salva = new JButton("SALVA");
		salva.setOpaque(true);
		salva.setBackground(Color.YELLOW.darker().darker());
		salva.setForeground(Color.BLACK);
		salva.setBorder(new RoundedBorder(raggio));
		
		pEAST.add(titoloSalva);
		pEAST.add(salva);

		this.add(pEAST, BorderLayout.EAST);
	}
	

}
