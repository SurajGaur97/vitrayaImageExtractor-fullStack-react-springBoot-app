package com.image.extract.vitraya.vitrayaimageextractor.authentication.exception;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.common.ErrorCode;

import lombok.Getter;

/**
 * User Management Service Resource Not Found Exception
 * 
 * @author Suraj G
 *
 */
public class UserServiceResourceNotFoundException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private ErrorCode errorCode;

	public UserServiceResourceNotFoundException(String message) {
		super(message);
	}

	public UserServiceResourceNotFoundException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public UserServiceResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public UserServiceResourceNotFoundException(String message, ErrorCode errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
}
