package com.image.extract.vitraya.vitrayaimageextractor.authentication.exception;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.common.ErrorCode;

import lombok.Getter;

/**
 * User Management Authorization Exception
 * 
 * @author Suraj G
 *
 */
public class UserAuthorizationException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private ErrorCode errorCode;
	
	public UserAuthorizationException(String message) {
		super(message);
	}
	
	public UserAuthorizationException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public UserAuthorizationException(String message, ErrorCode errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
	
	public UserAuthorizationException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
