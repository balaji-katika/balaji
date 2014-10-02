package org.balaji.dao.impl;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.balaji.dao.SystemDetailsDao;
import org.balaji.db.transaction.MyDBTransaction;
import org.balaji.db.transaction.MyTransaction;
import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.SystemDetails;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of SystemDetailsDao
 * 
 * @author root
 *
 */
@Repository("systemDetailsDao")
public class SystemDetailsDaoImpl extends BaseObjectDaoImpl<SystemDetails>
		implements SystemDetailsDao {
	private static Logger logger = Logger.getLogger(SystemDetailsDaoImpl.class);

	/**
	 * Default constructor
	 */
	public SystemDetailsDaoImpl() {
		super(SystemDetails.class);
	}

	@Override
	public void updateSystemDetails(final Customer customer,
			final Properties properties) {
		MyTransaction<Void> transaction = new MyDBTransaction<Void>(
				getCurrentSession()) {

			@Override
			protected Void doSomething() {
				SystemDetails details = null;
				for (Object key : properties.keySet()) {
					String k1 = (String) key;
					SystemDetails systemDetails = null;
					systemDetails = findByCustKey(customer, k1);

					if (systemDetails == null) {
						details = new SystemDetails();
						details.setCustomer(customer);
						details.setKey(k1);
						details.setValue(((String) properties.getProperty(k1)));
						save(details);
					} else {
						/*
						 * Update the existing system details
						 */
						logger.info("Updating the system detail " + systemDetails);
						systemDetails.setValue((String) properties
								.getProperty(k1));
						update(systemDetails);
					}
				}

				return null;
			}
		};

		transaction.execute();

		return;
	}

	@Override
	public SystemDetails findByCustKey(final Customer customer, final String k1) {
		MyTransaction<SystemDetails> transaction = new MyDBTransaction<SystemDetails>(
				getCurrentSession()) {

			@Override
			protected SystemDetails doSomething() {
				@SuppressWarnings("rawtypes")
				List list = session.createQuery(
						"from SystemDetails where customer = "
								+ customer.getId() + " and key = '" + k1 + "'")
						.list();
				if (list == null || list.size() == 0) {
					return null;
				}

				return (SystemDetails) list.get(0);
			}
		};
		return transaction.executeReadOnly();
	}
}
