package org.balaji.exception;

/**
 * Generic exception thrown for telemetry related errors
 * 
 * @author root
 *
 */
public class BalajiException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cause = null;

	/**
	 * Message to be parsed
	 * @param msg
	 */
	public BalajiException(String msg) {
		super(msg);
	}
	
	public void setCause(String cause)
	{
		this.cause  = cause;		
	}
}
