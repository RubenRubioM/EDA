import java.util.*;
public class compilador {

	public static void main(String[] args) {
		Coordenada c1 = new Coordenada(20,40,'N');
		Coordenada c2 = new Coordenada(20,43,'O');
		Coordenada c3 = new Coordenada(31,54,'N');
		Coordenada c4 = new Coordenada(11,52,'O');
		PLoc p1 = new PLoc("America", null, "Buenos Aires");
		PLoc p2 = new PLoc("Europa","Espana","Madrid");
		PLoc p3 = new PLoc("Africa","Kenya","Abju");
		PLoc p4 = new PLoc("Africa","Marruecos","Kiuys");
		ArrayList<PLoc> array = new ArrayList<PLoc>();
		ListaG lista = new ListaG();
		VectorG ve = new VectorG();
		
		
		
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
		
		String a = "ASD";
		
		System.out.println(a.length());

	}
}


