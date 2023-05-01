package com.example.goodWriting.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostTempCreateResponse {
	public String url;
	public String title;
	public String description;
	public String img;

}
