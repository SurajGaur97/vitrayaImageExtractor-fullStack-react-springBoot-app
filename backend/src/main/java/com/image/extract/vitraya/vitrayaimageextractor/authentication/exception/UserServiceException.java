package com.image.extract.vitraya.vitrayaimageextractor.authentication.exception;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.common.ErrorCode;

import lombok.Getter;

/**
 * User Management Service Exception
 * 
 * @author Suraj G
 *
 */
public class UserServiceException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private ErrorCode errorCode;

	public UserServiceException(String message) {
		super(message);
	}
	
	public UserServiceException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public UserServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	


	public UserServiceException(String message, ErrorCode errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
}
