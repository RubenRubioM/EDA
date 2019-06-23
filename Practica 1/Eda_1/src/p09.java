public class p09 {
	public static void main(String[] args) {
		if (args.length > 0) {
			Atlas mini = new Atlas();
			mini.leeAtlas(args[0]);

			PLoc localidad = new PLoc("EU", "Spain", "Sagra");

			try {
				localidad.setLatitud(new Coordenada(38, 49, 'N'));
				localidad.setLongitud(new Coordenada(10, 05, 'O'));
			} catch (CoordenadaExcepcion c) {
				System.out.println(c);
			}
			mini.muestraAtlasParcial(localidad, 5);
		}
	}
}
