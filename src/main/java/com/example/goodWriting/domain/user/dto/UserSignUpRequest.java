package com.example.goodWriting.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {

	@NotBlank(message = "아이디를 입력해주세요")
	private String id;
	@NotBlank(message = "이름을 입력해주세요")
	private String name;
	@NotBlank(message = "비밀번호를 입력해주세요")
	private String password;
	@NotBlank(message = "비밀번호를 다시 한번 입력해주세요")
	private String passwordCheck;

}
