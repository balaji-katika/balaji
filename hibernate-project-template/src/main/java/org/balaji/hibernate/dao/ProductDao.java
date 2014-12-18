package org.balaji.hibernate.dao;

import org.balaji.hibernate.model.Product;

/**
 * DAO for Product
 * @author root
 *
 */
public interface ProductDao extends BaseObjectDao<Product> {
	/**
	 * Fetch the Product associated with the collector id
	 * @param collector_id
	 * @return
	 */
	public Product getByCollectorId(String collector_id);
}
