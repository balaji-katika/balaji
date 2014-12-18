package org.balaji.db.transaction;

/**
 * Facade for DB transaction through ORM
 * @author Balaji Katika
 *
 * @param <S>
 */
public interface DBTransactionFacade <S> {
	/**
	 * Execute a read only transaction
	 */
	public S executeReadOnly();
	/**
	 * Execute and commit the transaction
	 */
	public void execute();
	/**
	 * Get the result associated with the transaction
	 * @return
	 */
	public S getResult();
}
