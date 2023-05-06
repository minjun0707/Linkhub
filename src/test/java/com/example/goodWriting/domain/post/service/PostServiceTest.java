package com.example.goodWriting.domain.post.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.goodWriting.domain.crawl.Crawler;
import com.example.goodWriting.domain.post.dto.PostCreateResponse;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateResponse;
import com.example.goodWriting.domain.post.exception.InvalidUrlException;
import com.example.goodWriting.domain.post.exception.SameUrlAlreadyExist;
import com.example.goodWriting.domain.post.repository.PostRepository;
import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.repository.UserRepository;
import com.example.goodWriting.domain.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

	@InjectMocks
	PostService postService;

	@Mock
	PostRepository postRepository;

	@Mock
	Crawler crawler;


	@Test
	@DisplayName("올바르지 못한 URL 예외")
	void postCreateInValidUrlException() throws IOException {
		// given
		PostTempCreateRequest postTempCreateRequest = new PostTempCreateRequest("google.com");
		given(crawler.isValidUrl(any())).willReturn(false);

		//when then
		Assertions.assertThrows(InvalidUrlException.class, () -> postService.createTempPost(postTempCreateRequest));

	}

	@Test
	@DisplayName("동일한 URL 예외")
	void postCreateSameUrlAlreadyExistException() throws IOException {
		// given
		PostTempCreateRequest postTempCreateRequest = new PostTempCreateRequest("google.com");
		given(postRepository.existsByUrl(any())).willReturn(true);

		//when then
		Assertions.assertThrows(SameUrlAlreadyExist.class, () -> postService.createTempPost(postTempCreateRequest));

	}

	// @Test
	// @DisplayName("임시 POST 생성 성공")
	// void postTempCreateSuccess() throws IOException {
	// 	// given
	// 	PostTempCreateResponse postTempCreateResponse =
	// 		new PostTempCreateResponse("https://tecoble.techcourse.co.kr/post/2022-11-01-mysql-dead-lock/",
	// 		"데드락 해결 모험기","용어 설명     Cycle이란, 사용자가 특정 챌린지에 도전할 때 생성되는 객체이다. 비즈니스 규칙 상 하루에 한번씩 총…"
	// 		,"https://tecoble.techcourse.co.kr/static/264a2517af906c5a1c3c22b6f4c181cd/2f1b1/giron.jpg");
	//
	// 	PostTempCreateRequest postTempCreateRequest = new PostTempCreateRequest("https://tecoble.techcourse.co.kr/post/2022-11-01-mysql-dead-lock/");
	//
	// 	//when
	// 	PostTempCreateResponse post = postService.createTempPost(postTempCreateRequest);
	//
	// 	//then
	// 	Assertions.assertEquals(postTempCreateResponse.getTitle(),post.getTitle());
	//
	// }

}
