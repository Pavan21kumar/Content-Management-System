package com.example.cms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InValidDateTimeException extends RuntimeException {

	private String message;
}
