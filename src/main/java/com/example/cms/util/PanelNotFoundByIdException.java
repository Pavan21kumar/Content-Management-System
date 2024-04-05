package com.example.cms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PanelNotFoundByIdException extends RuntimeException {

	private String message;
}
