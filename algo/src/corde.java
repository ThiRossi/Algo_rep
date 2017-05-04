
public class corde {
	
	private sommet sommetDepard;
	private sommet sommetArrive;
	double longueur;
	
	public corde(sommet sd, sommet sa, double l){
		setSommetDepard(sd);
		setSommetArrive(sa);
		longueur = l;
	}
	
	public boolean egalCorde(corde c1, corde c2){
		if (c1.getSommetDepard() == c2.getSommetArrive() && c1.getSommetArrive() == c2.getSommetDepard()){
			return true;
		}
		else if (c1.getSommetDepard() == c2.getSommetDepard() && c1.getSommetArrive() == c2.getSommetArrive()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double calculLongueur(sommet s1, sommet s2) {
		return Math.sqrt(Math.pow((s2.abs - s1.abs), 2)+Math.pow((s2.aord - s1.ord), 2));
	}
	
	@Override
	public String toString() {
		return "corde [sommetDepard=" + sommetDepard + ", sommetArrive="
				+ sommetArrive + ", longueur=" + longueur + "]";
	}

	public double getLongueur() {
		return longueur;
	}

	public sommet getSommetDepard() {
		return sommetDepard;
	}

	public void setSommetDepard(sommet sommetDepard) {
		this.sommetDepard = sommetDepard;
	}

	public sommet getSommetArrive() {
		return sommetArrive;
	}

	public void setSommetArrive(sommet sommetArrive) {
		this.sommetArrive = sommetArrive;
	}
	
	
	
}
