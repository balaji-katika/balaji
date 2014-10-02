package org.balaji.dao.impl;

import java.util.List;

import org.balaji.dao.ProductDao;
import org.balaji.db.transaction.MyDBTransaction;
import org.balaji.db.transaction.MyTransaction;
import org.balaji.hibernate.model.Product;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends BaseObjectDaoImpl<Product> implements
		ProductDao {
	/**
	 * Constructor for Product DAO
	 */
	public ProductDaoImpl() {
		super(Product.class);
	}

	@Override
	public Product getByCollectorId(final String collector_id) {
		MyTransaction<Product> myTransaction = new MyDBTransaction<Product>(
				getCurrentSession()) {

			@Override
			protected Product doSomething() {
				@SuppressWarnings("rawtypes")
				List product = session.createQuery(
						"from Product where collectorId = '" + collector_id
								+ "'").list();
				if (product.size() == 0) {
					return null;
				}
				return (Product) product.get(0);
			}
		};
		return myTransaction.executeReadOnly();
	}
}
