package com.example.goodWriting.domain.post.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import lombok.Getter;

@Getter
public class PostReadListData {

	String title;
	String description;
	String img;
	String url;
	String name;
	String time;

	public PostReadListData(String description, String img, String url, String name, String title, LocalDateTime time) {
		this.description = description;
		this.img = img;
		this.url = url;
		this.name = name;
		this.title = title;
		this.time = time.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));;
	}
}
