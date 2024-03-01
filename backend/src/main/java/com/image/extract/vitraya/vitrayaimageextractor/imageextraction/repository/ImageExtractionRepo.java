package com.image.extract.vitraya.vitrayaimageextractor.imageextraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.image.extract.vitraya.vitrayaimageextractor.imageextraction.entity.ImageExtraction;


public interface ImageExtractionRepo extends JpaRepository<ImageExtraction, Long>{

}
