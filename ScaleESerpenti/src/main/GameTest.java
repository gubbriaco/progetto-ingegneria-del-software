package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import app.modalita.Modalita;
import app.tabellone.Tabellone;
import app.tabellone.casella.CasellaAstratta;
import gui.factory.FinestraFactory;
import gui.factory.FinestraFactoryIF;
import gui.window.FinestraIF;
import gui.window.finestraprincipale.FinestraPrincipaleAstratta;
import gui.window.pannello.concrete.PannelloConfigurazione;

public class GameTest {
	
	private File fileRipristino;
	private FinestraIF finestra;
	
	private static String numeroGiocatori, numeroRighe, numeroColonne, modalita;
	private static FinestraPrincipaleAstratta finestraAbstract;
	private static int nrRighe=10, nrColonne=10;
	
	private static final int TIMEOUT=7000;
	
	
	/** Effettuo dei test dal punto di vista implementativo, cioè verifico che 
	 *  determinati algoritmi implementati risultano funzionare correttamente */
	@Test(timeout=TIMEOUT)
	public void test() {
		
		/** Verifico che l'applicazione ripristini correttamente i parametri 
		 *  fondamentali per l'inizializzazione della modalita' di esecuzione,
		 *  dei giocatori e del tabellone al suo interno */
		/** POSIZIONARE LA CARTELLA DEL PROGETTO SUL DESKTOP */
		fileRipristino = new File(System.getProperty("user.home")+"\\Desktop\\ProgettoIngegneriaDelSoftware\\ScaleESerpenti\\src\\main\\Configurazione.properties");
		ripristina();
		
		Properties p = new Properties();
		try(FileInputStream in = new FileInputStream(fileRipristino.getAbsolutePath())){
			
			p.load(in);
			numeroGiocatori = p.getProperty("NumeroGiocatori");
			numeroRighe = p.getProperty("NumeroRighe");
			numeroColonne = p.getProperty("NumeroColonne");
			modalita = p.getProperty("Modalita");
			finestraAbstract = (FinestraPrincipaleAstratta)finestra;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		testRipristino();
		
		testTabellone();
		
	}
	
	public static void testTabellone() {
		Tabellone tabellone = new Tabellone(nrRighe,nrColonne);
		CasellaAstratta[][] matriceTabellone = tabellone.getTabellone();
		
		assertSame("Numero dell'ultima casella non corretto!", (nrRighe*nrColonne), matriceTabellone[0][0].getNumeroCasella());
	}
	
	public static void testRipristino() {
		assertEquals("Numero giocatori ripristinato non correttamente!", numeroGiocatori, String.valueOf(finestraAbstract.getNumeroGiocatori()));
		assertEquals("Numero righe ripristinato non correttamente!", numeroRighe, String.valueOf(finestraAbstract.getNrRighe()));
		assertEquals("Numoro colonne ripristinato non correttamente!", numeroColonne, String.valueOf(finestraAbstract.getNrColonne()));
		assertEquals("Modalita ripristinata non correttamente!", modalita, String.valueOf(finestraAbstract.getModalita().toString()));
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
			
			FinestraFactoryIF fPrincipale = new FinestraFactory();
			
			if(mod == Modalita.Mod.AUTOMATICA) {
				finestra = fPrincipale.createFinestra
				("FinestraPrincipaleAstratta", "FinestraPrincipaleAutomatica",
				mod, nrGiocatori, dimensioniTabellone );
				finestra.inizializzaFinestra();
			}
			else {
				finestra = fPrincipale.createFinestra
				("FinestraPrincipaleAstratta", "FinestraPrincipaleManuale",
				mod, nrGiocatori, dimensioniTabellone);
				finestra.inizializzaFinestra();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
