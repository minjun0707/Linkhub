package com.example.goodWriting.domain.user.dto;

import com.example.goodWriting.domain.user.domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {

	@NotBlank(message = "이메일을 입력해주세요")
	@Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$" , message = "이메일을 올바르게 입력해주세요.")
	private String email;
	@NotBlank(message = "이름을 입력해주세요")
	private String name;
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,16}$", message = "비밀번호는 8~16자,영문 소문자, 숫자를 사용해서 만들어주세요")
	private String password;

	@NotBlank(message = "비밀번호를 다시 한번 입력해주세요")
	private String passwordCheck;

	public User toEntity() {
		return new User(this.name,this.email,this.password);
	}

}
