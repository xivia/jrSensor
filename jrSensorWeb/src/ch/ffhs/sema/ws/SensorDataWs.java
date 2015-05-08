package ch.ffhs.sema.ws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.ffhs.sema.controller.DataTypeBeanLocal;
import ch.ffhs.sema.controller.SensorBeanLocal;
import ch.ffhs.sema.controller.SensorDataBeanLocal;
import ch.ffhs.sema.controller.StationBeanLocal;
import ch.ffhs.sema.model.DataType;
import ch.ffhs.sema.model.Sensor;
import ch.ffhs.sema.model.SensorData;
import ch.ffhs.sema.model.Station;

@Path("sensor")
@Stateless
public class SensorDataWs {

	// JSON data
	public static class SensorDataLocal {
		private String station;
		private String stationDescription;
		private String sensor;
		private String sensorDescription;
		private String value;
		private String unit;
		private String unitName;

		public String getStation() { return station; }
		public String getStationDescription() { if (stationDescription != null) return stationDescription; else return ""; }
		public String getSensor() { return sensor; }
		public String getSensorDescription() { if (sensorDescription != null) return sensorDescription; else return ""; }
		public String getValue() { return value; }
		public String getUnit() { return unit; }
		public String getUnitName() { if (unitName != null) return unitName; else return ""; }
	}
	
	@EJB
	private StationBeanLocal stationBean;
	@EJB
	private SensorBeanLocal sensorBean;
	@EJB
	private SensorDataBeanLocal sensorDataBean;
	@EJB
	private DataTypeBeanLocal dataTypeBean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll( @QueryParam(value = "station") String station, 
							@QueryParam(value = "sensor") String sensor) {
		Response response;
		
		Collection<SensorData> data = sensorDataBean.getList();		
		response = Response.status(200).entity(filterAndformatJSON(data, station, sensor)).build();
		
		return response;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(SensorDataLocal data) {
		Response response;
	
		// check station
		if (stationBean.getByName(data.getStation()).isEmpty()) {
			stationBean.create(data.getStation(), data.getStationDescription());
		}
		Station station = stationBean.getByName(data.getStation()).iterator().next();
		
		// check sensor
		if (sensorBean.getByName(data.getSensor()).isEmpty()) {
			sensorBean.create(station.getId(), data.getSensor(), data.getSensorDescription());
		}
		Sensor sensor = sensorBean.getByName(data.getSensor()).iterator().next();
		
		// check datatype
		if (dataTypeBean.getByUnit(data.getUnit()).isEmpty()) {
			dataTypeBean.create(data.getUnitName(), data.getUnit());
		}
		DataType datatype = dataTypeBean.getByUnit(data.getUnit()).iterator().next();
		
		
		// insert data
		Long newId = sensorDataBean.create(sensor.getId(), datatype.getId(), data.getValue());
		
		
		SensorData newData = sensorDataBean.getById(newId);
		Collection<SensorData> newDataCol = new ArrayList<SensorData>();
		newDataCol.add(newData);
		
		response = Response.status(200).entity(filterAndformatJSON(newDataCol, station.getName(), sensor.getName()).iterator().next()).build();
		
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
