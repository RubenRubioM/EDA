//DNI 20084606 Rubio Martinez, Ruben
import java.io.*;
import java.util.*;



//TODO acuerdate de comprobar todos los nullpointers y tal
public class VectorG implements Lista{

	private ArrayList<PLoc> vector;
	
	public VectorG(){
		vector = new ArrayList<PLoc>();
	}
	
	public void escribeVectorG(){
		String cadena="";
		
		for (int i = 0; i < vector.size(); i++) {
			if(vector.get(i)!=null){
				System.out.print("posic "+i+": ");
				vector.get(i).escribeInfoGps();
				
			}
			
		}
	}
	
	
	//El mecanismo es el mismo que seguimos en la practica anterior con la excepcion de que ahora los introduciremos en un arraylist
	public void leeLista(String f) {
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
						
						//anadimos el PLoc al comienzo del ArrayList
						vector.add(0, pl);
						
						
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


	//devuelve si el arraylist esta vacio
	public boolean esVacia() {
		boolean devolucion=false;
		devolucion=vector.isEmpty();
		return devolucion;
	}

	//insertamos el valor al comienzo del arraylist
	public void insertaCabeza(PLoc p) {
		vector.add(0,p);
		
	}

	//insertamos el valor al final del arraylist
	public void insertaCola(PLoc v) {
		vector.add(vector.size(),v);
		
	}

	//insertamos muchos valores al final del arraylist
	public void insertaArrayPLoc(PLoc[] v) {
		if(v!=null && v.length>0){
			for (int i = 0; i < v.length; i++) {
				vector.add(vector.size(), v[i]);
			}
		}
		
		
	}

	//Eliminamos el primer elemento del arraylist
	public boolean borraCabeza() {
		boolean devolucion=false;
		if(vector.get(0)!=null){
			vector.remove(0);
			devolucion=true;
		}
		return devolucion;
	}

	//eliminamos el ultimo valor del arraylist
	public boolean borraCola() {
		boolean devolucion=false;
		if(vector.size()>0){
			vector.remove(vector.size()-1);
			devolucion=true;
		}
		return devolucion;
	}

	//recorremos el arraylist en busca de la ciudad pasada por parametro y devolvemos su posicion
	//En caso de no encontrarla se lanza la excepcion
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion {
		int devolucion=100000;
		
		if(v!=null){
			for (int i = 0; i < vector.size(); i++) {
				if(vector.get(i)!=null){
					if(vector.get(i).getCiudad().equalsIgnoreCase(v)){
						devolucion=i;
						break;
					}
				}
				
			}
		}
		
		
		if(devolucion>100000-1){
			throw new CiudadNoEncontradaExcepcion(v);
		}
		
		return devolucion;
	}

	//recorremos el arraylist y eliminamos la ciudad pasada por parametro
	public boolean borraCiudad(String v) {
		boolean devolucion=false;
		
		if(v!=null){
			for (int i = 0; i < vector.size(); i++) {
				if(vector.get(i)!=null){
					if(vector.get(i).getCiudad().equalsIgnoreCase(v)){
						vector.remove(i);
						devolucion=true;
						break;
					}
				}
			}
		}
		
		
		return devolucion;
	}

	//recorremos el arraylist con un ciclo for en busca de que coincida con el string pasado por parametro y eliminamos todos los plocs con ese pais
	public boolean borraPais(String s) {
		boolean devolucion=false;
		
		if(s!=null){
			for (int i = 0; i < vector.size(); i++) {
				if(vector.get(i)!=null){
					if(vector.get(i).getPais().equalsIgnoreCase(s)){
						vector.remove(i);
						devolucion=true;
						
					}
				}
			}
		}
		
		
		return devolucion;
	}

	//lanza una excepcion si la posicion pasada por parametro no pertenece al arraylist
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException {
		PLoc devolucion = null;
		
		
		
		if(pos>=vector.size() || pos<0){
			throw new IndexOutOfBoundsException(Integer.toString(pos));
		}else if(pos>=0 && pos<vector.size()){
			devolucion=vector.get(pos);
		}
		
		return devolucion;
	}
	
	//recorreremos con un ciclo for todo el arraylist en busca de los PLocs que su pais concida con el pasa por parametro
	//Nos ayudaremos de un array que iremos redimensionando para guardar los PLocs a devolver
	public PLoc[] Pais(String p){
		PLoc[] devolucion=new PLoc[1];
		int ingresados=0;
		
		if(vector.isEmpty()==false && p!=null){
			for (int i = 0; i < vector.size(); i++) {
				if(vector.get(i).getPais()!=null && vector.get(i).getPais().equalsIgnoreCase(p)){
					
					if(ingresados<devolucion.length){
						devolucion[ingresados]=vector.get(i);
						ingresados++;
					}else{
						int k = 0;
						PLoc[] tmp=new PLoc[devolucion.length+1];
						for (int j = 0; j < devolucion.length; j++) {
							tmp[j]=devolucion[j];
							k++;
						}
						tmp[k]=vector.get(i);
						devolucion=tmp;
						ingresados++;
					}
					
					
				}
			}
		}else{
			devolucion=null;
		}
		
		
		
		return devolucion;
	}

	//Ordenacion en burbuja amortizada
	//Se basa en recorrer el arraylist comparando siempre con el elemento anterior e intercambiarlos en funcion de lo que exijas
	//Finalmente saldremos del bucle infinito en la iteracion que no se hayan realizado ningun cambio
	public void ordenaLista() {
		boolean cambios=false;
		PLoc aux=null;
		//-1 cuando el primero es mayor y 1 cuando el segundo es mayor
		//Ordenar los mas pequenos al principio
		
		while(true){
			cambios=false;
			
			for (int i = 1; i < vector.size(); i++) {
				
				if(vector.get(i).compareTo(vector.get(i-1))==-1){
					aux=vector.get(i);
					vector.set(i, vector.get(i-1));
					vector.set(i-1, aux);
					cambios=true;
				}
			}
			
			if(cambios==false){
				break;
			}
		}

	}
	
	//NEW devuelve el tamano del vector
	public int getSize(){
		return vector.size();
	}

}
