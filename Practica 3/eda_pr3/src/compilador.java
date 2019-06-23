import java.util.*;

public class compilador {

	public static void main(String[] args) {
		Coordenada c1 = new Coordenada(20,40,'N');
		Coordenada c2 = new Coordenada(20,43,'O');
		Coordenada c3 = new Coordenada(31,54,'N');
		Coordenada c4 = new Coordenada(11,52,'O');
		
		PLoc p1 = new PLoc("America", "USA","Chicago");
		PLoc p2 = new PLoc("Europa", "Spain","Alicante");
		PLoc p3 = new PLoc("Africa", "Kenya","Abju");
		PLoc p4 = new PLoc("America", "Mexico","Guadalupe");
		
		
		try {
			p1.setLatitud(c1);
			p1.setLongitud(c2);
			p2.setLatitud(c1);
			p2.setLongitud(c4);
			p3.setLatitud(c3);
			p3.setLongitud(c4);
			p4.setLatitud(c1);
			p4.setLongitud(c2);
			
		} catch (CoordenadaExcepcion e) {
			System.out.println(e.getMessage());
		}
		TreeSet<PLoc> ts = new TreeSet<PLoc>();
		TreeSet<PLoc> ts2 = new TreeSet<PLoc>();
		TreeMap<String, TreeSet<PLoc>> tm = new TreeMap<String, TreeSet<PLoc>>();
		ts.add(p1);
		ts.add(p2);
		ts.add(p3);
		ts.add(p4);
		
		tm.put("Francia", ts);
		tm.put("Espana", ts2);
		
		Iterator<PLoc> it = ts.iterator();
		while(it.hasNext()){
			System.out.println(it.next().getCiudad());
		}
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("a");
		lista.add("b");
		lista.add("f");
		lista.add("c");
		
		System.out.println(lista);
		Collections.sort(lista);
		System.out.println(lista);
		

	}

}
