package com.example.goodWriting.domain.post.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.goodWriting.domain.crawl.ConnectionTimeOutException;
import com.example.goodWriting.domain.crawl.CrawledDataDTO;
import com.example.goodWriting.domain.crawl.Crawler;
import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostCreateResponse;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateResponse;
import com.example.goodWriting.domain.post.exception.InvalidUrlException;

@Service
public class PostService {

	public PostCreateResponse createPost(PostCreateRequest postCreateRequest){
		String url = postCreateRequest.getUrl();


		//데이터베이스저장
		//




		return new PostCreateResponse();
	}

	public PostTempCreateResponse createTempPost(PostTempCreateRequest postTempCreateRequest){

		String url = postTempCreateRequest.getUrl();

		Crawler crawler = new Crawler();

		//올바르지 못한 url 에러처리
		try {
			if(!crawler.isValidUrl(url)) {
				throw new InvalidUrlException();
			}
		} catch (IOException e) {
			throw new ConnectionTimeOutException();
		}

		//동일한 url 들어오면 예외 터트린다
		//db에서 확인

		CrawledDataDTO data = crawler.getData(url);

		return new PostTempCreateResponse(url,data.getTitle(), data.getDescription(), data.getImg());
	}
}
