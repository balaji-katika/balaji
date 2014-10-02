package org.balaji.dao.impl;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.balaji.dao.IssueDao;
import org.balaji.db.transaction.MyDBTransaction;
import org.balaji.db.transaction.MyTransaction;
import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.Issue;
import org.balaji.hibernate.model.SystemDetails;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of Issue DAO
 * 
 * @author root
 *
 */
@Repository("issueDao")
public class IssueDaoImpl extends BaseObjectDaoImpl<Issue> implements IssueDao {
	private static Logger logger = Logger.getLogger(IssueDaoImpl.class);

	/**
	 * Default constructor
	 */
	public IssueDaoImpl() {
		super(Issue.class);
	}

	@Override
	public void updateIssues(final Customer customer,
			final Properties properties) {
		MyTransaction<Void> transaction = new MyDBTransaction<Void>(
				getCurrentSession()) {

			@Override
			protected Void doSomething() {
				Issue issue = null;
				for (Object key : properties.keySet()) {
					String k1 = (String) key;

					issue = findByCustKey(customer, k1);

					if (issue == null) {
						issue = new Issue();
						issue.setCustomer(customer);
						issue.setName(k1);
						issue.setCount(Integer.parseInt(((String) properties
								.getProperty(k1)).trim()));
						save(issue);
					} else {
						/*
						 * Update the existing issue details
						 */
						logger.debug("Updating the issue detail " + issue);
						issue.setCount(Integer.parseInt(((String) properties
								.getProperty(k1)).trim()));
						update(issue);
					}
				}

				return null;
			}
		};

		transaction.execute();

		return;

	}

	@Override
	public Issue findByCustKey(final Customer customer, final String name) {
		MyTransaction<Issue> transaction = new MyDBTransaction<Issue>(
				getCurrentSession()) {

			@Override
			protected Issue doSomething() {
				@SuppressWarnings("rawtypes")
				List list = session.createQuery(
						"from Issue where customer = " + customer.getId()
								+ " and name = '" + name + "'").list();
				if (list == null || list.size() == 0) {
					return null;
				}

				return (Issue) list.get(0);
			}
		};

		return transaction.executeReadOnly();
	}

}
