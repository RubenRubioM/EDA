import java.io.*;
import java.text.*;
import java.util.*;

public class compilador {

	public static void main(String[] args) {
		Coordenada latitud = new Coordenada(69,41,'N');
		Coordenada longitud = new Coordenada(70,11,'O');
		Coordenada latitud2 = new Coordenada(34,47,'S');
		Coordenada longitud2 = new Coordenada(68,11,'O');
		PLoc p1 = new PLoc ("America",null,"Buenos Aires");
		PLoc p2 = new PLoc ("Europa","Spain","Alicante");
		Atlas a = new Atlas();
		double db = 7.23123;
		PLoc[] representantes = new PLoc[1];
		PLoc[] plocs = new PLoc[1];
		
		
		try {
			p1.setLatitud(latitud);
			p1.setLongitud(longitud);
			p2.setLatitud(latitud2);
			p2.setLongitud(longitud2);
		} catch (CoordenadaExcepcion e) {
			// TODO: handle exception
		}
		
		
		
		
	}
	

}