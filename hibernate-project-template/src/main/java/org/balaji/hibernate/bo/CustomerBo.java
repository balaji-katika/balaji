package org.balaji.hibernate.bo;

import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.Product;

/**
 * Customer Business Object
 * @author root
 *
 */
public interface CustomerBo extends BaseObjectBo<Customer> {

	/**
	 * Get the customer for the instance id and product combination
	 * @param productId
	 * @param instance_id
	 * @return
	 */
	Customer getCustomer(Product productId, String instance_id);
}
