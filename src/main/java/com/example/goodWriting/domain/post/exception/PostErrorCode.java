package com.example.goodWriting.domain.post.exception;

import org.springframework.http.HttpStatus;

import com.example.goodWriting.common.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum  PostErrorCode implements ErrorCode {

	INVALID_URL(HttpStatus.BAD_REQUEST, "URL을 올바르게 입력해주세요");

	private HttpStatus code;
	private String msg;

	@Override
	public String getMsg() {
		return this.msg;
	}

	@Override
	public HttpStatus getCode() {
		return this.code;
	}

}
