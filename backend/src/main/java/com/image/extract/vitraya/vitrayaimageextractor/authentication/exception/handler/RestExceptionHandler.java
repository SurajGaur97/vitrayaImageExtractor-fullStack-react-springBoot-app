package com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserAuthenticationException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserAuthorizationException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserServiceException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserServiceInternalServerErrorException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserServiceResourceNotFoundException;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = UserServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBadRequest(UserServiceException ex) {
		return new ErrorResponse(400, ex.getErrorCode(), "Bad Request");
	}

	@ExceptionHandler(value = NoSuchAlgorithmException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleInternalServerError(NoSuchAlgorithmException ex) {
		return new ErrorResponse(500, "Internal Server Error");
	}

	@ExceptionHandler(value = UserServiceInternalServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleInternalServerError(UserServiceInternalServerErrorException ex) {
		return new ErrorResponse(500, ex.getErrorCode(), "Internal Server Error");
	}

	@ExceptionHandler(value = UserServiceResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNotFound(UserServiceResourceNotFoundException ex) {
		return new ErrorResponse(404, ex.getErrorCode(), "Resource not found");
	}

	@ExceptionHandler(value = UserAuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResponse handleNotAutheticate(UserAuthenticationException ex) {
		return new ErrorResponse(401, "User is unauthenticated");
	}

	@ExceptionHandler(value = UserAuthorizationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorResponse handleNotAuthorize(UserAuthorizationException ex) {
		return new ErrorResponse(403, ex.getErrorCode(), "User is unauthorized");
	}
}
