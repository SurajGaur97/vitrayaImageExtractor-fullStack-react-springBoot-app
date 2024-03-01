package com.image.extract.vitraya.vitrayaimageextractor.authentication.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Response class for User
 * 
 * @author Suraj G
 *
 */
@Builder
@Getter
@Setter
public class UserResponse {
	private String userName;
	private String userType;
    private String loginIdStatus;
    private String role;
}
