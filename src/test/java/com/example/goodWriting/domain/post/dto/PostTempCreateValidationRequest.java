package com.example.goodWriting.domain.post.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.goodWriting.domain.user.dto.UserLoginRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class PostTempCreateValidationRequest {

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test()
	@DisplayName("URL 미입력")
	public void urlBlankExceptionTest() throws Exception {
		//given
		PostTempCreateRequest postTempCreateRequest = new PostTempCreateRequest("");

		Set<ConstraintViolation<PostTempCreateRequest>> validate = validator.validate(postTempCreateRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("URL을 입력해주세요");
	}



	private static List<String> getMessages(Set<ConstraintViolation<PostTempCreateRequest>> validate) {
		Iterator<ConstraintViolation<PostTempCreateRequest>> iterator = validate.iterator();
		List<String> messages = new ArrayList<>();
		while (iterator.hasNext()) {
			ConstraintViolation<PostTempCreateRequest> next = iterator.next();
			messages.add(next.getMessage());
		}
		return messages;
	}
}
