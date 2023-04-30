package com.example.goodWriting.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodWriting.common.ResponseDto;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/api/user/test")
	public ResponseEntity test() {



		return ResponseEntity.ok()
			.body(ResponseDto.builder()
				.path("/api/user/login")
				.method("POST")
				.message("로그인 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

	@PostMapping("/api/user/login")
	public ResponseEntity login(@RequestBody UserLoginRequest userLoginRequest) {



		return ResponseEntity.ok()
			.body(ResponseDto.builder()
				.path("/api/user/login")
				.method("POST")
				.message("로그인 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

	@PostMapping("/api/user/signUp")
	public ResponseEntity signUp(@RequestBody UserSignUpRequest UserSignUpRequest) {

		//유효성 검사

		//회원가입 중복체크


		return ResponseEntity.ok()
			.body(ResponseDto.builder()
				.path("/api/user/signUp")
				.method("POST")
				.message("회원가입 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

}
