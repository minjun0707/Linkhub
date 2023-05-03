package com.example.goodWriting.domain.user;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.exception.NotLoginException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);

		if(session == null) {
			throw new NotLoginException();
		}

		// 세션에서 로그인 정보 가져오기
		User user = (User) session.getAttribute("user");

		if (user == null) {
			throw new NotLoginException();
		}

		return true;
	}
}
