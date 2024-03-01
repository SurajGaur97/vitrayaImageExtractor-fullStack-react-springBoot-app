package com.image.extract.vitraya.vitrayaimageextractor.authentication.service;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.dto.request.UserCredentialsRequest;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserAuthenticationException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.model.UserDto;

/**
 * Interface for user service
 * 
 * @author Suraj G
 *
 */
public interface UserService {
	
	public UserDto getAuthenticatUser(UserCredentialsRequest userCredentials) throws UserAuthenticationException;

}
