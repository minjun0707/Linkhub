package com.example.goodWriting.domain.user.service;

import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.exception.NotFountUserException;
import com.example.goodWriting.domain.user.exception.PasswordNotMatchedException;
import com.example.goodWriting.domain.user.exception.UserEmailAlreadyException;
import com.example.goodWriting.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	public User login(UserLoginRequest userLoginRequest) {

		String email = userLoginRequest.getEmail();
		String password = userLoginRequest.getPassword();

		User foundUser = userRepository.findByEmail(email).orElseThrow(NotFountUserException::new);

		//일치하지 않으면
		if (password.equals(foundUser.getPassword()) == false) {
			log.info("NotFountUserException");
			throw new NotFountUserException();
		}

		return foundUser;
	}

	public User signUp(UserSignUpRequest userSignUpRequest) {

		//아이디가 존재하는 지 확인
		if (userRepository.existsByEmail(userSignUpRequest.getEmail())) {
			log.info("UserEmailAlreadyException");
			throw new UserEmailAlreadyException();
		}

		//비멀번호가 서로 일치하는 지 확인
		if (userSignUpRequest.getPassword().equals(userSignUpRequest.getPasswordCheck()) == false) {
			log.info("PasswordNotMatchedException");
			throw new PasswordNotMatchedException();
		}

		User savedUser = userRepository.save(userSignUpRequest.toEntity());
		return savedUser;
	}
}
