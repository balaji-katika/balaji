package org.balaji.dao;

import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.Product;

/**
 * DAO for Customer
 * 
 * @author root
 *
 */
public interface CustomerDao extends BaseObjectDao<Customer> {
	/**
	 * Fetch Customer details for the Product and instance id
	 * 
	 * @param product
	 * @param instanceId
	 * @return
	 */
	Customer getCustomer(Product product, String instanceId);
}
