package com.example.goodWriting.domain.post.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
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

import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateResponse;
import com.example.goodWriting.domain.post.service.PostService;
import com.example.goodWriting.domain.user.controller.UserController;
import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {
	@InjectMocks
	PostController postController;

	@Mock
	PostService postService;

	// MockHttpSession session;
	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(postController)
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	// @BeforeEach
	// public void setUp() throws Exception {
	// 	session = new MockHttpSession();
	// 	session.setAttribute("email", "jmk7117@naver.com");
	// }

	@Test
	@DisplayName("POST 생성")
	void create() throws Exception {
		//given
		PostCreateRequest postCreateRequest = new PostCreateRequest("google.com","구글","구글내용");
		String body = objectMapper.writeValueAsString(postCreateRequest);

		//when //then
		mockMvc.perform(
				post("/api/board/create")
					.content(body)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.path").value("/api/board/create"))
			.andExpect(jsonPath("$.message").value("게시글 등록 성공"))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("OK"));
	}


	@Test
	@DisplayName("POST 임시 생성")
	void createTemp() throws Exception {

		//given
		PostTempCreateRequest postTempCreateRequest = new PostTempCreateRequest("google.com");
		PostTempCreateResponse postTempCreateResponse = new PostTempCreateResponse("google.com","title","description","img");
		String body = objectMapper.writeValueAsString(postTempCreateRequest);
		given(postService.createTempPost(any())).willReturn(postTempCreateResponse);

		//when //then
		mockMvc.perform(
				post("/api/board/create/temp")
					.content(body)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.path").value("/api/board/create/temp"))
			.andExpect(jsonPath("$.message").value("임시 게시글 요청 성공"))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("OK"));
	}

}
