package ch.ffhs.sema.ws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.ffhs.sema.controller.SensorDataBeanLocal;
import ch.ffhs.sema.model.SensorData;

@Path("sensor")
@Stateless
public class SensorDataWs {

	@EJB
	private SensorDataBeanLocal sensorDataBean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll( @QueryParam(value = "station") String station, 
							@QueryParam(value = "sensor") String sensor) {
		Response response;
		
		Collection<SensorData> data = sensorDataBean.getList();		
		response = Response.status(200).entity(filterAndformatJSON(data, station, sensor)).build();
		
		return response;
	}
	
	Collection<Map<String, String>> filterAndformatJSON(Collection<SensorData> data, String station, String sensor) {
		
		Collection<Map<String, String>> myCollection = new ArrayList<Map<String,String>>();
		
		Iterator<SensorData> iter = data.iterator();
		while(iter.hasNext()) {
			SensorData item = iter.next();
			
			if ((
				(station == null) || (station.compareTo(item.getSensor().getStation().getName()) == 0)
				) && (
				(sensor == null) || (sensor.compareTo(item.getSensor().getName()) == 0)
			   )) {
				Map<String, String> myMap = new LinkedHashMap<String, String>();
				myMap.put("station", item.getSensor().getStation().getName());
				myMap.put("sensor", item.getSensor().getName());
				myMap.put("value", String.valueOf(item.getValue()));
				myMap.put("unit", item.getDatatype().getUnit());
				
				myCollection.add(myMap);
			}
		}
		
		return myCollection;
	}
	
}
