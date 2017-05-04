import java.util.ArrayList;


public class Polygone {
	private ArrayList<Sommet> listSom;
	
	public Polygone(ArrayList<Sommet> listSommets){
		listSommets=listSom;
	}
	public void setListSom(ArrayList<Sommet> listSom) {
		this.listSom = listSom;
	}
	public ArrayList<Sommet> getListSom() {
		return listSom;
	}

	public String toString() {
		return "Polygone [listSom=" + listSom + "]";
	}
	
}
