package com.example.cms.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.DuplicateEmailException;
import com.example.cms.util.ErrorStructure;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class DuplicateEmailExceptionHandler {

	private ErrorStructure<String> structure;

	private ResponseEntity<ErrorStructure<String>> handlerexception(HttpStatus status, String message,
			String rootCouse) {
		return ResponseEntity.badRequest()
				.body(structure.setStatusCode(status.value()).setMessage(message).setRootCouse(rootCouse));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> duplicateEmailExceptionHandler(DuplicateEmailException e) {

		return handlerexception(HttpStatus.BAD_REQUEST, "User Register faild", e.getMessage());
		// ResponseEntity.badRequest().body(structure.setStatusCode(HttpStatus.BAD_REQUEST.value())
		// .setMessage("duplicate email ").setRootCouse(e.getMessage()));
	}

}
