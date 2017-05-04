import java.util.ArrayList;

/**
 * 
 *
 */

public class StrategieSuccessive implements Strategie {
	private Polygone poly;
	ArrayList<Corde> cordesCrées = new ArrayList<Corde>();

	public double triangulation(Polygone p) {
		// TODO Auto-generated method stub
		poly = p;
		return 0;
	}

	public boolean validCorde(Sommet i, Sommet j) {

		for (int k = 0; k < cordesCrées.size(); k++) {
			Sommet somDep = cordesCrées.get(k).getSommetDepard();
			Sommet somFin = cordesCrées.get(k).getSommetArrive();
			int indexSD = poly.getListSom().indexOf(somDep);
			int indexSF = poly.getListSom().indexOf(somFin);
			int indexI = poly.getListSom().indexOf(i);
			int indexJ = poly.getListSom().indexOf(j);

			if (somDep.equals(i) && somFin.equals(j) || somDep.equals(j)
					&& somFin.equals(i)) { // utiliser equalcorde
				return false;
			} else if (indexSD < indexSF) {
				testIndex(indexI, indexJ, indexSD, indexSF);
			}
			else{
				testIndex(indexI, indexJ, indexSF, indexSD);
			}
			
		}
		return true;

	}
	
	
	public boolean testIndex(int i, int j, int d, int f){
		if (i < d && (j < d || j > f)
				|| j < d && (i < d || i > f)
				|| (i>d && i<f) && ((j<d && j<f)
				|| i>f && j>f) ) {
			return true;
		}
		else 
			return false;
	}

}
