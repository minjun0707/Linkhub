package com.example.goodWriting.domain.post.controller;

import java.util.Enumeration;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodWriting.common.CommonResponse;
import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostReadResponse;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateResponse;
import com.example.goodWriting.domain.post.service.PostService;
import com.example.goodWriting.domain.user.domain.User;

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

	@PostMapping("/api/board/create")
	public ResponseEntity create(@RequestBody @ Valid PostCreateRequest PostCreateRequest, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");

		postService.createPost(email,PostCreateRequest);


		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/board/create")
				.method("POST")
				.message("게시글 등록 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}
	@PostMapping("/api/board/create/temp")
	public ResponseEntity createTemp(@RequestBody @Valid PostTempCreateRequest PostTempCreateRequest,
		HttpServletResponse response) {

		// response.setHeader("Access-Control-Allow-Origin", "*");
		// response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		// response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		// response.setHeader("Access-Control-Max-Age", "3600");
		// response.setHeader("Access-Control-Allow-Credentials", "false");

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


	@PostMapping("/api/board/test")
	public ResponseEntity test(@RequestBody PostCreateRequest PostCreateRequest) {

		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/board/create")
				.method("POST")
				.message("test")
				.statusCode(HttpStatus.OK)
				.build());
	}

}
