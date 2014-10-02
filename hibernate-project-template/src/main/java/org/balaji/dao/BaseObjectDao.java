package org.balaji.dao;

import java.util.List;

import org.balaji.hibernate.model.BaseObject;

/**
 * DAO for the Base Object
 * @author root
 *
 * @param <S>
 */
public interface BaseObjectDao <S extends BaseObject>{
	/**
	 * Save the entity
	 * @param obj
	 */
	public void save(S obj);
	/**
	 * Update the entity
	 * @param obj
	 */
	public void update(S obj);
	/**
	 * Delete the entity
	 * @param obj
	 */
	public void delete(S obj);
	
	public S findById(int id);
	/**
	 * Load all entities
	 * @return
	 */
	public List<S> findAll();
}
