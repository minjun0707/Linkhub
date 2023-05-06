package com.example.goodWriting.domain.user.service;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.exception.NotFountUserException;
import com.example.goodWriting.domain.user.exception.PasswordNotMatchedException;
import com.example.goodWriting.domain.user.exception.UserEmailAlreadyException;
import com.example.goodWriting.domain.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;


	@Test
	@DisplayName("로그인 성공")
	void loginSuccess() {
		// given
		UserLoginRequest userLoginRequest = new UserLoginRequest("jmk7117@naver.com", "123456abc");
		User user = new User("김민준","jmk7117@naver.com","123456abc");
		given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));

		// when
		User loginUser = userService.login(userLoginRequest);

		// then
		Assertions.assertEquals(loginUser.getEmail(),user.getEmail());

	}

	@Test
	@DisplayName("회원가입 성공")
	void signUpSuccess() {
		// given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117@naver.com","김민준", "123456abc","123456abc");
		User user = new User("김민준","jmk7117@naver.com","123456abc");
		given(userRepository.save(any())).willReturn(user);

		// when
		User savedUser = userService.signUp(userSignUpRequest);

		// then
		Assertions.assertEquals(savedUser.getEmail(),user.getEmail());

	}


	@Test
	@DisplayName("회원가입시 이메일이 이미 존재한다")
	void signUpThrowUserEmailAlreadyException() {
		// given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117@naver.com", "김민준", "123456abc",
			"123456abc");

		// 레포지토리에 동일한 이메일이 존재한다
		given(userRepository.existsByEmail(anyString())).willReturn(true);

		// when, then
		Assertions.assertThrows(UserEmailAlreadyException.class, () -> userService.signUp(userSignUpRequest));

	}

	@Test
	@DisplayName("회원가입시 비밀번호와 비밀번호체크가 일치하지 않는다.")
	void signUpThrowPasswordNotMatchedException() {
		// given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest("jmk7117", "김민준", "fsdfds", "123");
		given(userRepository.existsByEmail(anyString())).willReturn(false);

		// when, then
		Assertions.assertThrows(PasswordNotMatchedException.class, () -> userService.signUp(userSignUpRequest));

	}

	@Test
	@DisplayName("로그인시 아이디가 존재하지 않는다.")
	void loginThrowNotFountUserException() {
		// given
		UserLoginRequest userLoginRequest = new UserLoginRequest("jmk7117", "password");

		given(userRepository.findByEmail(anyString())).willReturn(Optional.empty());

		// when, then
		Assertions.assertThrows(NotFountUserException.class, () -> userService.login(userLoginRequest));

	}

	@Test
	@DisplayName("로그인시 비밀번호가 일치하지 않는다.")
	void loginThrowNotFountUserException2() {
		// given
		UserLoginRequest userLoginRequest = new UserLoginRequest("jmk7117", "123456abc");
		User differentPasswordUser = new User("김민준", "jmk7117", "12");
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(differentPasswordUser));

		// when, then
		Assertions.assertThrows(NotFountUserException.class, () -> userService.login(userLoginRequest));

	}

}
