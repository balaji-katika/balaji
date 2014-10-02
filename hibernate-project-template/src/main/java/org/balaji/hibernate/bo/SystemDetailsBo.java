package org.balaji.hibernate.bo;

import java.util.Properties;

import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.SystemDetails;

/**
 * Business Object for System Details
 * @author root
 *
 */
public interface SystemDetailsBo extends BaseObjectBo<SystemDetails> {

	/**
	 * Update the system details for the customer
	 * @param customer
	 * @param properties
	 */
	void updateSystemDetails(Customer customer, Properties properties);

}
