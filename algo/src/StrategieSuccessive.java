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
	 *  Premet de tester si une corde est traçable 
	 *  
	 * @param sI
	 * @param sJ
	 * @param cordesCrées
	 * 
	 * @return un boolean
	 */
	public boolean valideCorde(Sommet sI, Sommet sJ, ArrayList<Corde> cordesCrées) {
		ArrayList<Sommet> p=poly.getListSom();
		int nbSommets = p.size();
		int indexI = p.indexOf(sI);
		int indexJ = p.indexOf(sJ);

		int IndexSD, IndexSA;
		Corde cCree = new Corde(sI, sJ);

		boolean possible = true;

		for (Corde cTeste : cordesCrées) {

			if (cCree.equals(cTeste)){
				possible = false;
			}
			else {
				IndexSD = p.indexOf(cTeste.getSommetDepard());
				IndexSA = p.indexOf(cTeste.getSommetArrive());	
				
				if((((indexI >= (IndexSD + 1) % nbSommets) && (indexI <= (IndexSA -1) % nbSommets)) &&
					    ((indexJ >= (IndexSA + 1) % nbSommets) && (indexJ<= (IndexSD -1) % nbSommets)))
					    ||
					(((indexJ >= (IndexSD + 1) % nbSommets) && (indexJ <= (IndexSA -1) % nbSommets)) &&
					    ((indexI >= (IndexSA + 1) % nbSommets) && (indexI<= (IndexSD -1) % nbSommets)))
					){
				possible = false;							
				}
			}
		}
		return possible;
	}

	
	/**
	 *  Premet de tester si une corde est traçable 
	 *  
	 * @param c
	 * @param cordesCrées
	 * 
	 * @return un boolean
	 */
	public boolean valideCorde(Corde c, ArrayList<Corde> cordesCrées) {
		Sommet sI = c.getSommetDepard();
		Sommet sJ = c.getSommetArrive();
		return valideCorde(sI, sJ, cordesCrées);
	}

	/**
	 * permet de calculer toutes les cordes possibles
	 * 
	 * @param p
	 */
	public void calculCordes(Polygone p) {
		for (int k = 0; k < p.getListSom().size(); k++) {
			Sommet sD = p.getListSom().get(k);
			if (k == 0) {
				for (int j = k + 2; j < (p.getListSom().size() - 1); j++) {
					Sommet sA = p.getListSom().get(j);
					Corde c = new Corde(sD, sA);
					cordesPossibles.add(c);
				}
			} else {
				for (int j = k + 2; j < (p.getListSom().size()); j++) {
					Sommet sA = p.getListSom().get(j);
					Corde c = new Corde(sD, sA);
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
						if (valideCorde(cordesPossibles.get(p), t)) {
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
