package Clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Thot {

	private Usuario usuario;
	private Localizacion localizacion;
	private String thot;
	private Date fecha;
	private int likes;
	private static int ultimoThot;
	private int thotId;

	public Thot(String thot, double latitud, double longitud, Date fecha) {
		this.localizacion = new Localizacion(latitud,longitud);
		this.thot = thot;
		this.fecha = fecha;
		this.thotId = ultimoThot;
		this.likes = 0;
		ultimoThot++;
	}

	public void asociarUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getLongitud() {
		return localizacion.getLongitud();
	}

	public double getLatitud() {
		return localizacion.getLatitud();
	}

	public String getFecha() {
//		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
		SimpleDateFormat format = new SimpleDateFormat();
        String fechaString = "";
		fechaString = format.format(fecha);
		return fechaString;
//		return fecha.toString();
	}

	public String getThot() {
		return this.thot;
	}
	
	public void like(){
		likes++;
	}

	public int getId() {
		return this.thotId;
	}

	public int getLikes() {
		return this.likes;
	}

	

}
