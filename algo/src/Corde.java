
public class Corde {
	
	private Sommet SommetDepard;
	private Sommet SommetArrive;
	double longueur;
	
	public Corde(Sommet sd, Sommet sa, double l){
		setSommetDepard(sd);
		setSommetArrive(sa);
		longueur = l;
	}
	
	public boolean egalCorde(Corde c1, Corde c2){
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
	
	public double calculLongueur(Sommet s1, Sommet s2) {
		return Math.sqrt(Math.pow((s2.abs - s1.abs), 2)+Math.pow((s2.aord - s1.ord), 2));
	}
	
	@Override
	public String toString() {
		return "corde [SommetDepard=" + SommetDepard + ", SommetArrive="
				+ SommetArrive + ", longueur=" + longueur + "]";
	}

	public double getLongueur() {
		return longueur;
	}

	public Sommet getSommetDepard() {
		return SommetDepard;
	}

	public void setSommetDepard(Sommet SommetDepard) {
		this.SommetDepard = SommetDepard;
	}

	public Sommet getSommetArrive() {
		return SommetArrive;
	}

	public void setSommetArrive(Sommet SommetArrive) {
		this.SommetArrive = SommetArrive;
	}
	
	
	
}
