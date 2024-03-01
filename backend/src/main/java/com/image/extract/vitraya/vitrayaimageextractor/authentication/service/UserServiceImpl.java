package com.image.extract.vitraya.vitrayaimageextractor.authentication.service;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.dto.request.UserCredentialsRequest;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.entity.AuthUser;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserAuthenticationException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.model.UserDto;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.repository.AuthUserRepo;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.util.UserMapperUtil;

/**
 * Service class for user
 * 
 * @author Suraj G
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AuthUserRepo userRepository;

	/**
	 * Get user if user is authenticate
	 * 
	 * @param userCredentials {@link UserCredentialsRequest}
	 * 
	 * @return {@link UserDto}
	 *
	 * @throws UserAuthenticationException
	 * @author Suraj G
	 */
	@Override
	public UserDto getAuthenticatUser(UserCredentialsRequest userCredentials) throws UserAuthenticationException {
		String encodedPass = userCredentials.getPassword();
		AuthUser user = userRepository.findByUserNameAndPassword(userCredentials.getUserName(), encodedPass);
		if (Objects.nonNull(user)) {
			return UserMapperUtil.mapToUserDto(user);
		}
		throw new UserAuthenticationException("User is not authenticated!");
	}

}
