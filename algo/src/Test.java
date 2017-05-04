import java.util.ArrayList;


public class Test {
	
	public static void main(String args[]) {
		Strategie s;
		Sommet s1 = new Sommet(0,0);
		Sommet s2 = new Sommet(0,5);
		Sommet s3 = new Sommet(2,6);
		Sommet s4 = new Sommet(3,4);
		
		ArrayList<Sommet> l = new ArrayList<Sommet>();
		l.add(s1);
		l.add(s2);
		l.add(s3);
		l.add(s4);
		Corde c = new Corde(s1,s2);
		System.out.println("c:"+c.toString());
		
		Polygone p = new Polygone(l);
		s = new StrategieDynamique();
		
		System.out.println("triangulation:"+s.triangulation(p)+"   "+s.toString());
	}
	
	
}
