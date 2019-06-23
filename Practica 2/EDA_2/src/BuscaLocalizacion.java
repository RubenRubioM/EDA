//DNI 20084606 Rubio Martinez, Ruben
import java.util.*;

public class BuscaLocalizacion {

	//NEW una clase nueva para guardar los pares de plocs y la distancia entre ellos
	private static class Pares{
		private PLoc primero;
		private PLoc segundo;
		private int distancia;
		
		//Primer constructor para crear un objeto de tipo Pares
		private Pares(PLoc p, PLoc s, int d){
			primero=p;
			segundo=s;
			distancia=d;
		}
		
		//Constructor por defecto inicializando todo a nulos
		private Pares(){
			primero=null;
			segundo=null;
			distancia=0;
		}
		
		//getters
		private PLoc getPrimero(){return primero;}
		private PLoc getSegundo(){return segundo;}
		private int getDistancia(){return distancia;}
		
		//setters
		private void setPrimero(PLoc p){primero=p;}
		private void setSegundo(PLoc p){segundo=p;}
		private void setDistancia(int d){distancia=d;}
		
		
		//comparacion para ver si dos objetos de tipo Pares son iguales
		//Comparamos si el primero es igual que el segundo y viceversa porque a la hora de guardarlos si son mutuos se guardaran al reves
		private boolean iguales(Pares f){
			boolean devolucion = false;
			
			if(f!=null){
				if(primero.iguales(f.getSegundo())){
					if(segundo.iguales(f.getPrimero())){
						if(distancia==f.getDistancia()){
							devolucion=true;
						}
					}
				}
			}
			
			
			return devolucion;
		}
		
		
		//Si el pasado por parametro es mayor devuelve 1, si es menor devuelve -1, si son iguales devuelve 0
		private int compareToFronteras(Pares f){
			int devolucion=0;
			
			if(this.distancia<f.getDistancia()){
				devolucion=1;
			}else if(this.distancia==f.getDistancia()){
				devolucion=0;
			}else if(this.distancia>f.getDistancia()){
				devolucion=-1;
			}
			
			return devolucion;
		}
		
		

	}
	
	
	
	public static void main(String[] args) {
		
		//Hacer busqueda por rango
		if(args[1].equalsIgnoreCase("R")){
			hacerConVector(args[0],Double.parseDouble(args[2]),Double.parseDouble(args[3]));
		}
		//Hacer busqueda por pares
		if(args[1].equalsIgnoreCase("P")){
			hacerConLista(args[0],args[2],Double.parseDouble(args[3]));
		}

	}
	
	
	public static void hacerConLista(String s,String continente,double LMax){
		ListaG lista = new ListaG();
		int distancia=0;
		int menorActual=10000;
		ArrayList<Pares> mutuas = new ArrayList<Pares>();
		ArrayList<Pares> fronteras = new ArrayList<Pares>();
		Pares f = new Pares();
		boolean repetido=false;
		boolean masDeUnPais=false;
		
		
		lista.leeLista(s);
		
		for (int i = 0; i < lista.size(); i++) {
			
			for (int j = 0; j < lista.size(); j++) {
				
				try {
					if(lista.getPLoc(i).getPais().equalsIgnoreCase(lista.getPLoc(j).getPais())==false){
						masDeUnPais=true;
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println(e.getMessage());
				}
				
				
			}
			
		}
		
		if(LMax<=0){
			System.out.println("EL VALOR DE LMAX ES INCORRECTO");
		}else{
			
			if(masDeUnPais){
				
				for (int i = 0; i < lista.size(); i++) {
					
					try {
						
						//Comprobamos que el principal sea del continente pasado por parametro
						if(lista.getPLoc(i).getContinente().equalsIgnoreCase(continente)){
							
							for (int j = 0; j < lista.size(); j++) {
								
								//Comprobamos que el segundo sea del continente pasado por parametro
								if(lista.getPLoc(j).getContinente().equalsIgnoreCase(continente)){
									
									//Comprobamos que no sean del mismo pais
									if(lista.getPLoc(i).getPais().equals(lista.getPLoc(j).getPais())==false){
										
										distancia = (int) Math.sqrt(Math.pow(lista.getPLoc(i).getGps()[0]-lista.getPLoc(i).getGps()[1], 2) + 
															Math.pow(lista.getPLoc(j).getGps()[0]-lista.getPLoc(j).getGps()[1], 2));
										
										
										//Entra si la distancia es la optima y es la menor de las distancias calculadas anteriormente
										if(distancia<=LMax && distancia<menorActual){
											menorActual=distancia;
											f = new Pares(lista.getPLoc(i),lista.getPLoc(j),distancia);																			
										}
										
									}
								}
							} //Acaba la comparacion de una ciudad con el resto de las ciudades del continente
							
							//Comprobamos que si estas ya introducido lo meteremos en el array de mutuas y si no al de fronteras
							for (int k = 0; k < fronteras.size(); k++) {
								if(fronteras.get(k).iguales(f)){
									mutuas.add(f);
									repetido=true;
									break;
								}
								
								if(repetido==false && k==fronteras.size()-1){
									fronteras.add(f);
								}
							}
							

							repetido=false;
						}
						
					} catch (IndexOutOfBoundsException e) {
						System.out.println(e.getMessage());
					}
					
					
					menorActual=10000;
					
				}//Fin del for y fin de la comprobacion, calculo y almacenado 
			

				//Ahora ordenamos el array primero lo de menor distancia y ultimos los de mayor distancia
				//Primero vamos a ordenar el arraylist de fronteras
				boolean cambios=false;
				Pares aux=null;
				
				while(true){
					
					cambios=false;
					
					for (int k = 1; k < fronteras.size(); k++) {
						if(fronteras.get(k).compareToFronteras(fronteras.get(k-1))==-1){
							aux=fronteras.get(k);
							fronteras.set(k, fronteras.get(k-1));
							fronteras.set(k-1, aux);
							cambios=true;
						}
						
					}
					
					if(cambios==false){
						break;
					}
					
				}
				PLoc aux2 = null;
				//Ahora vamos a ordenar ortograficamente los valores en el arraylist mutuas
				for (int i = 0; i < mutuas.size(); i++) {
					if(mutuas.get(i).getPrimero().getCiudad().compareTo(mutuas.get(i).getSegundo().getCiudad())>0){
						aux2=mutuas.get(i).getPrimero();
						mutuas.get(i).setPrimero(mutuas.get(i).getSegundo());
						mutuas.get(i).setSegundo(aux2);
					}
				}
				
				cambios=false;
				aux=null;
				
				//Segundo vamos a ordenar el arraylist de mutuas
				//Ahora ordenamos el array de mutuas primero lo de menor distancia y ultimos los de mayor distancia
				//Debemos ordenar los nombres por orden ortografico tambien
				while(true){
					cambios=false;
					
					for (int i = 1; i < mutuas.size(); i++) {
						if(mutuas.get(i).compareToFronteras(mutuas.get(i-1))==-1){
							aux=mutuas.get(i);
							mutuas.set(i, mutuas.get(i-1));
							mutuas.set(i-1, aux);
							cambios=true;
						}
					}
					
					if(cambios==false){
						break;
					}
				}
				
				
				//TODO Cual es la forma de imprimirlos
				if(fronteras.size()>0){
					System.out.println("CIUDADES FRONTERIZAS");
					for (int i = 0; i < fronteras.size(); i++) {
						System.out.println(fronteras.get(i).getPrimero().getCiudad()+" ("+fronteras.get(i).getPrimero().getPais()+")"+" - "+
											fronteras.get(i).getSegundo().getCiudad()+" ("+fronteras.get(i).getSegundo().getPais()+")");
					}
				}
				
				
				if(mutuas.size()>0){
					System.out.println("CIUDADES FRONTERIZAS MUTUAS");
					for (int i = 0; i < mutuas.size(); i++) {
						System.out.println(mutuas.get(i).getPrimero().getCiudad()+" ("+mutuas.get(i).getPrimero().getPais()+")"+" - "+
											mutuas.get(i).getSegundo().getCiudad()+" ("+mutuas.get(i).getSegundo().getPais()+")");
					}
				}
				
				
			}else{ //Solo hay un pais en ese continente
				System.out.println("NO HAY FRONTERAS INTRACONTINENTALES");
			}

			
			
		}//fin del else LMax>0
		
		
	}//Fin del metodo
	
	
	
	public static void hacerConVector(String s, double longGPS,double rango){
		VectorG ve = new VectorG();
		PLoc[] plocs = new PLoc[1];
		int plocsingresados = 0;
		double minimoRango = longGPS - rango;
		double maxRango = longGPS + rango;
		double distanciaMinima=1000000;
		double distancia;
		ve.leeLista(s);
		
		
		try {
			
			
			if(rango<0){
				System.out.println("EL VALOR DEL RANGO ES INCORRECTO");
			}else if(rango==0){
				
				
				//Recorremos el vector para encontrar al ploc mas cercano al longGPS mas cercano
				for (int i = 0; i < ve.getSize(); i++) {
					
					if(ve.getPLoc(i)!=null){	
						
						distancia = Math.abs(longGPS-ve.getPLoc(i).getGps()[1]);
						
						if(distancia<distanciaMinima){
							distanciaMinima=distancia;
							plocs[0]=ve.getPLoc(i);
						}
						
					}
					
				}
				
				if(plocs[0]!=null){
					plocs[0].escribeInfoGps();
				}
				
			//Si el rango es mayor que 0	
			}else if(rango>0){
				
				//Recorremos el vector buscando todos los plocs que esten entre el rango de longitudes determinado y los almacenamos
				//Al mismo tiempo vamos redimensionando el array 
				for (int i = 0; i < ve.getSize(); i++) {
					if(ve.getPLoc(i)!=null){
						//Si entra quiere decir que esta en el rango de busqueda
						if(ve.getPLoc(i).getGps()[1]>=minimoRango && ve.getPLoc(i).getGps()[1]<=maxRango){
							
							if(plocsingresados<plocs.length){
								plocs[plocsingresados]=ve.getPLoc(i);
								plocsingresados++;
							}else{
								//Redimensionamos
								
								PLoc[] tmp = new PLoc[plocs.length+1];
								int k=0;
								for (int j = 0; j < plocs.length; j++) {
									tmp[k]=plocs[j];
									k++;
								}
								tmp[k]=ve.getPLoc(i);
								plocs = tmp;
								plocsingresados++;
								
							}						
						}
					}
				}
				
				
				//si no se ingresa ningun ploc
				if(plocs[0]==null){
					System.out.println("NO HAY CIUDADES EN ESE RANGO");
				}else{
					
					boolean cambios=false;
					//Aqui tengo que ordenar el array en funcion de su proximidad con abs(longGPS - plocs[i].getGPS[1])
					
					//Ordenacion en burbuja amortizada
					//Se basa en recorrer el vector comparando siempre con el elemento anterior e intercambiarlos en funcion de lo que exijas
					//Finalmente saldremos del bucle infinito en la iteracion que no se hayan realizado ningun cambio
					while(true){
						cambios=false;
						for (int i = 1; i < plocs.length; i++) {
							if(Math.abs(longGPS - plocs[i].getGps()[1])<Math.abs(longGPS - plocs[i-1].getGps()[1])){
								PLoc aux = new PLoc();
								
								aux=plocs[i];
								plocs[i]=plocs[i-1];
								plocs[i-1]=aux;
								cambios=true; 
							}
						}
						
						if(cambios==false){
							break;
						}
					}
				}
				
				//mostramos los nombres de las ciudades con los plocs una vez ordenados
				for (int i = 0; i < plocs.length; i++) {
					System.out.println(plocs[i].getCiudad());
				}
			} //Fin del if(rango>0)
			
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
	}//Fin del metodo
	
	/*
	 * COSTES ListaG:
	 * 1) int esVacia(): O(1)
	 * 2) void insertaCabeza(): O(1)
	 * 3) void insertaCola(): O(n)
	 * 4) boolean borraCabeza(): O(1)
	 * 5) boolean borraCola(): O(n)
	 * 6) int ciudadEnLista(String v): O(n)
	 * 7) boolean borraCiudad(String v): O(n)
	 * 8) boolean borraPais(String s): O(n)
	 * 9) PLoc getPLoc(int pos): O(n)
	 * 
	 * COSTES VectorG:
	 * 1) int esVacia(): O(1)
	 * 2) void insertaCabeza(): O(1)
	 * 3) void insertaCola(): O(1)
	 * 4) boolean borraCabeza(): O(1)
	 * 5) boolean borraCola(): O(1)
	 * 6) int ciudadEnLista(String v): O(n)
	 * 7) boolean borraCiudad(String v): O(n)
	 * 8) boolean borraPais(String s): O(n)
	 * 9) PLoc getPLoc(int pos): O(n)
	 * 
	 * COSTES aplicacion:
	 * 1) busqueda por rango: O(n*n)
	 * 2) busqueda por pares: O(n*n)
	 */
}
