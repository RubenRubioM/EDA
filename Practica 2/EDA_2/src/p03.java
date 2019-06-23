/**
* @author Alicia Garrido Alenda
* Se declaran variables de tipo Lista en las que se crean objetos
* VectorG y ListaG, se invoca leeLista de estos objetos y escribeVectorG 
* y escribeListaG con los objetos correspondientes.
* Se invoca borraCola y borraCabeza de los objetos de tipo Lista mientras 
* la lista no este vacia, invocando escribeVectorG y escribeListaG con 
* los objetos correspondientes.
*/

public class p03{

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
     for(int i=0;i<l.length;i++){
       System.out.println("TIPO --------> "+l[i].getClass().getName());
       p03.escribe(l[i]);
     }
     for(int i=0;i<l.length;i++){
       System.out.println("TIPO --------> "+l[i].getClass().getName());
       while(!l[i].esVacia()){
         l[i].borraCabeza();
         l[i].borraCola();
         p03.escribe(l[i]);
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
posic 0: NA - Kansas - Abbyville - 37.97 - -98.20
posic 1: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
posic 2: SA - Ecuador - Quiroz - -0.27 - -77.17
posic 3: SA - Peru - Cuzco - -6.98 - -76.47
posic 4: SA - Brazil - Abacate - -1.12 - -49.65
posic 5: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 6: EU - Spain - Barcelona - 41.38 - 2.15
posic 7: EU - Italy - Roma - 41.88 - 12.48
posic 8: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 9: AF - Sudan - Saqadi - 17.10 - 33.65
posic 10: AF - Uganda - Katerema - 0.63 - 34.08
posic 11: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
posic 12: AS - Iran - Gazeh - 36.67 - 55.87
posic 13: AS - Pakistan - Moshu Khan - 33.60 - 73.27
posic 14: OC - Australia - Melbourne - -37.80 - 144.95
posic 0: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
posic 1: SA - Ecuador - Quiroz - -0.27 - -77.17
posic 2: SA - Peru - Cuzco - -6.98 - -76.47
posic 3: SA - Brazil - Abacate - -1.12 - -49.65
posic 4: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 5: EU - Spain - Barcelona - 41.38 - 2.15
posic 6: EU - Italy - Roma - 41.88 - 12.48
posic 7: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 8: AF - Sudan - Saqadi - 17.10 - 33.65
posic 9: AF - Uganda - Katerema - 0.63 - 34.08
posic 10: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
posic 11: AS - Iran - Gazeh - 36.67 - 55.87
posic 12: AS - Pakistan - Moshu Khan - 33.60 - 73.27
posic 0: SA - Ecuador - Quiroz - -0.27 - -77.17
posic 1: SA - Peru - Cuzco - -6.98 - -76.47
posic 2: SA - Brazil - Abacate - -1.12 - -49.65
posic 3: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 4: EU - Spain - Barcelona - 41.38 - 2.15
posic 5: EU - Italy - Roma - 41.88 - 12.48
posic 6: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 7: AF - Sudan - Saqadi - 17.10 - 33.65
posic 8: AF - Uganda - Katerema - 0.63 - 34.08
posic 9: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
posic 10: AS - Iran - Gazeh - 36.67 - 55.87
posic 0: SA - Peru - Cuzco - -6.98 - -76.47
posic 1: SA - Brazil - Abacate - -1.12 - -49.65
posic 2: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 3: EU - Spain - Barcelona - 41.38 - 2.15
posic 4: EU - Italy - Roma - 41.88 - 12.48
posic 5: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 6: AF - Sudan - Saqadi - 17.10 - 33.65
posic 7: AF - Uganda - Katerema - 0.63 - 34.08
posic 8: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
posic 0: SA - Brazil - Abacate - -1.12 - -49.65
posic 1: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 2: EU - Spain - Barcelona - 41.38 - 2.15
posic 3: EU - Italy - Roma - 41.88 - 12.48
posic 4: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 5: AF - Sudan - Saqadi - 17.10 - 33.65
posic 6: AF - Uganda - Katerema - 0.63 - 34.08
posic 0: EU - Ireland - Ballinameen - 53.90 - -8.30
posic 1: EU - Spain - Barcelona - 41.38 - 2.15
posic 2: EU - Italy - Roma - 41.88 - 12.48
posic 3: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 4: AF - Sudan - Saqadi - 17.10 - 33.65
posic 0: EU - Spain - Barcelona - 41.38 - 2.15
posic 1: EU - Italy - Roma - 41.88 - 12.48
posic 2: AF - Zimbaue - Ngome - -19.70 - 31.82
posic 0: EU - Italy - Roma - 41.88 - 12.48
TIPO --------> ListaG                                  //TODO Me va bien hasta aqui que da error
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
nodo 0: NA - Kansas - Abbyville - 37.97 - -98.20
nodo 1: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
nodo 2: SA - Ecuador - Quiroz - -0.27 - -77.17
nodo 3: SA - Peru - Cuzco - -6.98 - -76.47
nodo 4: SA - Brazil - Abacate - -1.12 - -49.65
nodo 5: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 6: EU - Spain - Barcelona - 41.38 - 2.15
nodo 7: EU - Italy - Roma - 41.88 - 12.48
nodo 8: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 9: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 10: AF - Uganda - Katerema - 0.63 - 34.08
nodo 11: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
nodo 12: AS - Iran - Gazeh - 36.67 - 55.87
nodo 13: AS - Pakistan - Moshu Khan - 33.60 - 73.27
nodo 14: OC - Australia - Melbourne - -37.80 - 144.95
nodo 0: NA - Minnesota - Bonnie Glen - 45.35 - -92.90
nodo 1: SA - Ecuador - Quiroz - -0.27 - -77.17
nodo 2: SA - Peru - Cuzco - -6.98 - -76.47
nodo 3: SA - Brazil - Abacate - -1.12 - -49.65
nodo 4: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 5: EU - Spain - Barcelona - 41.38 - 2.15
nodo 6: EU - Italy - Roma - 41.88 - 12.48
nodo 7: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 8: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 9: AF - Uganda - Katerema - 0.63 - 34.08
nodo 10: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
nodo 11: AS - Iran - Gazeh - 36.67 - 55.87
nodo 12: AS - Pakistan - Moshu Khan - 33.60 - 73.27
nodo 0: SA - Ecuador - Quiroz - -0.27 - -77.17
nodo 1: SA - Peru - Cuzco - -6.98 - -76.47
nodo 2: SA - Brazil - Abacate - -1.12 - -49.65
nodo 3: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 4: EU - Spain - Barcelona - 41.38 - 2.15
nodo 5: EU - Italy - Roma - 41.88 - 12.48
nodo 6: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 7: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 8: AF - Uganda - Katerema - 0.63 - 34.08
nodo 9: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
nodo 10: AS - Iran - Gazeh - 36.67 - 55.87
nodo 0: SA - Peru - Cuzco - -6.98 - -76.47
nodo 1: SA - Brazil - Abacate - -1.12 - -49.65
nodo 2: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 3: EU - Spain - Barcelona - 41.38 - 2.15
nodo 4: EU - Italy - Roma - 41.88 - 12.48
nodo 5: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 6: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 7: AF - Uganda - Katerema - 0.63 - 34.08
nodo 8: AS - Saudi Arabia - Abu Maragh - 21.52 - 39.78
nodo 0: SA - Brazil - Abacate - -1.12 - -49.65
nodo 1: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 2: EU - Spain - Barcelona - 41.38 - 2.15
nodo 3: EU - Italy - Roma - 41.88 - 12.48
nodo 4: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 5: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 6: AF - Uganda - Katerema - 0.63 - 34.08
nodo 0: EU - Ireland - Ballinameen - 53.90 - -8.30
nodo 1: EU - Spain - Barcelona - 41.38 - 2.15
nodo 2: EU - Italy - Roma - 41.88 - 12.48
nodo 3: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 4: AF - Sudan - Saqadi - 17.10 - 33.65
nodo 0: EU - Spain - Barcelona - 41.38 - 2.15
nodo 1: EU - Italy - Roma - 41.88 - 12.48
nodo 2: AF - Zimbaue - Ngome - -19.70 - 31.82
nodo 0: EU - Italy - Roma - 41.88 - 12.48
*/
