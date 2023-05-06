package com.example.goodWriting.domain.user.dto;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class UserRequestValidationTest {

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test()
	@DisplayName("이메일 미입력")
	public void emailBlankExceptionTest() throws Exception {
		//given
		UserLoginRequest userLoginRequest = new UserLoginRequest(null,"1234");

		Set<ConstraintViolation<UserLoginRequest>> validate = validator.validate(userLoginRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("이메일을 입력해주세요");
	}


	@Test()
	@DisplayName("이메일이 regex를 만족하지 못한다")
	public void invalidEmailRegex() throws Exception {
		//given
		UserLoginRequest userLoginRequest = new UserLoginRequest("fsdaflsdfjlkfdks","1234");

		Set<ConstraintViolation<UserLoginRequest>> validate = validator.validate(userLoginRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("이메일을 올바르게 입력해주세요");

	}

	@Test()
	@DisplayName("비밀번호 미입력")
	public void passwordBlankExceptionTest() throws Exception {
		//given
		UserLoginRequest userLoginRequest = new UserLoginRequest("jmk7117@naver.com","");


		Set<ConstraintViolation<UserLoginRequest>> validate = validator.validate(userLoginRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("비밀번호를 입력해주세요");


	}


	private static List<String> getMessages(Set<ConstraintViolation<UserLoginRequest>> validate) {
		Iterator<ConstraintViolation<UserLoginRequest>> iterator = validate.iterator();
		List<String> messages = new ArrayList<>();
		while (iterator.hasNext()) {
			ConstraintViolation<UserLoginRequest> next = iterator.next();
			messages.add(next.getMessage());
		}
		return messages;
	}
}