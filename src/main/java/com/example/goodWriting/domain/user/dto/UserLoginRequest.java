package com.example.goodWriting.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

	@NotBlank(message = "아이디를 입력해주세요")
	private String userId;
	@NotBlank(message = "비밀번호를 입력해주세요")
	private String password;

}
