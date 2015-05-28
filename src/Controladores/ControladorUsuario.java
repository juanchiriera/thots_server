package Controladores;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Vector;


import Clases.Usuario;
import Excepciones.ThotsException;
import Servicios.ServicioUsuario;
import Servlets.UsuarioServlet;

public class ControladorUsuario {
	private static Vector<Usuario> usuarios;
	private static ControladorUsuario instancia;
	
	private ControladorUsuario() {
		usuarios = new Vector<Usuario>();
	}
	public static ControladorUsuario getInstancia(){
		if(instancia==null){
			instancia = new ControladorUsuario();
		}
		return instancia;
	}


	public void crearUsuario(String email, String nombre,
			String apellido, Date fechaNacimiento, String nombreUsuario,
			String contraseña, String reContraseña) throws ThotsException {
		Usuario usuario = buscarUsuario(nombreUsuario);
		if(usuario == null){
			usuario = ServicioUsuario.crearUsuario(email, nombre, apellido, fechaNacimiento, nombreUsuario, contraseña, reContraseña);
			usuarios.add(usuario);
		}else{
			throw new ThotsException(509, "El usuario ya existe");
		}
		//TODO: Aca se implementa el save	

	}


	public String login(String nombreUsuario, String contraseña) throws ThotsException {
		Usuario usuario = buscarUsuario(nombreUsuario);
		if(usuario!=null){
			ServicioUsuario.validarLogin(usuario,nombreUsuario,contraseña);
			String token = generarToken(usuario);
			return token;
		}else{
			throw new ThotsException(508, "El nombre de usuario no existe");
		}
	

}


	
	private static String generarToken(Usuario usuario) {
		  SecureRandom random = new SecureRandom();
		  String token =  new BigInteger(130, random).toString(32);
		  Date date = new Date();
		  usuario.agregarLogin(token,date);
		  return token;
		}


	public static Usuario buscarUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub
		for (Usuario usuario : usuarios) {
			if(usuario.sosUsuario(nombreUsuario)){
				return usuario;
			}
		}
		return null;
	}
	
	public Usuario buscarUsuarioToken(String token) throws ThotsException{
		for (Usuario usuario : usuarios) {
			if(usuario.tenesToken(token)){
				return usuario;
			}
		}
		throw new ThotsException(511, "No se ha iniciado sesion, o la sesion ha expirado");
	}
}
