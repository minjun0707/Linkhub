package com.example.goodWriting.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

public class MethodValidationControllerAdvice {

	//UserSaveRequest 유효성 검사
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity userLoginRequestValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

		MethodValidationErrorData ErrorData =
			new MethodValidationErrorData((e.getBindingResult().getFieldError().getField()),
				String.valueOf(e.getBindingResult().getFieldError().getRejectedValue()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(CommonResponse.builder()
				.path(request.getRequestURI())
				.method(request.getMethod())
				.message(e.getBindingResult().getFieldError().getDefaultMessage())
				.data(ErrorData)
				.statusCode(HttpStatus.BAD_REQUEST)
				.build());
	}
}
