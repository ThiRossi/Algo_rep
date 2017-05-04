import java.util.ArrayList;


public class StrategieDynamique implements Strategie {
	
	private ArrayList<Corde> triangulation = new ArrayList<Corde>();
	
	public double triangulation(Polygone p) {
		//declaration des variables
		int i;
		int k = 1;
		
		//création d'une corde correspondant à l'un des cotes du polygone
		ArrayList<Sommet> l = p.getListSom();
		Corde c = new Corde(l.get(0),l.get(l.size()-1));
		int n=l.size();
		
		//cas où le polygone peut avoir une triangulisation
		if (n>3){
			
			//variable contenant la longueur de la triangulisation
			double longueurMin = c.calculLongueur(l.get(0), l.get(k)) + c.calculLongueur(l.get(k), l.get(n-1))+ c.calculLongueur(l.get(0), l.get(n-1));
			//System.out.println("longueurMin:"+longueurMin);
			
			//on parcourt tous les autres points
			for (i=2;i<n-1;i++) {
				//test si le triangle cree est plus petit que celui precedant
				if (c.calculLongueur(l.get(0), l.get(i)) + c.calculLongueur(l.get(i), l.get(n-1))+ c.calculLongueur(l.get(0), l.get(n-1))<longueurMin){
					longueurMin = c.calculLongueur(l.get(0), l.get(i)) + c.calculLongueur(l.get(i), l.get(n-1))+ c.calculLongueur(l.get(0), l.get(n-1));
					k=i;
				}
			}
			
			if (k==1){
				longueurMin = longueurMin - c.calculLongueur(l.get(0), l.get(k)) - c.calculLongueur(l.get(0), l.get(n-1));
				Corde ct = new Corde(l.get(k),l.get(n-1));
				triangulation.add(ct);
			}
			else if (k==n-2){
				longueurMin = longueurMin - c.calculLongueur(l.get(k), l.get(n-1)) - c.calculLongueur(l.get(0), l.get(n-1));
				Corde ct = new Corde(l.get(0),l.get(k));
				triangulation.add(ct);
			}
			else {
				longueurMin = longueurMin - c.calculLongueur(l.get(0), l.get(n-1));
				Corde ct = new Corde(l.get(k),l.get(n-1));
				Corde cd = new Corde(l.get(0),l.get(k));
				triangulation.add(ct);
				triangulation.add(cd);
			}
			//création de deux liste
			ArrayList<Sommet> l1 = new ArrayList<Sommet>();
			ArrayList<Sommet> l2 = new ArrayList<Sommet>();
			
			//affectation des deux liste afin de diviser le problème en deux
			for (i=0;i<n-1;i++){
				if (i<k){
					l1.add(l.get(i));
				}
				else if(i==k){
					l1.add(l.get(i));
					l2.add(l.get(i));
				}
				else {
					l2.add(l.get(i));
				}
			}
			
			//création des polygone
			Polygone p1 = new Polygone(l1);
			Polygone p2 = new Polygone(l2);
			
			//calcul de la longueur min par récurence
			return triangulation(p1) + triangulation(p2) + longueurMin;
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
