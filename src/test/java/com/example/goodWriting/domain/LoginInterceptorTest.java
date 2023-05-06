// package com.example.goodWriting.domain;
//
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.BDDMockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.stereotype.Component;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.filter.CharacterEncodingFilter;
//
// import com.example.goodWriting.domain.post.controller.PostController;
// import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
// import com.example.goodWriting.domain.user.LoginInterceptor;
// import com.example.goodWriting.domain.user.controller.UserController;
// import com.example.goodWriting.domain.user.controller.UserControllerAdvice;
// import com.example.goodWriting.domain.user.exception.NotFountUserException;
// import com.example.goodWriting.domain.user.exception.NotLoginException;
// import com.example.goodWriting.domain.user.service.UserService;
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// @ExtendWith(MockitoExtension.class)
// public class LoginInterceptorTest {
//
// 	@InjectMocks PostController postController;
// 	MockMvc mockMvc;
// 	ObjectMapper objectMapper = new ObjectMapper();
//
//
// 	@BeforeEach
// 	void beforeEach() {
// 		mockMvc = MockMvcBuilders.standaloneSetup(postController)
// 			.setControllerAdvice(new UserControllerAdvice())
// 			.addInterceptors(new LoginInterceptor())
// 			.addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
// 	}
//
// 	@Test
// 	@DisplayName("API 응답 - 비 로그인 상태이다")
// 	public void notLoginException() throws Exception {
//
// 		PostTempCreateRequest postTempCreateRequest = new PostTempCreateRequest("https://url");
// 		//when //then
// 		mockMvc.perform(
// 				post("/api/board/create/temp")
// 					.content(objectMapper.writeValueAsString(postTempCreateRequest))
// 					.contentType(MediaType.APPLICATION_JSON)
// 					.accept(MediaType.APPLICATION_JSON))
// 			.andExpect(status().isBadRequest())
// 			.andExpect(jsonPath("$.path").value("/api/board/create/temp"))
// 			.andExpect(jsonPath("$.message").value("로그인을 해주세요."))
// 			.andExpect(jsonPath("$.method").value("POST"))
// 			.andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"));
// 	}
// }
