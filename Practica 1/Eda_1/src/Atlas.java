//DNI 20084606 Rubio Martinez, Ruben
import java.io.*;


public class Atlas {
	private PLoc[][] local;
	
	public Atlas(){
		local = new PLoc[181][361];
	}
	
	//done 
	boolean setLocalidad(PLoc p){
		boolean devolucion=false;
		int x = 0,y = 0;
		if(p!=null){
			if(p.getLatitud()!=null && p.getLongitud()!=null){
				int[] coordenadas = new int[2];
				coordenadas = transformacion(p);
				x=coordenadas[0];
				y=coordenadas[1];
				if(local[x][y]==null){
					local[x][y] = p;
					devolucion = true;

				}
			}
		}
		
		
		return devolucion;
	}
	
	//NEW realiza las transformaciones de grados a valores sexagesimales
	private int[] transformacion(PLoc p){
		int[] devolucion = new int[2];
		/*
		 * No debo comprobar los rangos de grados, minutos y posicion
		 * porque al crearlos en PLoc ya se comprueba que esten en rango
		 * y si no es asi se lanza la excepcion y no se crean
		 */
		if(p.getLatitud().getPos()=='N' && p.getLongitud().getPos()=='E'){
			devolucion[0] = p.getLatitud().getGrados() + 90;
			devolucion[1] = p.getLongitud().getGrados() + 180;
		}
		
		if(p.getLatitud().getPos()=='N' && p.getLongitud().getPos()=='O'){
			devolucion[0] = p.getLatitud().getGrados() + 90;
			devolucion[1] = 180 - p.getLongitud().getGrados() ;
		}
		
		if(p.getLatitud().getPos()=='S' && p.getLongitud().getPos()=='E'){
			devolucion[0] = 90 - p.getLatitud().getGrados();
			devolucion[1] = p.getLongitud().getGrados() + 180;
		}

		if(p.getLatitud().getPos()=='S' && p.getLongitud().getPos()=='O'){
			devolucion[0] = 90 - p.getLatitud().getGrados();
			devolucion[1] = 180 - p.getLongitud().getGrados();
		}
		
		return devolucion;
	}
	
	/*
	 * Mediante la libreria java.io.* podemos usar los objetos bufferedReader y FileReader.
	 * Esto nos servira para leer el archivo pasado por parametro. A su vez he implementado un ciclo while
	 * que ira leyendo el archivo hasta que el cotenido sea null. Ya dentro del while usamos el metodo split
	 * para poder separar la informacion y poder tratarla independientemente
	 */
	public void leeAtlas(String f){
		BufferedReader lec = null;
		FileReader fe = null;
		
		if(f!=null){
			try {
				fe = new FileReader(f);
				lec = new BufferedReader(fe);
				String leida = lec.readLine();
				String[] elems,coordenadas;
				PLoc pl;
;
				
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
						
						//los introduce en el local[i][j]
						setLocalidad(pl);
						
						//al final volvemos a leer la siguiente linea
						leida = lec.readLine();
;
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
	
	
	//devuelve la ciudad del ploc pasado por parametro 
	public String consultaAtlas(PLoc f){
		String devolucion = null;
		
		if(f!=null){
			for (int i = 0; i < local.length; i++) {
				for (int j = 0; j < local[0].length; j++) {
					if(local[i][j]!=null){
						if(f.getCiudad()!=null){
							if(f.getCiudad().equalsIgnoreCase(local[i][j].getCiudad())){
								if(f.getLongitud()!=null && f.getLongitud()!=null){
									devolucion = f.getCiudad();
								}
								
							}
						}
						
					}
				}
			}
		}
		
		return devolucion;
	}
	
	/*
	 * Para implementar este metodo he decidido crear una matriz y rellenarla de puntos,
	 * mas adelante he buscado el ploc central y mediante aplicaciones de formulas basicas he asignado cual seria el comienzo y el final de la submatriz.
	 * Luego una vez tenemos las coordenadas donde empezar y acabar en la matriz grande nos situamos en el inicio y la recorremos hasta el subfinal
	 * para asi poder asignar las iniciales de las ciudades que encuentre usando CharAt(0) que nos devuelve la inicial del String
	 * Por ultimo recorremos el string de la submatriz para mostrarlo por pantalla
	 */
	public void muestraAtlasParcial(PLoc f, int n){
		int posicionCentralX=0;
		int posicionCentralY=0;

		if(f!=null){
			if(f.getLatitud()!=null && f.getLongitud()!=null){
				int[] coordenadas = transformacion(f);
				
				if(n>=0 && n<=90){
					char[][] submatriz = new char[(2*n)+1][(2*n)+1];
					
					for (int i = 0; i < submatriz.length; i++) {
						for (int j = 0; j < submatriz[0].length; j++) {
							submatriz[i][j]='.';
						}
					}
					
					

					//buscamos la posicion en la matriz local del PLoc pasado
					for (int i = 0; i < local.length; i++) {
						for (int j = 0; j < local[0].length; j++) {
							if(local[i][j]!=null){
								if(local[i][j].getCiudad()!=null){
									if(coordenadas[0] == i && coordenadas[1] == j){

										posicionCentralX=i;
										posicionCentralY=j;
										i=local.length;
										j=local[0].length;
										//submatriz[n][n]=local[posicionCentralX][posicionCentralY].getCiudad().charAt(0);
									}
								}else{
									if(coordenadas[0] == i && coordenadas[1] == j){

										posicionCentralX=i;
										posicionCentralY=j;
										i=local.length;
										j=local[0].length;
										submatriz[n][n]='.';
									}
								}
							}
						}
					}
					
					//Todos estos datos estan en regla asi
					int posicionInicialX = posicionCentralX-(n);
					int posicionInicialY = posicionCentralY-(n);
					int posicionFinalX = posicionCentralX+(n);
					int posicionFinalY = posicionCentralY+(n);
					int posicionActualX;
					int posicionActualY;
					int k = 0,m = 0;
					
					/*
					System.out.println("Inicio x: "+posicionInicialX);
					System.out.println("Inicio y: "+posicionInicialY);
					System.out.println("Final x: "+posicionFinalX);
					System.out.println("Final y: "+posicionFinalY);
					System.out.println("Referencia x: "+posicionCentralX);
					System.out.println("Referencia y: "+posicionCentralY);
					System.out.println("Longitud de la matriz: "+submatriz.length);
					*/
					
					
				
					for (posicionActualX=posicionInicialX; posicionActualX <= posicionFinalX; posicionActualX++) {
						
						for (posicionActualY=posicionInicialY; posicionActualY <= posicionFinalY; posicionActualY++) {
							
							
							if(posicionActualX>=0 && posicionActualY>=0 && posicionActualX<local.length && posicionActualY<local[0].length){
								
								
								if(local[posicionActualX][posicionActualY]!=null){
									
									if(local[posicionActualX][posicionActualY].getCiudad()!=null){
										
										
										submatriz[k][m]=local[posicionActualX][posicionActualY].getCiudad().charAt(0);
									}
								}
							}
							
							m++;
							
						}
						m=0;
						k++;
						
						
					}
					
					//mostramos la submatriz
					for (int i = 0; i < submatriz.length; i++) {
						for (int j = 0; j < submatriz[0].length; j++) {
							System.out.print(submatriz[i][j]);
							
						}
						System.out.println();
					}
					
					
					
				}else{
					System.out.println("NO ES DE ESTE PLANETA");
				}
			}else{
				System.out.println("NO ES DE ESTE PLANETA");
			}	
		}else{
			System.out.println("NO ES DE ESTE PLANETA");
		}
	}
	
	//NEW devuelve el local de la clase Atlas
	public PLoc[][] getLocal(){
		return local;
	}
	
	
}
