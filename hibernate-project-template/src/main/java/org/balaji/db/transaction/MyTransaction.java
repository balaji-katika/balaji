package org.balaji.db.transaction;

/**
 * Interface for DB transaction through ORM
 * @author root
 *
 * @param <S>
 */
public interface MyTransaction <S> {
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
