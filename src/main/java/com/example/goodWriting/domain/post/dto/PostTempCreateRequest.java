package com.example.goodWriting.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostTempCreateRequest {

	@NotBlank(message = "URL을 입력해주세요")
	private String url;
}
