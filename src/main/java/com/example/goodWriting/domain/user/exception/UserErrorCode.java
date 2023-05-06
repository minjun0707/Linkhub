package com.example.goodWriting.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.example.goodWriting.common.ErrorCode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

	// NOT_BLANK_ID(HttpStatus.BAD_REQUEST, "아이디를 입력해주세요"),
	// NOT_BLANK_PASSWORD(HttpStatus.BAD_REQUEST,"비밀번호를 입력해주세요"),
	// NOT_BLANK_NICKNAME(HttpStatus.BAD_REQUEST,"닉네임을 입력해주세요");
	PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST,"비밀번호를 동일하게 입력해주세요."),
	SAME_EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST,"이미 회원가입 되어있습니다."),
	NOT_FOUNT_USER(HttpStatus.BAD_REQUEST,"로그인 정보를 올바르게 입력해주세요"),
	NOT_LOGIN(HttpStatus.BAD_REQUEST,"로그인을 해주세요.");


	// NOT_BLANK_NICKNAME(HttpStatus.BAD_REQUEST,"닉네임을 입력해주세요");
	// NOT_BLANK_NICKNAME(HttpStatus.BAD_REQUEST,"닉네임을 입력해주세요");

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
