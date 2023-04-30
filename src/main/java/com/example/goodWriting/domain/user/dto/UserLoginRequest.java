package com.example.goodWriting.domain.user.dto;

import lombok.Data;

@Data
public class UserLoginRequest {

	private String userId;
	private String password;

}
