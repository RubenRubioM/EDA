/**
* @author Alicia Garrido Alenda
* Se crea un ArbolS. Se invoca leeArbol y ciudadEnArbol, de manera que unas
* veces la ciudad si que estara en el arbol y otras no, mostrando
* por pantalla la informacion devuelta en cada caso.
*/
import java.util.*;
public class p03{
  private static String[] creaLista(){
    String[] consulta={"Katerema","Bonnie Glen","Bolulla","Moshu Khan","Roma","Gympie","Abacate","Wellington"};
    return consulta;
  }
  
  public static void main(String[] args){
   if(args.length>0){
     ArbolS tree=new ArbolS();
     tree.leeArbol(args[0]);
     String[] ciudad=p03.creaLista();
     for(int i=0;i<ciudad.length;i++)
       System.out.println(ciudad[i]+" esta en el arbol? -> "+tree.ciudadEnArbol(ciudad[i]));
   }
   else System.out.println("falta el nombre del fichero");
  }
}
/*
 * Katerema esta en el arbol? -> true
Bonnie Glen esta en el arbol? -> true
Bolulla esta en el arbol? -> false
Moshu Khan esta en el arbol? -> true
Roma esta en el arbol? -> true
Gympie esta en el arbol? -> false
Abacate esta en el arbol? -> true
Wellington esta en el arbol? -> true

 */
