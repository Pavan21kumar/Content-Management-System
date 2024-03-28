package com.example.cms.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.ErrorStructure;
import com.example.cms.util.TopicIsNullException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class TopicIsNullExceptionHandler {

	private ErrorStructure<String> errorStructure;

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTopicIsNullException(TopicIsNullException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("topic is empty").setRootCouse(e.getMessage()));
	}

}
