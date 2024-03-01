package com.image.extract.vitraya.vitrayaimageextractor.authentication.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.common.CommonConstants;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.dto.request.UserCredentialsRequest;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserAuthenticationException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserServiceResourceNotFoundException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.service.UserService;
import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.utils.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Controller class for login and session management
 * 
 * @author Suraj G
 *
 */
@CrossOrigin(methods = { RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST },
allowCredentials = "${allow.credentials}", origins = { "${front.end.host}" })
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	/**
	 * Generate token
	 * 
	 * @param userRequest {@link UserRequest}
	 * @return Returns role {@link RoleType}
	 * @throws UserAuthenticationException
	 * @throws UserServiceResourceNotFoundException 
	 */
	@PostMapping("v1/authenticateUser")
	public ResponseEntity<ApiResponse<String>> generateToken(@RequestBody UserCredentialsRequest userCredentials, HttpServletRequest request)
			throws UserAuthenticationException {
		try {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			
			userService.getAuthenticatUser(userCredentials);
			request.getSession().setAttribute(CommonConstants.IS_AUTHENTICATED, true);	//proving Key-Value pairs for setting an attribute.
			
			apiResponse.setMessage("Login Successfull!");
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setResult("Success!");
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setMessage("Login Failed!");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			apiResponse.setResult("failed!");
			
			return ResponseEntity.ok(apiResponse);
		}
		
	}

	/**
	 * Invalidates the session
	 * 
	 * @param request
	 */
	@GetMapping("v1/invalidateUser")
	public ResponseEntity<ApiResponse<String>> destroySession(HttpServletRequest request) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.invalidate();
		}
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("Logout successfull!");
		apiResponse.setResult("Logout");
		
		return ResponseEntity.ok(apiResponse);
	}

	/**
	 * Check if current session is active or not
	 * 
	 * @param request
	 * @return boolean
	 */
	@GetMapping("v1/isSessionActive")
	public boolean isSessionActive(HttpServletRequest request) {
		HttpSession existingSession = request.getSession(false);
		if (Objects.nonNull(existingSession)
				&& Objects.nonNull(existingSession.getAttribute(CommonConstants.IS_AUTHENTICATED))) {
			return true;
		}
		return false;
	}

}
