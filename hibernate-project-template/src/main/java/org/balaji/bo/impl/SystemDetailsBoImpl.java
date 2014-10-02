package org.balaji.bo.impl;

import java.util.Properties;

import org.balaji.dao.BaseObjectDao;
import org.balaji.dao.SystemDetailsDao;
import org.balaji.hibernate.bo.SystemDetailsBo;
import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.SystemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of System Details Business Object
 * @author root
 *
 */
@Service("systemDetailsBo")
public class SystemDetailsBoImpl extends BaseObjectBoImpl<SystemDetails>
		implements SystemDetailsBo {

	@Autowired
	private SystemDetailsDao systemDetailsDao;

	@Override
	public void updateSystemDetails(Customer customer, Properties properties) {
		systemDetailsDao.updateSystemDetails(customer, properties);
	}

	@Override
	public BaseObjectDao<SystemDetails> getDao() {
		return systemDetailsDao;
	}

}
