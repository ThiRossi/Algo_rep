import java.util.ArrayList;

public class Test {
	
	/**
	 * Test de toutes les méthodes de triangulation
	 * @param args
	 */

	public static void main(String args[]) {
		Strategie g;
		Strategie d;
		Strategie s;
		Sommet s1 = new Sommet(0, 0);
		Sommet s2 = new Sommet(0, 5);
		Sommet s3 = new Sommet(2, 6);
		Sommet s4 = new Sommet(3, 4);
		Sommet s5 = new Sommet(0, 2);
		Sommet s6 = new Sommet(0, 2);
		Sommet s7 = new Sommet(1, 2);
		Sommet s8 = new Sommet(9, 2);
		Sommet s9 = new Sommet(0, 6);
		Sommet s10 = new Sommet(8, 4);
		Sommet s11 = new Sommet(7, 2);
		Sommet s12 = new Sommet(6, 7);
		Sommet s13 = new Sommet(5, 3);
		Sommet s14 = new Sommet(6, 3);
		

		ArrayList<Sommet> l = new ArrayList<Sommet>();
		l.add(s1);
		l.add(s2);
		l.add(s3);
		l.add(s4);
		l.add(s5);
		l.add(s6);
		l.add(s7);
		l.add(s8);
		l.add(s9);
		l.add(s10);
		l.add(s11);
		l.add(s12);
		l.add(s13);
		l.add(s14);
		
		Corde c = new Corde(s1, s2);
		System.out.println("c:" + c.toString());

		Polygone p = new Polygone(l);
		g = new StrategieGloutone();
		d = new StrategieDynamique();
		
		double startTime = System.currentTimeMillis();
		s = new StrategieSuccessive();
		double endTime = System.currentTimeMillis();
		
		
		System.out.println("triangulation Successive :" + s.triangulation(p)
				+ "   " + s.toString());
		System.out.println("Temps d'execution : "+(endTime-startTime)+"minutes");
		System.out.println("triangulation Gloutonne :" + g.triangulation(p)
				+ "   " + g.toString());
		System.out.println("triangulation Dynamique :" + d.triangulation(p));
	}

}
