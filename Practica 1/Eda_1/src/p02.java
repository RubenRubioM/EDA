/**
* @author Alicia Garrido Alenda
* Se crean diferentes localidades y se invoca setLatitud y setLongitud de
* todas ellas, de manera que todas las localidades tienen coordenadas
* asignadas. Se crea un Atlas y se invoca setLocalidad con todas las
* localidades creadas, de manera que unas veces devuelve cierto y otras falso.
*/

public class p02{
  private static PLoc[] creaLocalidades(){
   String[] names={"Abdet","Cabo Roig","El Rodriguillo","Pego","Abejuela","Agres","Aras de Alpuente","Arroyo Cerezo","Alcantud",
                   "Albaladejo del Cuende","Alcoi","El Gollizo","Barranco de los Asensios","Capellanía","Alcubillas","Abanilla"};
   PLoc[] sitios=new PLoc[names.length];
   for(int i=0;i<sitios.length;i++)
      sitios[i]=new PLoc(new String("EU"),new String("Spain"),names[i]);
   return sitios;
  }
  private static Coordenada[] creaLatitud(PLoc[] llocs){
   Coordenada[] lats=null;
   if(llocs!=null){
     lats=new Coordenada[llocs.length];
     int[] gra={38,37,38,38,39,38,39,40,40,39,38,38,37,37,38,38};
     int[] mins={42,54,22,51,54,47,55,7,33,48,42,31,29,55,45,12};
     for(int i=0;i<lats.length&&i<mins.length;i++)
       lats[i]=new Coordenada(gra[i],mins[i],'N');
   }
   return lats;
  }

  private static Coordenada[] creaLongitud(PLoc[] llocs){
   Coordenada[] lons=null;
   if(llocs!=null){
     lons=new Coordenada[llocs.length];
     int[] gra={0,0,1,7,0,0,1,1,2,2,0,2,1,2,3,1};
     int[] mins={15,43,3,8,53,31,8,25,20,13,28,25,39,7,8,3};
     for(int i=0;i<lons.length&&i<mins.length;i++)
       lons[i]=new Coordenada(gra[i],mins[i],'O');
   }
   return lons;
  }
  public static void main(String[] args){
    PLoc[] llocs=p02.creaLocalidades();
    Coordenada[] lat=p02.creaLatitud(llocs);
    Coordenada[] lon=p02.creaLongitud(llocs);
    if(llocs!=null&&lat!=null&&lon!=null){
      for(int i=0;i<llocs.length&&i<lat.length&&i<lon.length;i++)
        try{
         llocs[i].setLatitud(lat[i]);
         llocs[i].setLongitud(lon[i]);
        }
        catch(CoordenadaExcepcion e){
          System.out.println(e);
        }
      Atlas local=new Atlas();
      boolean fet=false;
      String ciudad=null;
      for(int i=0;i<llocs.length;i++){
        fet=local.setLocalidad(llocs[i]);
        ciudad=llocs[i].getCiudad();
        if(fet&&ciudad!=null)
         System.out.println("Situada "+ciudad);
        else if(!fet)
         System.out.println("No se pudo situar "+ciudad);
      }
    }
  }
}

/*
Situada Abdet
Situada Cabo Roig
Situada El Rodriguillo
Situada Pego
Situada Abejuela
No se pudo situar Agres
Situada Aras de Alpuente
Situada Arroyo Cerezo
Situada Alcantud
Situada Albaladejo del Cuende
No se pudo situar Alcoi
Situada El Gollizo
Situada Barranco de los Asensios
Situada Capellanía
Situada Alcubillas
No se pudo situar Abanilla
*/