package com.example.goodWriting.domain.post.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class PostCreateResponse {

	public String name;
	private String url;
	private String img;
	private String title;
	private String description;

	public PostCreateResponse(String name, String url, String img, String title, String description) {
		this.name = name;
		this.url = url;
		this.img = img;
		this.title = title;
		this.description = description;
	}
}
