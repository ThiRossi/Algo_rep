import java.util.ArrayList;

public class StrategieDynamique implements Strategie {

	private ArrayList<Corde> triangulation = new ArrayList<Corde>();

	public double triangulation(Polygone p) {
		// declaration des variables
		int i;
		int j;
		int k = 1;

		// création d'une corde correspondant à l'un des cotes du polygone
		ArrayList<Sommet> l = p.getListSom();
		Corde c = new Corde(l.get(0), l.get(l.size() - 1));
		int n = l.size();

		// cas où le polygone peut avoir une triangulisation
		if (n > 3) {

			// création de deux liste
			ArrayList<Sommet> l1 = new ArrayList<Sommet>();
			ArrayList<Sommet> l2 = new ArrayList<Sommet>();

			// affectation des deux liste afin de diviser le problème en deux
			for (i = 0; i < n; i++) {
				if (i < k) {
					l1.add(l.get(i));
				} else if (i == k) {
					l1.add(l.get(i));
					l2.add(l.get(i));
				} else {
					l2.add(l.get(i));
				}
			}

			// création des polygone
			Polygone p1 = new Polygone(l1);
			Polygone p2 = new Polygone(l2);

			// variable contenant la longueur de la triangulisation
			double longueurMin = c.calculLongueur(l.get(0), l.get(k))
					+ c.calculLongueur(l.get(k), l.get(n - 1))
					+ c.calculLongueur(l.get(0), l.get(n - 1))
					+ triangulation(p1) + triangulation(p2);
			// System.out.println("longueurMin:"+longueurMin);

			// on parcourt tous les autres points
			for (i = 2; i < n-1; i++) {

				l1 = new ArrayList<Sommet>();
				l2 = new ArrayList<Sommet>();
				
				for (j = 0; j < n; j++) {
					if (j < i) {
						l1.add(l.get(j));
					} else if (j == i) {
						l1.add(l.get(j));
						l2.add(l.get(j));
					} else {
						l2.add(l.get(j));
					}
				}

				p1 = new Polygone(l1);
				p2 = new Polygone(l2);

				// test si le triangle cree est plus petit que celui precedant
				if (c.calculLongueur(l.get(0), l.get(i))
						+ c.calculLongueur(l.get(i), l.get(n - 1))
						+ c.calculLongueur(l.get(0), l.get(n - 1))
						+ triangulation(p1) + triangulation(p2) < longueurMin) {
					longueurMin = c.calculLongueur(l.get(0), l.get(i))
							+ c.calculLongueur(l.get(i), l.get(n - 1))
							+ c.calculLongueur(l.get(0), l.get(n - 1))
							+ triangulation(p1) + triangulation(p2);
					k = i;
				}
			}

			// permet de retirer la longueur des cotés du polynome dans la
			// longueur de la trinagulation
			System.out.println("avant:" +longueurMin);
			if (k == 1) {
				longueurMin = longueurMin
						- c.calculLongueur(l.get(0), l.get(k))
						- c.calculLongueur(l.get(0), l.get(n - 1));
				Corde ct = new Corde(l.get(k), l.get(n - 1));
				System.out.println("1");
				//triangulation.add(ct);
			} else if (k == n - 2) {
				longueurMin = longueurMin
						- c.calculLongueur(l.get(k), l.get(n - 1))
						- c.calculLongueur(l.get(0), l.get(n - 1));
				Corde ct = new Corde(l.get(0), l.get(k));
				//triangulation.add(ct);
				System.out.println("2");
			} else {
				longueurMin = longueurMin
						- c.calculLongueur(l.get(0), l.get(n - 1));
				Corde ct = new Corde(l.get(k), l.get(n - 1));
				Corde cd = new Corde(l.get(0), l.get(k));
				//triangulation.add(ct);
				//triangulation.add(cd);
				System.out.println("3");
			}
			System.out.println("avant:" +longueurMin);

			return longueurMin;
		}

		else {
			return 0;
		}

	}

	@Override
	public String toString() {
		return "StrategieDynamique [triangulation=" + triangulation + "]";
	}

	public ArrayList<Corde> getTriangulation() {
		return triangulation;
	}

	public void setTriangulation(ArrayList<Corde> triangulation) {
		this.triangulation = triangulation;
	}
}
