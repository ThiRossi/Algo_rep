import java.util.ArrayList;

public class StrategieGloutone implements Strategie {

	private ArrayList<Corde> triangulation = new ArrayList<Corde>();

	public double triangulation(Polygone p) {
		// declaration des variables
		int i;
		int k = 0;

		// création d'une corde correspondant à l'un des cotes du polygone
		ArrayList<Sommet> l = p.getListSom();
		Corde c = new Corde(l.get(0), l.get(l.size() - 1));
		int n = l.size();

		if (n > 3) {

			// variable contenant la longueur de la triangulisation
			double longueurMin = c.calculLongueur(l.get(n - 1), l.get(1));

			// on parcourt tous les autres points
			for (i = 1; i < n; i++) {
				if (i != (n - 1)
						&& c.calculLongueur(l.get(i - 1), l.get(i + 1)) < longueurMin) {
					longueurMin = c.calculLongueur(l.get(i - 1), l.get(i + 1));
					k = i;
				}

				if (i == (n - 1)
						&& c.calculLongueur(l.get(i - 1), l.get(0)) < longueurMin) {
					longueurMin = c.calculLongueur(l.get(i - 1), l.get(0));
					k = i;
				}
			}
			if (k == n - 1) {
				Corde ct = new Corde(l.get(k - 1), l.get(0));
				triangulation.add(ct);
			}

			else if (k == 0) {
				Corde ct = new Corde(l.get(n - 1), l.get(k + 1));
				triangulation.add(ct);

			}

			else {
				Corde ct = new Corde(l.get(k - 1), l.get(k + 1));
				triangulation.add(ct);
			}

			ArrayList<Sommet> l1 = new ArrayList<Sommet>(l);
			l1.remove(k);
			Polygone p1 = new Polygone(l1);

			return longueurMin + triangulation(p1);

		} else {
			return 0;
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
		return "StrategieCoutone [triangulation=" + triangulation + "]";
	}

}
