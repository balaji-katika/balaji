package org.balaji.hibernate.bo;

import java.util.Map;

import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.FeatureUsage;

public interface FeatureUsageBo extends BaseObjectBo<FeatureUsage> {

	/**
	 * Update the feature map for the customer
	 * @param usage
	 * @param custId
	 * @return
	 */
	Boolean updateMap(Map<String, Integer> usage, Customer custId);

}
