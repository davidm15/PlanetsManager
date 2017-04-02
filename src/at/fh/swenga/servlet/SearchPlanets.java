package at.fh.swenga.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class SearchEmployees
 */
@WebServlet("/searchPlanets")
public class SearchPlanets extends ThymeleafServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPlanets() {
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
		String searchString = request.getParameter("searchString");
		HttpSession session = request.getSession(true);
		
		PlanetManager planetManager =(PlanetManager)session.getAttribute("planetManager");
		
		List<PlanetModel> filteredPlanets = planetManager.getFilteredPlanets(searchString);
		
		request.setAttribute("planets", filteredPlanets);
		
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
	}

}
