package Controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;



import Clases.Thot;
import Clases.Usuario;
import Excepciones.ThotsException;
import Servicios.ServicioThot;



public class ControladorThot {

	private static ControladorThot instancia;
	private static Vector<Thot> thots;
	
	private ControladorThot() {
		thots = new Vector<Thot>();
	}

	public static ControladorThot getInstancia() {
		if (instancia == null) {
			instancia = new ControladorThot();
		}
		return instancia;
	}
	
	public Date crearThot(String tokenID, String thotCadena,
			double latitud, double longitud) throws ThotsException {
		Usuario usuario = ControladorUsuario.getInstancia().buscarUsuarioToken(tokenID);
		Date fecha = new Date();
		if(usuario!=null){
			Thot thot = ServicioThot.crearThot(thotCadena, latitud, longitud, fecha);
			thot.asociarUsuario(usuario);
			usuario.agregarThot(thot);
			thots.add(thot);
			return fecha;
		}
		return null;
	}

	public String recuperarThotsCercanos(String tokenID, double latitud,
			double longitud) throws ThotsException {
		Usuario usuario = ControladorUsuario.getInstancia().buscarUsuarioToken(tokenID);
		Vector<Thot> thotsCercanos = buscarThotsCercanos(latitud,longitud, usuario);
		String res; 
		if(thotsCercanos.isEmpty()){
			throw new ThotsException(512, "No se encuentran thots cerca de la posicion definida");
		}else{
			res = "{\"clave\":\"200\",\"thot\":[";
			for (Thot thot : thotsCercanos) {
	//			res = res + "<thot> \n\t <fecha>" + thot.getFecha() + "</fecha> \n\t <distancia>" + calcularDistancia(thot, latitud, longitud) + "</distancia> \n\t <texto>" + thot.getThot() + "</texto>\n </thot> \n";
				res = res + "{\"fecha\":\"" + thot.getFecha() + "\",\"distancia\":\"" + calcularDistancia(thot, latitud, longitud) + "\",\"texto\":\"" + thot.getThot() + "\",\"id\":\"" + thot.getId() + "\",\"likes\":\"" + thot.getLikes() + "\"}";
				if(thotsCercanos.lastElement()!=thot){
					res = res + ",\n";
				}
				else{
					res = res + "]}";
				}
			}
		}
		return res;
	}

	private static Vector<Thot> buscarThotsCercanos(double latitud,
			double longitud, Usuario usuario) {
		Vector<Thot> thotsCercanos = new Vector<Thot>();
		for (Thot thot : thots) {
			int i = 0;
			if(calcularDistancia(thot,latitud,longitud)<=usuario.getDistanciaMaxima()&&i<100){
				//TODO: Armar un algoritmo para limitar la carga de thots
				i++;
				thotsCercanos.add(thot);
			}
		}
		return thotsCercanos;
	}

	private static int calcularDistancia(Thot thot, double latitud2,
			double longitud2) {
		double latitud1 = thot.getLatitud();
		double longitud1 = thot.getLongitud();
		double lat = latitud2 - latitud1;
		double lon = longitud2 - longitud1;
		double distancia = Math.floor(Math.sqrt(lat*lat+lon*lon));
		return (int)distancia;
	}

}
