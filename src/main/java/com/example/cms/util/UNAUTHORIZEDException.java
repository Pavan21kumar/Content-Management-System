package com.example.cms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UNAUTHORIZEDException extends RuntimeException {

	private String message;
}
