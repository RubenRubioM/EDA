//DNI 20084606 Rubio Martinez, Ruben

import java.util.*;


public class BuscaLocalizacion2 {
	
	public static void main(String[] args) {
		
		if(args.length==4){
			if(args[0]!=null){
				busqueda4parametros(args[0],Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]));
			}
		}else if(args.length==2){
			if(args[0]!=null && args[1]!=null){
				busqueda2parametros(args[0],args[1]);
			}
		}
		
	}

	//Aqui trabajamos sobre el ArbolS
	private static void busqueda2parametros(String f, String pais) {
		ArbolS arbol = new ArbolS(); 			
		TreeMap<String, TreeSet<PLoc>> tm = arbol.getTM();

		arbol.leeArbol(f);	
		
		//Si tiene el pais almacenado
		if(tm.containsKey(pais)){
			TreeSet<PLoc> ts = tm.get(pais);
			Iterator<PLoc> it = ts.iterator();
			ArrayList<String> lista = new ArrayList<String>();
			
			//Recorremos todo el TreeSet y almacenamos las ciudades en un ArrayList
			while(it.hasNext()){
				lista.add(it.next().getCiudad());
			}
			//Ordenamos el array de strings
			Collections.sort(lista);
		
			String mostrar = pais +" ("+lista.size()+"): ";
			
			//recorremos la lista ordenada y actualizando el string para mostrar
			for (int i = 0; i < lista.size(); i++) {
				if(i==lista.size()-1){
					mostrar = mostrar + lista.get(i);
				}else{
					mostrar = mostrar + lista.get(i)+" - ";
				}

			}
			
			System.out.println(mostrar);
		//No tiene el pais almacenado	
		}else{
			ArrayList<String> listaPaises = new ArrayList<String>(); 
			//Guardamos todos los paises en un arrayList
			for(Map.Entry<String, TreeSet<PLoc>> entry : tm.entrySet()){
				listaPaises.add(entry.getKey());
			}
			Collections.sort(listaPaises);
			
			//Recorremos todos los paises del arraylist
			for (int i = 0; i < listaPaises.size(); i++) {
				TreeSet<PLoc> ts = new TreeSet<PLoc>();
				Iterator<PLoc> it = ts.iterator();
				ArrayList<String> listaCiudades = new ArrayList<String>();
				
				//Guardamos todas las ciudades para luego ordenarlas alfabeticamente
				while(it.hasNext()){
					listaCiudades.add(it.next().getCiudad());
				}
				
				Collections.sort(listaCiudades);
				
				String mostrar = listaPaises.get(i)+" ("+listaCiudades.size()+"): ";
				
				//Formamos la linea de texto, un pais por iteracion
				for (int j = 0; j < listaCiudades.size(); j++) {
					if(i==listaCiudades.size()-1){
						mostrar = mostrar + listaCiudades.get(j);
					}else{
						mostrar = mostrar + listaCiudades.get(j)+" - ";
					}
				}
				System.out.println(mostrar);
			}
		}
		
		
	}

	private static void busqueda4parametros(String f, double latitud, double longitud, double rango) {
		ArbolG arbol = new ArbolG();
		arbol.leeArbol(f);
		
		double[] latMax = new double[2];
		latMax[0] = latitud+rango;
		latMax[1] = latitud-rango;
		
		double[] longMax = new double[2];
		longMax[0] = latitud+rango;
		longMax[1] = latitud-rango;
		
		//Tendre que crear algun metodo en la clase ArbolG que si no no puedo acceder a los nodos
		
	}
	
	
	
}

//TODO
/*
 * 
COSTES ArbolG:

1) boolean esVacio(): O(1)
2) boolean inserta(PLoc p): O(n)
3) boolean ciudadEnArbol(String v): O(log(n))
4) PLoc busquedaLejana(String s): O(log(n))

COSTES aplicacion:

1) busqueda por rango: O(n*n)
2) busqueda por paises:
 * 
 * 
 * 
 */
