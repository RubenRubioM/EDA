/**
* @author Alicia Garrido Alenda
* Se crea un Atlas. Se invoca leeAtlas con un fichero en el que alguna
* de las localidades tienen datos incorrectos para sus coordenadas
*/

public class p03{
  public static void main(String[] args){
    if(args.length>=1){
      Atlas mini=new Atlas();
      mini.leeAtlas(args[0]);
    }
  }
}
/*
CoordenadaExcepcion: Longitud minutos 60
CoordenadaExcepcion: Latitud minutos -15
CoordenadaExcepcion: Latitud grados 91
CoordenadaExcepcion: Latitud posicion O
CoordenadaExcepcion: Longitud grados -170
CoordenadaExcepcion: Longitud posicion W
*/