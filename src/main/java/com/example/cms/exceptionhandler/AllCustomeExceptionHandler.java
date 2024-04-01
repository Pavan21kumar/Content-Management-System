package com.example.cms.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.util.BlogNotFoundException;
import com.example.cms.util.DuplicateEmailException;
import com.example.cms.util.ErrorStructure;
import com.example.cms.util.IllegalAccessRequestException;
import com.example.cms.util.PanelNotFoundByIdException;
import com.example.cms.util.TitleAllreadyPresentException;
import com.example.cms.util.TopicIsNullException;
import com.example.cms.util.UNAUTHORIZEDException;
import com.example.cms.util.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class AllCustomeExceptionHandler {

	private ErrorStructure<String> errorStructure;

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTitleAllreadyPresentException(TitleAllreadyPresentException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("title Allready preset").setRootCouse(e.getMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTopicIsNullException(TopicIsNullException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("topic is empty").setRootCouse(e.getMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundByIdExceptionHandler(UserNotFoundByIdException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("user Not Found").setRootCouse(e.getMessage()));

	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerUserNameNotFoundExceptionHandler(UsernameNotFoundException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("Panel Not Found By Given Id").setRootCouse(e.getMessage()));
	}

	private ResponseEntity<ErrorStructure<String>> handlerexception(HttpStatus status, String message,
			String rootCouse) {
		return ResponseEntity.badRequest()
				.body(errorStructure.setStatusCode(status.value()).setMessage(message).setRootCouse(rootCouse));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> duplicateEmailExceptionHandler(DuplicateEmailException e) {

		return handlerexception(HttpStatus.BAD_REQUEST, "User Register faild", e.getMessage());
		// ResponseEntity.badRequest().body(structure.setStatusCode(HttpStatus.BAD_REQUEST.value())
		// .setMessage("duplicate email ").setRootCous/e(e.getMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerPanelNotFoundExceptionHandler(PanelNotFoundByIdException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("user name not found").setRootCouse(e.getMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerIllegalAccessRequestExceptionHandler(
			IllegalAccessRequestException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("IllegalAccess Request......").setRootCouse(e.getMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerUnutherizedExceptionHandler(UNAUTHORIZEDException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("Owner shloud be login.......").setRootCouse(e.getMessage()));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerBlogNotFoundExceptionHandler(BlogNotFoundException e) {
		return ResponseEntity.badRequest().body(errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value())
				.setMessage("Blog Not Found ......").setRootCouse(e.getMessage()));
	}

}
