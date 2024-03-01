package com.image.extract.vitraya.vitrayaimageextractor.imageextraction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.dto.ImageExtractionResponseDto;



public interface ImageExtractorService {

	ImageExtractionResponseDto saveExtractedData(MultipartFile mediaFile) throws Exception;

	ImageExtractionResponseDto getImageData(Long id) throws IOException;

	List<ImageExtractionResponseDto> getImagesList();
	
}
