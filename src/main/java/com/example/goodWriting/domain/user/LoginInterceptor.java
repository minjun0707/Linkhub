package com.example.goodWriting.domain.user;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.exception.NotLoginException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if(session == null) {
			log.info("세션이없어용");
			throw new NotLoginException();
		}

		// 세션에서 로그인 정보 가져오기
		String email = (String) session.getAttribute("email");
		System.out.println("email" + email);


		if (email == null) {
			log.info("세션에 해당하는 사용자가 없어용");
			throw new NotLoginException();
		}

		return true;
	}
}
