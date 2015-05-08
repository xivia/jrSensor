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

import ch.ffhs.sema.model.Sensor;
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
	
	@EJB
	private SensorBeanLocal sensorBean;
	
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
		
		// station
		String station = request.getParameter("station");
		if (station == null) station = "0";
		
		// sensor
		String sensor = request.getParameter("sensor");
		if (sensor == null) sensor = "0";
		
		ServletContext sc = getServletContext();
		
		//fill lists
		Collection<Station> ejbResultStation = stationBean.getList();
		Collection<Sensor> ejbResultSensor = sensorBean.getListByStation(Long.parseLong(station));
		
		request.setAttribute("station", station);
		request.setAttribute("sensor", sensor);
		request.setAttribute("resultListStation", ejbResultStation);
		request.setAttribute("resultListSensor", ejbResultSensor);
		
		RequestDispatcher rdDefault = sc.getRequestDispatcher(urlList);
		rdDefault.forward(request, response);
	}

}
