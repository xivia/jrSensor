package ch.ffhs.sema.controller;

import java.util.Collection;

import javax.ejb.Local;

import ch.ffhs.sema.model.Sensor;

@Local
public interface SensorBeanLocal {
	
	/**
	 * get all records
	 */
	Collection<Sensor> getList();

	/**
	 * get records by station
	 */	
	Collection<Sensor> getListByStation(Long stationId);
	
	/**
	 * get one record by id
	 */
	Sensor getById(Long id);

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
