package com.image.extract.vitraya.vitrayaimageextractor.authentication.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Request class for user
 * @author Suraj G
 *
 */
@Builder
@Getter
@Setter
public class UserRequest {
	private String userName;
	private String userType;
    private String loginIdStatus;
    private String role;
}
