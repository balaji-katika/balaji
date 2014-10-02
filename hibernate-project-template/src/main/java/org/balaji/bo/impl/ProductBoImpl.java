package org.balaji.bo.impl;

import org.balaji.dao.BaseObjectDao;
import org.balaji.dao.ProductDao;
import org.balaji.hibernate.bo.ProductBo;
import org.balaji.hibernate.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productBo")
public class ProductBoImpl extends BaseObjectBoImpl<Product> implements
		ProductBo {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product getByCollectorId(String collectorId) {
		return productDao.getByCollectorId(collectorId);
	}

	@Override
	public BaseObjectDao<Product> getDao() {
		// TODO Auto-generated method stub
		return productDao;
	}

}
