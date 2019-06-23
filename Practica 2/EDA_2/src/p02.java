/**
* @author Alicia Garrido Alenda
* Se declaran variables de tipo Lista en las que se crean objetos
* VectorG y ListaG, se invoca leeLista de estos objetos y escribeVectorG 
* y escribeListaG con los objetos correspondientes.
* Se crean coordenadas y localidades. Se asigna latitud y longitud a todas las 
* localidades. Se invoca insertaArrayPLoc de los objetos de tipo Lista con las localidades.
* Se invoca escribeVectorG y escribeListaG con los objetos correspondientes.
*/

public class p02{

  private static Coordenada[] creaCoordenadas(int g,int m,char p,int n){
    Coordenada[] creadas=null;
    if(n>0){
      creadas=new Coordenada[n];
      for(int i=0;i<n;i++)
        creadas[i]=new Coordenada(g+i,m+i,p);
    }
    return creadas;
  }
  
  private static PLoc[] creaLocalidades(int n,String conti,String pais){
    PLoc[] creadas=null;
    if(n>0){
      creadas=new PLoc[n];
      String[] ciudad={"Monserrat","Guadalaviar","Fuentegelmes","Villarmero"};
      Coordenada[] lat=p02.creaCoordenadas(39,20,'N',n);
      Coordenada[] lon=p02.creaCoordenadas(0,40,'O',n);
      for(int i=0;i<ciudad.length&&i<n;i++)
        try{
          creadas[i]=new PLoc(conti,pais,ciudad[i]);
          creadas[i].setLatitud(lat[i]);
          creadas[i].setLongitud(lon[i]);
        }
        catch(CoordenadaExcepcion e){
          System.out.println("Ciudad -> "+ciudad[i]+" "+e);
        }
    }
    return creadas;
  }
  
  private static void escribe(Lista una){
    if(una instanceof VectorG)
      ((VectorG)una).escribeVectorG();
    else
      ((ListaG)una).escribeListaG();
  }

  public static void main(String[] args){
   if(args.length>0){
     Lista[] l=new Lista[2];
     l[0]=new VectorG();
     l[1]=new ListaG();
     for(int i=0;i<l.length;i++)
       l[i].leeLista(args[0]);
     for(int i=0;i<l.length;i++)
       p02.escribe(l[i]);
     PLoc[] llocs=p02.creaLocalidades(4,"EU","Spain");
     for(int i=0;i<l.length;i++)
       l[i].insertaArrayPLoc(llocs);
     for(int i=0;i<l.length;i++)
       p02.escribe(l[i]);
       
   }
   else System.out.println("falta el nombre del fichero");
  }
}
/*
posic 0: NA - Nebraska - Cotesfield - 41.35 - -98.62
posic 1: NA - Kansas - Abbyville - 37.97 - -98.20
posic 2: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
posic 3: SA - Ecuador - Quiroz - -0.27 - -77.17
posic 4: SA - Peru - Cuzco - -6.98 - -76.47
posic 5: SA - Brazil - Abacate - -1.12 - -49.65
posic 6: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 7: EU - Spain - Barcelona - 41.38 - 2.15
posic 8: EU - Italy - Roma - 41.88 - 12.48
posic 9: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 10: AF - Sudan - Saqadi - 17.10 - 33.65
posic 11: AF - Uganda - Katerema - 0.63 - 34.08
posic 12: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
posic 13: AS - Iran - Gazeh - 36.67 - 55.87
posic 14: AS - Pakistan - Moshu Khan - 33.60 - 73.27
posic 15: OC - Australia - Melbourne - -37.80 - 144.95
posic 16: OC - Australia - Canberra - -35.28 - 149.12
posic 17: OC - Australia - Albion - -27.42 - 153.03
nodo 0: NA - Nebraska - Cotesfield - 41.35 - -98.62
nodo 1: NA - Kansas - Abbyville - 37.97 - -98.20
nodo 2: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
nodo 3: SA - Ecuador - Quiroz - -0.27 - -77.17
nodo 4: SA - Peru - Cuzco - -6.98 - -76.47
nodo 5: SA - Brazil - Abacate - -1.12 - -49.65
nodo 6: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 7: EU - Spain - Barcelona - 41.38 - 2.15
nodo 8: EU - Italy - Roma - 41.88 - 12.48
nodo 9: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 10: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 11: AF - Uganda - Katerema - 0.63 - 34.08
nodo 12: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
nodo 13: AS - Iran - Gazeh - 36.67 - 55.87
nodo 14: AS - Pakistan - Moshu Khan - 33.60 - 73.27
nodo 15: OC - Australia - Melbourne - -37.80 - 144.95
nodo 16: OC - Australia - Canberra - -35.28 - 149.12
nodo 17: OC - Australia - Albion - -27.42 - 153.03
posic 0: NA - Nebraska - Cotesfield - 41.35 - -98.62
posic 1: NA - Kansas - Abbyville - 37.97 - -98.20
posic 2: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
posic 3: SA - Ecuador - Quiroz - -0.27 - -77.17
posic 4: SA - Peru - Cuzco - -6.98 - -76.47
posic 5: SA - Brazil - Abacate - -1.12 - -49.65
posic 6: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 7: EU - Spain - Barcelona - 41.38 - 2.15
posic 8: EU - Italy - Roma - 41.88 - 12.48
posic 9: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 10: AF - Sudan - Saqadi - 17.10 - 33.65
posic 11: AF - Uganda - Katerema - 0.63 - 34.08
posic 12: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
posic 13: AS - Iran - Gazeh - 36.67 - 55.87
posic 14: AS - Pakistan - Moshu Khan - 33.60 - 73.27
posic 15: OC - Australia - Melbourne - -37.80 - 144.95
posic 16: OC - Australia - Canberra - -35.28 - 149.12
posic 17: OC - Australia - Albion - -27.42 - 153.03
posic 18: EU - Spain - Monserrat - 39.33 - -0.67
posic 19: EU - Spain - Guadalaviar - 40.35 - -1.68
posic 20: EU - Spain - Fuentegelmes - 41.37 - -2.70
posic 21: EU - Spain - Villarmero - 42.38 - -3.72
nodo 0: NA - Nebraska - Cotesfield - 41.35 - -98.62
nodo 1: NA - Kansas - Abbyville - 37.97 - -98.20
nodo 2: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
nodo 3: SA - Ecuador - Quiroz - -0.27 - -77.17
nodo 4: SA - Peru - Cuzco - -6.98 - -76.47
nodo 5: SA - Brazil - Abacate - -1.12 - -49.65
nodo 6: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 7: EU - Spain - Barcelona - 41.38 - 2.15
nodo 8: EU - Italy - Roma - 41.88 - 12.48
nodo 9: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 10: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 11: AF - Uganda - Katerema - 0.63 - 34.08
nodo 12: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
nodo 13: AS - Iran - Gazeh - 36.67 - 55.87
nodo 14: AS - Pakistan - Moshu Khan - 33.60 - 73.27
nodo 15: OC - Australia - Melbourne - -37.80 - 144.95
nodo 16: OC - Australia - Canberra - -35.28 - 149.12
nodo 17: OC - Australia - Albion - -27.42 - 153.03
nodo 18: EU - Spain - Monserrat - 39.33 - -0.67
nodo 19: EU - Spain - Guadalaviar - 40.35 - -1.68
nodo 20: EU - Spain - Fuentegelmes - 41.37 - -2.70
nodo 21: EU - Spain - Villarmero - 42.38 - -3.72
*/
