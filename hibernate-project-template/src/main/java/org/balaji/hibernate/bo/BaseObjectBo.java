package org.balaji.hibernate.bo;

import java.util.List;

import org.balaji.hibernate.model.BaseObject;

public interface BaseObjectBo<S extends BaseObject> {
	/**
	 * Save the Entity in the DB
	 * @param obj
	 */
	public void save(S obj);
	/**
	 * Update the entity in the DB
	 * @param obj
	 */
	public void update(S obj);
	/**
	 * Delete the entity in the DB
	 * @param obj
	 */
	public void delete(S obj);
	public S findById(int id);
	
	/**
	 * Fetch all the Entities from the DB
	 * @return
	 */
	public List<S> findAll();	
}
