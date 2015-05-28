package Clases;

import java.util.Date;
import java.util.Vector;



public class Usuario {

	private String email;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nombreUsuario;
	private String contraseña;
	private String token;
	private Date ultimoLogin;
	private Vector<Thot> thots;
	private int distanciaMaxima;

	public Usuario(String email, String nombre, String apellido, Date fechaNacimiento,
			String nombreUsuario, String contraseña) {
		this.email=email;
		this.nombre=nombre;
		this.apellido=apellido;
		this.fechaNacimiento=fechaNacimiento;
		this.nombreUsuario=nombreUsuario;
		this.contraseña=contraseña;
		this.distanciaMaxima = 5000;
		thots = new Vector<Thot>();
	}

	public boolean sosUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub
		return this.nombreUsuario.equals(nombreUsuario);
	}

	public void agregarLogin(String token, Date ultimoLogin) {
		this.token = token;
		this.ultimoLogin = ultimoLogin;
		
	}

	public Object getContraseña() {
		// TODO Auto-generated method stub
		return this.contraseña;
	}

	public String getNombreUsuario() {
		// TODO Auto-generated method stub
		return this.nombreUsuario;
	}

	public boolean tenesToken(String token) {
		// TODO Auto-generated method stub
		return this.token.equals(token);
	}

	public void agregarThot(Thot thot) {
		thots.add(thot);
	}

	public int getDistanciaMaxima() {
		// TODO Auto-generated method stub
		return this.distanciaMaxima;
	}

}
