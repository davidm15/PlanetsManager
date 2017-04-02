package at.fh.swenga.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.WebContext;

import at.fh.swenga.model.PlanetManager;

/**
 * Servlet implementation class ListEmployees
 */
@WebServlet("/")
public class ListPlanets extends ThymeleafServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPlanets() {
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
		HttpSession session = request.getSession(true);
		PlanetManager planetManager =(PlanetManager)session.getAttribute("planetManager");
		
		if (planetManager==null) {
			planetManager=new PlanetManager();
			session.setAttribute("planetManager", planetManager);
		}
		
		request.setAttribute("planets", planetManager.getAllPlanets());
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
