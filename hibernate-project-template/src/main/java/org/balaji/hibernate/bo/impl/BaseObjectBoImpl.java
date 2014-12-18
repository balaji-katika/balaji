package org.balaji.hibernate.bo.impl;

import java.util.List;

import org.balaji.hibernate.bo.BaseObjectBo;
import org.balaji.hibernate.dao.BaseObjectDao;
import org.balaji.hibernate.model.BaseObject;
import org.springframework.stereotype.Service;

@Service("baseObjBo")
public abstract class BaseObjectBoImpl<S extends BaseObject> implements
		BaseObjectBo<S> {

	@Override
	public void save(S obj) {
		getDao().save(obj);
	}

	@Override
	public void update(S obj) {
		getDao().update(obj);
	}

	@Override
	public void delete(S obj) {
		getDao().delete(obj);
	}

	@Override
	public S findById(int id) {
		return getDao().findById(id);
	}
	
	@Override
	public List<S> findAll() {
		return getDao().findAll();
	}

	/**
	 * Abstract method to be implemented by the derived business objects to get
	 * the associated DAO object
	 * 
	 * @return - DAO object
	 */
	public abstract BaseObjectDao<S> getDao();

}
