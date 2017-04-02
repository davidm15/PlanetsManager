package at.fh.swenga.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.WebContext;

import at.fh.swenga.model.PlanetManager;
import at.fh.swenga.model.PlanetModel;

/**
 * Servlet implementation class EditEmployee
 */
@WebServlet("/editPlanet")
public class EditPlanet extends ThymeleafServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditPlanet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String ssnString = request.getParameter("ssn");

		if (ssnString == null) {
			WebContext ctx = new WebContext(request, response, getServletContext(), request.getLocale());
	        String templateName = "/editPlanet.html";
	        String result = engine.process(templateName, ctx);

	        PrintWriter out = null;
	        try {
	            out = response.getWriter();
	            out.println(result);
	        } finally {
	            out.close();
	        }
	        return;
		}
		try {
			int ssn = Integer.parseInt(ssnString);
			
			HttpSession session = request.getSession(true);
			PlanetManager planetManager = (PlanetManager) session.getAttribute("planetManager");

			if (planetManager != null) {
				PlanetModel planetModel = planetManager.getPlanetbySSN(ssn);
				if (planetModel != null) {
					request.setAttribute("planet", planetModel);
					WebContext ctx = new WebContext(request, response, getServletContext(), request.getLocale());
			        String templateName = "/editPlanet.html";
			        String result = engine.process(templateName, ctx);

			        PrintWriter out = null;
			        try {
			            out = response.getWriter();
			            out.println(result);
			        } finally {
			            out.close();
			        }
				}
			}
			
			request.setAttribute("errorMessage", "No planet with ssn "+ssn);
			WebContext ctx = new WebContext(request, response, getServletContext(), request.getLocale());
	        String templateName = "/listPlanets.html";
	        String result = engine.process(templateName, ctx);

	        PrintWriter out = null;
	        try {
	            out = response.getWriter();
	            out.println(result);
	        } finally {
	            out.close();
	        }
	        return;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
