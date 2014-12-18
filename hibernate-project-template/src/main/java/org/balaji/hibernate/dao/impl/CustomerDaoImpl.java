package org.balaji.hibernate.dao.impl;

import java.util.List;

import org.balaji.db.transaction.DBTransactionFacadeImpl;
import org.balaji.db.transaction.DBTransactionFacade;
import org.balaji.hibernate.dao.CustomerDao;
import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.Product;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for accessing through Hibernate
 * 
 * @author root
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl extends BaseObjectDaoImpl<Customer> implements
		CustomerDao {

	public CustomerDaoImpl() {
		super(Customer.class);
	}

	@Override
	public Customer getCustomer(final Product product, final String instanceId) {
		DBTransactionFacade<Customer> myTransaction = new DBTransactionFacadeImpl<Customer>(
				getCurrentSession()) {

			@Override
			protected Customer doSomething() {
				@SuppressWarnings("rawtypes")
				List customers = session.createQuery(
						"from Customer where instanceId = '" + instanceId
								+ "' and product = " + product.getId()).list();
				if (customers.size() == 0) {
					return null;
				}
				return (Customer) customers.get(0);
			}
		};
		return myTransaction.executeReadOnly();
	}

}
