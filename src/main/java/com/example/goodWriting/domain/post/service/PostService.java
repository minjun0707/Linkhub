package com.example.goodWriting.domain.post.service;

import org.springframework.stereotype.Service;

import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostCreateResponse;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateResponse;

@Service
public class PostService {

	public PostCreateResponse createPost(PostCreateRequest postCreateRequest){
		String url = postCreateRequest.getUrl();

		// url 존재 여부 검증

		// 크롤링

		// 데이터베이스 등록

		//이미지


		return new PostCreateResponse();
	}

	public PostTempCreateResponse createTempPost(PostTempCreateRequest postTempCreateRequest){
		String url = postTempCreateRequest.getUrl();

		// 크롤링

		// 이미지 파싱
		// 제목 파싱

		return new PostTempCreateResponse();
	}
}
