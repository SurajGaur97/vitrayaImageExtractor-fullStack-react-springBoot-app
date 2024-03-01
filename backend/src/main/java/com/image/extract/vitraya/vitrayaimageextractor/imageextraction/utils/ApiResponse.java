package com.image.extract.vitraya.vitrayaimageextractor.imageextraction.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
	
	private int status;
    private String message;
    private T result;
    private Long count;
    private Long id;
    private String token;

}
