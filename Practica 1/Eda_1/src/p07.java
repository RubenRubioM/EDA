public class p07 {
	public static void main(String[] args) {
		PLoc localidad = new PLoc();
		PLoc localidad2 = new PLoc();
		if (localidad.getGps() == null) {
			System.out.println("No hay Coordenadas");
		}

		Coordenada latitud = new Coordenada(9, 3, 'N');
		Coordenada longitud = new Coordenada(14, 4, 'E');
		Coordenada latitud2 = new Coordenada(9, 3, 'S');
		Coordenada longitud2 = new Coordenada(14, 4, 'O');

		try {
			localidad.setLatitud(latitud);
			localidad.setLongitud(longitud);
			localidad2.setLatitud(latitud2);
			localidad2.setLongitud(longitud2);
		} catch (CoordenadaExcepcion c) {
			System.out.println(c);
		}

		System.out.println(localidad.getGps()[0] + " - " + localidad.getGps()[1]);
		System.out.println(localidad2.getGps()[0] + " - " + localidad2.getGps()[1]);
	}
}
