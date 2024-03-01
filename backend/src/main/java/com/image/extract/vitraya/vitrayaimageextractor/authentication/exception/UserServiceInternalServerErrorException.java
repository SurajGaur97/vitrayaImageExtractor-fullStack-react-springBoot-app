package com.image.extract.vitraya.vitrayaimageextractor.authentication.exception;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.common.ErrorCode;

import lombok.Getter;

/**
 * User Management Service Internal server Exception
 * 
 * @author Suraj G
 *
 */
public class UserServiceInternalServerErrorException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private ErrorCode errorCode;

	public UserServiceInternalServerErrorException(String message) {
		super(message);
	}
	
	public UserServiceInternalServerErrorException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public UserServiceInternalServerErrorException(String message, Throwable throwable) {
		super(message, throwable);
	}
	


	public UserServiceInternalServerErrorException(String message, ErrorCode errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
}
