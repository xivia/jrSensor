package ch.ffhs.sema.controller;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.ffhs.sema.model.Station;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/manage")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//redirect urls
	private static final String urlList = "/list.jsp";
	
	@EJB
	private StationBeanLocal stationBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		
		//fill list
		Collection<Station> ejbResult = stationBean.getList();
		
		RequestDispatcher rdDefault = sc.getRequestDispatcher(urlList);
		
		request.setAttribute("resultList", ejbResult);
		rdDefault.forward(request, response);
	}

}
