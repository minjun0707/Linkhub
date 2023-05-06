package com.example.goodWriting.domain.user.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.example.goodWriting.domain.user.LoginInterceptor;
import com.example.goodWriting.domain.user.controller.UserController;
import com.example.goodWriting.domain.user.controller.UserControllerAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserRequestValidationTest {

	@Autowired
	UserController userController;

	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.setControllerAdvice(new UserControllerAdvice())
			.addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
	}

	// public void
}