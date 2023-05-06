package com.example.goodWriting.domain.user.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.goodWriting.domain.user.domain.User;
import com.example.goodWriting.domain.user.exception.NotFountUserException;

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
	@DisplayName("해당 이메일을 가진 user가 존재한다")
	void existsByEmail() {
		User user = new User("test", "test@email.com", "testPassword");
		userRepository.save(user);
		clear();

		Assertions.assertTrue(userRepository.existsByEmail(user.getEmail()));

	}

	@Test
	@DisplayName("해당이메일로 user를 찾는다")
	void findByEmail() {
		User user = new User("test", "test@email.com", "testPassword");
		userRepository.save(user);
		clear();

		User foundUser = userRepository.findByEmail(user.getEmail()).orElseThrow(NotFountUserException::new);
		Assertions.assertEquals(foundUser.getEmail(),user.getEmail());
	}

}
