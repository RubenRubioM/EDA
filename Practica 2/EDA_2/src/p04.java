/**
* @author Alicia Garrido Alenda
* Se declaran variables de tipo Lista en las que se crean objetos
* VectorG y ListaG, se invoca leeLista de estos objetos y escribeVectorG 
* y escribeListaG con los objetos correspondientes.
* Se invoca ciudadEnLista, getPLoc con la posicion que devuelve y Pais con
* el pais de la localidad devuelta de los objetos de tipo Lista. 
* Se invoca escribeInfoGps de las localidades devueltas.
*/

public class p04{

  private static void escribe(Lista una){
    if(una instanceof VectorG)
      ((VectorG)una).escribeVectorG();
    else
      ((ListaG)una).escribeListaG();
  }
  
  private static void listado(PLoc[] v){
    if(v!=null)
     for(int i=0;i<v.length;i++)
       if(v[i]!=null)
         v[i].escribeInfoGps();
  }

  public static void main(String[] args){
   if(args.length>0){
     Lista[] l=new Lista[2];
     String[] ciudad={new String("Buffalo"),new String("Albion")};
     l[0]=new VectorG();
     l[1]=new ListaG();
     for(int i=0;i<l.length;i++)
       l[i].leeLista(args[0]);
     for(int i=0;i<l.length;i++){
       System.out.println("TIPO --------> "+l[i].getClass().getName());
       p04.escribe(l[i]);
     }
     for(int i=0;i<l.length;i++){
      System.out.println("TIPO --------> "+l[i].getClass().getName());
      for(int j=0;j<ciudad.length;j++){
       try{
         int pos=l[i].ciudadEnLista(ciudad[j]);
         System.out.println(ciudad[j]+" en posicion "+pos);
         PLoc trobat=l[i].getPLoc(pos);
         if(trobat!=null){
           PLoc[] relacio=l[i].Pais(trobat.getPais());
           p04.listado(relacio);
         }
       }
       catch(Exception e){
         System.out.println(e);
       }
      }
     }
   }
   else System.out.println("falta el nombre del fichero");
  }
}


/*
 * TIPO --------> VectorG
posic 0: NA - Montana - Buffalo - 46.82 - -109.82
posic 1: NA - Nebraska - Cotesfield - 41.35 - -98.62
posic 2: NA - Kansas - Abbyville - 37.97 - -98.20
posic 3: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
posic 4: SA - Ecuador - Quiroz - -0.27 - -77.17
posic 5: SA - Peru - Cuzco - -6.98 - -76.47
posic 6: SA - Brazil - Abacate - -1.12 - -49.65
posic 7: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 8: EU - Spain - Barcelona - 41.38 - 2.15
posic 9: EU - Italy - Roma - 41.88 - 12.48
posic 10: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 11: AF - Sudan - Saqadi - 17.10 - 33.65
posic 12: AF - Uganda - Katerema - 0.63 - 34.08
posic 13: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
posic 14: AS - Iran - Gazeh - 36.67 - 55.87
posic 15: AS - Pakistan - Moshu Khan - 33.60 - 73.27
posic 16: OC - Australia - Melbourne - -37.80 - 144.95
posic 17: OC - Australia - Canberra - -35.28 - 149.12
posic 18: OC - Australia - Albion - -27.42 - 153.03
TIPO --------> ListaG
nodo 0: NA - Montana - Buffalo - 46.82 - -109.82
nodo 1: NA - Nebraska - Cotesfield - 41.35 - -98.62
nodo 2: NA - Kansas - Abbyville - 37.97 - -98.20
nodo 3: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
nodo 4: SA - Ecuador - Quiroz - -0.27 - -77.17
nodo 5: SA - Peru - Cuzco - -6.98 - -76.47
nodo 6: SA - Brazil - Abacate - -1.12 - -49.65
nodo 7: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 8: EU - Spain - Barcelona - 41.38 - 2.15
nodo 9: EU - Italy - Roma - 41.88 - 12.48
nodo 10: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 11: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 12: AF - Uganda - Katerema - 0.63 - 34.08
nodo 13: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
nodo 14: AS - Iran - Gazeh - 36.67 - 55.87
nodo 15: AS - Pakistan - Moshu Khan - 33.60 - 73.27
nodo 16: OC - Australia - Melbourne - -37.80 - 144.95
nodo 17: OC - Australia - Canberra - -35.28 - 149.12
nodo 18: OC - Australia - Albion - -27.42 - 153.03
TIPO --------> VectorG
Buffalo en posicion 0
NA - Montana - Buffalo - 46.82 - -109.82
Albion en posicion 18
OC - Australia - Melbourne - -37.80 - 144.95
OC - Australia - Canberra - -35.28 - 149.12
OC - Australia - Albion - -27.42 - 153.03
TIPO --------> ListaG
Buffalo en posicion 0
NA - Montana - Buffalo - 46.82 - -109.82
Albion en posicion 18
OC - Australia - Melbourne - -37.80 - 144.95
OC - Australia - Canberra - -35.28 - 149.12
OC - Australia - Albion - -27.42 - 153.03
*/
