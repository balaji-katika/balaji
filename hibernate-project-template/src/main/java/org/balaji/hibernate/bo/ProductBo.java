package org.balaji.hibernate.bo;

import org.balaji.hibernate.model.Product;

/**
 * Product Business Object
 * @author root
 *
 */
public interface ProductBo extends BaseObjectBo<Product> {
	/**
	 * Get the Product associated with the collectorId
	 * @param collectorId
	 * @return
	 */
	Product getByCollectorId(String collectorId);
}
