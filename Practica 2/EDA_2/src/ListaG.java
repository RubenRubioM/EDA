//DNI 20084606 Rubio Martinez, Ruben
import java.io.*;

//TODO acuerdate de comprobar todos los nullpointers y tal
public class ListaG implements Lista{
	
	private class NodoLG{
		private PLoc pd;
		private NodoLG next;

		
		public NodoLG(){
			pd=new PLoc();
			next=null;

		}
		
		public NodoLG(PLoc p){
			pd=p;
			next=null;

		}
		
		public NodoLG getNext(){
			return next;
		}
		
		
		public PLoc getPLoc(){
			return pd;
		}
		
		public void setPLoc(PLoc p) {
			pd=p;
		}
		
		public void setNext(NodoLG nodo){
			next=nodo;
		}
		

	}//fin NodoLG

	private NodoLG pr;
	private int size;

	public ListaG(){
		pr=null;

		size=0;
	}
	
	//Utilizamos el metodo escribeInfoGPS que ya usamos en PLoc
	public void escribeListaG(){
		String cadena="";
		NodoLG temporal=pr;
		int contador=0;
		
		while(temporal!=null && temporal.getPLoc()!=null){
			
			System.out.print("nodo "+contador+": ");
			temporal.getPLoc().escribeInfoGps();
			
			temporal = temporal.getNext();
			contador++;
		}
		
		
	}
	
	
	//Usamos practicamente el mismo sistema usado en la anterior practica para leer los plocs solo que con unas pequenas modificaciones
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
						
						//Creamos un nuevo nodo que sera el que vamos a insertar
						NodoLG nuevo = new NodoLG();
						
						//Si la lista aun no tiene cabeza insertamos este nodo al principio
						if(pr==null){
							pr=nuevo;
							
							nuevo.setPLoc(pl);
							size++;
						
						//Si ya tenia cabeza insertamos el nodo como cabeza y apuntamos hacia el que anteriormente era la cabeza	
						}else{
							NodoLG temporal = pr;
							
							nuevo.setPLoc(pl);
							nuevo.setNext(pr);
							pr=nuevo;
							
							size++;
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

	//Comprobamos si tiene cabeza, si la tiene es que no esta vacia
	public boolean esVacia() {
		boolean devolucion = false;
		
		if(size==0){
			devolucion=true;
		}else if(size>0){
			devolucion=false;
		}
		return devolucion;
	}

	
	//guardamos la actual cabeza en un nodo temporal y asignamos la nueva cabeza apuntando a la anterior cabeza
	public void insertaCabeza(PLoc p) {
		
		if(pr==null){
			pr = new NodoLG(p);
			
			
		}else{
			
			NodoLG temp = pr;
			NodoLG nuevo = new NodoLG(p);
			nuevo.setNext(temp);
			pr=nuevo;
			
		}
		size++;
	}

	
	//recorremos toda la lista e insertamos un nuevo nodo
	public void insertaCola(PLoc v) {
		NodoLG nuevo = new NodoLG();
		NodoLG temporal = pr;
		
		if(pr==null){
			insertaCabeza(v);
		}else{
			while(temporal.getNext()!=null){
				temporal=temporal.getNext();
			}
			nuevo.setPLoc(v);
			temporal.setNext(nuevo);
			size++;
		}
		
		
		
	}

	
	//si no tiene cabeza insertamos el primer elemento del vector como cabeza y el resto insertamos normalmente al final de la lista
	//En caso de ya tener cabeza recorremos la lista hasta el final con un while y despues insertamos uno a uno los nodos con los plocs pasados
	public void insertaArrayPLoc(PLoc[] v) {
		NodoLG temporal=pr;
		NodoLG aux= new NodoLG();
		
		//While basico para llegar al final de la lista porque no tengo enlace a la cola
		if(v!=null && v.length>0){
			if(pr==null){
				for (int i = 0; i < v.length; i++) {
					if(pr==null){
						if(v[i]!=null){
							insertaCabeza(v[i]);
						}
					}else{
						if(v[i]!=null && temporal!=null){
							
							temporal.setNext(aux);
							aux.setPLoc(v[i]);
							temporal=temporal.getNext();
							aux=new NodoLG();
							size++;
						}
					}
				}
			}else{
				while(temporal.getNext()!=null){
					temporal=temporal.getNext();
				}
				
				for (int i = 0; i < v.length; i++) {
					if(v[i]!=null){
						
						temporal.setNext(aux);
						aux.setPLoc(v[i]);
						temporal=temporal.getNext();
						aux=new NodoLG();
						size++;
					}
				}
			}
		}
		
		
		
	}

	
	//comprobamos que tenga cabeza y la eliminamos, haciendo que el segundo elemento sea la nueva cabeza
	//por otro lado si solo existe la cabeza la eliminamos dejando la lista vacia y la cabeza a null
	public boolean borraCabeza() {
		boolean devolucion=false;
		
		if(pr!=null && pr.getNext()!=null){
			NodoLG temporal = pr.getNext();
			pr.setNext(null);
			pr=temporal;
			devolucion=true;
			size--;
		}else if(pr!=null && pr.getNext()==null){
			pr=null;
			size--;
		}

		return devolucion;
	}

	//eliminamos el ultimo elemento y hacemos que el nodo anterior a este ahora apunte a nada (null)
	public boolean borraCola() {
		boolean devolucion=false;
		
		NodoLG temporal = pr;
		NodoLG nodoAnterior = new NodoLG();
		
		if(pr!=null){
			while(temporal.getNext()!=null){
				nodoAnterior=temporal;
				temporal= temporal.getNext();
			}
			if(nodoAnterior.getNext()!=null){
				nodoAnterior.setNext(null);
				devolucion=true;
			}else{
				pr=null;
			}
			
			size--;
		}
		
		
		return devolucion;
	}
	
	//buscamos una ciudad y si la encontramos devolvemos su posicion, si no la encontramos lanzaremos la excepcion 
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion {
		int devolucion=100000, contador=0;
		NodoLG temporal=pr;
		if(v!=null){
			while(temporal!=null){
				
				if(temporal.getPLoc().getCiudad().equalsIgnoreCase(v)){
					devolucion=contador;
					
					break;
				}
				temporal = temporal.getNext();
				
				contador++;
			}
		}
		
		
		if(devolucion>100000-1){

			throw new CiudadNoEncontradaExcepcion(v);
		}

		return devolucion;
	}

	//recorremos la lista en busca de la ciudad pasada por parametro
	public boolean borraCiudad(String v) {
		boolean devolucion=false;
		
		NodoLG temporal = pr;
		int contador=1;
		if(v!=null){
			while(temporal!=null){
				if(temporal.getPLoc().getCiudad().equalsIgnoreCase(v)){
					borra(contador);
					devolucion=true;
					break;
				}
				temporal = temporal.getNext();
				contador++;
			}
		}
		
		
		return devolucion;
		
	}

	
	//recorremos la lista en busca de los PLocs que coincidan con el pais pasado por parametro y los eliminamos
	public boolean borraPais(String s) {
		boolean devolucion=false;
		
		NodoLG temporal = pr;
		int contador=1;
		if(s!=null){
			while(temporal!=null){
				if(temporal.getPLoc().getPais().equalsIgnoreCase(s)){
					borra(contador); //elimina la posicion de la lista y hace que el anterior a esta apunte al siguiente del eliminado
									//1 - 2 - 3 si eliminamos el 2 ahora el 1 apuntaria a 3      1 - 3
					devolucion=true;
					
				}
				temporal = temporal.getNext();
				contador++;
			}
		}
		
		
		return devolucion;
	}

	
	//Devuelve el PLoc en la posicion indicada
	//Para ello recorremos la lista en su busca, si en el final cuando intentamos acceder a temporal.getNext() cuando temporal ya es nulo
	//nos saltara un nullpointer y al capturarlo nosotros lanzamos una excepcion indexoutofbounds pasandole por parametro la posicion
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException {
		PLoc devolucion = null;
		int contador=0;
		NodoLG temporal = pr;
		
		while(contador<=pos){
			
			if(contador>0){
				try {
					temporal = temporal.getNext();
				} catch (NullPointerException e) {
					throw new IndexOutOfBoundsException(Integer.toString(pos));
				}
			}
			
			
			contador++;
			if(temporal!=null){
				devolucion=temporal.getPLoc();
			}
			
			
			
		}
		
		if(pos<0){
			throw new IndexOutOfBoundsException(Integer.toString(pos));
		}

		return devolucion;
	}
	
	
	//Devuelve todos los PLocs que sean del pais pasado por parametro
	//Para ello crearemos un array que redimensionaremos cuando se quede sin espacios
	//Basicamente recorreremos la lista hasta que sea nula y compararemos su pais con el pasado por parametro
	// en caso de ser iguales almacenaremos ese ploc en el array
	public PLoc[] Pais(String p){
		PLoc[] devolucion=new PLoc[1];
		int ingresados=0;
		NodoLG temporal=pr;
		
		if(pr!=null && p!=null){
			while(temporal!=null){
				if(temporal.getPLoc().getPais()!=null && temporal.getPLoc().getPais().equalsIgnoreCase(p)){
					
					if(ingresados<devolucion.length){
						devolucion[ingresados]=temporal.getPLoc();
						ingresados++;
					}else{
						int k = 0;
						PLoc[] tmp=new PLoc[devolucion.length+1];
						for (int i = 0; i < devolucion.length; i++) {
							tmp[i]=devolucion[i];
							k++;
						}
						tmp[k]=temporal.getPLoc();
						devolucion=tmp;
						ingresados++;
					}
					
				}
				temporal = temporal.getNext();
			}
		}
		
		
		
		return devolucion;
	}

	
	//Ordenacion en burbuja amortizada
	//Se basa en recorrer la lista comparando siemrpe con el elemento anterior e intercambiarlos en funcion de lo que exijas
	//Finalmente saldremos del bucle infinito en la iteracion que no se hayan realizado ningun cambio
	
	public void ordenaLista() {
		
		PLoc aux = null;
		int contador=0;
		boolean cambios=false;
		//-1 cuando el primero es mayor y 1 cuando el segundo es mayor
		//Ordenar los mas pequenos al principio
		if(pr!=null && pr.getNext()!=null){
			NodoLG menor=pr;
			NodoLG mayor=pr.getNext();
			while(true){
				cambios=false;
				
				for (int i = 1; i < size; i++) {
					
					if(getNodo(i).getPLoc().compareTo(getNodo(i-1).getPLoc())==-1){
						
						mayor=getNodo(i-1);
						menor=getNodo(i);
						
						aux=menor.getPLoc();
						menor.setPLoc(mayor.getPLoc());
						mayor.setPLoc(aux);
						cambios=true;
						
					}
				}
				
				if(cambios==false){
					break;
				}
			}
		}
		
		
		pr=getNodo(0);
		
	}
	
	
	//NEW devuelve el nodo en la posicion pasada por parametro
	public NodoLG getNodo(int index){
		NodoLG devolucion=null;
		NodoLG temporal=pr;
		int contador=0;
		
		
		if(index<size){
			while(contador<=index && temporal!=null){
				devolucion=temporal;
				temporal = temporal.getNext();
				contador++;
			}
		}
		
		return devolucion;
	}
	
	//NEW devuelve el tamano de la lista
	public int size(){
		return size;
	}
	
	
	
	
	/*
	NEW metodo para borrar un nodo pasando el numero del nodo. Para ello
	miramos el numero de nodo si es el primero o el ultimo realiza los metodos
	Ya implementados pero en caso contrario de estar entre medias
	recorremos la lsita hasta el numero pasado guardamos el anterior y el siguiente
	para poder linkearlos sin que desaparezcan
	*/
	
	//Empieza en 0
	public void borra(int index){
		
		if(index==0){
			borraCabeza();
		}else if(index==size){
			borraCola();
		}else if(index>0 && index<size){
			
			int contador=0;
			NodoLG temporal = pr;
			NodoLG anterior = null;
			
			while(contador<index){
				anterior=temporal;
				temporal = temporal.getNext();
				contador++;

			}
			
			anterior.setNext(temporal.getNext());
			size--;
		}
	}
	
	//NEW devuelve todos los plocs de la lista
	public PLoc[] devuelve(){

		PLoc[] devolucion = new PLoc[size];
		NodoLG temporal=pr;
		int contador=0;
		devolucion[contador]=temporal.getPLoc();
		contador++;
		while(temporal.getNext()!=null){
			temporal = temporal.getNext();
			devolucion[contador]=temporal.getPLoc();
			
			contador++;
		}
		return devolucion;
	}
	
}

	