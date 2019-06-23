import java.util.ArrayList;


public class p08 {
	public static void main(String[] args) {
		if (args.length >= 1) {
			Atlas mini = new Atlas();
			mini.leeAtlas(args[0]);

			ArrayList<PLoc> localidades = new ArrayList<>();
			localidades.add(new PLoc("EU", "Spain", "Sagra"));
			localidades.add(new PLoc("OC", "Samoa", "Alao"));
			localidades.add(new PLoc("NA", "EEUU", "Akulurak"));
			localidades.add(new PLoc("SA", "Falkland Islands", "Speedwell Island Settlement"));
			localidades.add(new PLoc("AF", "Togo", "Abakouande"));
			localidades.add(new PLoc("AS", "Saudi Arabia", "Abu Maragh"));

			ArrayList<Coordenada> coordenadas = new ArrayList<>();
			coordenadas.add(new Coordenada(38, 49, 'N'));
			coordenadas.add(new Coordenada(0, 05, 'O'));
			coordenadas.add(new Coordenada(14, 16, 'S'));
			coordenadas.add(new Coordenada(170, 33, 'O'));
			coordenadas.add(new Coordenada(62, 33, 'N'));
			coordenadas.add(new Coordenada(164, 33, 'O'));
			coordenadas.add(new Coordenada(52, 13, 'N'));
			coordenadas.add(new Coordenada(59, 41, 'O'));
			coordenadas.add(new Coordenada(9, 24, 'N'));
			coordenadas.add(new Coordenada(1, 15, 'E'));
			coordenadas.add(new Coordenada(21, 31, 'N'));
			coordenadas.add(new Coordenada(39, 47, 'E'));

			int i = 0;
			for (PLoc localidad : localidades) {
				try {
					localidad.setLatitud(coordenadas.get(i));
					i++;
					localidad.setLongitud(coordenadas.get(i));
					i++;
				} catch (CoordenadaExcepcion c) {
					System.out.println(c);
				}
				System.out.println(mini.consultaAtlas(localidad));
			}
			
			System.out.println(mini.consultaAtlas(new PLoc()));
		}
	}
}
