package com.example.cms.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class Responstructure<T> {

	private int statusCode;
	private String message;
	private T data;

	public Responstructure<T> setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public Responstructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public Responstructure<T> setData(T data) {
		this.data = data;
		return this;
	}

}
