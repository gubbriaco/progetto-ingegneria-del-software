package app.tabellone;

import app.tabellone.cella.CellaAstratta;
import app.tabellone.cella.concrete.Cella;

public class Tabellone {
	
	public final static int[] dimensioniFACILE = { 5, 5 }, dimensioniMEDIA = { 10, 10 },
			dimensioniDIFFICILE = { 20, 20 };

	
	public static CellaAstratta[][] getTabellone(String tipologiaTabellone){
		int[] dimensioni = new int[2];
		if(tipologiaTabellone.equalsIgnoreCase("TabelloneFACILE")) {
			dimensioni[0] = dimensioniFACILE[0];
			dimensioni[1] = dimensioniFACILE[1];
		}else if(tipologiaTabellone.equalsIgnoreCase("TabelloneMEDIA")) {
			dimensioni[0] = dimensioniMEDIA[0];
			dimensioni[1] = dimensioniMEDIA[1];
		}else if(tipologiaTabellone.equalsIgnoreCase("TabelloneDIFFICILE")) {
			dimensioni[0] = dimensioniDIFFICILE[0];
			dimensioni[1] = dimensioniDIFFICILE[1];
		}
		
		int ID = dimensioni[0] * dimensioni[1];
		CellaAstratta[][] tabellone = new CellaAstratta[dimensioni[0]][dimensioni[1]];

		boolean sensoOrario = true;

		for (int i = 0; i < tabellone.length; ++i) {
			for (int j = 0; j < tabellone[i].length; ++j) {
				if (sensoOrario) {
					tabellone[i][j] = new Cella(ID);
					ID = ID - 1;
				} else {
					tabellone[i][j] = new Cella(ID);
					ID = ID + 1;
				}
			}

			boolean tmp = !sensoOrario;
			sensoOrario = tmp;
			int tmpi = ID;
			if (!sensoOrario)
				tmpi = tmpi - dimensioniFACILE[1] + 1;
			else
				tmpi = tmpi - dimensioniFACILE[1] - 1;
			ID = tmpi;
		}

		return tabellone;
			
	}
	
	

}
