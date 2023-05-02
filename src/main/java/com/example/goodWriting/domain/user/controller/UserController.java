package com.example.goodWriting.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodWriting.common.CommonResponse;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	@PostMapping("/api/login")
	public ResponseEntity login(@RequestBody @Valid UserLoginRequest userLoginRequest) {

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/login")
				.method("POST")
				.message("로그인 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

	@PostMapping("/api/sign-up")
	public ResponseEntity signUp(@RequestBody UserSignUpRequest UserSignUpRequest) {

		//유효성 검사
		userService.signUp(UserSignUpRequest);

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/signUp")
				.method("POST")
				.message("회원가입 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}


	@GetMapping("/api/test")
	public ResponseEntity test() {

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/user/test")
				.method("POST")
				.message("로그인 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

}
