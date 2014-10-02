package org.balaji.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.balaji.dao.FeatureUsageDao;
import org.balaji.db.transaction.MyDBTransaction;
import org.balaji.db.transaction.MyTransaction;
import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.FeatureUsage;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implemenation for feature usage DAO
 * 
 * @author root
 *
 */
@Repository("featureUsageDao")
public class FeatureUsageDaoImpl extends BaseObjectDaoImpl<FeatureUsage>
		implements FeatureUsageDao {
	private static Logger logger = Logger.getLogger(FeatureUsageDaoImpl.class);

	/**
	 * Default constructor
	 */
	public FeatureUsageDaoImpl() {
		super(FeatureUsage.class);
	}

	@Override
	public Boolean updateMap(final Map<String, Integer> usage,
			final Customer customer) {
		MyTransaction<Boolean> transaction = new MyDBTransaction<Boolean>(
				getCurrentSession()) {

			@Override
			protected Boolean doSomething() {
				FeatureUsage featureUsage = null;
				@SuppressWarnings("rawtypes")
				List list = null;
				for (String entry : usage.keySet()) {
					list = session.createQuery(
							"from FeatureUsage where featureName = '" + entry
									+ "' and customer = " + customer.getId())
							.list();
					if (list.size() != 0) {
						featureUsage = (FeatureUsage) list.get(0);
						featureUsage.setCount(usage.get(entry));
						update(featureUsage);
						logger.debug("Updating " + featureUsage);
					} else {
						featureUsage = new FeatureUsage();
						featureUsage.setCustomer(customer);
						if (entry.equals("Miscellaneous") == true) {
							featureUsage.setCount(100);
						} else {
							featureUsage.setCount(usage.get(entry));
						}
						featureUsage.setFeatureName(entry);
						/*
						 * TODO: The below columns might be redundant. Hence
						 * placing dummy values
						 */
						featureUsage.setUrl("Dummy");
						featureUsage.setRetCode(200);
						save(featureUsage);
						logger.debug("Saving" + featureUsage);
					}
				}
				return true;
			}
		};

		transaction.execute();

		return transaction.getResult();
	}
}
