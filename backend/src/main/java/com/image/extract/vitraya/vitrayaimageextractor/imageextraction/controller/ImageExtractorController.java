package com.image.extract.vitraya.vitrayaimageextractor.imageextraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.common.CommonConstants;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.common.ErrorCode;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserAuthenticationException;
import com.image.extract.vitraya.vitrayaimageextractor.authentication.exception.UserAuthorizationException;
import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.dto.ImageExtractionResponseDto;
import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.service.ImageExtractorService;
import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.utils.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, 
allowCredentials = "${allow.credentials}", origins = { "${front.end.host}" })
@RestController
@RequestMapping("/api/image/extractor")
public class ImageExtractorController {
	
	@Autowired
	private ImageExtractorService extractorService;
	
	/**
	 * @apiNote This API will save the image related data into database
	 * @param mediaFile
	 * @return ResponseEntity<ApiResponse<String>> 
	 * @throws UserAuthenticationException 
	 * @throws UserAuthorizationException 
	 */
	@PostMapping("v1/uploadImage")
	public ResponseEntity<ApiResponse<?>> uploadImage(@RequestPart(value = "mediaFile") MultipartFile mediaFile, 
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			validateAuth(session);
			
			ApiResponse<ImageExtractionResponseDto> response = new ApiResponse<>();

			ImageExtractionResponseDto responseDto = extractorService.saveExtractedData(mediaFile);
			
			response.setMessage("File upload Success!");
			response.setStatus(HttpStatus.ACCEPTED.value());
			response.setResult(responseDto);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			ApiResponse<String> response = new ApiResponse<>();
			response.setResult("There is some error occured !!");
			response.setMessage(e.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.ok(response);
		}
	}
	
	/**
	 * @apiNote This will give the image file and the image extracted data.
	 * @param id
	 * @return ResponseEntity<ApiResponse<?>>
	 * @throws UserAuthenticationException 
	 */
	@GetMapping("v1/getImageData")
	public ResponseEntity<ApiResponse<?>> getImageData(@RequestParam Long id, HttpServletRequest request) { 
		try {
			HttpSession session = request.getSession(false);
			validateAuth(session);
			
			ApiResponse<ImageExtractionResponseDto> response = new ApiResponse<>();

			ImageExtractionResponseDto responseDto = extractorService.getImageData(id);
			
			response.setMessage("Image get Successfull!");
			response.setStatus(HttpStatus.ACCEPTED.value());
			response.setResult(responseDto);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			ApiResponse<String> response = new ApiResponse<>();
			response.setResult("There is some error occured !!");
			response.setMessage(e.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.ok(response);
		}
	}
	
	@GetMapping("v1/getImagesList")
	public ResponseEntity<ApiResponse<?>> getImagesList(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			validateAuth(session);
			
			ApiResponse<List<ImageExtractionResponseDto>> response = new ApiResponse<>();

			List<ImageExtractionResponseDto> responseDto = extractorService.getImagesList();
			
			response.setMessage("Images List Get Success!");
			response.setStatus(HttpStatus.ACCEPTED.value());
			response.setResult(responseDto);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			ApiResponse<String> response = new ApiResponse<>();
			response.setResult("There is some error occured !!");
			response.setMessage(e.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.ok(response);
		}
	}
	
	private void validateAuth(HttpSession session)
			throws UserAuthenticationException {
		if (null == session || null == session.getAttribute(CommonConstants.IS_AUTHENTICATED)) {
			throw new UserAuthenticationException("User is not authenticated!", ErrorCode.AUTHENTICATION_FAILED);
		}
	}
	
	
	/**
	 * @implNote testing API
	 * @return
	 */
	@GetMapping("/v1/welcome")
	public ResponseEntity<ApiResponse<String>> welcome() {
		ApiResponse<String> response = new ApiResponse<>();
		
		response.setMessage("Welcome to the Image-Extration Service!");
		response.setStatus(HttpStatus.OK.value());
		
		
		return ResponseEntity.ok(response);
	}
}
