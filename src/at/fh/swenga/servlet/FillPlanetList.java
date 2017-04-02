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
 * Servlet implementation class FillList
 */
@WebServlet("/fillPlanetList")
public class FillPlanetList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FillPlanetList() {
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

		HttpSession session = request.getSession(true);
		
		PlanetManager planetManager = (PlanetManager) session.getAttribute("planetManager");

		if (planetManager == null) {
			planetManager = new PlanetManager();
			session.setAttribute("planetManager", planetManager);
		}

		planetManager.addPlanet(new PlanetModel(1,"Earth","blue", 40000));
		planetManager.addPlanet(new PlanetModel(2,"Mars","red", 35000));
		planetManager.addPlanet(new PlanetModel(3,"Saturn","brown", 100000));

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listPlanets");
		rd.forward(request, response);
	}

}
