package com.example.cms.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.ErrorStructure;
import com.example.cms.util.TitleAllreadyPresentException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class TitleAllreadyPresentExceptionHandler {

	private ErrorStructure<String> errorStructure;

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTitleAllreadyPresentException(TitleAllreadyPresentException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("title Allready preset").setRootCouse(e.getMessage()));
	}
}
