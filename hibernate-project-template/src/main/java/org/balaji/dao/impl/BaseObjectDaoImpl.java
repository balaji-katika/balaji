package org.balaji.dao.impl;

import java.util.List;

import org.balaji.dao.BaseObjectDao;
import org.balaji.db.transaction.CustomHibernateDaoSupport;
import org.balaji.db.transaction.MyDBTransaction;
import org.balaji.db.transaction.MyTransaction;
import org.balaji.hibernate.model.BaseObject;

//@Repository("baseObjectDao")
public abstract class BaseObjectDaoImpl<S extends BaseObject> extends
		CustomHibernateDaoSupport implements BaseObjectDao<S> {

	private Class<? extends BaseObject> class1;

	public BaseObjectDaoImpl() {
	}

	/**
	 * Constructor for the Base Object DAO Impl
	 * 
	 * @param class1
	 */
	public BaseObjectDaoImpl(Class<? extends BaseObject> class1) {
		// TODO Auto-generated constructor stub
		this.class1 = class1;
	}

	@Override
	public void save(final S obj) {
		MyTransaction<Void> transaction = new MyDBTransaction<Void>(
				getCurrentSession()) {
			@Override
			protected Void doSomething() {
				session.save(obj);
				return null;
			}
		};
		transaction.execute();
	}

	@Override
	public void update(final S obj) {
		MyTransaction<Void> transaction = new MyDBTransaction<Void>(
				getCurrentSession()) {
			@Override
			protected Void doSomething() {
				session.update(obj);
				return null;
			}
		};
		transaction.execute();
	}

	@Override
	public void delete(final S obj) {
		MyTransaction<Void> transaction = new MyDBTransaction<Void>(
				getCurrentSession()) {
			@Override
			protected Void doSomething() {
				session.delete(obj);
				return null;
			}
		};
		transaction.execute();

	}

	@Override
	public S findById(final int id) {
		MyTransaction<S> transaction = new MyDBTransaction<S>(
				getCurrentSession()) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			protected S doSomething() {
				List list = null;
				list = session.createQuery(
						"from " + class1.getName() + " where id = " + id)
						.list();
				if (list.size() == 0) {
					return null;
				}
				return (S) list.get(0);
			}
		};
		return transaction.executeReadOnly();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<S> findAll() {
		MyTransaction<List> transaction = new MyDBTransaction<List>(
				getCurrentSession()) {
			@Override
			protected List doSomething() {
				List list = null;
				list = session.createQuery("from " + class1.getName()).list();
				return list;
			}
		};
		return transaction.executeReadOnly();
	}

	/**
	 * Returns the class for the Entity
	 * 
	 * @return
	 */
	protected Class<? extends BaseObject> getObjectClass() {
		return this.class1;
	}

	/**
	 * Find the entity by the query
	 * 
	 * @param query
	 * @return
	 */
	public S findEntityByQuery(final String query) {
		MyTransaction<S> myTransaction = new MyDBTransaction<S>(
				getCurrentSession()) {

			@SuppressWarnings("unchecked")
			@Override
			protected S doSomething() {
				@SuppressWarnings("rawtypes")
				List list = session.createQuery(query).list();
				if (list != null && list.size() != 0) {
					return (S) list.get(0);
				}

				return null;
			}
		};

		return myTransaction.executeReadOnly();

	}
}
