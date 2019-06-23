/**
* @author Alicia Garrido Alenda
* Se crea un Atlas y se invoca leeAtlas con un fichero que contiene solo
* localidades con coordenadas correctas. Se crea una localidad, se le asignan
* coordenadas y se invoca muestraAtlasParcial de
* manera que muestra una submatriz situada completamente dentro de la matriz
* original en la que todas sus posiciones contienen una localidad.
*/

public class p04{
  public static void main(String[] args){
    if(args.length>=1){
      Atlas mini=new Atlas();
      mini.leeAtlas(args[0]);
      PLoc situa=new PLoc();
      Coordenada lat=new Coordenada(38,22,'N');
      Coordenada lon=new Coordenada(1,3,'O');
      try{
        situa.setLatitud(lat);
        situa.setLongitud(lon);
      }
      catch(Exception e){
        System.out.println(e);
      }
      mini.muestraAtlasParcial(situa,1);
    }
  }
}

/*
CBT
AES
HFA
*/