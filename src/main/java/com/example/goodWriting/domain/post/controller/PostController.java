package com.example.goodWriting.domain.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.goodWriting.common.CommonResponse;
import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateResponse;
import com.example.goodWriting.domain.post.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/api/board/create")
	public ResponseEntity create(@RequestBody PostCreateRequest PostCreateRequest) {

		// 로그인 검증

		// url 유효성 검증
		// 잘못될시 리


		postService.createPost(PostCreateRequest);


		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/board/create")
				.method("POST")
				.message("게시글 등록 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}


	@PostMapping("/api/board/create/temp")
	public ResponseEntity createTemp(@RequestBody PostTempCreateRequest PostTempCreateRequest) {

		//url 유효성 검사


		// 넘겨주기


		postService.createTempPost(PostTempCreateRequest);


		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.path("/api/board/create")
				.method("POST")
				.message("게시글 등록 성공")
				.statusCode(HttpStatus.OK)
				.build());
	}
}
