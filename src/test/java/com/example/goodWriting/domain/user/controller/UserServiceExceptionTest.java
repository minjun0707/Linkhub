package com.example.goodWriting.domain.user.controller;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.exception.PasswordNotMatchedException;
import com.example.goodWriting.domain.user.exception.UserEmailAlreadyException;
import com.example.goodWriting.domain.user.repository.UserRepository;
import com.example.goodWriting.domain.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceExceptionTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Test
	void throwUserEmailValidException() {
		// given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117","김민준","123","123");

		// 레포지토리에 동일한 이메일이 존재한다
		given(userRepository.existsByEmail(anyString())).willReturn(true);

		// when, then
		Assertions.assertThrows(UserEmailAlreadyException.class , () -> userService.signUp(userSignUpRequest));

	}

	@Test
	void throwPasswordNotMatchedException() {
		// given
		//패스워드 불일치
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117","김민준","fsdfds","123");
		//데이터베이스에 동일한 유저 없다
		given(userRepository.existsByEmail(anyString())).willReturn(false);

		// when, then
		Assertions.assertThrows(PasswordNotMatchedException.class , () -> userService.signUp(userSignUpRequest));

	}


}
