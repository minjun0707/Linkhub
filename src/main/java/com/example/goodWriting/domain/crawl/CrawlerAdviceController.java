package com.example.goodWriting.domain.crawl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.goodWriting.common.CommonResponse;
import com.example.goodWriting.domain.user.exception.UserErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class CrawlerAdviceController {

	@ExceptionHandler(ConnectionTimeOutException.class)
	public ResponseEntity SameUserIdExistsException(HttpServletRequest request) {
		return ResponseEntity.status(CrawlErrorCode.CONNECTION_TIME_OUT.getCode())
			.body(CommonResponse.builder()
				.path(request.getRequestURI())
				.method(request.getMethod())
				.message(CrawlErrorCode.CONNECTION_TIME_OUT.getMsg())
				.data(null)
				.statusCode(CrawlErrorCode.CONNECTION_TIME_OUT.getCode())
				.build());
	}

}
