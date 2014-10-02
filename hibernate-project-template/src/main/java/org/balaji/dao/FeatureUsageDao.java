package org.balaji.dao;

import java.util.Map;

import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.FeatureUsage;

/**
 * DAO for feature usage
 */
public interface FeatureUsageDao extends BaseObjectDao<FeatureUsage> {

	/**
	 * Update the feature usage map for the customer in persistence store
	 * @param usage
	 * @param custId
	 * @return
	 */
	Boolean updateMap(Map<String, Integer> usage, Customer custId);

}
