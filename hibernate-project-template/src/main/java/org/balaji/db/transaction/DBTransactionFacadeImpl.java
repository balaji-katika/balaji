package org.balaji.db.transaction;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Facade for interacting with hibernate transactions
 * @author Balaji Katika
 *
 */
public abstract class DBTransactionFacadeImpl<S> implements DBTransactionFacade<S> {
	private Logger logger = Logger.getLogger(DBTransactionFacadeImpl.class);
	protected Session session;
	private boolean isOpenedByMe = false;
	private Transaction transaction = null;
	S result;

	/**
	 * Constructor for MyDBTransaction
	 * 
	 * @param session
	 */
	public DBTransactionFacadeImpl(Session session) {
		this.session = session;
	}
	
	/**
	 * Execute and commit the transaction
	 */
	@Override
	public void execute() {
		begin();
		result = doSomething();
		commit();
	}

	/**
	 * Do the actual work
	 * @return
	 */
	protected abstract S doSomething();
	
	@Override
	public S getResult() {
		return result;
	}

	/**
	 * Execute the statement but do not commit it.
	 */
	public S executeReadOnly() {
		begin();
		result = doSomething();
		rollback();
		return getResult();
	}

	/**
	 * Rollback the transaction
	 */
	private void rollback() {
		if (isOpenedByMe == true) {
			logger.debug("Rollback the transaction");
			transaction.rollback();
			isOpenedByMe = false;
		}		
	}

	/**
	 * Commit the transaction
	 */
	private void commit() {
		// TODO Auto-generated method stub
		if (isOpenedByMe == true) {
			logger.debug("Committing the transaction");
			transaction.commit();	
			isOpenedByMe = false;
		}
	}

	private void begin() {
		transaction  = session.getTransaction();
		if (transaction == null) {
			logger.debug("Transaction object is null");
			transaction = session.beginTransaction();
			isOpenedByMe  = true;
		}
		
		if (transaction != null && !transaction.isActive()) {
			logger.debug("Transaction not open. Opening it)");
				transaction.begin();
				isOpenedByMe = true;			
		}		
	}
}
