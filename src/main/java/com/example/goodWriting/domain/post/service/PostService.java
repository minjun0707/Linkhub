package com.example.goodWriting.domain.post.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.goodWriting.domain.crawl.ConnectionTimeOutException;
import com.example.goodWriting.domain.crawl.CrawledDataDTO;
import com.example.goodWriting.domain.crawl.Crawler;
import com.example.goodWriting.domain.post.domain.Post;
import com.example.goodWriting.domain.post.dto.PostCreateRequest;
import com.example.goodWriting.domain.post.dto.PostCreateResponse;
import com.example.goodWriting.domain.post.dto.PostTempCreateRequest;
import com.example.goodWriting.domain.post.dto.PostTempCreateResponse;
import com.example.goodWriting.domain.post.exception.InvalidUrlException;
import com.example.goodWriting.domain.post.exception.SameUrlAlreadyExist;
import com.example.goodWriting.domain.post.repository.PostRepository;
import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.exception.NotFountUserException;
import com.example.goodWriting.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	public PostCreateResponse createPost(String email,PostCreateRequest postCreateRequest){

		String url = postCreateRequest.getUrl();

		Crawler crawler = new Crawler();

		//올바르지 못한 url 에러처리
		try {
			if(!crawler.isValidUrl(url)) {
				throw new InvalidUrlException();
			}
		} catch (IOException e) {
			throw new ConnectionTimeOutException();
		}

		CrawledDataDTO data = crawler.getData(url);

		User user = userRepository.findByEmail(email).orElseThrow(NotFountUserException::new);

		//데이터베이스저장
		Post post = postCreateRequest.toEntity(user,data.getImg());
		Post savedPost = postRepository.save(post);

		return new PostCreateResponse(user.getName(), savedPost.getUrl(),savedPost.getImg(),savedPost.getTitle(),savedPost.getDescription());
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

		if(postRepository.existsByUrl(url)){
			throw new SameUrlAlreadyExist();
		}

		CrawledDataDTO data = crawler.getData(url);

		return new PostTempCreateResponse(url,data.getTitle(), data.getDescription(), data.getImg());
	}
}
