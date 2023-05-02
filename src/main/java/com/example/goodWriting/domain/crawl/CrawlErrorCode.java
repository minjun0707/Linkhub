package com.example.goodWriting.domain.crawl;

import org.springframework.http.HttpStatus;

import com.example.goodWriting.common.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum CrawlErrorCode implements ErrorCode {

	CONNECTION_TIME_OUT(HttpStatus.BAD_REQUEST, "해당 url에 접속하지 못했습니다");

	private HttpStatus code;
	private String msg;

	@Override
	public String getMsg() {
		return null;
	}

	@Override
	public HttpStatus getCode() {
		return null;
	}
}
