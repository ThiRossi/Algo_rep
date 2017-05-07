import java.util.ArrayList;





/**
 * Strategie Successive
 *
 */
public class StrategieSuccessive implements Strategie {
	private Polygone poly;
	private ArrayList<Corde> cordesPossibles = new ArrayList<Corde>();
	private ArrayList<Corde> triangulation = new ArrayList<Corde>();
	private double lTriangulation;
	
	/**
	 * Cette fonction permet de calculer la triangulation minimale grâce à l'algorithme Successif
	 *
	 * @param p
	 * 
	 * @return la longueur de la triangulation 
	 */
	public double triangulation(Polygone p) {
		poly = p;
		ArrayList<Corde> cCrées = new ArrayList<Corde>();
		calculCordes(p);
		noeud(cCrées, 0, 0.0);
		return lTriangulation;
	}
	
	/**
	 *  Premet de tester si une corde est trassable 
	 *  
	 * @param si
	 * @param sj
	 * @param cordesCrées
	 * 
	 * @return un boolean
	 */
	public boolean validCorde(Sommet si, Sommet sj, ArrayList<Corde> cordesCrées) {

		int indexI = poly.getListSom().indexOf(si);
		int indexJ = poly.getListSom().indexOf(sj);

		if (!cordesCrées.isEmpty()) {

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
				} else {
					testIndex(indexI, indexJ, indexSF, indexSD);
				}

			}
			return true;
		} else {
			return true;
		}
	}
	
	/**
	 *  Premet de tester si une corde est trassable 
	 *  
	 * @param c
	 * @param cordesCrées
	 * 
	 * @return un boolean
	 */
	public boolean validCorde(Corde c, ArrayList<Corde> cordesCrées) {
		Sommet si = c.getSommetDepard();
		Sommet sj = c.getSommetArrive();
		return validCorde(si, sj, cordesCrées);
	}
	
	/**
	 * Premet de tester si les index des sommets sont bon
	 * 
	 * @param i
	 * @param j
	 * @param d
	 * @param f
	 * 
	 * @return un boolean
	 */
	public boolean testIndex(int i, int j, int d, int f) {
		if (i < d && (j < d || j > f) || j < d && (i < d || i > f)
				|| (i > d && i < f) && ((j < d && j < f) || i > f && j > f)) {
			return true;
		} else
			return false;
	}

	/**
	 * permet de calculer toutes les cordes possibles
	 * 
	 * @param p
	 */
	public void calculCordes(Polygone p) {
		for (int k = 0; k < p.getListSom().size(); k++) {
			p.getListSom().get(k);
			if (k == 0) {
				for (int j = k + 2; j < (p.getListSom().size() - 1); j++) {
					p.getListSom().get(j);
					Corde c = new Corde(p.getListSom().get(k), p.getListSom()
							.get(j));
					cordesPossibles.add(c);
				}
			} else {
				for (int j = k + 2; j < (p.getListSom().size()); j++) {
					p.getListSom().get(j);
					Corde c = new Corde(p.getListSom().get(k), p.getListSom()
							.get(j));
					cordesPossibles.add(c);
				}
			}
		}
	}
	
	/**
	 * Construit les abres de triangulation et enregistre la meilleure
	 * 
	 * @param t
	 * @param p
	 * @param l
	 */
	public void noeud(ArrayList<Corde> t, int p, double l) {
		ArrayList<Corde> t1 = new ArrayList<Corde>(t);
		ArrayList<Corde> t2 = new ArrayList<Corde>(t);
		int tailleP = poly.getListSom().size(); // somme des sommets
		int tailleC = cordesPossibles.size(); // nombre corde possible
		
		if (p < tailleC) {
			if (t.isEmpty()) { // aucune cordes de tracées
				noeud(t1, p + 1, l); // corde p pas prise
				t2.add(cordesPossibles.get(p));
				l += cordesPossibles.get(p).longueur;
				noeud(t2, p + 1, l); // corde p prise
			} else {
				// nombre de corde max
				if (t.size() < (tailleP - 3)) { 
					// test si ça peut être la meilleure
					if (triangulation.isEmpty() || l < lTriangulation) { 
						noeud(t1, p + 1, l); // corde p pas prise
						//test si elle peut être tracée
						if (validCorde(cordesPossibles.get(p), t)) {
							t2.add(cordesPossibles.get(p));
							l += cordesPossibles.get(p).longueur;
							noeud(t2, p + 1, l); // corde p prise
						}
					}
				} else if (t.size() == (tailleP - 3)
						&& (triangulation.isEmpty() || l < lTriangulation)) {
					triangulation = new ArrayList<Corde>(t);
					lTriangulation = l;
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


	public String toString() {
		return "StrategieSuccessive [triangulation=" + triangulation + "]";
	}

}
