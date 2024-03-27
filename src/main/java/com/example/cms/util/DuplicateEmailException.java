package com.example.cms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DuplicateEmailException extends RuntimeException {

	private String message;
	
}
