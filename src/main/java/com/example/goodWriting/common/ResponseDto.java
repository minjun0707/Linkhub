package com.example.goodWriting.common;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseDto<T> {
	public String path;
	public String method;
	public T data;
	public String message;
	public HttpStatus statusCode;

	@Builder
	public ResponseDto(String path, String method, T data, String message, HttpStatus statusCode) {
		this.path = path;
		this.method = method;
		this.data = data;
		this.message = message;
		this.statusCode = statusCode;
	}

	@Builder
	public ResponseDto(String path, String method, String message, HttpStatus statusCode) {
		this.path = path;
		this.method = method;
		this.message = message;
		this.statusCode = statusCode;
	}

}
