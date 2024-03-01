package com.image.extract.vitraya.vitrayaimageextractor.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * DTO class for User
 * 
 * @author Suraj G
 *
 */
@Builder
@Getter
@Setter
public class UserDto {
	private Long id;
	private String userName;
	private String password;
    private String userType;
    private String loginIdStatus;
    private String role;
}
