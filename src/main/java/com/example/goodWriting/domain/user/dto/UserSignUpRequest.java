package com.example.goodWriting.domain.user.dto;

import lombok.Data;

@Data
public class UserSignUpRequest {

	private String id;
	private String name;
	private String password;
	private String passwordCheck;

}
