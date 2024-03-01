package com.image.extract.vitraya.vitrayaimageextractor.authentication.exception;

/**
 * User Management Database write exception
 * 
 * @author Suraj G
 *
 */
public class UserDatabaseWriteException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	public UserDatabaseWriteException(String message) {
		super(message);
	}
	
	public UserDatabaseWriteException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
