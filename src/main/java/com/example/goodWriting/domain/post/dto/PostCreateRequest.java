package com.example.goodWriting.domain.post.dto;

import lombok.Data;

@Data
public class PostCreateRequest {

	private String url;
	private String title;
	private String description;

}
