package com.example.goodWriting.domain.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.goodWriting.common.CommonResponse;
import com.example.goodWriting.domain.post.exception.InvalidUrlException;
import com.example.goodWriting.domain.post.exception.PostErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class PostControllerAdvice {

	@ExceptionHandler(InvalidUrlException.class)
	public ResponseEntity invalidUrlException(HttpServletRequest request) {

		return ResponseEntity.status(PostErrorCode.INVALID_URL.getCode())
			.body(CommonResponse.builder()
				.path(request.getRequestURI())
				.method(request.getMethod())
				.message(PostErrorCode.INVALID_URL.getMsg())
				.statusCode(PostErrorCode.INVALID_URL.getCode())
				.build());
	}

}
