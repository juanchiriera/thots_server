package Clases;

public class Localizacion {

	private double latitud;
	private double longitud;

	public Localizacion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public double getLatitud() {
		return this.latitud;
	}

}
