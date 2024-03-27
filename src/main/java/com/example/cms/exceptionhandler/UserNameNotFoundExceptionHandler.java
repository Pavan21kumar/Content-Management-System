package com.example.cms.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.ErrorStructure;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class UserNameNotFoundExceptionHandler {

	private ErrorStructure<String> errorStructure;

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerUserNameNotFoundExceptionHandler(UsernameNotFoundException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("user name not found").setRootCouse(e.getMessage()));
	}
}
