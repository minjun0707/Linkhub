package com.example.goodWriting.domain.post.controller;

import java.util.Enumeration;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodWriting.common.CommonResponse;
import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostCreateResponse;
import com.example.goodWriting.domain.post.dto.PostReadResponse;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateResponse;
import com.example.goodWriting.domain.post.service.PostService;
import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.exception.NotFountUserException;
import com.example.goodWriting.domain.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

	private final PostService postService;
	private final UserRepository userRepository;

	@PostMapping("/api/board/create")
	@CrossOrigin("*")
	public ResponseEntity create(@RequestBody @Valid PostCreateRequest PostCreateRequest, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");

		postService.createPost(email, PostCreateRequest);

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/board/create")
				.method("POST")
				.message("게시글 등록 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}
	@PostMapping("/api/board/create/temp")
	@CrossOrigin("*")
	public ResponseEntity createTemp(@RequestBody @Valid PostTempCreateRequest PostTempCreateRequest,
		HttpServletResponse response,HttpServletRequest request) {

		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		User user = userRepository.findByEmail(email).orElseThrow(NotFountUserException::new);

		PostTempCreateResponse postTempCreateResponse = postService.createTempPost(PostTempCreateRequest);

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/board/create/temp")
				.method("POST")
				.data(postTempCreateResponse)
				.message("임시 게시글 요청 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}

	@GetMapping("/api/board/read")
	@CrossOrigin("*")
	public ResponseEntity readBoard() {

		PostReadResponse postReadResponse = postService.read();

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/board/read")
				.method("POST")
				.data(postReadResponse)
				.message("페이지 조회 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}



}
