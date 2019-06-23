/**
* @author Alicia Garrido Alenda
* Se crea una localidad (PLoc) y 2 coordenadas. Se invoca setLatitud y
* setLongitud de la localidad con las coordenadas creadas, escribeInfoGrados
* y escribeInfoGps.
*/

public class p01{
  public static void main(String[] args){
    PLoc primera=new PLoc(new String("EU"),new String("Spain"),new String("Alicante"));
    Coordenada lat=new Coordenada(38,20,'N');
    Coordenada lon=new Coordenada(0,28,'O');
    try{
      primera.setLatitud(lat);
      primera.setLongitud(lon);
    }
    catch(CoordenadaExcepcion e){
      System.out.println(e);
    }
    primera.escribeInfoGrados();
    primera.escribeInfoGps();
  }
}
/*EU - Spain - Alicante - 38 20 N - 0 28 O
EU - Spain - Alicante - 38.33 - -0.47 */