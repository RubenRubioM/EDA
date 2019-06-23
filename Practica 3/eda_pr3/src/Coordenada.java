//DNI 20084606 Rubio Martinez, Ruben
public class Coordenada {
	private int grados;
	private int minutos;
	private char pos;
	
	public Coordenada(int g,int m,char p){
		grados = g;
		minutos = m;
		pos = p;
	}
	
	public int getGrados(){
		return grados;
	}
	
	public int getMinutos(){
		return minutos;
	}
	
	public char getPos(){
		return pos;
	}
	
	
	//NEW compara si dos coordenadas son iguales. Para ello comparamos una a una todas sus componentes
	public boolean iguales(Coordenada c){
		boolean devolucion=false;
		
		if(c!=null){
			if(c.getGrados()==this.getGrados()){
				if(c.getMinutos()==this.getMinutos()){
					if(c.getPos()==this.getPos()){
						devolucion=true;
					}
				}
			}
		}
		
		
		return devolucion;
	}
}
