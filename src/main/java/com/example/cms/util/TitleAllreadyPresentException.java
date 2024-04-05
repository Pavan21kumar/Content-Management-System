package com.example.cms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TitleAllreadyPresentException extends RuntimeException {

	private String message;
}
