package ch.ffhs.sema.controller;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.ffhs.sema.model.Sensor;

/**
 * Session Bean implementation class SensorBean
 */
@Stateless
public class SensorBean implements SensorBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private StationBeanLocal stationBean;
	
    /**
     * Default constructor. 
     */
    public SensorBean() { }

	@Override
	public Collection<Sensor> getList() {
		return em.createNamedQuery("Sensor.findAll",Sensor.class).getResultList();
	}

	@Override
	public Collection<Sensor> getListByStation(Long stationId) {
		TypedQuery<Sensor> q = em.createNamedQuery("Sensor.findByStation", Sensor.class);	
		q.setParameter("station", stationBean.getById(stationId));
		return q.getResultList();
	}

	@Override
	public Sensor getById(Long id) {
		return em.find(Sensor.class, new Long(id));
	}

	@Override
	public Long create(String name, String description) {
		Sensor sensor = new Sensor();
		
		sensor.setName(name);
		sensor.setDescription(description);
		
		em.persist(sensor);
		em.flush();
		
		return sensor.getId();
	}

	@Override
	public void update(Long id, String name, String description) {
		Sensor sensor = getById(id);
		
		sensor.setName(name);
		sensor.setDescription(description);
		
		em.merge(sensor);
		em.flush();
	}

	@Override
	public void delete(Long id) {
		Sensor sensor = getById(id);
		
		if (sensor != null) em.remove(sensor);
		em.flush();
	}

}
