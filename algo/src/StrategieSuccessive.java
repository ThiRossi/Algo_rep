import java.util.ArrayList;

/**
 * jejejejejeje
 *
 */

public class StrategieSuccessive implements Strategie {
	private Polygone poly;
	ArrayList<Corde> cordesPossibles = new ArrayList<Corde>();
	ArrayList<Corde> triangulation;
	double lTriangulation;
	
	public double triangulation(Polygone p) {
		poly = p;
		ArrayList<Corde> cCrées=new ArrayList<Corde>();
		calculCordes(p);
		noeud(cCrées, 0, 0);
		return lTriangulation;
	}

	public boolean validCorde(Sommet si, Sommet sj, ArrayList<Corde> cordesCrées) {
		
		int indexI = poly.getListSom().indexOf(si);
		int indexJ = poly.getListSom().indexOf(sj);
		
		for (int k = 0; k < cordesCrées.size(); k++) {
			
			Sommet somDep = cordesCrées.get(k).getSommetDepard();
			Sommet somFin = cordesCrées.get(k).getSommetArrive();
			int indexSD = poly.getListSom().indexOf(somDep);
			int indexSF = poly.getListSom().indexOf(somFin);

			if (somDep.equals(si) && somFin.equals(sj) || somDep.equals(sj)
					&& somFin.equals(si)) { 
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
	
	public boolean validCorde(Corde c, ArrayList<Corde> cordesCrées){
		Sommet si = c.getSommetDepard();
		Sommet sj = c.getSommetArrive();
		return validCorde(si, sj, cordesCrées);

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
	
	public void calculCordes(Polygone p){
		for (int k = 0; k < p.getListSom().size(); k++) {
			p.getListSom().get(k);
			for (int j = k+2; j < (p.getListSom().size()-1); j++) {
				p.getListSom().get(j);
				Corde c = new Corde(p.getListSom().get(k), p.getListSom().get(j));
				cordesPossibles.add(c);
			}
		}
	}
	
	public void noeud(ArrayList<Corde> t, int p, double l){
		
		if (t!=null){
			int tailleC = cordesPossibles.size();
			int tailleP =poly.getListSom().size();
			if (p<tailleC && (t.size()+tailleC-p)<(tailleP-3)){
				if(triangulation != null && l<lTriangulation || triangulation==null){
					if(t.size()<(tailleP-3)){
						noeud(t, p-1, l);
						if (validCorde(cordesPossibles.get(p), t)){
							t.add(cordesPossibles.get(p));
							l+=cordesPossibles.get(p).longueur;
							noeud(t, p+1, l);
						}
					}
					else{
						triangulation=t;
						lTriangulation=l;
					}
				}
			}
		}
		
	}

	public ArrayList<Corde> getTriangulation() {
		return triangulation;
	}

	public void setTriangulation(ArrayList<Corde> triangulation) {
		this.triangulation = triangulation;
	}

	@Override
	public String toString() {
		return "StrategieSuccessive [triangulation=" + triangulation + "]";
	}
	


}
