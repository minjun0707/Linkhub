package com.example.goodWriting.domain.post.dto;

import com.example.goodWriting.domain.post.domain.Post;
import com.example.goodWriting.domain.user.domain.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {

	@NotBlank(message = "URL을 입력해주세요")
	private String url;

	@NotBlank(message = "제목을 입력해주세요")
	private String title;

	@NotBlank(message = "내용을 입력해주세요")
	private String description;

	public Post toEntity(User user,String img) {
		return new Post(user,this.url,img,this.title,this.description);
	}


}
