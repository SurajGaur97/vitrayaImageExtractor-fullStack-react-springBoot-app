package com.image.extract.vitraya.vitrayaimageextractor.imageextraction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "image_extraction")
public class ImageExtraction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "extracted_text")
	private String extractedText;
	
	@Column(name = "bold_text")
	private String boldText;
	
	@Column(name = "file_type")
	private String fileType;
	
	@Column(name = "image_base64", columnDefinition = "longtext")
	private String imageBase64;
	
}
