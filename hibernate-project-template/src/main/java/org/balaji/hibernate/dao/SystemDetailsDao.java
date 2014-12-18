package org.balaji.hibernate.dao;

import java.util.Properties;

import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.SystemDetails;

/**
 * DAO for system details 
 * @author root
 *
 */
public interface SystemDetailsDao extends BaseObjectDao<SystemDetails> {

	/**
	 * Update the system details for the customer
	 * @param customer
	 * @param properties
	 */
	void updateSystemDetails(Customer customer, Properties properties);
	
	/**
	 * Find the System Details for the customer with the key
	 * @param customer
	 * @param k1
	 * @return
	 */
	public SystemDetails findByCustKey(Customer customer, String k1);

}
