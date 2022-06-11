package app.tabellone.casella.strategy.creators;

import java.util.Random;

import app.tabellone.Tabellone;
import app.tabellone.casella.CasellaAstratta;
import app.tabellone.casella.concrete.special.CasellaScala;
import app.tabellone.casella.factory.CasellaFactory;
import app.tabellone.casella.factory.CasellaFactoryIF;
import app.tabellone.casella.strategy.CasellaCreator;
import app.tabellone.object.OggettoTrasferimento;
import app.tabellone.object.Scala;

public class ScalaCreator implements CasellaCreator {
	
	private int nrRiga;
	private Tabellone t;
	
	private CasellaFactoryIF casellaFactory = new CasellaFactory(); 

	private OggettoTrasferimento scala;
	
	public ScalaCreator(int nrRiga, Tabellone t) {
		this.nrRiga = nrRiga;
		this.t = t;
	}
	
	@Override public CasellaAstratta[][] createCasella(CasellaAstratta[][] tabellone, int random) {
		
		/** Controllo che la scala che sto andando a creare non si posizioni 
		 * sull'ultima casella o sulla prima riga */
		if(random == tabellone[0][0].getNumeroCasella() || nrRiga==tabellone.length-1)
			return tabellone;
		
		boolean testaPosizionata = false;
		int rigaTesta=-1, colonnaTesta = -1;
		
		CasellaAstratta testaScala = null;
		
		for(int j=0;j<tabellone[nrRiga].length;++j) {
			
			if(tabellone[nrRiga][j].getNumeroCasella() == random) {
				
				/** verifico che non si tratti di una casella di tipologia Un 
				 *  Solo Dado essendo che quest'ultime sono pre-fissate */
				if(tabellone[nrRiga][j].getNumeroCasella() >= Tabellone.CELLE_UN_SOLO_DADO[0] &&
				tabellone[nrRiga][j].getNumeroCasella() <= Tabellone.CELLE_UN_SOLO_DADO[1])
					break;
				
				/** controllo che tale casella sia una casella non speciale */
				if( t.verificaCellaNonSpeciale(nrRiga, j) ) {
					
					rigaTesta = nrRiga;
					colonnaTesta = j;
					testaPosizionata = true;
					t.scale[nrRiga][j] = true;
					
					tabellone[nrRiga][j] = casellaFactory.createCella(
				    "Scala", tabellone[nrRiga][j].getNumeroCasella());
					
					testaScala = tabellone[nrRiga][j];
					
					/** Effettuo la break perche' ormai una testa di una scala 
					 *  per tale riga e' stata posizionata  */
					break;
				}
			}
		}
		
		/** se non sono riuscito ad assegnare una testa alla scala per la riga
		 *  in questione allora non perdo tempo ad allocare una coda ad una scala
		 *  che non ha una testa*/
		if(!testaPosizionata)
			return tabellone;
		
		Random rigaCodaScala = new Random();
		
		int randomRigaCoda = -1;
		
		/** Controllo che il numero della riga non sia maggiore del numero di
		 *  righe rimanenti altrimenti verrebbe sollevata l'eccezzione 
		 *  IllegalArgumentException dovuto al fatto che {@link Random#nextInt()}
		 *  riceve due argomenti dove il primo deve essere minore del secondo 
		 *  essendo un intervallo di valori. Nel caso in cui il primo e' maggiore
		 *  del secondo allora li scambio. */
		if(nrRiga > (tabellone.length-nrRiga)) {
			randomRigaCoda = rigaCodaScala.nextInt((tabellone.length-nrRiga), nrRiga);
			
			if(nrRiga==randomRigaCoda) {
				
				
				t.scale[nrRiga][colonnaTesta] = false;
				
				tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
			    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
				
				rigaTesta = -1;
				colonnaTesta = -1;
				
				return tabellone;
			}
			/** non si possono assegnare testa e coda sulla stessa riga */

		}
		else if(nrRiga < (tabellone.length-nrRiga)) {
			if(nrRiga==randomRigaCoda) {
				
				t.scale[nrRiga][colonnaTesta] = false;
				
				tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
			    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
				
				rigaTesta = -1;
				colonnaTesta = -1;
				
				return tabellone;
			}
			randomRigaCoda = rigaCodaScala.nextInt(nrRiga, (tabellone.length-nrRiga));
			/** non si possono assegnare testa e coda sulla stessa riga */

		}
		else {
			/** semplicemente dealloco la testa della scala trovata 
			 * precedentemente poiche' se il numero della riga in questione e
			 * il numero delle righe rimanenti e' uguale significa che sta
			 * cercando di creare una scala sulla stessa riga*/
			
			t.scale[nrRiga][colonnaTesta] = false;
			
			tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
		    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
			
			rigaTesta = -1;
			colonnaTesta = -1;
			
			return tabellone;
		}
		
		boolean codaPosizionata = false;
		
		CasellaAstratta codaScala = null;
		
		int rigaCoda=-1 , colonnaCoda=-1;
		
		/** assegno la coda della scala corrispondente alla cella booleana che 
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
				
				codaPosizionata = true;
				t.scale[randomRigaCoda][j] = true;
				
				tabellone[randomRigaCoda][j] = casellaFactory.createCella(
			    "Scala", tabellone[randomRigaCoda][j].getNumeroCasella());
				
				codaScala = tabellone[randomRigaCoda][j];
				codaScala.repaint();
				break;
			}
		}
		
		/** se la testa della scala e' stata posizionata mentre la coda no 
		 *  allora deassegno anche la testa della scala posizionata */
		if(testaPosizionata && !codaPosizionata) {
			
			t.scale[nrRiga][colonnaTesta] = false;
			
			tabellone[nrRiga][colonnaTesta] = casellaFactory.createCella(
		    "Standard", tabellone[nrRiga][colonnaTesta].getNumeroCasella());
			tabellone[nrRiga][colonnaTesta].repaint();
			
			rigaTesta = -1;
			colonnaTesta = -1;
			
		}
		else if(testaPosizionata && codaPosizionata){

			if(testaScala.getNumeroCasella()>codaScala.getNumeroCasella()) {
				scala = new Scala(testaScala, codaScala);

				((CasellaScala) codaScala).setScala((Scala) scala);
				((CasellaScala) testaScala).setScala((Scala) scala);
			}
			else if(testaScala.getNumeroCasella()<codaScala.getNumeroCasella()) {
				scala = new Scala(codaScala, testaScala);

				((CasellaScala) codaScala).setScala((Scala) scala);
				((CasellaScala) testaScala).setScala((Scala) scala);
			}

			else {
				t.scale[rigaTesta][colonnaTesta] = false;
				
				tabellone[rigaTesta][colonnaTesta] = casellaFactory.createCella(
			    "Standard", tabellone[rigaTesta][colonnaTesta].getNumeroCasella());
				tabellone[rigaTesta][colonnaTesta].repaint();
				
				rigaTesta = -1;
				colonnaTesta = -1;
				
				t.scale[rigaCoda][colonnaCoda] = false;
				
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
