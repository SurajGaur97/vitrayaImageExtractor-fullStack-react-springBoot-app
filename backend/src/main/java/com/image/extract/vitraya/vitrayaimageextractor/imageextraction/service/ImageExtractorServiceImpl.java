package com.image.extract.vitraya.vitrayaimageextractor.imageextraction.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.dto.ImageExtractionResponseDto;
import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.entity.ImageExtraction;
import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.repository.ImageExtractionRepo;
import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.utils.UtilFunctions;



@Service
public class ImageExtractorServiceImpl implements ImageExtractorService {

	@Autowired
	private ImageExtractionRepo extractionRepo;
	
	@Autowired
	private UtilFunctions utilFunctions;

	
	@Override
	public ImageExtractionResponseDto saveExtractedData(MultipartFile mediaFile) throws Exception {
		
		//Getting extracted text from image.
		String text = utilFunctions.extractTextByTesseract(mediaFile);
//		String text = utilFunctions.extractTextByAspose(mediaFile);

		//Converting image to base64 string data.
		String base64Image = utilFunctions.encodeImageToBase64(mediaFile);

		ImageExtraction imageExtraction = new ImageExtraction();
		imageExtraction.setFileName(mediaFile.getOriginalFilename());
		imageExtraction.setExtractedText(text);
		imageExtraction.setBoldText(text);
		imageExtraction.setImageBase64(base64Image);
		imageExtraction.setFileType(mediaFile.getContentType());

		extractionRepo.save(imageExtraction);
		
		ImageExtractionResponseDto responseDto = ImageExtractionResponseDto.builder()
				.id(imageExtraction.getId())
				.fileName(imageExtraction.getFileName())
				.extractedText(imageExtraction.getExtractedText())
				.boldText(imageExtraction.getBoldText())
				.base64String(imageExtraction.getImageBase64())
				.fileType(imageExtraction.getFileType())
				.build();
		
		return responseDto;
	}

	@Override
	public ImageExtractionResponseDto getImageData(Long id) throws IOException {
		Optional<ImageExtraction> imageExtractionOptional = extractionRepo.findById(id);
		
		ImageExtractionResponseDto responseDto = null;
		if(imageExtractionOptional.isPresent()) {
			ImageExtraction imageExtraction = imageExtractionOptional.get();
			
			responseDto = ImageExtractionResponseDto.builder()
					.id(imageExtraction.getId())
					.fileName(imageExtraction.getFileName())
					.extractedText(imageExtraction.getExtractedText())
					.boldText(imageExtraction.getBoldText())
					.base64String(imageExtraction.getImageBase64())
					.fileType(imageExtraction.getFileType())
					.build();
			
//					MultipartFile imageFile = utilFunctions.createMultipartFile(imageExtraction.getImageBase64(), 
//					imageExtraction.getFileName(), imageExtraction.getFileType());
		}
		
		return responseDto;
	}

	@Override
	public List<ImageExtractionResponseDto> getImagesList() {
		List<ImageExtraction> imageExtractionList = extractionRepo.findAll(Sort.by(Sort.Order.desc("id")));
		
		List<ImageExtractionResponseDto> responseDtoList = new ArrayList<>();
		
		if(!imageExtractionList.isEmpty() && imageExtractionList != null) {
			imageExtractionList.forEach(item -> {
				ImageExtractionResponseDto responseDto = ImageExtractionResponseDto
						.builder().id(item.getId()).fileName(item.getFileName()).extractedText(item.getExtractedText())
						.boldText(item.getBoldText()).base64String(item.getImageBase64()).fileType(item.getFileType())
						.build();
				
				responseDtoList.add(responseDto);
			});
		}
		return responseDtoList;
	}

}
