package com.image.extract.vitraya.vitrayaimageextractor.authentication.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Credentials dto
 * 
 * @author Suraj G
 *
 */
@Getter
@Setter
public class UserCredentialsRequest {
	
	private String userName;
	private String password;

}
