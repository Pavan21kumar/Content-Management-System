package com.example.cms.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.ErrorStructure;
import com.example.cms.util.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class UserNotFoundByIdExceptionHandler {
	private ErrorStructure<String> structure;

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundByIdExceptionHandler(UserNotFoundByIdException e) {
		return ResponseEntity.badRequest().body(structure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("user Not Found").setRootCouse(e.getMessage()));

	}
}
