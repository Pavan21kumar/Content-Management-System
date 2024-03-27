package com.example.cms.exceptionhandler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cms.util.ErrorStructure;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

	private ErrorStructure<Map<String, String>> errorStructure;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> errors = new LinkedHashMap<>();
		ex.getAllErrors().forEach(error -> {
			errors.put(((FieldError) error).getField(), error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("invalid inputs").setRootCouse(errors));
	}
}
