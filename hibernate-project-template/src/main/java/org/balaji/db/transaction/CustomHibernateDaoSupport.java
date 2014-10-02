package org.balaji.db.transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CustomHibernateDaoSupport /* extends HibernateDaoSupport */{
	/*
	 * @Autowired public void anyMethodName(SessionFactory factory) {
	 * //setSessionFactory(factory); }
	 */
	//@Resource(name="sessionFactory")
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Session getCurrentSession() {
		//return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}

}
