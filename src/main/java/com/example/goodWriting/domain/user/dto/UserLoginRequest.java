package com.example.goodWriting.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

	@NotBlank(message = "이메일을 입력해주세요")
	@Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$" , message = "이메일을 올바르게 입력해주세요.")
	private String email;
	@NotBlank(message = "비밀번호를 입력해주세요")
	private String password;

}
