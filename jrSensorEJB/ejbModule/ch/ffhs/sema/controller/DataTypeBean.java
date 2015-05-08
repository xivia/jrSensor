package ch.ffhs.sema.controller;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.ffhs.sema.model.DataType;

/**
 * Session Bean implementation class DataTypeBean
 */
@Stateless
public class DataTypeBean implements DataTypeBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DataTypeBean() { }

	@Override
	public Collection<DataType> getList() {
		return em.createNamedQuery("DataType.findAll",DataType.class).getResultList();
	}

	@Override
	public DataType getById(Long id) {
		return em.find(DataType.class, new Long(id));
	}

	@Override
	public Collection<DataType> getByUnit(String unit) {
		TypedQuery<DataType> q = em.createNamedQuery("DataType.findByUnit", DataType.class);	
		q.setParameter("unit", unit);
		return q.getResultList();
	}
	
	@Override
	public Long create(String name, String unit) {
		DataType dataType = new DataType();
		
		dataType.setName(name);
		dataType.setUnit(unit);
		
		em.persist(dataType);
		em.flush();
		
		return dataType.getId();
	}

	@Override
	public void update(Long id, String name, String unit) {
		DataType dataType = getById(id);
		
		dataType.setName(name);
		dataType.setUnit(unit);
		
		em.merge(dataType);
		em.flush();
	}

	@Override
	public void delete(Long id) {
		DataType dataType = getById(id);
		
		if (dataType != null) em.remove(dataType);
		em.flush();
	}

}
