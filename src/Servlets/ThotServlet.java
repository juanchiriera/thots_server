package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controladores.ControladorThot;
import Excepciones.ThotsException;


/**
 * Servlet implementation class ThotServlet
 */
@WebServlet("/ThotServlet")
public class ThotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tokenID = request.getParameter("tokenID");
		double latitud = Double.parseDouble(request.getParameter("latitud"));
		double longitud = Double.parseDouble(request.getParameter("longitud"));
		try{
			String res = ControladorThot.getInstancia().recuperarThotsCercanos(tokenID,latitud,longitud);
			response.setContentType("application/json");
			PrintWriter printout = response.getWriter();
			response.setStatus(200);
	        printout.print(res);
	        printout.flush();
		}catch (ThotsException e){
			response.setContentType("application/json");
			PrintWriter printout = response.getWriter();
			String res = "{\"clave\":\"" + e.getClave() + "\",\"mensaje\":\"" + e.getMessage() + "\"}";
			response.setStatus(200);
	        printout.print(res);
	        printout.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tokenID = request.getParameter("tokenID");
		String thot = request.getParameter("thot");
		double latitud = Double.parseDouble(request.getParameter("latitud"));
		double longitud = Double.parseDouble(request.getParameter("longitud"));
		try{
			Date fecha = ControladorThot.getInstancia().crearThot(tokenID,thot,latitud,longitud);
			response.setContentType("application/json");
			PrintWriter printout = response.getWriter();
			String res = "{\"fecha\":\"" + fecha.toString() + "\",\"clave\":\"200\"}";
			response.setStatus(200);
	        printout.print(res);
	        printout.flush();
		}catch (ThotsException e){
			response.setContentType("application/json");
			PrintWriter printout = response.getWriter();
			String res = "{\"clave\":\"" + e.getClave() + "\",\"mensaje\":\"" + e.getMessage() + "\"}";
			response.setStatus(200);
	        printout.print(res);
	        printout.flush();
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
