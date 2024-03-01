package com.image.extract.vitraya.vitrayaimageextractor.imageextraction.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageExtractionResponseDto {
	
	private Long id;
	
	private String fileName;
	
	private String extractedText;
	
	private String boldText;
	
	private String base64String;
	
	private String fileType;

}
