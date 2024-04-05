package com.example.cms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TopicIsNullException extends RuntimeException {

	private String message;
}
