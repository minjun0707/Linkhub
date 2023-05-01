package com.example.goodWriting.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.goodWriting.common.CommonResponse;
import com.example.goodWriting.common.MethodValidationErrorData;

import jakarta.servlet.http.HttpServletRequest;

public class UserControllerAdvice {

	// @ExceptionHandler(SameUserIdExistsException.class)
	// public ResponseEntity SameUserIdExistsException(HttpServletRequest request) {
	// 	return ResponseEntity.status(UserErrorCode.EXISTED_SAME_ID.getCode())
	// 		.body(ResponseDto.builder()
	// 			.path(request.getRequestURI())
	// 			.method(request.getMethod())
	// 			.message(UserErrorCode.EXISTED_SAME_ID.getMsg())
	// 			.data(null)
	// 			.statusCode(UserErrorCode.EXISTED_SAME_ID.getCode())
	// 			.build());
	// }


}
