package com.example.goodWriting.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MethodValidationControllerAdvice {

	//UserSaveRequest 유효성 검사
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity methodArgumentNotValidException(HttpServletRequest request,
		MethodArgumentNotValidException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(CommonResponse.builder()
				.path(request.getRequestURI())
				.method(request.getMethod())
				.message(e.getBindingResult().getFieldError().getDefaultMessage())
				.statusCode(HttpStatus.BAD_REQUEST)
				.build());
	}
}
