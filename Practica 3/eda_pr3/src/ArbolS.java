//DNI 20084606 Rubio Martinez, Ruben
import java.util.*;
import java.io.*;


public class ArbolS implements Arbol{
	
	private TreeMap<String, TreeSet<PLoc>> tm;
	
	public ArbolS(){
		tm = new TreeMap<String, TreeSet<PLoc>>();
	}

	//Lee arbol
	@Override
	public void leeArbol(String f) {
		BufferedReader lec = null;
		FileReader fe = null;
		
		if(f!=null){
			try {
				fe = new FileReader(f);
				lec = new BufferedReader(fe);
				String leida = lec.readLine();
				String[] elems,coordenadas;
				PLoc pl;
			
				if(leida!=null){
					while(leida!=null){
						/*
						 * elems[0] = Continente --- Puede estar vacio --- String
						 * elems[1] = Pais --- Puede estar vacio --- String
						 * elems[2] = Ciudad --- Puede estar vacio --- String
						 * elems[3] = Latitud --- NO puede estar vacio --- Coordenada
						 * elems[4] = Longitud --- NO puede estar vacio --- Coordenada
						 * 
						 * elems[3] y elems[4] se dividiran en otro array de 3 que sera
						 * 		coordenadas[0] = Grados --- int
						 * 		coordenadas[1] = minutos --- int
						 * 		coordenadas[2] = posicion --- char
						 */
						elems = leida.split("#");
						String continente,pais,ciudad;
						Coordenada latitud, longitud;

						//lectura e inicializacion de los strings
						if(elems[0].equalsIgnoreCase("")){
							continente = null;
						}else{
							continente = elems[0];
						}
						
						if(elems[1].equalsIgnoreCase("")){
							pais = null;
						}else{
							pais = elems[1];
						}
						
						if(elems[2].equalsIgnoreCase("")){
							ciudad = null;
						}else{
							ciudad = elems[2];
						}
						
						//lectura e inicializacion de las coordenadas
						coordenadas = elems[3].split(" ");

						latitud = new Coordenada(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]),coordenadas[2].charAt(0));
						
						coordenadas = elems[4].split(" ");
						
						longitud = new Coordenada(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]),coordenadas[2].charAt(0));
						
						//vamos a crear el PLoc
						pl = new PLoc(continente, pais, ciudad);
						
						try {
							pl.setLatitud(latitud);
							pl.setLongitud(longitud);
						} catch (CoordenadaExcepcion e) {
							System.out.println(e);

						}
						
						//Insertamos los nodos siguiendo el algoritmo determinado
						
						//Comprobamos si tenemos ya ese pais como Key en el TreeMap
						if(tm.containsKey(pl.getPais())){
							TreeSet<PLoc> tsAux = tm.get(pl.getPais());
							tsAux.add(pl);
							tm.put(pl.getPais(), tsAux);
						}else{
							TreeSet<PLoc> ts = new TreeSet<PLoc>();
							ts.add(pl);
							tm.put(pl.getPais(), ts);
						}
						
						
						//al final volvemos a leer la siguiente linea
						leida = lec.readLine();

					}//fin del ciclo while(leida!=null)
				}
			} catch (IOException e) {
				System.err.println("Error con " + f);
				System.exit(0);
			}
			
			try {
				if(fe!=null){
					fe.close();
				}
				if(lec!=null){
					lec.close();
				}
				
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
	}

	//Llamamos al metodo isEmpty que nos devuelve si esta vacio o no el arbol
	@Override
	public boolean esVacio() {
		boolean devolucion = false;
		if(tm.isEmpty()){
			devolucion = true;
		}
		return devolucion;
	}
	
	//Comprobamos si ya contamos con ese pais como clave y si no lo creamos y si la tenemos lo adjuntamos al TreeSet
	@Override
	public boolean inserta(PLoc p) {
		boolean devolucion = false;
		
		if(!tm.isEmpty()){
			if(p!=null){
				if(tm.containsKey(p.getPais())){
					TreeSet<PLoc> tsAux = tm.get(p.getPais());
					tsAux.add(p);
					tm.put(p.getPais(), tsAux);
					devolucion = true;
				}else{
					TreeSet<PLoc> ts = new TreeSet<PLoc>();
					ts.add(p);
					tm.put(p.getPais(), ts);
					devolucion = true;
				}
			}
		}
		
		
		return devolucion;
	}

	//Recorremos todos los PLocs del TreeMap en busca de uno que concuerde con el String pasado por parametro
	@Override
	public boolean ciudadEnArbol(String v) {
		boolean devolucion = false;
		
		if(!tm.isEmpty()){

			if(v!=null){
				for(Map.Entry<String, TreeSet<PLoc>> entry : tm.entrySet()){
					String key = entry.getKey();
					TreeSet<PLoc> ts = entry.getValue();
					Iterator<PLoc> it = ts.iterator();
					
					//Recorremos todo el TreeSet
					while(it.hasNext()){
						PLoc aux = it.next();
						
						if(aux.getCiudad().equalsIgnoreCase(v)){
							devolucion = true;
							break;
						}
					}
				}
			}
		}
		
		
		return devolucion;
	}
	
	
	//Recorremos todos los PLocs del TreeMap y devolvemos todas las ciudades del pais pasado por parametro
	@Override
	public TreeSet<String> getCiudades(PLoc p) {
		TreeSet<String> devolucion = new TreeSet<String>();
		
		if(p!=null){
			
			for(Map.Entry<String, TreeSet<PLoc>> entry : tm.entrySet()){
				TreeSet<PLoc> ts = entry.getValue();
				Iterator<PLoc> it = ts.iterator();
				
				while(it.hasNext()){
					PLoc aux = it.next();
					
					if(aux.getPais().equalsIgnoreCase(p.getPais())){
						devolucion.add(aux.getCiudad());
					}
				}
			}
			
			
			
		}
		
		return devolucion;
	}
	
	//Busca el PLoc mas alejado en funcion de la direccion pasada por parametro
	@Override
	public PLoc busquedaLejana(String s) {
		PLoc devolucion = null;
		
		/*		Lat	Long
		 * 
		 * NO = 90,-180
		 * SO = -90, -180
		 * NE = 90, 180
		 * SE = -90, 180
		 * 
		 */
		double[] limite = new double[2];
		double distanciaMinima = 100000;
		
		if(s!=null){
			if(s.equalsIgnoreCase("NO") || s.equalsIgnoreCase("SO") || 
		    	s.equalsIgnoreCase("NE") || s.equalsIgnoreCase("SE")){
				
				if(s.equalsIgnoreCase("NO")){
					limite[0] = 90;
					limite[1] = -180;
				}
				if(s.equalsIgnoreCase("SO")){
					limite[0] = -90;
					limite[1] = -180;				
				}
				if(s.equalsIgnoreCase("NE")){
					limite[0] = 90;
					limite[1] = 180;
				}
				if(s.equalsIgnoreCase("SE")){
					limite[0] = -90;
					limite[1] = 180;
				}
				
			}
		}
		
		
		if(s!=null){
			for(Map.Entry<String, TreeSet<PLoc>> entry : tm.entrySet()){
				TreeSet<PLoc> ts = entry.getValue();
				Iterator<PLoc> it = ts.iterator();
				
				while(it.hasNext()){
					
					double distancia = 0;
					
					//Calculamos la distancia
					distancia = Math.sqrt(Math.pow(limite[0]-it.next().getGps()[0], 2)+
							Math.pow(limite[1]-it.next().getGps()[1], 2));
					
					if(distancia<distanciaMinima){
						devolucion = it.next();
						distanciaMinima = distancia;
					}
					
				}
			}
		}
		
		return devolucion;
	}
	
	//Borramos el pais que concuerde con el pasado por parametro
	public void borraPais(String p){
		
		if(!tm.isEmpty()){
			if(p!=null){
				tm.remove(p);
			}
		}
		
		
	}
	
	//Recorremos todo el TreeMap y vamos almacenando todas las Keys (Paises)
	public Set<String> getPaises() {
		Set<String> devolucion = new TreeSet<String>();
		
		for(Map.Entry<String, TreeSet<PLoc>> entry : tm.entrySet()){
			devolucion.add(entry.getKey());
		}
		
		return devolucion;
	}
	
	//NEW devuelve el treeMap
	public TreeMap<String, TreeSet<PLoc>> getTM(){
		return tm;
	}

	
}
