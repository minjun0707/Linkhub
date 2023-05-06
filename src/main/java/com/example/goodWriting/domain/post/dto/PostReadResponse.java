package com.example.goodWriting.domain.post.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.goodWriting.domain.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostReadResponse {
	public int count;
	public List<PostReadListData> postList = new ArrayList<>();

	public PostReadResponse(List<Post> postList) {
		this.count = postList.size();

		for (Post post : postList) {
			this.postList.add(new PostReadListData(post.getDescription(), post.getImg(), post.getUrl(),post.getUser().getName(),
				post.getTitle(), post.getCreatedAt()));
		}

	}
}
