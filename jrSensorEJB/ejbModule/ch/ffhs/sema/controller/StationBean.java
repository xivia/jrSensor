package ch.ffhs.sema.controller;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.ffhs.sema.model.Station;

/**
 * Session Bean implementation class SensorBean
 */
@Stateless
public class StationBean implements StationBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public StationBean() { }

	@Override
	public Collection<Station> getList() {
		return em.createNamedQuery("Station.findAll",Station.class).getResultList();
	}

	@Override
	public Station getById(Long id) {
		return em.find(Station.class, new Long(id));
	}

	@Override
	public Long create(String name, String description) {
		Station station = new Station();
		
		station.setName(name);
		station.setDescription(description);
		
		em.persist(station);
		em.flush();
		
		return station.getId();
	}

	@Override
	public void update(Long id, String name, String description) {
		Station station = getById(id);
		
		station.setName(name);
		station.setDescription(description);
		
		em.merge(station);
		em.flush();
	}

	@Override
	public void delete(Long id) {
		Station station = getById(id);
		
		if (station != null) em.remove(station);
		em.flush();
	}

}
