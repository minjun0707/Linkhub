package com.example.goodWriting.domain.user.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)


public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	// MockHttpSession session;
	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(userController)
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	// @BeforeEach
	// public void setUp() throws Exception {
	// 	session = new MockHttpSession();
	// 	session.setAttribute("email", "jmk7117@naver.com");
	// }

	@Test
	void signUp() throws Exception {
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest( "jmk7117@naver.com","김민준" ,"123456abc","123456abc");
		String body = objectMapper.writeValueAsString(userSignUpRequest);
		User user = new User("김민준","jmk7117@naver.com","123456abc");
		when(userService.signUp((any()))).thenReturn(user);

		//when //then
		mockMvc.perform(
				post("/api/sign-up")
					.content(body)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.path").value("/api/sign-up"))
			.andExpect(jsonPath("$.message").value("회원가입 성공"))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("OK"));
	}


	@Test
	@DisplayName("로그인 성공")
	void login() throws Exception {

		//given
		UserLoginRequest userLoginRequest = new UserLoginRequest("jmk7117@naver.com", "123456abc");
		String body = objectMapper.writeValueAsString(userLoginRequest);
		User user = new User("김민준","jmk7117@naver.com","123456abc");
		when(userService.login((any()))).thenReturn(user);

		//when //then
		mockMvc.perform(
				post("/api/login")
					.content(body)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.path").value("/api/login"))
		.andExpect(jsonPath("$.message").value("로그인 성공"))
		.andExpect(jsonPath("$.statusCode").value("OK"));
	}

	@Test
	void logout() throws Exception {


		//when //then
		mockMvc.perform(
				get("/api/logout"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.path").value("/api/logout"))
			.andExpect(jsonPath("$.message").value("로그아웃 성공"))
			.andExpect(jsonPath("$.statusCode").value("OK"));

		// Assertions.assertNull(session);
	}


}
