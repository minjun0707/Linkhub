package com.example.goodWriting.domain.user.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	EntityManager em;

	private void clear() {
		em.flush();
		em.clear();
	}

	@Test
	void existsByEmailTest() {

		// given
		User user = new User("name","email","password");
		userRepository.save(user);
		clear();

		// when, then
		Assertions.assertTrue(userRepository.existsByEmail(user.getEmail()));
	}

}
