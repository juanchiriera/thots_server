package Servicios;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Clases.Usuario;
import Excepciones.ThotsException;

public class ServicioUsuario {
	
	
	public static Usuario crearUsuario(String email, String nombre,
			String apellido, Date fechaNacimiento, String nombreUsuario, String contraseña, String reContraseña) throws ThotsException {
		verificarNombre(nombre);
		verificarApellido(apellido);
		verificarEmail(email);
		verificarEdad(fechaNacimiento);
		verificarNombreUsuario(nombreUsuario);
		verificarContraseña(contraseña,reContraseña);
		String contraseñaSegura = codificarContraseña(contraseña);
		Usuario usuario = new Usuario(email, nombre, apellido, fechaNacimiento, nombreUsuario, contraseñaSegura);
		return usuario;
//		TODO: Falta implementar aca el .save()
	}

	private static void verificarContraseña(String contraseña,
			String reContraseña) throws ThotsException {
		if(!contraseña.equals(reContraseña)){
			throw new ThotsException(507,"Las contraseñas no coinciden");
		}else{
			constraintContraseña(contraseña);
		}
		
	}

	private static void constraintContraseña(String contraseña) throws ThotsException {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]{8,21}$");
		Matcher m = p.matcher(contraseña);
		if(!m.matches()){
			throw new ThotsException(506,"La contraseña no cumple los requerimientos de seguridad");
		}
	}

	private static void verificarNombreUsuario(String nombreUsuario) throws ThotsException {
		Pattern p = Pattern.compile("^[a-z\\d_]{4,20}$");
		Matcher m = p.matcher(nombreUsuario);
		if(!m.matches()){
			throw new ThotsException(505,"Nombre de usuario invalido");
		}
	}

	private static void verificarEdad(Date fechaNacimiento) throws ThotsException {
		int edad = calcularEdad(fechaNacimiento,new Date() );
		if(edad<=13){
			throw new ThotsException(504,"El usuario debe ser mayor de 13");
		}
		
	}

	private static void verificarEmail(String email) throws ThotsException {
		Pattern p = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$");
		Matcher m = p.matcher(email);
		if(!m.matches()){
			throw new ThotsException(503,"No es una direccion de mail valida");
		}
	}

	private static void verificarApellido(String apellido) throws ThotsException {
		Pattern p = Pattern.compile("^([a-zA-Z]{3,10}[ ]{0,1}){1,3}$");
		Matcher m = p.matcher(apellido);
		if(!m.matches()){
			throw new ThotsException(502,"Solo se permiten letras y dos espacios");
		}
	}

	private static void verificarNombre(String nombre) throws ThotsException {
		Pattern p = Pattern.compile("^([a-zA-Z]{3,10}[ ]{0,1}){1,3}$");
		Matcher m = p.matcher(nombre);
		if(!m.matches()){
			throw new ThotsException(502,"Solo se permiten letras y dos espacios");
		}
	}
	
	private static int calcularEdad(Date fechaNacimiento, Date date){
	    Calendar fechaAct = Calendar.getInstance();
	    fechaAct.setTime(date);

	    Calendar fechaNac = Calendar.getInstance();
	    fechaNac.setTime(fechaNacimiento);

	    int dif_anios = fechaAct.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
	    int dif_meses = fechaAct.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
	    int dif_dias = fechaAct.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);

	    if(dif_meses<0 || (dif_meses==0 && dif_dias<0)){
	        dif_anios--;
	    }
	    return dif_anios;
	}

	public static boolean validarLogin(Usuario usuario, String nombreUsuario,
			String contraseña) {
		String encodedPass = codificarContraseña(contraseña);
	     if(usuario.getContraseña().equals(encodedPass) && usuario.getNombreUsuario().equals(nombreUsuario)){
	    	 return true;
	     }
	     return false;
	}

	private static String codificarContraseña(String contraseña) {
		java.security.MessageDigest d=null;
		try {
			d = java.security.MessageDigest.getInstance("SHA-1");
			d.reset();
		    d.update(contraseña.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return d.digest().toString();
	}


}
