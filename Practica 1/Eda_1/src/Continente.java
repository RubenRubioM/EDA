//DNI 20084606 Rubio Martinez, Ruben
import java.io.*;

public class Continente {

	public static void main(String[] args) {
		/*
		 * EU = Europa - NA = America del norte - SA = America del sur
		 * AF = Africa - OC = Oceania - AS = Asia
		 */
		BufferedReader lec = null;
		FileReader fe = null;
		
		PLoc[] representantes = new PLoc[6];
		PLoc[] plocs = new PLoc[1];

		int plocsIngresados = 0;
		
		if(args!=null){
			
			try {
				fe = new FileReader(args[0]);
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

						//lectura e inicializacion de los strings. Cambiamos las cadenas vacias por nulos para evitar errores
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
						
						//Asignamos los 6 primeros representantes y los guardamos en un array de plocs
						if(pl!=null){
							if(pl.getLatitud()!=null && pl.getLongitud()!=null){
								//Eleccion de los 6 primeros representantes
								/*
								 * 0 = AF  -  1 = NA  -  2 = SA  -  3 = AS  -  4 = EU  -  5 = OC
								 */
								if(elems[0]!=null){
									if(elems[0].equalsIgnoreCase("AF") && representantes[0]==null){
										representantes[0]=pl;
										
									}
									if(elems[0].equalsIgnoreCase("NA") && representantes[1]==null){
										representantes[1]=pl;
										
									}
									if(elems[0].equalsIgnoreCase("SA") && representantes[2]==null){
										representantes[2]=pl;
										
									}
									if(elems[0].equalsIgnoreCase("AS") && representantes[3]==null){
										representantes[3]=pl;
										
									}
									if(elems[0].equalsIgnoreCase("EU") && representantes[4]==null){
										representantes[4]=pl;
										
									}
									if(elems[0].equalsIgnoreCase("OC") && representantes[5]==null){
										representantes[5]=pl;
										
									}
								}
								
								//Guardamos todos los plocs que vamos ingresando, con cuidado y redimensionando cada vez que este lleno el array
								//Para ello usamos el metodo de un array temporal aumentando en 1 su capacidad
								//Redimension para los plocs ingresados
								if(plocsIngresados<plocs.length){
									plocs[plocsIngresados]=pl;
									plocsIngresados++;
								}else{ 
									//Redimensionamos
									PLoc[] tmp = new PLoc[plocs.length+1];
									int k=0;
									for (int i = 0; i < plocs.length; i++) {
										tmp[k]=plocs[i];
										k++;
									}
									tmp[k]=pl;
									plocs = tmp;
									plocsIngresados++;
								}
							}
						}
						
						
						//al final volvemos a leer la siguiente linea
						leida = lec.readLine();

					}//fin del ciclo while(leida!=null)
				}//Fin de (leida!=null)
			} catch (IOException e) {
				System.err.println("Error con " + args[0]);
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
		}//Salimos de lo que viene a ser siendo leer datos, elegir primeros representantes y meter los plocs en el array
		
		// PASO 1
		
		
		//Creamos un string para imprimir los representantes. Sustituimos los valores nulos por "x"
		String cadena="REPRESENTANTES: ";
		
		for (int i = 0; i < representantes.length; i++) {
			if(representantes[i].getCiudad()!=null && i!=representantes.length-1){
				cadena = cadena + representantes[i].getCiudad() + " - ";
			}else if(representantes[i].getCiudad()!=null && i==representantes.length-1){
				cadena = cadena + representantes[i].getCiudad();
			}else if(representantes[i].getCiudad()==null && i!=representantes.length-1){
				cadena = cadena + "x" + " - ";
			}else if(representantes[i].getCiudad()==null && i!=representantes.length-1){
				cadena = cadena + "x";
			}
		}
		System.out.println(cadena);
		
		//Ahora guardamos en un array todos los plocs que inicialmente no tienen un continente asignado
		//Para ello realizamos algo parecido al metodo de antes. Recorrer todos los plocs buscanso los que no tienen continente y guardandolos
		//en otro array especial para ellos, redimensionando cada vez que no quede espacio
		PLoc[] plocsSinContinente = new PLoc[1];
		int plocsSinContinenteIngresados = 0;
		
		for (int i = 0; i < plocs.length; i++) {
			if(plocs[i].getContinente()==null){
				if(plocsSinContinenteIngresados<plocsSinContinente.length){
					plocsSinContinente[plocsSinContinenteIngresados]=plocs[i];
					plocsSinContinenteIngresados++;
				}else{
					//Redimensionamos
					PLoc[] tmp = new PLoc[plocsSinContinente.length+1];
					int k=0;
					for (int j = 0; j < plocsSinContinente.length; j++) {
						tmp[k]=plocsSinContinente[j];
						k++;
					}
					tmp[k]=plocs[i];
					plocsSinContinente = tmp;
					plocsSinContinenteIngresados++;
				}
			}
		}
		
		double distancia;
		double minimaDistancia=1000000;
		
		// PASO 2
		
		//Esto funciona correctamente
		/*
		 * Aqui tenemos un for para todo los plocs sin continente y otro para los representantes de tal manera que el ploc se compare la distancia a
		 * todos los representantes y se quede con la mas corta
		 */
		for (int i = 0; i < plocsSinContinente.length; i++) {
			for (int j = 0; j < representantes.length; j++) {
				
				distancia= Math.sqrt(Math.pow(Math.abs(plocsSinContinente[i].getGps()[0]-representantes[j].getGps()[0]), 2)+
							Math.pow(Math.abs(plocsSinContinente[i].getGps()[1]-representantes[j].getGps()[1]), 2));
				
				if(distancia<minimaDistancia){
					
					plocsSinContinente[i].setContinente(representantes[j].getContinente());
					minimaDistancia=distancia;
				}
			}
			minimaDistancia=100000;
		}//fin del for para etiquetar por primera vez
		
		
		
		
		// PASO 3
		
		/*
		 * Aqui tenemos dos for anidados para poder calcular la distancia de un ploc al resto de plocs de su mismo continente,
		 * cuando acabamos el segundo for (j==plocs.length-1) asignamos el representante y guardamos su distancia para ver si 
		 * el proximo ploc hace una marca menor
		 */
		distancia=0;
		minimaDistancia=1000000;
		double sumaDistancia=0;
		double minimaDistanciaAF = 100000;
		double minimaDistanciaNA = 100000;
		double minimaDistanciaSA = 100000;
		double minimaDistanciaAS = 100000;
		double minimaDistanciaEU = 100000;
		double minimaDistanciaOC = 100000;
		
		
		//Procedemos a buscar nuevos representantes
		for (int i = 0; i < plocs.length; i++) {
			for (int j = 0; j < plocs.length; j++) {
				if(plocs[i].getContinente().equalsIgnoreCase(plocs[j].getContinente())){

					distancia= Math.sqrt(Math.pow(Math.abs(plocs[i].getGps()[0]-plocs[j].getGps()[0]), 2)+
							Math.pow(Math.abs(plocs[i].getGps()[1]-plocs[j].getGps()[1]), 2));
					
					sumaDistancia+=distancia;		
				}
				if(j==plocs.length-1){
					
					if(plocs[i].getContinente().equalsIgnoreCase("AF") && sumaDistancia<minimaDistanciaAF){
						representantes[0]=plocs[i];
						minimaDistanciaAF=sumaDistancia;
					}
					if(plocs[i].getContinente().equalsIgnoreCase("NA") && sumaDistancia<minimaDistanciaNA){
						representantes[1]=plocs[i];
						minimaDistanciaNA=sumaDistancia;
					}
					if(plocs[i].getContinente().equalsIgnoreCase("SA") && sumaDistancia<minimaDistanciaSA){
						representantes[2]=plocs[i];
						minimaDistanciaSA=sumaDistancia;
						
					}
					if(plocs[i].getContinente().equalsIgnoreCase("AS") && sumaDistancia<minimaDistanciaAS){
						representantes[3]=plocs[i];
						minimaDistanciaAS=sumaDistancia;
						
					}
					if(plocs[i].getContinente().equalsIgnoreCase("EU") && sumaDistancia<minimaDistanciaEU){
						representantes[4]=plocs[i];
						minimaDistanciaEU=sumaDistancia;
					}
					if(plocs[i].getContinente().equalsIgnoreCase("OC") && sumaDistancia<minimaDistanciaOC){
						representantes[5]=plocs[i];
						minimaDistanciaOC=sumaDistancia;
					}

				}
					
			}
			sumaDistancia=0;	
		}//Fin del for para elegir nuevos representantes
		
		minimaDistancia=100000;
		distancia=0;
		int posicionRepresentantes=0;
		
		/*
		 * Como el paso 2, volvemos a reetiquetar los plocs que venian inicialmente sin continente
		 */
		// PASO 4
		
		//Reetiquetamos 
		for (int i = 0; i < plocsSinContinente.length; i++) {
			for (int j = 0; j < representantes.length; j++) {
				
				distancia= Math.sqrt(Math.pow(Math.abs(plocsSinContinente[i].getGps()[0]-representantes[j].getGps()[0]), 2)+
							Math.pow(Math.abs(plocsSinContinente[i].getGps()[1]-representantes[j].getGps()[1]), 2));
				
				if(distancia<minimaDistancia){
					posicionRepresentantes=j;
					
					minimaDistancia=distancia;
				}
			}
			plocsSinContinente[i].setContinente(representantes[posicionRepresentantes].getContinente());
			minimaDistancia=100000;
		}
		
		
		//Imprimimos los nuevos representantes
		// REPRESENTANTES DEL PASO 3
		/*
		 * Como en el paso 1, volvemos a imprimir los representantes una vez reescogidos
		 */
		cadena="REPRESENTANTES: ";
		
		for (int i = 0; i < representantes.length; i++) {
			if(representantes[i].getCiudad()!=null && i!=representantes.length-1){
				cadena = cadena + representantes[i].getCiudad() + " - ";
			}else if(representantes[i].getCiudad()!=null && i==representantes.length-1){
				cadena = cadena + representantes[i].getCiudad();
			}else if(representantes[i].getCiudad()==null && i!=representantes.length-1){
				cadena = cadena + "x" + " - ";
			}else if(representantes[i].getCiudad()==null && i!=representantes.length-1){
				cadena = cadena + "x";
			}
		}
		System.out.println(cadena);
		
		/*
		 * Volvemos a hacer por segunda vez la seleccion de representantes
		 */
		// PASO 5
		distancia=0;
		minimaDistancia=1000000;
		sumaDistancia=0;
		minimaDistanciaAF = 100000;
		minimaDistanciaNA = 100000;
		minimaDistanciaSA = 100000;
		minimaDistanciaAS = 100000;
		minimaDistanciaEU = 100000;
		minimaDistanciaOC = 100000;
		
		
		//Procedemos a buscar nuevos representantes
		for (int i = 0; i < plocs.length; i++) {
			for (int j = 0; j < plocs.length; j++) {
				if(plocs[i].getContinente().equalsIgnoreCase(plocs[j].getContinente())){

					distancia= Math.sqrt(Math.pow(Math.abs(plocs[i].getGps()[0]-plocs[j].getGps()[0]), 2)+
							Math.pow(Math.abs(plocs[i].getGps()[1]-plocs[j].getGps()[1]), 2));
					
					sumaDistancia+=distancia;		
				}
				if(j==plocs.length-1){
					
					if(plocs[i].getContinente().equalsIgnoreCase("AF") && sumaDistancia<minimaDistanciaAF){
						representantes[0]=plocs[i];
						minimaDistanciaAF=sumaDistancia;
					}
					if(plocs[i].getContinente().equalsIgnoreCase("NA") && sumaDistancia<minimaDistanciaNA){
						representantes[1]=plocs[i];
						minimaDistanciaNA=sumaDistancia;
					}
					if(plocs[i].getContinente().equalsIgnoreCase("SA") && sumaDistancia<minimaDistanciaSA){
						representantes[2]=plocs[i];
						minimaDistanciaSA=sumaDistancia;
					}
					if(plocs[i].getContinente().equalsIgnoreCase("AS") && sumaDistancia<minimaDistanciaAS){
						representantes[3]=plocs[i];
						minimaDistanciaAS=sumaDistancia;
					}
					if(plocs[i].getContinente().equalsIgnoreCase("EU") && sumaDistancia<minimaDistanciaEU){
						representantes[4]=plocs[i];
						minimaDistanciaEU=sumaDistancia;
					}
					if(plocs[i].getContinente().equalsIgnoreCase("OC") && sumaDistancia<minimaDistanciaOC){
						representantes[5]=plocs[i];
						minimaDistanciaOC=sumaDistancia;
					}

				}
					
			}
			sumaDistancia=0;	
		}//Fin del for para elegir nuevos representantes


		//Imprimimos los nuevos representantes
		// REPRESENTANTES DEL PASO 3
		
		cadena="REPRESENTANTES: ";
		
		for (int i = 0; i < representantes.length; i++) {
			if(representantes[i].getCiudad()!=null && i!=representantes.length-1){
				cadena = cadena + representantes[i].getCiudad() + " - ";
			}else if(representantes[i].getCiudad()!=null && i==representantes.length-1){
				cadena = cadena + representantes[i].getCiudad();
			}else if(representantes[i].getCiudad()==null && i!=representantes.length-1){
				cadena = cadena + "x" + " - ";
			}else if(representantes[i].getCiudad()==null && i!=representantes.length-1){
				cadena = cadena + "x";
			}
		}
		System.out.println(cadena);
			
		
		//Aqui separamos todos los plocs que venian sin continente individualmente siguiendo su continente final			
		
		PLoc[] plocsSinContinenteAF = new PLoc[1];
		int plocsSinContinenteIngresadosAF = 0;
		PLoc[] plocsSinContinenteNA = new PLoc[1];
		int plocsSinContinenteIngresadosNA = 0;
		PLoc[] plocsSinContinenteSA = new PLoc[1];
		int plocsSinContinenteIngresadosSA = 0;
		PLoc[] plocsSinContinenteAS = new PLoc[1];
		int plocsSinContinenteIngresadosAS = 0;
		PLoc[] plocsSinContinenteEU = new PLoc[1];
		int plocsSinContinenteIngresadosEU = 0;
		PLoc[] plocsSinContinenteOC = new PLoc[1];
		int plocsSinContinenteIngresadosOC = 0;
		
		/*
		 * Como ya he usado anteriormente, recorreremos el array de plocsSinContinente (que ahora si tienen continente) y dependiendo de su string
		 * los introduciremos en uno de los 6 nuevos arrays para diferenciarlos por su continente. Redimensionaremos si es necesario
		 */
		for (int j = 0; j < plocsSinContinente.length; j++) {
			
			if(plocsSinContinente[j].getContinente().equalsIgnoreCase("AF")){
				
				if(plocsSinContinenteIngresadosAF<plocsSinContinenteAF.length){
					plocsSinContinenteAF[plocsSinContinenteIngresadosAF]=plocsSinContinente[j];
					plocsSinContinenteIngresadosAF++;
				}else{
					//Redimensionamos
					PLoc[] tmp = new PLoc[plocsSinContinenteAF.length+1];
					int k=0;
					for (int n = 0; n < plocsSinContinenteAF.length; n++) {
						tmp[k]=plocsSinContinenteAF[n];
						k++;
					}
					tmp[k]=plocsSinContinente[j];
					plocsSinContinenteAF = tmp;
					plocsSinContinenteIngresadosAF++;
				}
				
			}
			
			if(plocsSinContinente[j].getContinente().equalsIgnoreCase("NA")){
				
				if(plocsSinContinenteIngresadosNA<plocsSinContinenteNA.length){
					plocsSinContinenteNA[plocsSinContinenteIngresadosNA]=plocsSinContinente[j];
					plocsSinContinenteIngresadosNA++;
				}else{
					//Redimensionamos
					PLoc[] tmp = new PLoc[plocsSinContinenteNA.length+1];
					int k=0;
					for (int n = 0; n < plocsSinContinenteNA.length; n++) {
						tmp[k]=plocsSinContinenteNA[n];
						k++;
					}
					tmp[k]=plocsSinContinente[j];
					plocsSinContinenteNA = tmp;
					plocsSinContinenteIngresadosNA++;
				}
				
			}
			
			if(plocsSinContinente[j].getContinente().equalsIgnoreCase("SA")){
				
				if(plocsSinContinenteIngresadosSA<plocsSinContinenteSA.length){
					plocsSinContinenteSA[plocsSinContinenteIngresadosSA]=plocsSinContinente[j];
					plocsSinContinenteIngresadosSA++;
				}else{
					//Redimensionamos
					PLoc[] tmp = new PLoc[plocsSinContinenteSA.length+1];
					int k=0;
					for (int n = 0; n < plocsSinContinenteSA.length; n++) {
						tmp[k]=plocsSinContinenteSA[n];
						k++;
					}
					tmp[k]=plocsSinContinente[j];
					plocsSinContinenteSA = tmp;
					plocsSinContinenteIngresadosSA++;
				}
				
			}
			
			if(plocsSinContinente[j].getContinente().equalsIgnoreCase("AS")){
				
				if(plocsSinContinenteIngresadosAS<plocsSinContinenteAS.length){
					plocsSinContinenteAS[plocsSinContinenteIngresadosAS]=plocsSinContinente[j];
					plocsSinContinenteIngresadosAS++;
				}else{
					//Redimensionamos
					PLoc[] tmp = new PLoc[plocsSinContinenteAS.length+1];
					int k=0;
					for (int n = 0; n < plocsSinContinenteAS.length; n++) {
						tmp[k]=plocsSinContinenteAS[n];
						k++;
					}
					tmp[k]=plocsSinContinente[j];
					plocsSinContinenteAS = tmp;
					plocsSinContinenteIngresadosAS++;
				}
				
			}
			
			if(plocsSinContinente[j].getContinente().equalsIgnoreCase("EU")){
				
				if(plocsSinContinenteIngresadosEU<plocsSinContinenteEU.length){
					plocsSinContinenteEU[plocsSinContinenteIngresadosEU]=plocsSinContinente[j];
					plocsSinContinenteIngresadosEU++;
				}else{
					//Redimensionamos
					PLoc[] tmp = new PLoc[plocsSinContinenteEU.length+1];
					int k=0;
					for (int n = 0; n < plocsSinContinenteEU.length; n++) {
						tmp[k]=plocsSinContinenteEU[n];
						k++;
					}
					tmp[k]=plocsSinContinente[j];
					plocsSinContinenteEU = tmp;
					plocsSinContinenteIngresadosEU++;
				}
				
			}
			
			if(plocsSinContinente[j].getContinente().equalsIgnoreCase("OC")){
				
				if(plocsSinContinenteIngresadosOC<plocsSinContinenteOC.length){
					plocsSinContinenteOC[plocsSinContinenteIngresadosOC]=plocsSinContinente[j];
					plocsSinContinenteIngresadosOC++;
				}else{
					//Redimensionamos
					PLoc[] tmp = new PLoc[plocsSinContinenteOC.length+1];
					int k=0;
					for (int n = 0; n < plocsSinContinenteOC.length; n++) {
						tmp[k]=plocsSinContinenteOC[n];
						k++;
					}
					tmp[k]=plocsSinContinente[j];
					plocsSinContinenteOC = tmp;
					plocsSinContinenteIngresadosOC++;
				}
				
			}
			
		}//Fin del for para separar los continentes en arrays
		
		
		//vamos a ordenarlos segun su latitud y longitud. Si son iguales S antes que N y O antes que E
		/*
		 * Ahora hacemos el meto de ordenacion de arrays. Para ello compararemos el primer ploc del array de cada continente con el inmediatamente siguiente
		 * Los ordenaremos segun dice el PDF
		 */

		// ORDENAMOS AF

		for (int i = 0; i < plocsSinContinenteAF.length; i++) {
			for (int j = i+1; j < plocsSinContinenteAF.length; j++) {
				if(j<plocsSinContinenteAF.length){
					
					if(plocsSinContinenteAF[i].getLatitud().getGrados()>plocsSinContinenteAF[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAF[i];
						plocsSinContinenteAF[i]=plocsSinContinenteAF[j];
						plocsSinContinenteAF[j]=variableauxiliar;
						
					}else if(plocsSinContinenteAF[i].getLatitud().getGrados()==plocsSinContinenteAF[j].getLatitud().getGrados()
							&& plocsSinContinenteAF[i].getLatitud().getMinutos()>plocsSinContinenteAF[j].getLatitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAF[i];
						plocsSinContinenteAF[i]=plocsSinContinenteAF[j];
						plocsSinContinenteAF[j]=variableauxiliar;
						
					}else if(plocsSinContinenteAF[i].getLatitud().getGrados()==plocsSinContinenteAF[j].getLatitud().getGrados()
							&& plocsSinContinenteAF[i].getLatitud().getMinutos()==plocsSinContinenteAF[j].getLatitud().getMinutos()
							&& plocsSinContinenteAF[i].getLatitud().getPos()=='S' && plocsSinContinenteAF[j].getLatitud().getPos()!='S'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAF[i];
						plocsSinContinenteAF[i]=plocsSinContinenteAF[j];
						plocsSinContinenteAF[j]=variableauxiliar;
					
					}else if(plocsSinContinenteAF[i].getLatitud().getGrados()==plocsSinContinenteAF[j].getLatitud().getGrados()
							&& plocsSinContinenteAF[i].getLatitud().getMinutos()==plocsSinContinenteAF[j].getLatitud().getMinutos()
							&& plocsSinContinenteAF[i].getLatitud().getPos()=='S' && plocsSinContinenteAF[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteAF[i].getLongitud().getGrados()>plocsSinContinenteAF[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAF[i];
						plocsSinContinenteAF[i]=plocsSinContinenteAF[j];
						plocsSinContinenteAF[j]=variableauxiliar;
						
					}else if(plocsSinContinenteAF[i].getLatitud().getGrados()==plocsSinContinenteAF[j].getLatitud().getGrados()
							&& plocsSinContinenteAF[i].getLatitud().getMinutos()==plocsSinContinenteAF[j].getLatitud().getMinutos()
							&& plocsSinContinenteAF[i].getLatitud().getPos()=='S' && plocsSinContinenteAF[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteAF[i].getLongitud().getGrados()==plocsSinContinenteAF[j].getLatitud().getGrados()
							&& plocsSinContinenteAF[i].getLongitud().getMinutos()>plocsSinContinenteAF[j].getLongitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAF[i];
						plocsSinContinenteAF[i]=plocsSinContinenteAF[j];
						plocsSinContinenteAF[j]=variableauxiliar;
						
					}else if(plocsSinContinenteAF[i].getLatitud().getGrados()==plocsSinContinenteAF[j].getLatitud().getGrados()
							&& plocsSinContinenteAF[i].getLatitud().getMinutos()==plocsSinContinenteAF[j].getLatitud().getMinutos()
							&& plocsSinContinenteAF[i].getLatitud().getPos()=='S' && plocsSinContinenteAF[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteAF[i].getLongitud().getGrados()==plocsSinContinenteAF[j].getLatitud().getGrados()
							&& plocsSinContinenteAF[i].getLongitud().getMinutos()==plocsSinContinenteAF[j].getLongitud().getMinutos()
							&& plocsSinContinenteAF[i].getLongitud().getPos()=='O' && plocsSinContinenteAF[j].getLongitud().getPos()!='O'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAF[i];
						plocsSinContinenteAF[i]=plocsSinContinenteAF[j];
						plocsSinContinenteAF[j]=variableauxiliar;
						
					}
					
				}	
				
			}
		}
		
		// ORDENAMOS NA
		
		for (int i = 0; i < plocsSinContinenteNA.length; i++) {
			for (int j = i+1; j < plocsSinContinenteNA.length; j++) {
				if(j<plocsSinContinenteNA.length){
					
					if(plocsSinContinenteNA[i].getLatitud().getGrados()>plocsSinContinenteNA[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteNA[i];
						plocsSinContinenteNA[i]=plocsSinContinenteNA[j];
						plocsSinContinenteNA[j]=variableauxiliar;
						
					}else if(plocsSinContinenteNA[i].getLatitud().getGrados()==plocsSinContinenteNA[j].getLatitud().getGrados()
							&& plocsSinContinenteNA[i].getLatitud().getMinutos()>plocsSinContinenteNA[j].getLatitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteNA[i];
						plocsSinContinenteNA[i]=plocsSinContinenteNA[j];
						plocsSinContinenteNA[j]=variableauxiliar;
						
					}else if(plocsSinContinenteNA[i].getLatitud().getGrados()==plocsSinContinenteNA[j].getLatitud().getGrados()
							&& plocsSinContinenteNA[i].getLatitud().getMinutos()==plocsSinContinenteNA[j].getLatitud().getMinutos()
							&& plocsSinContinenteNA[i].getLatitud().getPos()=='S' && plocsSinContinenteNA[j].getLatitud().getPos()!='S'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteNA[i];
						plocsSinContinenteNA[i]=plocsSinContinenteNA[j];
						plocsSinContinenteNA[j]=variableauxiliar;
					
					}else if(plocsSinContinenteNA[i].getLatitud().getGrados()==plocsSinContinenteNA[j].getLatitud().getGrados()
							&& plocsSinContinenteNA[i].getLatitud().getMinutos()==plocsSinContinenteNA[j].getLatitud().getMinutos()
							&& plocsSinContinenteNA[i].getLatitud().getPos()=='S' && plocsSinContinenteNA[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteNA[i].getLongitud().getGrados()>plocsSinContinenteNA[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteNA[i];
						plocsSinContinenteNA[i]=plocsSinContinenteNA[j];
						plocsSinContinenteNA[j]=variableauxiliar;
						
					}else if(plocsSinContinenteNA[i].getLatitud().getGrados()==plocsSinContinenteNA[j].getLatitud().getGrados()
							&& plocsSinContinenteNA[i].getLatitud().getMinutos()==plocsSinContinenteNA[j].getLatitud().getMinutos()
							&& plocsSinContinenteNA[i].getLatitud().getPos()=='S' && plocsSinContinenteNA[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteNA[i].getLongitud().getGrados()==plocsSinContinenteNA[j].getLatitud().getGrados()
							&& plocsSinContinenteNA[i].getLongitud().getMinutos()>plocsSinContinenteNA[j].getLongitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteNA[i];
						plocsSinContinenteNA[i]=plocsSinContinenteNA[j];
						plocsSinContinenteNA[j]=variableauxiliar;
						
					}else if(plocsSinContinenteNA[i].getLatitud().getGrados()==plocsSinContinenteNA[j].getLatitud().getGrados()
							&& plocsSinContinenteNA[i].getLatitud().getMinutos()==plocsSinContinenteNA[j].getLatitud().getMinutos()
							&& plocsSinContinenteNA[i].getLatitud().getPos()=='S' && plocsSinContinenteNA[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteNA[i].getLongitud().getGrados()==plocsSinContinenteNA[j].getLatitud().getGrados()
							&& plocsSinContinenteNA[i].getLongitud().getMinutos()==plocsSinContinenteNA[j].getLongitud().getMinutos()
							&& plocsSinContinenteNA[i].getLongitud().getPos()=='O' && plocsSinContinenteNA[j].getLongitud().getPos()!='O'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteNA[i];
						plocsSinContinenteNA[i]=plocsSinContinenteNA[j];
						plocsSinContinenteNA[j]=variableauxiliar;
						
					}
					
				}	
				
			}
		}
		
		// ORDENAMOS SA
		
		for (int i = 0; i < plocsSinContinenteSA.length; i++) {
			for (int j = i+1; j < plocsSinContinenteSA.length; j++) {
				if(j<plocsSinContinenteSA.length){
					
					if(plocsSinContinenteSA[i].getLatitud().getGrados()>plocsSinContinenteSA[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteSA[i];
						plocsSinContinenteSA[i]=plocsSinContinenteSA[j];
						plocsSinContinenteSA[j]=variableauxiliar;
						
					}else if(plocsSinContinenteSA[i].getLatitud().getGrados()==plocsSinContinenteSA[j].getLatitud().getGrados()
							&& plocsSinContinenteSA[i].getLatitud().getMinutos()>plocsSinContinenteSA[j].getLatitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteSA[i];
						plocsSinContinenteSA[i]=plocsSinContinenteSA[j];
						plocsSinContinenteSA[j]=variableauxiliar;
						
					}else if(plocsSinContinenteSA[i].getLatitud().getGrados()==plocsSinContinenteSA[j].getLatitud().getGrados()
							&& plocsSinContinenteSA[i].getLatitud().getMinutos()==plocsSinContinenteSA[j].getLatitud().getMinutos()
							&& plocsSinContinenteSA[i].getLatitud().getPos()=='S' && plocsSinContinenteSA[j].getLatitud().getPos()!='S'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteSA[i];
						plocsSinContinenteSA[i]=plocsSinContinenteSA[j];
						plocsSinContinenteSA[j]=variableauxiliar;
					
					}else if(plocsSinContinenteSA[i].getLatitud().getGrados()==plocsSinContinenteSA[j].getLatitud().getGrados()
							&& plocsSinContinenteSA[i].getLatitud().getMinutos()==plocsSinContinenteSA[j].getLatitud().getMinutos()
							&& plocsSinContinenteSA[i].getLatitud().getPos()=='S' && plocsSinContinenteSA[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteSA[i].getLongitud().getGrados()>plocsSinContinenteSA[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteSA[i];
						plocsSinContinenteSA[i]=plocsSinContinenteSA[j];
						plocsSinContinenteSA[j]=variableauxiliar;
						
					}else if(plocsSinContinenteSA[i].getLatitud().getGrados()==plocsSinContinenteSA[j].getLatitud().getGrados()
							&& plocsSinContinenteSA[i].getLatitud().getMinutos()==plocsSinContinenteSA[j].getLatitud().getMinutos()
							&& plocsSinContinenteSA[i].getLatitud().getPos()=='S' && plocsSinContinenteSA[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteSA[i].getLongitud().getGrados()==plocsSinContinenteSA[j].getLatitud().getGrados()
							&& plocsSinContinenteSA[i].getLongitud().getMinutos()>plocsSinContinenteSA[j].getLongitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteSA[i];
						plocsSinContinenteSA[i]=plocsSinContinenteSA[j];
						plocsSinContinenteSA[j]=variableauxiliar;
						
					}else if(plocsSinContinenteSA[i].getLatitud().getGrados()==plocsSinContinenteSA[j].getLatitud().getGrados()
							&& plocsSinContinenteSA[i].getLatitud().getMinutos()==plocsSinContinenteSA[j].getLatitud().getMinutos()
							&& plocsSinContinenteSA[i].getLatitud().getPos()=='S' && plocsSinContinenteSA[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteSA[i].getLongitud().getGrados()==plocsSinContinenteSA[j].getLatitud().getGrados()
							&& plocsSinContinenteSA[i].getLongitud().getMinutos()==plocsSinContinenteSA[j].getLongitud().getMinutos()
							&& plocsSinContinenteSA[i].getLongitud().getPos()=='O' && plocsSinContinenteSA[j].getLongitud().getPos()!='O'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteSA[i];
						plocsSinContinenteSA[i]=plocsSinContinenteSA[j];
						plocsSinContinenteSA[j]=variableauxiliar;
						
					}
					
				}	
				
			}
		}
		
		// ORDENAMOS AS
		
		for (int i = 0; i < plocsSinContinenteAS.length; i++) {
			for (int j = i+1; j < plocsSinContinenteAS.length; j++) {
				if(j<plocsSinContinenteAS.length){
					
					if(plocsSinContinenteAS[i].getLatitud().getGrados()>plocsSinContinenteAS[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAS[i];
						plocsSinContinenteAS[i]=plocsSinContinenteAS[j];
						plocsSinContinenteAS[j]=variableauxiliar;
						
					}else if(plocsSinContinenteAS[i].getLatitud().getGrados()==plocsSinContinenteAS[j].getLatitud().getGrados()
							&& plocsSinContinenteAS[i].getLatitud().getMinutos()>plocsSinContinenteAS[j].getLatitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAS[i];
						plocsSinContinenteAS[i]=plocsSinContinenteAS[j];
						plocsSinContinenteAS[j]=variableauxiliar;
						
					}else if(plocsSinContinenteAS[i].getLatitud().getGrados()==plocsSinContinenteAS[j].getLatitud().getGrados()
							&& plocsSinContinenteAS[i].getLatitud().getMinutos()==plocsSinContinenteAS[j].getLatitud().getMinutos()
							&& plocsSinContinenteAS[i].getLatitud().getPos()=='S' && plocsSinContinenteAS[j].getLatitud().getPos()!='S'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAS[i];
						plocsSinContinenteAS[i]=plocsSinContinenteAS[j];
						plocsSinContinenteAS[j]=variableauxiliar;
					
					}else if(plocsSinContinenteAS[i].getLatitud().getGrados()==plocsSinContinenteAS[j].getLatitud().getGrados()
							&& plocsSinContinenteAS[i].getLatitud().getMinutos()==plocsSinContinenteAS[j].getLatitud().getMinutos()
							&& plocsSinContinenteAS[i].getLatitud().getPos()=='S' && plocsSinContinenteAS[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteAS[i].getLongitud().getGrados()>plocsSinContinenteAS[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAS[i];
						plocsSinContinenteAS[i]=plocsSinContinenteAS[j];
						plocsSinContinenteAS[j]=variableauxiliar;
						
					}else if(plocsSinContinenteAS[i].getLatitud().getGrados()==plocsSinContinenteAS[j].getLatitud().getGrados()
							&& plocsSinContinenteAS[i].getLatitud().getMinutos()==plocsSinContinenteAS[j].getLatitud().getMinutos()
							&& plocsSinContinenteAS[i].getLatitud().getPos()=='S' && plocsSinContinenteAS[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteAS[i].getLongitud().getGrados()==plocsSinContinenteAS[j].getLatitud().getGrados()
							&& plocsSinContinenteAS[i].getLongitud().getMinutos()>plocsSinContinenteAS[j].getLongitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAS[i];
						plocsSinContinenteAS[i]=plocsSinContinenteAS[j];
						plocsSinContinenteAS[j]=variableauxiliar;
						
					}else if(plocsSinContinenteAS[i].getLatitud().getGrados()==plocsSinContinenteAS[j].getLatitud().getGrados()
							&& plocsSinContinenteAS[i].getLatitud().getMinutos()==plocsSinContinenteAS[j].getLatitud().getMinutos()
							&& plocsSinContinenteAS[i].getLatitud().getPos()=='S' && plocsSinContinenteAS[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteAS[i].getLongitud().getGrados()==plocsSinContinenteAS[j].getLatitud().getGrados()
							&& plocsSinContinenteAS[i].getLongitud().getMinutos()==plocsSinContinenteAS[j].getLongitud().getMinutos()
							&& plocsSinContinenteAS[i].getLongitud().getPos()=='O' && plocsSinContinenteAS[j].getLongitud().getPos()!='O'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteAS[i];
						plocsSinContinenteAS[i]=plocsSinContinenteAS[j];
						plocsSinContinenteAS[j]=variableauxiliar;
						
					}
					
				}	
				
			}
		}
		
		// ORDENAMOS EU
		
		
		
		for (int i = 0; i < plocsSinContinenteEU.length; i++) {
			for (int j = i+1; j < plocsSinContinenteEU.length; j++) {
				if(j<plocsSinContinenteEU.length){
					
					if(plocsSinContinenteEU[i].getLatitud().getGrados()>plocsSinContinenteEU[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteEU[i];
						plocsSinContinenteEU[i]=plocsSinContinenteEU[j];
						plocsSinContinenteEU[j]=variableauxiliar;
						
					}else if(plocsSinContinenteEU[i].getLatitud().getGrados()==plocsSinContinenteEU[j].getLatitud().getGrados()
							&& plocsSinContinenteEU[i].getLatitud().getMinutos()>plocsSinContinenteEU[j].getLatitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteEU[i];
						plocsSinContinenteEU[i]=plocsSinContinenteEU[j];
						plocsSinContinenteEU[j]=variableauxiliar;
						
					}else if(plocsSinContinenteEU[i].getLatitud().getGrados()==plocsSinContinenteEU[j].getLatitud().getGrados()
							&& plocsSinContinenteEU[i].getLatitud().getMinutos()==plocsSinContinenteEU[j].getLatitud().getMinutos()
							&& plocsSinContinenteEU[i].getLatitud().getPos()=='S' && plocsSinContinenteEU[j].getLatitud().getPos()!='S'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteEU[i];
						plocsSinContinenteEU[i]=plocsSinContinenteEU[j];
						plocsSinContinenteEU[j]=variableauxiliar;
					
					}else if(plocsSinContinenteEU[i].getLatitud().getGrados()==plocsSinContinenteEU[j].getLatitud().getGrados()
							&& plocsSinContinenteEU[i].getLatitud().getMinutos()==plocsSinContinenteEU[j].getLatitud().getMinutos()
							&& plocsSinContinenteEU[i].getLatitud().getPos()=='S' && plocsSinContinenteEU[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteEU[i].getLongitud().getGrados()>plocsSinContinenteEU[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteEU[i];
						plocsSinContinenteEU[i]=plocsSinContinenteEU[j];
						plocsSinContinenteEU[j]=variableauxiliar;
						
					}else if(plocsSinContinenteEU[i].getLatitud().getGrados()==plocsSinContinenteEU[j].getLatitud().getGrados()
							&& plocsSinContinenteEU[i].getLatitud().getMinutos()==plocsSinContinenteEU[j].getLatitud().getMinutos()
							&& plocsSinContinenteEU[i].getLatitud().getPos()=='S' && plocsSinContinenteEU[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteEU[i].getLongitud().getGrados()==plocsSinContinenteEU[j].getLatitud().getGrados()
							&& plocsSinContinenteEU[i].getLongitud().getMinutos()>plocsSinContinenteEU[j].getLongitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteEU[i];
						plocsSinContinenteEU[i]=plocsSinContinenteEU[j];
						plocsSinContinenteEU[j]=variableauxiliar;
						
					}else if(plocsSinContinenteEU[i].getLatitud().getGrados()==plocsSinContinenteEU[j].getLatitud().getGrados()
							&& plocsSinContinenteEU[i].getLatitud().getMinutos()==plocsSinContinenteEU[j].getLatitud().getMinutos()
							&& plocsSinContinenteEU[i].getLatitud().getPos()=='S' && plocsSinContinenteEU[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteEU[i].getLongitud().getGrados()==plocsSinContinenteEU[j].getLatitud().getGrados()
							&& plocsSinContinenteEU[i].getLongitud().getMinutos()==plocsSinContinenteEU[j].getLongitud().getMinutos()
							&& plocsSinContinenteEU[i].getLongitud().getPos()=='O' && plocsSinContinenteEU[j].getLongitud().getPos()!='O'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteEU[i];
						plocsSinContinenteEU[i]=plocsSinContinenteEU[j];
						plocsSinContinenteEU[j]=variableauxiliar;
						
					}
					
				}	
				
			}
		}
		
		// ORDENAMOS OC
		
		for (int i = 0; i < plocsSinContinenteOC.length; i++) {
			for (int j = i+1; j < plocsSinContinenteOC.length; j++) {
				if(j<plocsSinContinenteOC.length){
					
					if(plocsSinContinenteOC[i].getLatitud().getGrados()>plocsSinContinenteOC[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteOC[i];
						plocsSinContinenteOC[i]=plocsSinContinenteOC[j];
						plocsSinContinenteOC[j]=variableauxiliar;
						
					}else if(plocsSinContinenteOC[i].getLatitud().getGrados()==plocsSinContinenteOC[j].getLatitud().getGrados()
							&& plocsSinContinenteOC[i].getLatitud().getMinutos()>plocsSinContinenteOC[j].getLatitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteOC[i];
						plocsSinContinenteOC[i]=plocsSinContinenteOC[j];
						plocsSinContinenteOC[j]=variableauxiliar;
						
					}else if(plocsSinContinenteOC[i].getLatitud().getGrados()==plocsSinContinenteOC[j].getLatitud().getGrados()
							&& plocsSinContinenteOC[i].getLatitud().getMinutos()==plocsSinContinenteOC[j].getLatitud().getMinutos()
							&& plocsSinContinenteOC[i].getLatitud().getPos()=='S' && plocsSinContinenteOC[j].getLatitud().getPos()!='S'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteOC[i];
						plocsSinContinenteOC[i]=plocsSinContinenteOC[j];
						plocsSinContinenteOC[j]=variableauxiliar;
					
					}else if(plocsSinContinenteOC[i].getLatitud().getGrados()==plocsSinContinenteOC[j].getLatitud().getGrados()
							&& plocsSinContinenteOC[i].getLatitud().getMinutos()==plocsSinContinenteOC[j].getLatitud().getMinutos()
							&& plocsSinContinenteOC[i].getLatitud().getPos()=='S' && plocsSinContinenteOC[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteOC[i].getLongitud().getGrados()>plocsSinContinenteOC[j].getLatitud().getGrados()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteOC[i];
						plocsSinContinenteOC[i]=plocsSinContinenteOC[j];
						plocsSinContinenteOC[j]=variableauxiliar;
						
					}else if(plocsSinContinenteOC[i].getLatitud().getGrados()==plocsSinContinenteOC[j].getLatitud().getGrados()
							&& plocsSinContinenteOC[i].getLatitud().getMinutos()==plocsSinContinenteOC[j].getLatitud().getMinutos()
							&& plocsSinContinenteOC[i].getLatitud().getPos()=='S' && plocsSinContinenteOC[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteOC[i].getLongitud().getGrados()==plocsSinContinenteOC[j].getLatitud().getGrados()
							&& plocsSinContinenteOC[i].getLongitud().getMinutos()>plocsSinContinenteOC[j].getLongitud().getMinutos()){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteOC[i];
						plocsSinContinenteOC[i]=plocsSinContinenteOC[j];
						plocsSinContinenteOC[j]=variableauxiliar;
						
					}else if(plocsSinContinenteOC[i].getLatitud().getGrados()==plocsSinContinenteOC[j].getLatitud().getGrados()
							&& plocsSinContinenteOC[i].getLatitud().getMinutos()==plocsSinContinenteOC[j].getLatitud().getMinutos()
							&& plocsSinContinenteOC[i].getLatitud().getPos()=='S' && plocsSinContinenteOC[j].getLatitud().getPos()=='S'
							&& plocsSinContinenteOC[i].getLongitud().getGrados()==plocsSinContinenteOC[j].getLatitud().getGrados()
							&& plocsSinContinenteOC[i].getLongitud().getMinutos()==plocsSinContinenteOC[j].getLongitud().getMinutos()
							&& plocsSinContinenteOC[i].getLongitud().getPos()=='O' && plocsSinContinenteOC[j].getLongitud().getPos()!='O'){
						
						PLoc variableauxiliar = new PLoc();
						variableauxiliar=plocsSinContinenteOC[i];
						plocsSinContinenteOC[i]=plocsSinContinenteOC[j];
						plocsSinContinenteOC[j]=variableauxiliar;
						
					}
					
				}	
				
			}
		}
		
		
		/*
		 * FIN DE ORDENAR LOS ARRAYS
		 */
		
		//Imprimimos los plocs sin continentes al final teniendolos ya escogidos
		
		/*
		 * Creamos las cadenas con las ciudades. Para ello recorremos los arrays ya ordenados y formamos los strings
		 */
		cadena = "AF: ";
		for (int i = 0; i < plocsSinContinenteAF.length; i++) {
			if(i!=plocsSinContinenteAF.length-1){
				cadena = cadena + plocsSinContinenteAF[i].getCiudad() +" ";
			}else{
				cadena = cadena + plocsSinContinenteAF[i].getCiudad();
			}
		}
		System.out.println(cadena);
		
		cadena = "NA: ";
		for (int i = 0; i < plocsSinContinenteNA.length; i++) {
			if(i!=plocsSinContinenteNA.length-1){
				cadena = cadena + plocsSinContinenteNA[i].getCiudad() +" ";
			}else{
				cadena = cadena + plocsSinContinenteNA[i].getCiudad();
			}
		}
		System.out.println(cadena);
		
		cadena = "SA: ";
		for (int i = 0; i < plocsSinContinenteSA.length; i++) {
			if(i!=plocsSinContinenteSA.length-1){
				cadena = cadena + plocsSinContinenteSA[i].getCiudad() +" ";
			}else{
				cadena = cadena + plocsSinContinenteSA[i].getCiudad();
			}
		}
		System.out.println(cadena);
		
		cadena = "AS: ";
		for (int i = 0; i < plocsSinContinenteAS.length; i++) {
			if(i!=plocsSinContinenteAS.length-1){
				cadena = cadena + plocsSinContinenteAS[i].getCiudad() +" ";
			}else{
				cadena = cadena + plocsSinContinenteAS[i].getCiudad();
			}
		}
		System.out.println(cadena);
		
		cadena = "EU: ";
		for (int i = 0; i < plocsSinContinenteEU.length; i++) {
			if(i!=plocsSinContinenteEU.length-1){
				cadena = cadena + plocsSinContinenteEU[i].getCiudad() +" ";
			}else{
				cadena = cadena + plocsSinContinenteEU[i].getCiudad();
			}
		}
		System.out.println(cadena);
		
		cadena = "OC: ";
		for (int i = 0; i < plocsSinContinenteOC.length; i++) {
			if(i!=plocsSinContinenteOC.length-1){
				cadena = cadena + plocsSinContinenteOC[i].getCiudad() +" ";
			}else{
				cadena = cadena + plocsSinContinenteOC[i].getCiudad();
			}
		}
		System.out.println(cadena);
		
	}//fin del main

}
