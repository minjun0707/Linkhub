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
public class PostCreateRequestValidationTest {

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test()
	@DisplayName("URL 미입력")
	public void urlBlankExceptionTest() throws Exception {
		//given
		PostCreateRequest postCreateRequest = new PostCreateRequest("","구글","구글내용");

		Set<ConstraintViolation<PostCreateRequest>> validate = validator.validate(postCreateRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("URL을 입력해주세요");
	}


	@Test()
	@DisplayName("제목 미입력")
	public void titleBlankExceptionTest() throws Exception {
		//given
		PostCreateRequest postCreateRequest = new PostCreateRequest("google.com","","구글내용");

		Set<ConstraintViolation<PostCreateRequest>> validate = validator.validate(postCreateRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("제목을 입력해주세요");

	}

	@Test()
	@DisplayName("내용 미입력")
	public void descriptionBlankExceptionTest() throws Exception {
		//given
		PostCreateRequest postCreateRequest = new PostCreateRequest("google.com","구글","");


		Set<ConstraintViolation<PostCreateRequest>> validate = validator.validate(postCreateRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("내용을 입력해주세요");


	}


	private static List<String> getMessages(Set<ConstraintViolation<PostCreateRequest>> validate) {
		Iterator<ConstraintViolation<PostCreateRequest>> iterator = validate.iterator();
		List<String> messages = new ArrayList<>();
		while (iterator.hasNext()) {
			ConstraintViolation<PostCreateRequest> next = iterator.next();
			messages.add(next.getMessage());
		}
		return messages;
	}


}
