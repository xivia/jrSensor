package ch.ffhs.sema.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.ffhs.sema.model.SensorData;

/**
 * Session Bean implementation class SensorDataBean
 */
@Stateless
public class SensorDataBean implements SensorDataBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private SensorBeanLocal sensorBean;
	@EJB
	private DataTypeBeanLocal dataTypeBean;
	
    /**
     * Default constructor. 
     */
    public SensorDataBean() { }

	@Override
	public Collection<SensorData> getList() {
		return em.createNamedQuery("SensorData.findAll",SensorData.class).getResultList();
	}

	@Override
	public Collection<SensorData> getListBySensor(Long sensorId) {
		TypedQuery<SensorData> q = em.createNamedQuery("SensorData.findBySensor", SensorData.class);	
		q.setParameter("sensor", sensorBean.getById(sensorId));
		return q.getResultList();
	}

	@Override
	public SensorData getById(Long id) {
		return em.find(SensorData.class, new Long(id));
	}

	@Override
	public Long create(Long sensor, Long dataType, String value) {
		SensorData sensorData = new SensorData();
		
		sensorData.setSensor(sensorBean.getById(sensor));
		sensorData.setDatatype(dataTypeBean.getById(dataType));
		sensorData.setValue(Double.parseDouble(value));
		// set now()
		sensorData.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
		
		em.persist(sensorData);
		em.flush();
		
		return sensorData.getId();
	}

	@Override
	public void delete(Long id) {
		SensorData sensorData = getById(id);
		
		if (sensorData != null) em.remove(sensorData);
		em.flush();
	}

}
