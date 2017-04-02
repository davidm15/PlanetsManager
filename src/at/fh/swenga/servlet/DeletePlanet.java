package at.fh.swenga.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.fh.swenga.model.PlanetManager;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/deletePlanet")
public class DeletePlanet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePlanet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ssnString = request.getParameter("ssn");
		
		try {
			int ssn = Integer.parseInt(ssnString);
			
			HttpSession session = request.getSession(true);
			PlanetManager planetManager = (PlanetManager)session.getAttribute("planetManager");

			if (planetManager!=null) {
				planetManager.remove(ssn);
			}
			request.setAttribute("warningMessage", "Planet "+ssn+" deleted");
			RequestDispatcher rd = request.getRequestDispatcher("/listPlanets");
			rd.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
