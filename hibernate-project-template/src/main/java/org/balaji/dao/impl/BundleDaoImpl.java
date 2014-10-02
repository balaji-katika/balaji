package org.balaji.dao.impl;

import org.balaji.dao.BundleDao;
import org.balaji.hibernate.model.Bundle;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of Bundle DAO
 * 
 * @author root
 *
 */
@Repository("bundleDao")
public class BundleDaoImpl extends BaseObjectDaoImpl<Bundle> implements
		BundleDao {
	/**
	 * Default constructor
	 */
	public BundleDaoImpl() {
		super(Bundle.class);
	}
}
