package com.example.goodWriting.domain.post.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
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

import com.example.goodWriting.domain.crawl.ConnectionTimeOutException;
import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.exception.InvalidUrlException;
import com.example.goodWriting.domain.post.exception.SameUrlAlreadyExist;
import com.example.goodWriting.domain.post.repository.PostRepository;
import com.example.goodWriting.domain.post.service.PostService;
import com.example.goodWriting.domain.user.LoginInterceptor;
import com.example.goodWriting.domain.user.controller.UserController;
import com.example.goodWriting.domain.user.controller.UserControllerAdvice;
import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(MockitoExtension.class)
public class PostControllerAdivceTest {

	@InjectMocks
	PostController postController;
	@Mock
	PostService postService;
	MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();


	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.standaloneSetup(postController)
			.setControllerAdvice(new PostControllerAdvice())
			.addInterceptors(new LoginInterceptor())
			.addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
	}

	@Test
	@DisplayName("유효하지 못한 URL")
	void postCreateInvalidUrlException() throws Exception {

		//given
		PostCreateRequest postCreateRequest = new PostCreateRequest("google.com","구글","구글설명");
		given(postService.createPost(any(),any())).willThrow(InvalidUrlException.class);

		//when //then
		mockMvc.perform(
				post("/api/board/create")
					.content(objectMapper.writeValueAsString(postCreateRequest))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.path").value("/api/board/create"))
			.andExpect(jsonPath("$.message").value("URL을 올바르게 입력해주세요"))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
	}



	@Test
	@DisplayName("유효하지 못한 URL")
	void postCreateTempInvalidUrlException() throws Exception {

		//given
		PostTempCreateRequest postTempCreateRequest = new PostTempCreateRequest("google.com");
		given(postService.createTempPost(any())).willThrow(InvalidUrlException.class);

		//when //then
		mockMvc.perform(
				post("/api/board/create/temp")
					.content(objectMapper.writeValueAsString(postTempCreateRequest))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.path").value("/api/board/create/temp"))
			.andExpect(jsonPath("$.message").value("URL을 올바르게 입력해주세요"))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
	}

	@Test
	@DisplayName("이미 존재하는 url")
	void postCreateSameUrlAlreadyExist() throws Exception {

		//given
		PostTempCreateRequest postTempCreateRequest = new PostTempCreateRequest("google.com");
		given(postService.createTempPost(any())).willThrow(SameUrlAlreadyExist.class);

		//when //then
		mockMvc.perform(
				post("/api/board/create/temp")
					.content(objectMapper.writeValueAsString(postTempCreateRequest))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.path").value("/api/board/create/temp"))
			.andExpect(jsonPath("$.message").value("이미 존재하는 URL 입니다"))
			.andExpect(jsonPath("$.method").value("POST"))
			.andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
	}


}
