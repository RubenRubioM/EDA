//DNI 20084606 Rubio Martinez, Ruben
import java.text.*;
import java.util.*;

public class PLoc implements Comparable<PLoc>{
	private String continente;
	private String pais;
	private String ciudad;
	private Coordenada latitud; //coordenadas latitud en grados
	private Coordenada longitud; //coordenadas longitud en grados
	private double[] gps;  //coordenadas gps(decimales) latitud y longitud
	
	//constructor inicializando todo a nulos
	public PLoc(){
		continente = null;
		pais = null;
		ciudad = null;
		Coordenada latitud = null;
		Coordenada longitud = null;
		gps = new double[2];
	}
	
	
	//constructor inicializando las variables pasando valores por parametro
	public PLoc(String c, String p, String l){
		continente = c;
		pais = p;
		ciudad = l;
		Coordenada latitud = null;
		Coordenada longitud = null;
		gps = new double[2];
	}
	

	/*
	 * Para asignar una Coordenada a la latitud hemos creado una clase CoordenadaExcepcion que
	 * tratara los rangos invalidos a la hora de asignarlo mediante el lanzamiento de excepciones
	 */
	public void setLatitud(Coordenada p) throws CoordenadaExcepcion{
	
		//comprobamos que los grados esten en su rango (0-90)
		if(p!=null){
			if(p.getGrados()<=90 && p.getGrados()>=0){
				//comprobamos que los minutos esten en su rango (0-59)
				if(p.getMinutos()<=59 && p.getMinutos()>=0){
					//comprobamos que la letra sea N o S
					if(p.getPos()=='N' || p.getPos()=='S'){
							latitud = new Coordenada(p.getGrados(),p.getMinutos(),p.getPos());
							formarGps(p);
					}else{
						String frase = "Latitud posicion "+p.getPos();
						throw new CoordenadaExcepcion(frase);
					}
				}else{
					String frase = "Latitud minutos "+p.getMinutos();
					throw new CoordenadaExcepcion(frase);
				}		
			}else{
				String frase = "Latitud grados "+p.getGrados();
				throw new CoordenadaExcepcion(frase);
			}
		}
		
		
	}
	
	
	//done - nunca se pasara un nulo aqui
	public void setLongitud(Coordenada p) throws CoordenadaExcepcion{
		
		//comprobamos que los grados esten en su rango (0-180)
		if(p!=null){
			if(p.getGrados()<=180 && p.getGrados()>=0){
				//comprobamos que los minutos esten en su rango (0-59)
				if(p.getMinutos()<=59 && p.getMinutos()>=0){
					//comprobamos que la letra sea E o O
					if(p.getPos()=='E' || p.getPos()=='O'){
							longitud = new Coordenada(p.getGrados(),p.getMinutos(),p.getPos());
							formarGps(p);
					}else{
						String frase = "Longitud posicion "+p.getPos();
						throw new CoordenadaExcepcion(frase);
					}
				}else{
					String frase = "Longitud minutos "+p.getMinutos();
					throw new CoordenadaExcepcion(frase);
				}		
			}else{
				String frase = "Longitud grados "+p.getGrados();
				throw new CoordenadaExcepcion(frase);
			}
		}
		
	}
	
	
	//NEW aplica la formula para transformar las coordenadas a valores del gps
	public void formarGps(Coordenada p){
		
		double valor = 0;
		//es latitud
		if(p.getPos()=='S' || p.getPos()=='N'){
			valor = p.getGrados()+(p.getMinutos()/60.0);
			//es negativo
			if(p.getPos()=='S'){
				valor = -valor;
			}
			gps[0] = valor;
		}
		
		//es longitud
		if(p.getPos()=='E' || p.getPos()=='O'){
			valor = p.getGrados()+(p.getMinutos()/60.0);
			//es negativo
			if(p.getPos()=='O'){
				valor = -valor;
			}
			gps[1] = valor;
		}

		
		
	}
	
	
	/*
	 * NEW realiza el redondeo del gps para mostrar por pantalla solo 2 valores decimales, 
	 * para ello multiplicamos por 100, convertimos en entero y dividimos por 100 el valor
	 */
	public double redondeo(double redondeo){
		
		redondeo = redondeo*100;
		redondeo = (int) redondeo;
		redondeo = (double) redondeo/100.0;

		return redondeo;
		
	}
	
	/*
	 * NEW realiza el redondeo del gps para mostrar por pantalla solo 2 valores decimales, 
	 * para ello utilizamos las librerias java.text y java.util
	 */
	public String mrf(double db){
		Locale lengua=new Locale("en");
		DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
		DecimalFormat formato=new DecimalFormat("0.00",chars);

		return formato.format(db);
	}
	
	
	public void escribeInfoGrados(){
		
		String mostrar = null;
		
		if(continente==null){
			mostrar = "x - ";
		}else{
			mostrar = continente + " - ";
		}
		
		if(pais==null){
			mostrar = mostrar + "x - ";
		}else{
			mostrar = mostrar + pais + " - ";
		}
		
		if(ciudad==null){
			mostrar = mostrar + "x - ";
		}else{
			mostrar = mostrar + ciudad + " - ";
		}
		
		mostrar = mostrar + latitud.getGrados() + " " + latitud.getMinutos() + " " + latitud.getPos() + " - ";
		mostrar = mostrar + longitud.getGrados() + " " + longitud.getMinutos() + " " + longitud.getPos();
		System.out.println(mostrar);
	}
	
	
	/*
	 * Se forma un String con los datos del PLoc para mostrarlo por pantalla. 
	 * He usado mi propio metodo llamado "mrf" para redondear a 2 decimales las coordenadas
	 * del gps
	 */
	public void escribeInfoGps(){
		
		String mostrar = null;
		
		if(continente==null){
			mostrar = "x - ";
		}else{
			mostrar = continente + " - ";
		}
		
		if(pais==null){
			mostrar = mostrar + "x - ";
		}else{
			mostrar = mostrar + pais + " - ";
		}
		
		if(ciudad==null){
			mostrar = mostrar + "x - ";
		}else{
			mostrar = mostrar + ciudad + " - ";
		}
		
		mostrar = mostrar + mrf(gps[0]) + " - ";
		mostrar = mostrar + mrf(gps[1]);
		System.out.println(mostrar);
		
	}
	
	
	public String getContinente(){
		return continente;
	}
	
	public String getPais(){
		return pais;
	}
	
	public String getCiudad(){
		return ciudad;
	}
	
	public Coordenada getLatitud(){
		return latitud;
	}
	
	public Coordenada getLongitud(){
		return longitud;
	}
	
	public double[] getGps(){
		double[] devolucion=null;
		if(longitud==null || latitud==null){
			devolucion=null;
		}else if(longitud!=null && latitud!=null){
			devolucion=gps;
		}
		return devolucion;
	}
	
	
	//NEW compara los PLocs, para ello comparamos una a una sus componentes
	public boolean iguales(PLoc p){
		boolean devolucion=false;
		
		if(p.getCiudad()!=null && p.getCiudad().equalsIgnoreCase(this.getCiudad())){
			if(p.getContinente()!=null && p.getContinente().equalsIgnoreCase(this.getContinente())){
				if((p.getGps()[0] == this.getGps()[0]) && (p.getGps()[1] == this.getGps()[1])){
					if(p.getContinente()!=null && p.getLatitud().iguales(this.getLatitud())){
						if(p.getLongitud().iguales(this.getLongitud())){
							devolucion=true;
						}
					}
				}
			}
		}
		
		return devolucion;
	}
	
	//NEW asigna una string al continte de la clase
	public void setContinente(String c){
		continente=c;
	}
	
	//NEW asigna valores al GPS de la clase
	public void setGPS(double uno, double dos){
		gps[0]=uno;
		gps[1]=dos;
	}
	
	//done
	//TODO preguntar alicia si tengo que hacerlo asi
	/*
	public int compareTo(PLoc p){
		int devolucion=0;
		
		if(this.gps[1]>p.getGps()[1]){
			devolucion=1;
		}else if(this.gps[1]<p.getGps()[1]){
			devolucion=-1;
		}else if(this.gps[1]==p.getGps()[1]){
			if(this.ciudad.compareTo(p.getCiudad())<0){
				devolucion=-1;
			}
			if(this.ciudad.compareTo(p.getCiudad())>0){
				devolucion=1;
			}
			if(this.ciudad.compareTo(p.getCiudad())==0){
				devolucion=0;
			}
		}
		
		return devolucion;
	}*/




	@Override
	public int compareTo(PLoc p) {
		int devolucion=0;
		if(p!=null){
			if(this.gps[1]>p.getGps()[1]){
				devolucion=1;
			}else if(this.gps[1]<p.getGps()[1]){
				devolucion=-1;
			}else if(this.gps[1]==p.getGps()[1]){
				if(this.ciudad.compareTo(p.getCiudad())<0){
					devolucion=-1;
				}
				if(this.ciudad.compareTo(p.getCiudad())>0){
					devolucion=1;
				}
				if(this.ciudad.compareTo(p.getCiudad())==0){
					devolucion=0;
				}
			}
		}
		
		
		return devolucion;
	}


	
}
