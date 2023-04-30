package com.example.goodWriting.domain.post.service;

import org.springframework.stereotype.Service;

import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostCreateResponse;

@Service
public class PostService {

	public PostCreateResponse createBoard (PostCreateRequest postCreateRequest){
		String url = postCreateRequest.getUrl();

		// url 존재 여부 검증

		// 크롤링

		// 데이터베이스 등록

		//이미지


		return new PostCreateResponse();
	}
}
