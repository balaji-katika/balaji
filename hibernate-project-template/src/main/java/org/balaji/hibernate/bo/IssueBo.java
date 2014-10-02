package org.balaji.hibernate.bo;

import java.util.Properties;

import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.Issue;

/**
 * Business object for the Issue
 * @author root
 *
 */
public interface IssueBo extends BaseObjectBo<Issue> {

	/**
	 * Update the issue details
	 * @param customer
	 * @param properties
	 */
	void updateIssues(Customer customer, Properties properties);

}
