package ch.ffhs.sema.controller;

import java.util.Collection;

import javax.ejb.Local;

import ch.ffhs.sema.model.DataType;

@Local
public interface DataTypeBeanLocal {
	
	/**
	 * get all records
	 */
	Collection<DataType> getList();	
	
	/**
	 * get one record by id
	 */
	DataType getById(Long id);

	/**
	 * add record
	 */
	Long create(String name, String unit);
	
	/**
	 * update record
	 */
	void update(Long id, String name, String unit);
	
	/**
	 * delete record
	 */
	void delete(Long id);
}
