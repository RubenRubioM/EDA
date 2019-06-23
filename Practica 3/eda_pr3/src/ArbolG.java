//DNI 20084606 Rubio Martinez, Ruben
import java.util.*;
import java.io.*;


public class ArbolG implements Arbol{

	
	//clase privada de nodos
	private class NodoAG{
		private PLoc pd;
		private NodoAG no;
		private NodoAG so;
		private NodoAG ne;
		private NodoAG se;
		
		//Constructor
		public NodoAG(){
			pd = null;
			no = null;
			so = null;
			ne = null;
			se = null;
		}
		//Constructor con ploc
		public NodoAG(PLoc p){
			pd = p;
			no = null;
			so = null;
			ne = null;
			se = null;
		}
		
		public PLoc getPLoc(){
			return pd;
		}
		
		public NodoAG getNO(){
			return no;
		}
		
		public NodoAG getSO(){
			return so;
		}
		
		public NodoAG getNE(){
			return ne;
		}
		
		public NodoAG getSE(){
			return se;
		}
				
		
		public void setPLoc(PLoc p){
			pd = p;
		}
		
		public void setNO(NodoAG nodo){
			no=nodo;
		}
		
		public void setSO(NodoAG nodo){
			so=nodo;
		}
		
		public void setNE(NodoAG nodo){
			ne=nodo;
		}
		
		public void setSE(NodoAG nodo){
			se=nodo;
		}
	}//Fin de la clase nodo
	
	private NodoAG pr;
	
	public ArbolG(){
		pr=null;
	}
	
	
	//Deberia estar
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
						NodoAG nodo = new NodoAG(pl);
						if(pr==null){
							pr=nodo;
						}else{
							NodoAG aux = pr;
							
							while(aux!=null){
								
								if(nodo.getPLoc().getGps()[1]<aux.getPLoc().getGps()[1]){
									if(nodo.getPLoc().getGps()[0]>=aux.getPLoc().getGps()[0]){
										//no
										if(aux.getNO()==null){
											aux.setNO(nodo);
											break;
										}else{
											aux = aux.getNO();
										}
									}else{
										//so
										if(aux.getSO()==null){
											aux.setSO(nodo);
											break;
										}else{
											aux = aux.getSO();
										}
									}
								}else{
									if(nodo.getPLoc().getGps()[0]>=aux.getPLoc().getGps()[0]){
										//ne
										if(aux.getNE()==null){
											aux.setNE(nodo);
											break;
										}else{
											aux = aux.getNE();
										}
									}else{
										//se
										if(aux.getSE()==null){
											aux.setSE(nodo);
											break;
										}else{
											aux = aux.getSE();
										}
									}
								}
								
							}
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
	
	//comprobamos si tiene pr y devolvemos false o true
	@Override
	public boolean esVacio() {
		boolean devolucion = false;
		if(pr==null){
			devolucion=true;
		}
		return devolucion;
	}
	
	//Aplica el algoritmo de ir comprobando las coordenadas y en funcion de que cosa se inserta en un determinado sitio
	@Override	
	public boolean inserta(PLoc p) {
		boolean devolucion=false;

		//Insertamos los nodos siguiendo el algoritmo determinado
		NodoAG nodo = new NodoAG(p);
		if(pr==null){
			pr=nodo;
		}else{
			NodoAG aux = pr;
			
			while(aux!=null){
				
				if(nodo.getPLoc().getGps()[1]<aux.getPLoc().getGps()[1]){
					if(nodo.getPLoc().getGps()[0]>=aux.getPLoc().getGps()[0]){
						//no
						if(aux.getNO()==null){
							aux.setNO(nodo);
							devolucion=true;
							break;
						}else{
							aux = aux.getNO();
						}
					}else{
						//so
						if(aux.getSO()==null){
							aux.setSO(nodo);
							devolucion=true;
							break;
						}else{
							aux = aux.getSO();
						}
					}
				}else{
					if(nodo.getPLoc().getGps()[0]>=aux.getPLoc().getGps()[0]){
						//ne
						if(aux.getNE()==null){
							aux.setNE(nodo);
							devolucion=true;
							break;
						}else{
							aux = aux.getNE();
						}
					}else{
						//se
						if(aux.getSE()==null){
							aux.setSE(nodo);
							devolucion=true;
							break;
						}else{
							aux = aux.getSE();
						}
					}
				}
				
			}
		}
		
		return devolucion;
		
	}

	
	//Recorremos todo el arbol y vamos comparando la ciudad con el pasado por parametro
	@Override
	public boolean ciudadEnArbol(String v) {
		boolean devolucion = false;
		
		if(pr!=null){
			devolucion=busquedaCiudad(devolucion,v,pr);
		}
		
		
		return devolucion;
	}

	//NEW metodo auxiliar iterativo para recorrer el arbol e ir comparando con la String pasada por parametro
	public boolean busquedaCiudad(boolean devolucion, String v,NodoAG actual){
		
		if(actual.getPLoc().getCiudad().equalsIgnoreCase(v)){
			devolucion=true;
			return devolucion;
		}else{
			if(actual.getNO()!=null){
				devolucion = busquedaCiudad(devolucion,v,actual.getNO());
			}
			if(actual.getSO()!=null){
				devolucion = busquedaCiudad(devolucion,v,actual.getSO());
			}
			if(actual.getNE()!=null){
				devolucion = busquedaCiudad(devolucion,v,actual.getNE());
			}
			if(actual.getSE()!=null){
				devolucion = busquedaCiudad(devolucion,v,actual.getSE());
			}
					
				
			
		}
		return devolucion;
	}
	
	
	//Recorremos todo el arbol ingresando en un treeSet las ciudades del pais pasado por parametro
	@Override
	public TreeSet<String> getCiudades(PLoc p) {
		TreeSet<String> devolucion = null;
		if(p!=null){
			devolucion = new TreeSet<String>();
			String pais = p.getPais();
			devolucion = ayudantegetCiudades(devolucion,pais,pr);
		}
		
		
		
		return devolucion;
	}
	
	
	//NEW metodo auxiliar iterativo para recorrer el arbol e ir comparando con el pais pasado por parametro
	public TreeSet<String> ayudantegetCiudades(TreeSet<String> devolucion, String v, NodoAG actual) {
		
		if(actual.getPLoc().getPais().equalsIgnoreCase(v)){
			devolucion.add(actual.getPLoc().getCiudad());
			
		}
		if(actual.getNO()!=null){
			devolucion = ayudantegetCiudades(devolucion,v,actual.getNO());
		}
		if(actual.getSO()!=null){
			devolucion = ayudantegetCiudades(devolucion,v,actual.getSO());
		}
		if(actual.getNE()!=null){
			devolucion = ayudantegetCiudades(devolucion,v,actual.getNE());
		}
		if(actual.getSE()!=null){
			devolucion = ayudantegetCiudades(devolucion,v,actual.getSE());
		}
					

		return devolucion;
	}

	
	//Busca el PLoc mas alejado en funcion de la direccion pasada por parametro
	@Override
	public PLoc busquedaLejana(String s) {
		PLoc devolucion = new PLoc();
		
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
			//Comprobamos que direccion es y asignamos unas coordenadas
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
				
				devolucion = ayudanteBusquedaLejana(limite,pr,distanciaMinima,devolucion);
			}
		}
		return devolucion;
	}
	
	//NEW metodo iterativo para recorer el arbol e ir comparando la minima distancia cada vez
	public PLoc ayudanteBusquedaLejana(double[] limite,NodoAG actual,double distanciaMinima,PLoc devolucion){
		
		double distancia = 0;
		
		distancia = Math.sqrt(Math.pow(limite[0]-actual.getPLoc().getGps()[0], 2)+
				Math.pow(limite[1]-actual.getPLoc().getGps()[1], 2));
		
		if(distancia<distanciaMinima){
			devolucion = actual.getPLoc();
			distanciaMinima = distancia;
		}
		
		if(actual.getNO()!=null){
			devolucion = ayudanteBusquedaLejana(limite, actual.getNO(), distanciaMinima, devolucion);
		}
		if(actual.getSO()!=null){
			devolucion = ayudanteBusquedaLejana(limite, actual.getSO(), distanciaMinima, devolucion);
		}
		if(actual.getNE()!=null){
			devolucion = ayudanteBusquedaLejana(limite, actual.getNE(), distanciaMinima, devolucion);
		}
		if(actual.getSE()!=null){
			devolucion = ayudanteBusquedaLejana(limite, actual.getSE(), distanciaMinima, devolucion);
		}
		
		return devolucion;
		
		
	}
	
	//Devuelve el recorrido NO SO RAIZ NE SE del arbol
	public void recorridoInorden(){
		ArrayList<String> recorrido = new ArrayList<String>();
		if(pr!=null){
			ayudanteInOrder(pr,recorrido);
		}
		
		for (int i = 0; i < recorrido.size(); i++) {
			System.out.println(recorrido.get(i));
		}
	}
	
	//NEW metodo auxiliar iterativo para recorrer el arbol en el orden establecido en el enunciado
	public ArrayList<String> ayudanteInOrder(NodoAG actual,ArrayList<String> devolucion) {
		
		//Si tiene NO entra por ahi
		if(actual.getNO()!=null){
			ayudanteInOrder(actual.getNO(),devolucion);
		}
		//Si tiene SO entra por ahi
		if(actual.getSO()!=null){
			ayudanteInOrder(actual.getSO(),devolucion);
		}
		
		//Aqui ingresamos la raiz
		if(actual.getPLoc()!=null){
			devolucion.add(actual.getPLoc().getCiudad());
		}
		
		//Si tiene NE entra por ahi
		if(actual.getNE()!=null){
			ayudanteInOrder(actual.getNE(),devolucion);
		}
		//Si tiene SE entra por ahi
		if(actual.getSE()!=null){
			ayudanteInOrder(actual.getSE(),devolucion);
		}
		
		return devolucion;
		
	}


	//recorrido por niveles
	public void recorridoNiveles(){
		Queue<NodoAG> cola = new LinkedList<NodoAG>();
		
		cola.add(pr);
		
		//Vamos ingresando a una cola los hijos del nodo y repetimos esto mientras tengamos elementos en la cola
		while(!cola.isEmpty()){
			
			NodoAG nodo = cola.poll();
			System.out.println(nodo.getPLoc().getCiudad());
			
			if(nodo.getNO()!=null){
				cola.add(nodo.getNO());
			}
			
			if(nodo.getSO()!=null){
				cola.add(nodo.getSO());
			}
			
			if(nodo.getNE()!=null){
				cola.add(nodo.getNE());
			}
			
			if(nodo.getSE()!=null){
				cola.add(nodo.getSE());
			}
		}
		
	}
	
	public NodoAG getPR(){
		return pr;
	}

}
