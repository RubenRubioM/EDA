import java.util.ArrayList;


public class p06 {
	public static void main(String[] args) {
		ArrayList<PLoc> localidades = new ArrayList<>();
		localidades.add(new PLoc());
		localidades.add(new PLoc(null, null, null));
		localidades.add(new PLoc("", "", ""));
		localidades.add(new PLoc("EU", null, null));
		localidades.add(new PLoc(null, "Catalunya", null));
		localidades.add(new PLoc(null, null, "Murcia"));
		localidades.add(new PLoc("NA", "Texas", null));
		localidades.add(new PLoc(null, "Francia", "Paris"));
		localidades.add(new PLoc("AS", null, "Tokio"));
		localidades.add(new PLoc("SA", "Argentina", "Buenos Aires"));

		ArrayList<Coordenada> coordenadas = new ArrayList<>();
		coordenadas.add(new Coordenada(1, 0, 'N'));
		coordenadas.add(new Coordenada(1, 2, 'E'));
		coordenadas.add(new Coordenada(1, 3, 'S'));
		coordenadas.add(new Coordenada(1, 4, 'O'));
		coordenadas.add(new Coordenada(2, 5, 'N'));
		coordenadas.add(new Coordenada(2, 6, 'E'));
		coordenadas.add(new Coordenada(2, 7, 'S'));
		coordenadas.add(new Coordenada(2, 8, 'O'));
		coordenadas.add(new Coordenada(3, 9, 'N'));
		coordenadas.add(new Coordenada(3, 10, 'E'));
		coordenadas.add(new Coordenada(3, 11, 'S'));
		coordenadas.add(new Coordenada(3, 12, 'O'));
		coordenadas.add(new Coordenada(4, 52, 'N'));
		coordenadas.add(new Coordenada(4, 53, 'E'));
		coordenadas.add(new Coordenada(4, 54, 'S'));
		coordenadas.add(new Coordenada(4, 55, 'O'));
		coordenadas.add(new Coordenada(5, 56, 'N'));
		coordenadas.add(new Coordenada(5, 57, 'E'));
		coordenadas.add(new Coordenada(5, 58, 'S'));
		coordenadas.add(new Coordenada(5, 59, 'O'));

		int i = 0;
		for (PLoc localidad : localidades) {
			try {
				localidad.setLatitud(coordenadas.get(i));
				i++;
				localidad.setLongitud(coordenadas.get(i));
				i++;
				localidad.escribeInfoGrados();
				localidad.escribeInfoGps();
			} catch (CoordenadaExcepcion c) {
				System.out.println(c);
			}
		}
	}
}