package org.balaji.dao;

import java.util.Properties;

import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.Issue;

/**
 * DAO for Issue
 * @author root
 *
 */
public interface IssueDao extends BaseObjectDao<Issue> {

	/**
	 * Update the issues in the persistent store
	 * 
	 * @param customer
	 * @param properties
	 */
	public void updateIssues(Customer customer, Properties properties);

	/**
	 * Find the issue for the customer
	 * @param customer
	 * @param name
	 * @return
	 */
	public Issue findByCustKey(Customer customer, String name);

}
