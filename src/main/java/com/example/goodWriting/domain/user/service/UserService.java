package com.example.goodWriting.domain.user.service;

import java.lang.reflect.Member;

import org.springframework.stereotype.Service;

import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.dto.UserLoginRequest;
import com.example.goodWriting.domain.user.dto.UserSignUpRequest;
import com.example.goodWriting.domain.user.exception.PasswordNotMatchedException;
import com.example.goodWriting.domain.user.exception.UserEmailAlreadyException;
import com.example.goodWriting.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void login(UserLoginRequest userLoginRequest) {
		// String id = userLoginRequest.getUserId();
		// String password = userLoginRequest.getPassword();
	}


	public void signUp(UserSignUpRequest userSignUpRequest) {

		//아이디가 존재하는 지 확인
		if(userRepository.existsByEmail(userSignUpRequest.getEmail())){
			throw new UserEmailAlreadyException();
		}

		//비멀번호가 서로 일치하는 지 확인
		if(userSignUpRequest.getPassword().equals(userSignUpRequest.getPasswordCheck()) == false) {
			throw new PasswordNotMatchedException();
		}

		userRepository.save(userSignUpRequest.toEntity());
	}
}
