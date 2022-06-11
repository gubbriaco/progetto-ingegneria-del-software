package app.tabellone.casella.strategy.creators;

import java.util.Random;

import app.tabellone.Tabellone;
import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.special.CasellaSerpente;
import app.tabellone.casella.factory.CasellaFactory;
import app.tabellone.casella.factory.CasellaFactoryIF;
import app.tabellone.casella.strategy.CasellaCreator;
import app.tabellone.object.OggettoTrasferimento;
import app.tabellone.object.Serpente;

public class SerpenteCreator implements CasellaCreator {
	
	private int nrRiga;
	private Tabellone t;
	
	private CasellaFactoryIF casellaFactory = new CasellaFactory(); 
	
	private OggettoTrasferimento serpente;
	
	public SerpenteCreator(int nrRiga, Tabellone t) {
		this.nrRiga = nrRiga;
		this.t = t;
	}

	@Override public CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random) {
		
		/** Controllo che il serpente che sto andando a creare non si posizioni 
		 * sull'ultima casella o sulla prima riga */
		if(random == tabellone[0][0].getNumeroCasella() || nrRiga==tabellone.length-1)
			return tabellone;

		boolean testaPosizionata = false;
		int rigaTesta=-1, colonnaTesta=-1;
		
		CasellaAstratta testaSerpente = null;
		
		for(int j=0;j<tabellone[nrRiga].length;++j) {
			
			/** verifico che non si tratti di una casella di tipologia Un 
			 * Solo Dado essendo che quest'ultime sono pre-fissate*/
			if(tabellone[nrRiga][j].getNumeroCasella() == random) {
				if(tabellone[nrRiga][j].getNumeroCasella() >= Tabellone.CELLE_UN_SOLO_DADO[0] &&
				tabellone[nrRiga][j].getNumeroCasella() <= Tabellone.CELLE_UN_SOLO_DADO[1])
					break;
				
				/** controllo che tale casella sia una casella non speciale */
				if( t.verificaCellaNonSpeciale(nrRiga, j) ) {
					
					rigaTesta = nrRiga;
					colonnaTesta = j;
					
					testaPosizionata = true;
					t.serpenti[nrRiga][j] = true;
					
					tabellone[nrRiga][j] = casellaFactory.createCella(
				    "Serpente", tabellone[nrRiga][j].getNumeroCasella());
							
					testaSerpente = tabellone[nrRiga][j];
					
					/** Effettuo la break perche' ormai una testa di un serpente 
					 *  per tale riga e' stata posizionata  */
					break;
				}
			}
		}
		
		/** se non sono riuscito ad assegnare una testa al serpente per la riga
		 * in questione allora non perdo tempo ad allocare una coda ad un 
		 * serpente che non ha una testa*/
		if(!testaPosizionata)
			return tabellone;
		
		Random rigaCodaSerpente = new Random();
		
		int randomRigaCoda = -1;
		/** Controllo che il numero della riga non sia maggiore del numero di
		 * righe rimanenti altrimenti verrebbe sollevata l'eccezzione 
		 * IllegalArgumentException dovuto al fatto che {@link Random#nextInt()}
		 * riceve due argomenti dove il primo deve essere minore del secondo 
		 * essendo un intervallo di valori. Nel caso in cui il primo e' maggiore
		 *  del secondo allora li scambio. */
		if(nrRiga > tabellone.length-nrRiga) {
			randomRigaCoda = rigaCodaSerpente.nextInt((tabellone.length-nrRiga), nrRiga);
			if(nrRiga==randomRigaCoda) {
				
				t.serpenti[nrRiga][colonnaTesta] = false;
				
				tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
			    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
				
				rigaTesta = -1;
				colonnaTesta = -1;
				
				return tabellone;
			}
			/** non si possono assegnare testa e coda sulla stessa riga */

		}
		else if(nrRiga < tabellone.length-nrRiga) {
			randomRigaCoda = rigaCodaSerpente.nextInt(nrRiga,(tabellone.length-nrRiga));
			if(nrRiga==randomRigaCoda) {
				t.serpenti[nrRiga][colonnaTesta] = false;
				
				tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
			    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
				
				rigaTesta = -1;
				colonnaTesta = -1;
				
				return tabellone;
			}
			/** non si possono assegnare testa e coda sulla stessa riga */

		}
		else {
			/** semplicemente dealloco la testa del serpente trovata 
			 *  precedentemente poiche' se il numero della riga in questione e
			 *  il numero delle righe rimanenti e' uguale significa che sta
			 *  cercando di creare un serpente sulla stessa riga*/
			t.serpenti[nrRiga][colonnaTesta] = false;
			
			tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
		    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
			
			rigaTesta = -1;
			colonnaTesta = -1;
			
			return tabellone;
		}
			
		
		boolean codaPosizionata = false;
		
		CasellaAstratta codaSerpente = null;
		
		int rigaCoda=-1, colonnaCoda=-1;
		
		/** assegno la coda della scala corrispondente alla casella booleana che 
		 *  soddisfa la condizione*/
		
		for(int j=0;j<tabellone[randomRigaCoda].length;++j) {
			
			/** verifico che non si tratti di una casella di tipologia Un 
			 *  Solo Dado essendo che quest'ultime sono pre-fissate*/
			if(tabellone[randomRigaCoda][j].getNumeroCasella() >= Tabellone.CELLE_UN_SOLO_DADO[0] &&
		    tabellone[randomRigaCoda][j].getNumeroCasella() <= Tabellone.CELLE_UN_SOLO_DADO[1])
				break;
			
			/** controllo che tale casella sia una casella non speciale */
			if( t.verificaCellaNonSpeciale(randomRigaCoda, j) ) {
				
				rigaCoda = randomRigaCoda;
				colonnaCoda = j;
				
				if(rigaTesta == rigaCoda) {
					t.scale[nrRiga][colonnaTesta] = false;
					
					tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
				    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
					tabellone[nrRiga][colonnaTesta].repaint();
					
					rigaTesta = -1;
					colonnaTesta = -1;
					return tabellone;
				}
				
				if(rigaTesta == rigaCoda) {
					t.serpenti[nrRiga][colonnaTesta] = false;
					
					tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
				    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
					tabellone[nrRiga][colonnaTesta].repaint();
					
					rigaTesta = -1;
					colonnaTesta = -1;
					break;
				}
				
				codaPosizionata = true;
				t.serpenti[randomRigaCoda][j] = true;
				
				tabellone[randomRigaCoda][j] = casellaFactory.createCella(
			    "Serpente", tabellone[randomRigaCoda][j].getNumeroCasella());
						
				codaSerpente = tabellone[randomRigaCoda][j];
				
				codaSerpente = tabellone[randomRigaCoda][j];
				codaSerpente.repaint();
				
				/** coda trovata quindi mi fermo */
				break;
			}
		}
		
		/** se la testa del serpente e' stata posizionata mentre la coda no 
		 *  allora deassegno anche la testa del serpente posizionata */
		if(testaPosizionata && !codaPosizionata) {
			
			t.serpenti[nrRiga][colonnaTesta] = false;
			
			
			tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
		    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
			tabellone[nrRiga][colonnaTesta].repaint();
			
			rigaTesta = -1;
			colonnaTesta=-1;
			
		}
		else if(testaPosizionata && codaPosizionata) {
			
			if(testaSerpente.getNumeroCasella()>codaSerpente.getNumeroCasella()) {
				serpente = new Serpente(testaSerpente, codaSerpente);
				((CasellaSerpente) codaSerpente).setSerpente((Serpente) serpente);
				((CasellaSerpente) testaSerpente).setSerpente((Serpente) serpente);
			}
			else if(testaSerpente.getNumeroCasella()<codaSerpente.getNumeroCasella()) {
				serpente = new Serpente(codaSerpente, testaSerpente);
				((CasellaSerpente) codaSerpente).setSerpente((Serpente) serpente);
				((CasellaSerpente) testaSerpente).setSerpente((Serpente) serpente);
			}
			
			else {
				t.serpenti[rigaTesta][colonnaTesta] = false;
				
				tabellone[rigaTesta][colonnaTesta] = casellaFactory.createCella(
			    "Standard", tabellone[rigaTesta][colonnaTesta].getNumeroCasella());
				tabellone[rigaTesta][colonnaTesta].repaint();
				
				rigaTesta = -1;
				colonnaTesta = -1;
				
				t.serpenti[rigaCoda][colonnaCoda] = false;
				
				tabellone[rigaCoda][colonnaCoda] = casellaFactory.createCella(
			    "Standard", tabellone[rigaCoda][colonnaCoda].getNumeroCasella());
				tabellone[rigaCoda][colonnaCoda].repaint();
				
				rigaCoda = -1;
				colonnaCoda = -1;
				
			}
		}
		
		return tabellone;
		
	}

}
