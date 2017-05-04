
public class Corde {
	
	private Sommet sommetDepard;
	private Sommet sommetArrive;
	double longueur;
	
	public Corde(Sommet sd, Sommet sa){
		setSommetDepard(sd);
		setSommetArrive(sa);
		longueur=Math.sqrt(Math.pow((sa.getAbs() - sd.getAbs()), 2)+Math.pow((sa.getOrd() - sd.getOrd()), 2));
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
		return Math.sqrt(Math.pow((s2.getAbs() - s1.getAbs()), 2)+Math.pow((s2.getOrd() - s1.getOrd()), 2));
	}
	
	@Override
	public String toString() {
		return "corde [SommetDepard=" + sommetDepard + ", SommetArrive="
				+ sommetArrive + ", longueur=" + longueur + "]";
	}

	public double getLongueur() {
		return longueur;
	}

	public Sommet getSommetDepard() {
		return sommetDepard;
	}

	public void setSommetDepard(Sommet SommetDepard) {
		this.sommetDepard = SommetDepard;
	}

	public Sommet getSommetArrive() {
		return sommetArrive;
	}

	public void setSommetArrive(Sommet SommetArrive) {
		this.sommetArrive = SommetArrive;
	}
	
	
	
}
