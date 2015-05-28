package Excepciones;



public class ThotsException extends Exception{

	private int clave;

	public ThotsException(int clave, String descripcion) {
		super(descripcion);
		this.clave = clave;
	}
	

	
	public int getClave(){
		return clave;
	}
}
