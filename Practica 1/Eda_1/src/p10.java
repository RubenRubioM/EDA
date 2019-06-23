public class p10 {
	public static void main(String[] args) {
		if (args.length > 0) {
			Atlas mini = new Atlas();
			mini.leeAtlas(args[0]);

			PLoc localidad = new PLoc("EU", "Spain", "Sagra");
			PLoc localidad2 = new PLoc("OC", "Samoa", "Alao");
			
			try {
				localidad.setLatitud(new Coordenada(38, 49, 'N'));
				localidad.setLongitud(new Coordenada(10, 05, 'O'));
			} catch (CoordenadaExcepcion c) {
				System.out.println(c);
			}
			
			System.out.println(mini.setLocalidad(localidad));
			System.out.println(mini.setLocalidad(localidad2));
			
			System.out.println(mini.consultaAtlas(localidad));
			System.out.println(mini.consultaAtlas(localidad2));
			
			mini.muestraAtlasParcial(localidad, 5);
			mini.muestraAtlasParcial(localidad2, 5);
		}
	}
}
