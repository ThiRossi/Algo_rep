
public class Sommet {

	private int abs;
	private int ord;
	
	public Sommet(int abscisse, int ordonnee){
		abscisse=abs;
		ordonnee=ord;
	}

	public int getAbs() {
		return abs;
	}

	public void setAbs(int abs) {
		this.abs = abs;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	
	public String toString() {
		return "Sommet [abs=" + abs + ", ord=" + ord + "]";
	}

}
