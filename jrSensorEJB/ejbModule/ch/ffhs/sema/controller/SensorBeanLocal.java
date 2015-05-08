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
	 * get records by name
	 */
	Collection<Sensor> getByName(String name);

	/**
	 * add record
	 */
	Long create(Long station, String name, String description);
	
	/**
	 * update record
	 */
	void update(Long id, Long station, String name, String description);
	
	/**
	 * delete record
	 */
	void delete(Long id);
}
