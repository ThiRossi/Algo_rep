import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

public class Test_rossi {

	public static void main(String args[]) {
		Strategie g;
		Strategie d;
		Strategie s;
		Sommet s1 = new Sommet(0, 0);
		Sommet s2 = new Sommet(0, 5);
		Sommet s3 = new Sommet(2, 6);
		Sommet s4 = new Sommet(3, 4);
		Sommet s5 = new Sommet(0, 2);

		ArrayList<Sommet> l = new ArrayList<Sommet>();
		l.add(s1);
		l.add(s2);
		l.add(s3);
		l.add(s4);
		l.add(s5);
		Corde c = new Corde(s1, s3);
		System.out.println("c:" + c.toString());

		Polygone p = new Polygone(l);
		s = new StrategieSuccessive();
		System.out.println("triangulation successive :" + s.triangulation(p)+ "   " + s.toString());
		g = new StrategieGloutone();
//		d = new StrategieDynamique();
//
		System.out.println("triangulation Gloutonne :" + g.triangulation(p)
				+ "   " + g.toString());
//		System.out.println("triangulation Dynamique :" + d.triangulation(p)
//				+ "   " + d.toString());
	}

}
