//DNI 20084606 Rubio Martinez, Ruben
public interface Lista {
	
	public void leeLista(String f);
	public boolean esVacia();
	public void insertaCabeza(PLoc p);
	public void insertaCola(PLoc v);
	public void insertaArrayPLoc(PLoc[] v);
	public boolean borraCabeza();
	public boolean borraCola();
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion;
	public boolean borraCiudad(String v);
	public boolean borraPais(String s);
	public PLoc[] Pais(String p);
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException;
	public void ordenaLista();
}
