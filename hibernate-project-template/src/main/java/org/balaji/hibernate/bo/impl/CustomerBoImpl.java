package org.balaji.hibernate.bo.impl;

import org.balaji.hibernate.bo.CustomerBo;
import org.balaji.hibernate.dao.BaseObjectDao;
import org.balaji.hibernate.dao.CustomerDao;
import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerBo")
public class CustomerBoImpl extends BaseObjectBoImpl<Customer> implements
		CustomerBo {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public BaseObjectDao<Customer> getDao() {
		return customerDao;
	}

	@Override
	public Customer getCustomer(Product productId, String instance_id) {
		return customerDao.getCustomer(productId, instance_id);
	}

}
