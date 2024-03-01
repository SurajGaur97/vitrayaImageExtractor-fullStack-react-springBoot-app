package com.image.extract.vitraya.vitrayaimageextractor.authentication.util;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.dto.request.UserRequest;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.dto.response.UserResponse;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.entity.AuthUser;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.model.UserDto;

import lombok.experimental.UtilityClass;

/**
 * An utility class for user mapping
 * 
 * @author Suraj G
 *
 */
@UtilityClass
public class UserMapperUtil {

	/**
	 * Map User to UserDto
	 * 
	 * @param user {@link User}
	 * @return userDto {@link UserDto}
	 */
	public UserDto mapToUserDto(AuthUser user) {
		return UserDto.builder().id(user.getId()).userName(user.getUserName()).password(user.getPassword())
				.userType(user.getUserType()).loginIdStatus(user.getLoginIdStatus()).role(user.getRole()).build();
	}

	/**
	 * Map UserRequest to UserDto
	 * 
	 * @param user {@link UserRequest}
	 * @return userDto {@link UserDto}
	 */
	public UserDto mapToUserDto(UserRequest userRequest) {
		return UserDto.builder().userName(userRequest.getUserName()).loginIdStatus(userRequest.getLoginIdStatus())
				.role(userRequest.getRole()).userType(userRequest.getUserType()).build();
	}

	/**
	 * Map UserDto to User
	 * 
	 * @param userDto {@link UserDto}
	 * @return user {@link User}
	 */
	public AuthUser mapToUser(UserDto userDto) {
		return AuthUser.builder().id(userDto.getId()).userName(userDto.getUserName()).password(userDto.getPassword())
				.userType(userDto.getUserType()).loginIdStatus(userDto.getLoginIdStatus()).role(userDto.getRole()).build();
	}

	/**
	 * Map UserDto to UserResponse
	 * 
	 * @param userDto {@link UserDto}
	 * @return user {@link UserResponse}
	 */
	public UserResponse mapToUserResponse(UserDto userDto) {
		return UserResponse.builder().userName(userDto.getUserName()).loginIdStatus(userDto.getLoginIdStatus())
				.role(userDto.getRole()).userType(userDto.getUserType()).build();
	}

}
