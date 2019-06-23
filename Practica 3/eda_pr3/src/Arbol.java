import java.util.TreeSet;

//DNI 20084606 Rubio Martinez, Ruben
public interface Arbol {
	public void leeArbol(String f);
	public boolean esVacio();
	public boolean inserta(PLoc p);
	public boolean ciudadEnArbol(String v);
	public TreeSet<String> getCiudades(PLoc p);
	public PLoc busquedaLejana(String s);
}
