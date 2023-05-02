package com.example.goodWriting.domain.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.example.goodWriting.common.MethodValidationControllerAdvice;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserControllerAdviceTest {

	@InjectMocks
	UserController userController;
	@Mock
	UserService userService;

	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.setControllerAdvice(new MethodValidationControllerAdvice())
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	@Test
	void user_유효성_검사_실패_결과() throws Exception {
		//given
		UserLoginRequest userLoginRequest =
			new UserLoginRequest("","123");
		String body = objectMapper.writeValueAsString(userLoginRequest);


		//when //then
		mockMvc.perform(
				post("/api/user/login")
					.content(body)
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(print());
			//.andExpect(status().isBadRequest())
			// .andExpect(jsonPath("$.path").value("/api/user/login"))
			// .andExpect(jsonPath("$.data.field").value(""))
			// .andExpect(jsonPath("$.data.invalidValue").value("null"))
			// .andExpect(jsonPath("$.method").value("POST"))
			// .andExpect(jsonPath("$.message").value("아이디를 입력해주세요"))
			// .andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
	}


	@Test
	void 유저_로그인_id_NOTNULL_예외발생() throws Exception {
		//given
		UserLoginRequest userLoginRequest =
			new UserLoginRequest("","123");
		String body = objectMapper.writeValueAsString(userLoginRequest);

		//when //then
		mockMvc.perform(
				post("/api/user/login")
					.content(body)
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8"))
			.andDo(print());
		//.andExpect(status().isBadRequest())
		// .andExpect(jsonPath("$.path").value("/api/user/login"))
		// .andExpect(jsonPath("$.data.field").value(""))
		// .andExpect(jsonPath("$.data.invalidValue").value("null"))
		// .andExpect(jsonPath("$.method").value("POST"))
		// .andExpect(jsonPath("$.message").value("아이디를 입력해주세요"))
		// .andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
	}
}
