package com.example.goodWriting.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodWriting.common.CommonResponse;
import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j

public class UserController {

	private final UserService userService;
	@PostMapping("/api/login")
	public ResponseEntity login(@RequestBody @Valid UserLoginRequest userLoginRequest, HttpServletRequest request
		) {

		log.info(userLoginRequest.getEmail());
		log.info(userLoginRequest.getPassword());

		User user = userService.login(userLoginRequest);

		//세션 생성 후 저장
		HttpSession session = request.getSession();
		session.setAttribute("email", user.getEmail());

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/login")
				.method("POST")
				.message("로그인 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

	@PostMapping("/api/sign-up")
	public ResponseEntity signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest,HttpServletRequest request) {

		User user = userService.signUp(userSignUpRequest);

		HttpSession session = request.getSession();
		session.setAttribute("email",user.getEmail());



		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/sign-up")
				.method("POST")
				.message("회원가입 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

	@GetMapping("/api/logout")
	public ResponseEntity logout(HttpServletRequest request) {

		log.info("logout");

		HttpSession session = request.getSession();
		session.invalidate();

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/logout")
				.method("GET")
				.message("로그아웃 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

	@GetMapping("/api/test")
	public String test() {
		return "테스트 성공!!";
	}



	

}
