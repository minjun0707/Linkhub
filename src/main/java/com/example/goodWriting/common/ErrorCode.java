package com.example.goodWriting.common;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
	String getMsg();
	HttpStatus getCode();
}
