package com.example.goodWriting.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.example.goodWriting.common.ErrorCode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

	NOT_BLANK_ID(HttpStatus.BAD_REQUEST, "아이디를 입력해주세요"),
	NOT_BLANK_PASSWORD(HttpStatus.BAD_REQUEST,"비밀번호를 입력해주세요"),
	NOT_BLANK_NICKNAME(HttpStatus.BAD_REQUEST,"닉네임을 입력해주세요");

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
