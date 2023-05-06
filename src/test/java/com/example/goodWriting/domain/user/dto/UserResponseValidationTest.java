package com.example.goodWriting.domain.user.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class UserResponseValidationTest {

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test()
	@DisplayName("이메일 미입력")
	public void emailBlankExceptionTest() throws Exception {
		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest(null, "김민준", "123456abc",
			"123456bc");

		Set<ConstraintViolation<UserSignUpRequest>> validate = validator.validate(userSignUpRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("이메일을 입력해주세요");
	}


	@Test()
	@DisplayName("이메일 regex를 만족하지 못한다")
	public void invalidEmailRegex() throws Exception {
		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117navercom", "김민준", "123456abc",
			"123456bc");

		Set<ConstraintViolation<UserSignUpRequest>> validate = validator.validate(userSignUpRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("이메일을 올바르게 입력해주세요.");

	}

	@Test()
	@DisplayName("이름 미입력")
	public void blankNameExceptionTest() throws Exception {
		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117@naver.com", null, "123456abc",
			"123456bc");


		Set<ConstraintViolation<UserSignUpRequest>> validate = validator.validate(userSignUpRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("이름을 입력해주세요");


	}

	@Test()
	@DisplayName("비밀번호 미입력")
	public void passwordBlankExceptionTest() throws Exception {
		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117@naver.com", "김민준", null,
			"123456bc");


		Set<ConstraintViolation<UserSignUpRequest>> validate = validator.validate(userSignUpRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("비밀번호를 입력해주세요");


	}

	@Test()
	@DisplayName("비밀번호 regex를 만족하지 못한다")
	public void invalidPasswordRegexExceptionTest() throws Exception {
		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117@naver.com", "김민준", "123",
			"123456bc");


		Set<ConstraintViolation<UserSignUpRequest>> validate = validator.validate(userSignUpRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("비밀번호는 8~16자,영문 소문자, 숫자를 사용해서 만들어주세요");


	}

	@Test()
	@DisplayName("비밀번호체크 미입력")
	public void passwordCheckBlankExceptionTest() throws Exception {
		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117@naver.com", "김민준", "123456abc",
			null);


		Set<ConstraintViolation<UserSignUpRequest>> validate = validator.validate(userSignUpRequest);
		List<String> messages = getMessages(validate);

		assertThat(validate).isNotEmpty();
		Assertions.assertThat(messages).contains("비밀번호를 다시 한번 입력해주세요");


	}


	private static List<String> getMessages(Set<ConstraintViolation<UserSignUpRequest>> validate) {
		Iterator<ConstraintViolation<UserSignUpRequest>> iterator = validate.iterator();
		List<String> messages = new ArrayList<>();
		while (iterator.hasNext()) {
			ConstraintViolation<UserSignUpRequest> next = iterator.next();
			messages.add(next.getMessage());
		}
		return messages;
	}
}
