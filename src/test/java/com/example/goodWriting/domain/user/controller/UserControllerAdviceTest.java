package com.example.goodWriting.domain.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.example.goodWriting.domain.user.LoginInterceptor;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.exception.NotFountUserException;
import com.example.goodWriting.domain.user.exception.PasswordNotMatchedException;
import com.example.goodWriting.domain.user.exception.UserEmailAlreadyException;
import com.example.goodWriting.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserControllerAdviceTest {

	@InjectMocks UserController UserController;
	@Mock UserService userService;
	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();


	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.standaloneSetup(UserController)
			.setControllerAdvice(new UserControllerAdvice())
			.addInterceptors(new LoginInterceptor())
			.addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
	}

	@Test()
	@DisplayName("API 응답 - 회원가입시 이메일이 이미 존재한다")
	public void singUpUserEmailAlreadyException() throws Exception {
		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("email@naver.com","김민준","123456abc","123456뮻");

		given(userService.signUp(any())).willThrow(UserEmailAlreadyException.class);


		//when //then
		mockMvc.perform(
				post("/api/sign-up")
					.content(objectMapper.writeValueAsString(userSignUpRequest))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.path").value("/api/sign-up"))
			.andExpect(jsonPath("$.message").value("이미 회원가입 되어있습니다."))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
	}

	@Test
	@DisplayName("API 응답 - 회원가입시 비밀번호와 비밀번호체크가 일치하지 않는다.")
	public void signUpPasswordNotMatchedException() throws Exception {

		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("email@naver.com","김민준","123456abc","123456뮻");

		given(userService.signUp(any())).willThrow(PasswordNotMatchedException.class);


		//when //then
		mockMvc.perform(
				post("/api/sign-up")
					.content(objectMapper.writeValueAsString(userSignUpRequest))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.path").value("/api/sign-up"))
			.andExpect(jsonPath("$.message").value("비밀번호를 동일하게 입력해주세요."))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
	}

	@Test
	@DisplayName("API 응답 -로그인시 해당하는 유저를 찾을 수 없다.")
	public void loginNotFountUserException() throws Exception {
		//given
		UserLoginRequest userLoginRequest = new UserLoginRequest("email@naver.com","123456abc");

		given(userService.login(any())).willThrow(NotFountUserException.class);


		//when //then
		mockMvc.perform(
				post("/api/login")
					.content(objectMapper.writeValueAsString(userLoginRequest))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.path").value("/api/login"))
			.andExpect(jsonPath("$.message").value("로그인 정보를 올바르게 입력해주세요"))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
	}




}
