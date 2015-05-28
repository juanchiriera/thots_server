package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import netscape.javascript.JSObject;
import Controladores.ControladorUsuario;
import Excepciones.ThotsException;


/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = request.getParameter("nombreUsuario");
		String contraseña = request.getParameter("contrasena");
		
		try {
			String token = ControladorUsuario.getInstancia().login(nombreUsuario,contraseña);
			String res = "{\"token\":\"" + token + "\",\"clave\":\"200\"}";
			response.setContentType("application/json");
			PrintWriter printout = response.getWriter();
			response.setStatus(200);
	        printout.write(res);
	        printout.flush();
		} catch (ThotsException e) {
			String res = "{\"clave\":\"" + e.getClave() + "\",\"mensaje\":\"" + e.getMessage() + "\"}";
			response.setContentType("application/json");
			
			PrintWriter printout = response.getWriter();
//			response.setStatus(200);
	        printout.write(res);
	        
	        printout.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String contraseña = request.getParameter("contrasena");
		String reContraseña = request.getParameter("reContrasena");
		int diaNac = Integer.parseInt(request.getParameter("diaNac"));
		int mesNac = Integer.parseInt(request.getParameter("mesNac"));
		int añoNac = Integer.parseInt(request.getParameter("anoNac"));
		String email = request.getParameter("email");
		String nombreUsuario = request.getParameter("nombreUsuario");
		@SuppressWarnings("deprecation")
		Date fechaNacimiento = new Date(añoNac-1900, mesNac, diaNac);
	
		try {
			ControladorUsuario.getInstancia().crearUsuario(email, nombre, apellido, fechaNacimiento, nombreUsuario, contraseña, reContraseña);
			response.setStatus(200);
		} catch (ThotsException e) {
			response.setContentType("application/json");
			PrintWriter printout = response.getWriter();
			String res = "{\"clave\":" + e.getClave() + ",\"mensaje\":\"" + e.getMessage() + "\"}";
//			response.setStatus(200);
	        printout.write(res);
	        printout.flush();
		}
		
	}

}
