package ch.ffhs.sema.controller;

import java.util.Collection;

import javax.ejb.Local;

import ch.ffhs.sema.model.Station;

@Local
public interface StationBeanLocal {
	
	/**
	 * get all records
	 */
	Collection<Station> getList();	
	
	/**
	 * get one record by id
	 */
	Station getById(Long id);

	/**
	 * add record
	 */
	Long create(String name, String description);
	
	/**
	 * update record
	 */
	void update(Long id, String name, String description);
	
	/**
	 * delete record
	 */
	void delete(Long id);
}
