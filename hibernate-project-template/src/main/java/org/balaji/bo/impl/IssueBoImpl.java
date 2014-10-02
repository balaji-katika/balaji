package org.balaji.bo.impl;

import java.util.Properties;

import org.balaji.dao.BaseObjectDao;
import org.balaji.dao.IssueDao;
import org.balaji.hibernate.bo.IssueBo;
import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of Issue Business Object
 * @author root
 *
 */
@Service("issueBo")
public class IssueBoImpl extends BaseObjectBoImpl<Issue> implements IssueBo {

	@Autowired
	private IssueDao dao;

	@Override
	public void updateIssues(Customer customer, Properties properties) {
		dao.updateIssues(customer, properties);
	}

	@Override
	public BaseObjectDao<Issue> getDao() {
		return dao;
	}

}
