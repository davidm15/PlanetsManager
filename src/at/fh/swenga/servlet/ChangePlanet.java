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
import at.fh.swenga.model.PlanetModel;

/**
 * Servlet implementation class SaveEmployee
 */
@WebServlet("/changePlanet")
public class ChangePlanet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePlanet() {
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
		String name = request.getParameter("name");
		String surface = request.getParameter("surface");
		String sizeString = request.getParameter("size");
		Float size = Float.parseFloat(sizeString);
		
		String errorMessage = "";
		
		//---- Convert SSN ----
		int ssn = 0;
		try {
			ssn = Integer.parseInt(ssnString);
		} catch (Exception e) {
			errorMessage += "SSN invalid";
		}

		// Data Conversion ok? -> Change Employee
		if ("".equals(errorMessage)) {
			HttpSession session = request.getSession(true);
			PlanetManager planetManager = (PlanetManager) session.getAttribute("planetManager");
			PlanetModel planet = planetManager.getPlanetbySSN(ssn);

			if (planet == null) {
				errorMessage += "Planet doesn't exist!";
			} else {
				planet.setName(name);
				planet.setSurface(surface);
				planet.setSize(size);
			}
		}
		
		if ("".equals(errorMessage)) {
			request.setAttribute("message", "planet " + ssn + " changed.");
		}
		else {
			request.setAttribute("errorMessage", errorMessage);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/listPlanets");
		rd.forward(request, response);
		return;
	}

}
