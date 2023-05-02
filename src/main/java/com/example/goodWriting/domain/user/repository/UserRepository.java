package com.example.goodWriting.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goodWriting.domain.user.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {

	boolean existsByEmail(String email);
}
