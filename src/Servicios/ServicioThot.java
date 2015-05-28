package Servicios;

import java.util.Date;

import Clases.Thot;
import Excepciones.ThotsException;

public class ServicioThot {

	public static Thot crearThot(String thot,
			double latitud,double longitud, Date date) throws ThotsException {
		validarThot(thot);
		return new Thot(thot,latitud,longitud,date);
	}

	private static void validarThot(String thot) throws ThotsException {
		if(thot.length()>300){
			throw new ThotsException(510, "El Thot es demasiado largo");
		}
	}

}
