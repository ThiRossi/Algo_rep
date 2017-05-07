import java.util.ArrayList;

public class StrategieDynamique implements Strategie {

	private ArrayList<Corde> triangulation = new ArrayList<Corde>();


	public double triangulation(Polygone p) {

		int i;
		int j;

		int k = 1;

		ArrayList<Sommet> l = p.getListSom();
		Corde c = new Corde(l.get(0), l.get(l.size() - 1));

		int n = l.size();

		if (n > 3) {

			ArrayList<Sommet> l1 = new ArrayList<Sommet>();
			ArrayList<Sommet> l2 = new ArrayList<Sommet>();

			l1.add(l.get(0));
			l1.add(l.get(k));
			l1.add(l.get(n - 1));

			for (i = 1; i < n; i++) {
				l2.add(l.get(i));
			}

			Polygone p1 = new Polygone(l1);
			Polygone p2 = new Polygone(l2);

			double longueurMin = c.calculLongueur(l.get(k), l.get(n - 1))
					+ triangulation(p1) + triangulation(p2);

			for (i = 2; i < n - 2; i++) {

				l1 = new ArrayList<Sommet>();
				l2 = new ArrayList<Sommet>();

				if (i == n - 2) {
					l2.add(l.get(0));
					l2.add(l.get(k));
					l2.add(l.get(n - 1));

					for (j = 0; j < n - 1; j++) {
						l1.add(l.get(j));
					}

				} else {
					
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
				}

				p1 = new Polygone(l1);
				p2 = new Polygone(l2);


				if (c.calculLongueur(l.get(0), l.get(i))
						+ c.calculLongueur(l.get(i), l.get(n - 1))
						+ triangulation(p1) + triangulation(p2) < longueurMin) {
					
					longueurMin = c.calculLongueur(l.get(0), l.get(i))
							+ c.calculLongueur(l.get(i), l.get(n - 1))
							+ triangulation(p1) + triangulation(p2);
					k = i;
				}
			}

			l1 = new ArrayList<Sommet>();
			l2 = new ArrayList<Sommet>();

			l2.add(l.get(0));
			l2.add(l.get(k));
			l2.add(l.get(n - 1));

			for (j = 0; j < n - 1; j++) {
				l1.add(l.get(j));
			}

			p1 = new Polygone(l1);
			p2 = new Polygone(l2);
			
			if (c.calculLongueur(l.get(0), l.get(n - 2)) + triangulation(p1)
					+ triangulation(p2) < longueurMin) {
				
				longueurMin = c.calculLongueur(l.get(0), l.get(n - 2))
						+ triangulation(p1) + triangulation(p2);
				k = n - 2;
			}

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
