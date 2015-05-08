package ch.ffhs.sema.controller;

import java.util.Collection;

import javax.ejb.Local;

import ch.ffhs.sema.model.SensorData;

@Local
public interface SensorDataBeanLocal {

	/**
	 * get all records
	 */
	Collection<SensorData> getList();

	/**
	 * get records by sensor
	 */	
	Collection<SensorData> getListBySensor(Long sensorId);
	
	/**
	 * get one record by id
	 */
	SensorData getById(Long id);

	/**
	 * add record
	 */
	Long create(Long sensor, Long dataType, String value);
	
	/**
	 * delete record
	 */
	void delete(Long id);
}
